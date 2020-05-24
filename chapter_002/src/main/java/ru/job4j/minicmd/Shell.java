package ru.job4j.minicmd;

import java.util.*;

/**
 * Класс для наигации по папкам
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 05.05.2020
 * @version 1.0
 */
public class Shell {
    Stack<String> path = new Stack<>();

    Shell cd(final String path) {
        for (String action : pathToActions(shortenSlashes(path))) {
            switch (action) {
                case "." :
                    break;
                case "/" :
                    this.path.removeAllElements();
                    break;
                case ".." :
                    this.path.pop();
                    break;
                default:
                    this.path.push(action);
            }
        }
        return this;
    }

    private List<String> pathToActions(String path) {
        List<String> result = new ArrayList<>();
        if (path.startsWith("/")) {
            result.add("/");
            if (path.length() > 1) {
                path = path.substring(1);
            }
        }
        String[] fragments = path.split("/");
        result.addAll(Arrays.asList(fragments));
        return result;
    }

    public String shortenSlashes(String path) {
        return path.replaceAll("\\/{2,}", "/");
    }

    public String path() {
        return "/" + String.join("/", path);
    }

    public static void main(String[] args) {
        Shell shell = new Shell();
        List<String> commands = List.of("/home/roman/projects/..", "/", "home/roman", "projects", "..", ".", "//usr//");
        for (String command : commands) {
            shell.cd(command);
            System.out.println(command + " -> " + shell.path());
        }
    }
}
