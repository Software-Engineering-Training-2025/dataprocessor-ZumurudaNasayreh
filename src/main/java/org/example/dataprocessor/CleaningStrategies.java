package org.example.dataprocessor;

import org.example.dataprocessor.enums.CleaningType;
import java.util.*;
import java.util.stream.Collectors;

public class CleaningStrategies {

    public interface cleaningStrategy {
        List<Integer> clean(List<Integer> data);
    }

    public static Map<CleaningType, cleaningStrategy> register() {
        Map<CleaningType, cleaningStrategy> cleaningStrategies = new HashMap<>();

        cleaningStrategies.put(CleaningType.REMOVE_NEGATIVES,
                c ->
                        c.stream()
                                .filter(x -> x >= 0)
                                .collect(Collectors.toList()));

        cleaningStrategies.put(CleaningType.REPLACE_NEGATIVES_WITH_ZERO,
                c ->
                        c.stream()
                                .map(x -> x < 0 ? 0 : x)
                                .collect(Collectors.toList()));

        return cleaningStrategies;
    }
}

