package com.ubs.homework.task;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

@Slf4j
public class Addition {

    public Set<List<Integer>> calc(List<Integer> numbers){

        int len = numbers.size();
        // set location index
        Set<Integer> locations = Sets.newHashSet();
        for (int i = 0; i < len; i++){
            locations.add(i);
        }
        // sort numbers
        List<Integer> sortedNumbers = Ordering.natural().sortedCopy(numbers);
        // power set of location index
        Set<Set<Integer>> powerSet = Sets.powerSet(locations);
        Iterable<Set<Integer>> filterSet = Iterables.filter(powerSet, new Predicate<Set<Integer>>() {
            @Override
            public boolean apply(Set<Integer> locations) {
                // sort location
                List<Integer> sortedLocations = Ordering.natural().sortedCopy(locations);
                // separate list
                int len = locations.size();
                if (len < 3) {
                    return false;
                }
                List<List<Integer>> partitions = Lists.partition(sortedLocations, len - 1);
                // location
                List<Integer> elementLocations = partitions.get(0);
                Integer expectedResultLocation = partitions.get(1).get(0);
                // sum up
                int result = 0;
                for (Integer elementLocation: elementLocations){
                    result += sortedNumbers.get(elementLocation);
                }
                // validate
                return result == sortedNumbers.get(expectedResultLocation);
            }
        });
        // transfer index location to number list
        ImmutableList<List<Integer>> transform = FluentIterable.from(filterSet).transform(new Function<Set<Integer>, List<Integer>>() {
            @Override
            public List<Integer> apply(Set<Integer> locations) {
                // sort location
                List<Integer> sortedLocations = Ordering.natural().sortedCopy(locations);
                List<Integer> numbers = Lists.newArrayList();
                for (Integer location : sortedLocations){
                    numbers.add(sortedNumbers.get(location));
                }
                return numbers;
            }
        }).toList();

        return Sets.newHashSet(transform);
    }

    public void print(List<Integer> numbers){
        int len = numbers.size();
        List<List<Integer>> partitions = Lists.partition(numbers, len - 1);
        // value
        List<Integer> elements = partitions.get(0);
        Integer expectedResult = partitions.get(1).get(0);
        // print
        StringBuffer result = new StringBuffer();
        result.append(elements.remove(0));
        for (Integer element : elements){
            result.append("+").append(element);
        }
        result.append("=").append(expectedResult);
        log.info(result.toString());
    }
}
