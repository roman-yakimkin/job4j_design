package ru.job4j.io;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Класс - сокет-сервер
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 28.04.2020
 * @version 1.0
 */
public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
//        String log4JPropertyFile = "./log4j.properties";
//        Properties p = new Properties();
//        try {
//            p.load(new FileInputStream(log4JPropertyFile));
//            PropertyConfigurator.configure(p);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Pattern pattern = Pattern.compile("\\?msg=.+ HTTP");
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    do {
                        str = in.readLine();
                        System.out.println(str);
                        if (str.contains("msg=Bye ")) {
                            server.close();
                        } else if (str.contains("msg=Exception ")) {
                            throw new IOException("Exception is thrown by user");
                        }
                    } while (str.isEmpty());
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        } catch (IOException e) {
            LOG.debug("Exception in the chat", e);
        }
    }
}
