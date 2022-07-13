package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void executeQuery(String tableName, String sql, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists %s(%s, %s);",
                tableName,
                "id serial primary key"
        );
        executeQuery(tableName, sql, connection);
        System.out.println(getTableScheme(connection, tableName));
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s;",
                tableName,
                columnName,
                type
        );
        executeQuery(tableName, sql, connection);
        System.out.println(getTableScheme(connection, tableName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                        "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName
        );
        executeQuery(tableName, sql, connection);
        System.out.println(getTableScheme(connection, tableName));
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN IF EXISTS %s;",
                tableName,
                columnName
        );
        executeQuery(tableName, sql, connection);
        System.out.println(getTableScheme(connection, tableName));
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s",
                tableName
        );
        executeQuery(tableName, sql, connection);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String tableName = "test_table";
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("TableEditor.properties")) {
            Properties config = new Properties();
            config.load(in);
            try (TableEditor tableEditor = new TableEditor(config)) {
                tableEditor.createTable(tableName);
                tableEditor.addColumn(tableName, "new_column", "VARCHAR(255)");
                tableEditor.renameColumn(tableName, "new_column", "renamed_column");
                tableEditor.dropColumn(tableName, "renamed_column");
                tableEditor.dropTable(tableName);
            }
        }
    }
}


