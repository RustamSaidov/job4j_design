package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            if (server.getLocalPort() != 9000) {
                throw new IOException("Server port isn't equals to 9000");
            }
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String outMessage = "What";
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String firstLine = in.readLine();
                    if (firstLine.contains("?msg=Hello")) {
                        outMessage = "Hello";
                    } else if (firstLine.contains("?msg=Exit")) {
                        outMessage = "";
                        server.close();
                    }
                    out.write(outMessage.getBytes());
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("the next IOExeption was catched: ", e);
        }
    }
}