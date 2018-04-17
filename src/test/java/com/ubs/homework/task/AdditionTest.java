package com.ubs.homework.task;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class AdditionTest {
    @Test
    public void calc() throws Exception {
        List<Integer> numbers = Arrays.asList(2, 3, 14, 7, 9);
        Addition addition = new Addition();
        Set<List<Integer>> result = addition.calc(numbers);
        for (List<Integer> locations : result){
            addition.print(locations);
        }
    }

}