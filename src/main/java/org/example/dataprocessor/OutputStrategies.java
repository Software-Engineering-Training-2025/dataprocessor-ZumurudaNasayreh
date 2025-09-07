package org.example.dataprocessor;

import org.example.dataprocessor.enums.OutputType;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class OutputStrategies {

    public interface OutputStrategy {
        void output(double result) throws Exception;
    }

    public static Map<OutputType, OutputStrategy> register()
    {
        Map<OutputType, OutputStrategy> Out = new HashMap<>();

        Out.put(OutputType.CONSOLE, O -> System.out.println("Result = " + O));

        Out.put(OutputType.TEXT_FILE, O -> {
            File NewFile = new File("target/result.txt");
            NewFile.getParentFile().mkdirs();
            try (FileWriter f = new FileWriter(NewFile))
            {
                f.write("Result = " + O);
            }
        });

        return Out;
    }
}
