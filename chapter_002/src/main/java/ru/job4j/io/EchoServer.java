package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            boolean isRunning = true;
            while (isRunning) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str, answerText = "";
                    while (!(str = in.readLine()).isEmpty()) {
                        Matcher matcher = pattern.matcher(str);
                        if (matcher.find()) {
                            String msg = str.substring(matcher.start() + 5, matcher.end() - 5);
                            answerText = msg;
                            switch (msg.toLowerCase()) {
                                case "hello" :
                                    answerText = "Hello, dear friend !";
                                    break;
                                case "exit" :
                                    answerText = "See you soon ...";
                                    isRunning = false;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(("<html><body><div style=\"position:absolute;width:100%;top:50%;text-align:center;font-size:40px;\">" + answerText + "</div></body></html>").getBytes());
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
