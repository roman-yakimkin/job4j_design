package ru.job4j.template;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GeneratorTest {

    @Test @Ignore
    public void produceCorrect() {
        Generator generator = new TemplateGenerator();
        String template = "I am ${name}, who are ${subject}?";
        Map<String, Object> args = Map.of(
                "name", "John",
                "subject", "you"
        );
        String result = generator.produce(template, args);
        String expected = "I am John, who are you?";
        assertThat(result, is(expected));
    }

    @Test(expected = SuperfluousInTemplateException.class) @Ignore
    public void superfluousInTemplate() throws SuperfluousInTemplateException {
        Generator generator = new TemplateGenerator();
        String template = "I am ${name}, my surname is ${surname}, who are ${subject}?";
        Map<String, Object> args = Map.of(
                "name", "John",
                "subject", "you"
        );
        String result = generator.produce(template, args);
        String expected = "I am John, my surname is ${surname}, who are you?";
        assertThat(result, is(expected));
    }

    @Test(expected = SuperfluousInArgsException.class) @Ignore
    public void superfluousInTemplate() throws SuperfluousInArgsException {
        Generator generator = new TemplateGenerator();
        String template = "I am ${name}, who are ${subject}?";
        Map<String, Object> args = Map.of(
                "name", "John",
                "surname", "Lennon",
                "subject", "you"
        );
        String result = generator.produce(template, args);
        String expected = "I am John, who are you?";
        assertThat(result, is(expected));
    }
}
