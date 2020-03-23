package ru.job4j.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для генерации шаблонов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 20.03.2020
 */
public class TemplateGenerator implements Generator {

    private List<String> getSubst(String template) {
        List<String> result = new ArrayList<>();
        Pattern pt = Pattern.compile("(\\$\\{)([a-zA-Z0-9-_]+)(\\})");
        Matcher mt = pt.matcher(template);
        while (mt.find()) {
            result.add(template.substring(mt.start()+2, mt.end()-1));
        }
        return result;
    }

    @Override
    public String produce(String template, Map<String, Object> args) throws SuperfluousInTemplateException, SuperfluousInArgsException {
        List<String> superfluousKeys = new ArrayList<>();
        List<String> superfluousSubst = new ArrayList<>();

        for(String key : args.keySet()) {
            if (!template.contains("${" + key + "}")) {
                superfluousKeys.add(key);
            }
        }
        if (superfluousKeys.size() > 0) {
            throw new SuperfluousInArgsException("There are some superfluous keys in args", superfluousKeys);
        }

        List<String> substitutions = getSubst(template);
        for (String subst : substitutions) {
            if (!args.keySet().contains(subst)) {
                superfluousSubst.add(subst);
            }
        }
        if (superfluousSubst.size() > 0) {
            throw new SuperfluousInTemplateException("There are some superfluous keys in template", superfluousSubst);
        }

        for(String key : args.keySet()) {
            template = template.replaceAll("\\$\\{" + key + "}", args.get(key).toString());
        }

        return template;
    }

    public static void main(String[] args) {
        String st = "Hello, I am ${name} and my surname is ${surname}";

        TemplateGenerator gen = new TemplateGenerator();
        List<String> subst = gen.getSubst(st);
    }
}
