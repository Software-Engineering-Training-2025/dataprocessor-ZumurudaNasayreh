package org.example.dataprocessor;


import org.example.dataprocessor.enums.AnalysisType;

import java.util.*;

public class AnalysisStrategies {

    public interface AnalysisStrategy {
        double analyze(List<Integer> data);
    }

    public static Map<AnalysisType, AnalysisStrategy> register() {
        Map<AnalysisType, AnalysisStrategy> analysisStrategy = new HashMap<>();

        analysisStrategy.put(AnalysisType.MEAN,
                a ->
                              a.isEmpty() ? Double.NaN : a
                                      .stream()
                                      .mapToDouble(x -> x)
                                      .average()
                                      .getAsDouble());

        analysisStrategy.put(AnalysisType.MEDIAN,
                a ->
                            {
                            if (a.isEmpty())
                                return Double.NaN;
                            List<Integer> sortt = new ArrayList<>(a);
                            Collections.sort(sortt);
                            int num = sortt.size();
                            return (num % 2 == 1)
                                ? sortt.get(num / 2)
                                : (sortt.get(num / 2 - 1) + sortt.get(num / 2)) / 2.0;
        });


        analysisStrategy.put(AnalysisType.STD_DEV,
                a -> {
                    if (a.isEmpty()) return Double.NaN;
                    int num = a.size();
                    double mean = a.stream()
                            .mapToDouble(x -> x)
                            .average()
                            .getAsDouble();
                    double sumsqr = a.stream()
                            .mapToDouble(x -> (x - mean) * (x - mean))
                            .sum();
                    return Math.sqrt(sumsqr / num);
                });


        analysisStrategy.put(AnalysisType.P90_NEAREST_RANK,
                a-> {
                    if (a.isEmpty())
                        return Double.NaN;
                    List<Integer> sortt = new ArrayList<>(a);
                    Collections.sort(sortt);
                    int rank = (int) Math.ceil(0.9 * sortt.size());
                    return sortt.get(rank - 1);
                });

        analysisStrategy.put(AnalysisType.TOP3_FREQUENT_COUNT_SUM,
                a ->
                     {
                     if (a.isEmpty())
                         return 0;
                     Map<Integer, Integer> freq = new HashMap<>();
                    for (int i : a)
                    {
                        freq.put(i, freq.getOrDefault(i, 0) + 1);
                    }
                    List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(freq.entrySet());
                    entries.sort((en1, en2) ->
                    {
                         int c = Integer.compare(en2.getValue(), en1.getValue());
                         return (c != 0) ? c : Integer.compare(en1.getKey(), en2.getKey());
                    });
                    int topTh = Math.min(3, entries.size());
                    int s = 0;
                    for (int i = 0; i < topTh; i++) s += entries.get(i).getValue();
                    return (double) s;
                    });

        return analysisStrategy;
    }
}