package org.example;

import org.example.dataprocessor.DataProcessorService;
import org.example.dataprocessor.enums.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        DataProcessorService cd = new DataProcessorService();

        System.out.println("This is in Below the 'MEAN' = " );

        cd.process(
                CleaningType.REMOVE_NEGATIVES,
                AnalysisType.MEAN,
                OutputType.CONSOLE,
                List.of(5, -2, 7, 8)
        );
        System.out.println("---------------------------------------------------");

        double median = cd.process(
                CleaningType.REPLACE_NEGATIVES_WITH_ZERO,
                AnalysisType.MEDIAN,
                OutputType.TEXT_FILE,
                List.of(5, -2, 7, 8)
        );

        System.out.println("to display the value saved to the file 'median'"+ median);
        System.out.println("---------------------------------------------------");

        System.out.println("This is in Below the 'P90_NEAREST-RANK' = " );

        cd.process(
                CleaningType.REMOVE_NEGATIVES,
                AnalysisType.P90_NEAREST_RANK,
                OutputType.CONSOLE,
                List.of(1,3,5,7,9,11,13,15,17,19)
        );

        System.out.println("---------------------------------------------------");

        double std = cd.process(
                CleaningType.REPLACE_NEGATIVES_WITH_ZERO,
                AnalysisType.STD_DEV,
                OutputType.TEXT_FILE,
                List.of(2,4,4,4,5,5,7,9)
        );
        System.out.println("to display the value saved to the file 'STD_DEV='"+ std);

        System.out.println("StdDev = " + std);
        System.out.println("---------------------------------------------------");
        System.out.println("This is in Below the 'TOP3_FREQUENT_COUNT_SUM' = " );

        cd.process(
                CleaningType.REMOVE_NEGATIVES,
                AnalysisType.TOP3_FREQUENT_COUNT_SUM,
                OutputType.CONSOLE,
                List.of(5,5,5,1,1,2,2,3)
        );

    }
}
