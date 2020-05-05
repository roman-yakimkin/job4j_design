package ru.job4j.minicmd;

import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Класс для наигации по папкам
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 05.05.2020
 * @version 1.0
 */
public class Shell {
    private String path = "/";
    private Map<String, UnaryOperator<String>> rules = Map.of(
            ".", (p -> p),
            "/", (p -> "/"),
            "..", (this::parentPath)
    );

    Shell cd(final String path) {
        List<String> actions = pathToActions(shortenSlashes(path));
        for (String action : actions) {
            if (rules.containsKey(action)) {
                this.path = rules.get(action).apply(this.path);
            } else {
                this.path = shortenSlashes(this.path + "/" + action);
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

    public String parentPath(String path) {
        List<String> fragments = Arrays.stream(path.split("/")).filter((s) -> (s.length() > 0)).collect(Collectors.toList());
        String result = "/";
        if (fragments.size() > 0) {
            result += String.join("/", fragments.subList(0, fragments.size() - 1));
        }
        return result;
    }

    public String path() {
        return path;
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
