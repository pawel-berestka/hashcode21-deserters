package com.hashcode2021.deserters.util;

import com.hashcode2021.deserters.data.OutputData;
import com.hashcode2021.deserters.data.ScenarioOutput;
import com.hashcode2021.deserters.util.Debuggable;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ScenarioOutputWriter implements Debuggable {
    private void addDataToBufferedWritter(BufferedWriter bufferedWriter, OutputData outputData) throws IOException {
        bufferedWriter.write(outputData.getTypesOfPizzas() + "\n");
        bufferedWriter.write(Stream.of(outputData.getPizzasIndexes()).map(Object::toString).collect(Collectors.joining(" ")));
    }

    public void writeOutputData(ScenarioOutput scenarioOutput) {
        BufferedWriter bufferedWriter = null;
        try {
            String outputFilePath = getRelativePathToOutputFile(scenarioOutput.getFileName());
            logDebug("Writing OutputData=[%s] to path=[%s].", scenarioOutput.getFileName(), outputFilePath);
            bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath));
            addDataToBufferedWritter(bufferedWriter, scenarioOutput.getOutputData());
            bufferedWriter.flush();
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Cannot write OutputData for=[%s] due to: [%s].", scenarioOutput.getFileName(), ex.getMessage()));
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (Exception ex) {
                    throw new RuntimeException(String.format("Something went wrong while writing output data for=[%s]: [%s].", scenarioOutput.getFileName(), ex.getMessage()));
                }
            }
        }
    }

    private final String getRelativePathToOutputFile(String fileName){
        if(null == fileName){
            throw new IllegalArgumentException("Cannot read InputData from null fileName.");
        }

        return "resources//output//" + fileName;
    }
}
