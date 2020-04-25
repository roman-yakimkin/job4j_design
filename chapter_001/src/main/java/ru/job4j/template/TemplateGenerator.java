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
            result.add(template.substring(mt.start() + 2, mt.end() - 1));
        }
        return result;
    }

    @Override
    public String produce(String template, Map<String, Object> args) throws SuperfluousInTemplateException, SuperfluousInArgsException {
        for (String key : args.keySet()) {
            if (!template.contains("${" + key + "}")) {
                throw new SuperfluousInArgsException("There are some superfluous keys in args");
            }
            template = template.replaceAll("\\$\\{" + key + "}", args.get(key).toString());
        }

        if (getSubst(template).size() > 0) {
            throw new SuperfluousInTemplateException("There are some superfluous keys in template");
        }

        return template;
    }
}
