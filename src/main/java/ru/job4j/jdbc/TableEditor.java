package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {

        Config config = new Config("src\\main\\resources\\TableEditor.properties");
        config.load();
        Class.forName(config.getValues().get("hibernate.connection.driver_class"));
        String url = config.getValues().get("hibernate.connection.url");
        String login = config.getValues().get("hibernate.connection.username");
        String password = config.getValues().get("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private static void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "create table if not exists %s(%s, %s);",
                    tableName,
                    "id serial primary key",
                    "name text"
            );
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql1 = String.format(
                    "ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s;",
                    tableName,
                    columnName,
                    type
            );
            statement.execute(sql1);
            System.out.println(getTableScheme(connection, tableName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql2 = String.format(

                    "DO $$\n"
                            + "BEGIN\n"
                            + "  IF EXISTS(SELECT * FROM information_schema.columns\n"
                            + "    WHERE table_name='" + tableName + "' and column_name='" + columnName + "')\n"
                            + "  THEN\n"
                            + "      ALTER TABLE %s RENAME COLUMN %s TO %s;\n"
                            + "  END IF;\n"
                            + "END $$;",
                    tableName,
                    columnName,
                    newColumnName
            );
            statement.execute(sql2);
            System.out.println(getTableScheme(connection, tableName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void dropColumn(String tableName, String columnName)  {
        try (Statement statement = connection.createStatement()) {
            String sql4 = String.format(
                    "ALTER TABLE %s DROP COLUMN IF EXISTS %s;",
                    tableName,
                    columnName
            );
            statement.execute(sql4);
            System.out.println(getTableScheme(connection, tableName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void dropTable(String tableName)  {
        try (Statement statement = connection.createStatement()) {
            String sql5 = String.format(
                    "DROP TABLE IF EXISTS %s",
                    tableName
            );
            statement.execute(sql5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        Properties config = new Properties();
        TableEditor tableEditor = new TableEditor(config);


        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("TableEditor.properties")) {
            config.load(in);

            createTable(tableName);
            addColumn(tableName, "new_column", "VARCHAR(255)");
            renameColumn(tableName, "new_column", "renamed_column");
            dropColumn(tableName, "renamed_column");
            dropTable(tableName);

        }
    }
}


