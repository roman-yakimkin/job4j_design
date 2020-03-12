package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MaxMinTest {

    @Test
    public void testMax() {
        List<Integer> values = List.of(1, 3, 4, 2, 16, 10);
        MaxMin maxMin = new MaxMin();
        Integer result = maxMin.max(values, (e1, e2) -> (e1 - e2));
        Integer expected = 16;
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testMin() {
        List<String> values = List.of("ab", "acd", "cdf", "aa", "a", "xyz");
        MaxMin maxMin = new MaxMin();
        String result = maxMin.min(values, String::compareTo);
        String expected = "a";
        Assert.assertEquals(result, expected);
    }
}
