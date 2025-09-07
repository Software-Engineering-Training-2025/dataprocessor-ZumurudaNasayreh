package org.example.dataprocessor;

import org.example.dataprocessor.enums.AnalysisType;
import org.example.dataprocessor.enums.CleaningType;
import org.example.dataprocessor.enums.OutputType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * Students ONLY implement the process(...) method below.
 *
 * Requirements:
 * - Order: Clean -> Analyze -> Output -> Return result
 * - Do NOT mutate the original input list
 * - Handle empties as specified in AnalysisType docs
 * - Output format EXACTLY: "Result = <value>"
 * - TEXT_FILE path: target/result.txt (create parent dirs, overwrite file)
 */
public class DataProcessorService {

    /**
     * Implement this method.
     */

    public double process(
            CleaningType cleaningType,
            AnalysisType analysisType,
            OutputType outputType,
            List<Integer> data) throws Exception {

        if (data == null)
            throw new IllegalArgumentException("data is null");

        List<Integer> copydata = new ArrayList<>(data);

        var clean = CleaningStrategies.register().get(cleaningType);
        if (clean == null)
            throw new IllegalArgumentException("Unknown cleaningType: " + cleaningType);
        List<Integer> cleaned = clean.clean(copydata);

        var analyze = AnalysisStrategies.register().get(analysisType);
        if (analyze == null)
            throw new IllegalArgumentException("Unknown analysisType: " + analysisType);
        double result = analyze.analyze(cleaned);

        var outputt = OutputStrategies.register().get(outputType);
        if (outputt == null)
            throw new IllegalArgumentException("Unknown outputType: " + outputType);
        outputt.output(result);

        return result;
    }
}


