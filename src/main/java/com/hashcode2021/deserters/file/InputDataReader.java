package com.hashcode2021.deserters.file;

import com.hashcode2021.deserters.data.InputData;
import com.hashcode2021.deserters.util.Debuggable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class InputDataReader implements Debuggable {
    private InputData getInputDataFromScanner(Scanner scanner) {
        String firstLine = scanner.nextLine().replace("\n", "");
        String secondLine = scanner.nextLine().replace("\n", "");

        InputData inputData = new InputData();
        String[] firstLineParsed = firstLine.split(" ");
        String[] secondLineParsed = secondLine.split(" ");
        inputData.setMaxNumberOfSlices(Integer.valueOf(firstLineParsed[0]));
        inputData.setDifferentTypes(Integer.valueOf(firstLineParsed[1]));

        Integer[] pizzas = new Integer[inputData.getDifferentTypes()];
        for (int i = 0; i < inputData.getDifferentTypes(); ++i) {
            pizzas[i] = Integer.valueOf(secondLineParsed[i]);
        }

        inputData.setPizzas(pizzas);
        return inputData;
    }

    public InputData readData(String fileName) {
        String path = getRelativePathToFile(fileName);
        File file = new File(path);
        logDebug("Reading InputData=[%s] from absolutePath=[%s].", fileName, file.getAbsolutePath());
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("Cannot read InputData from fileName=[%s] due to: [%s].", fileName, e.getMessage()));
        }

        return getInputDataFromScanner(fileScanner);
    }

    private final String getRelativePathToFile(String fileName){
        if(null == fileName){
            throw new IllegalArgumentException("Cannot read InputData from null fileName.");
        }

        return "resources//input//" + fileName;
    }
}
