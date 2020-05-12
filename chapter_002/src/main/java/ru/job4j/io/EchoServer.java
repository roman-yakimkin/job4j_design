package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Класс - сокет-сервер
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 28.04.2020
 * @version 1.0
 */
public class EchoServer {
    public static void main(String[] args) {
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
                        }
                    } while (str.isEmpty());
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
