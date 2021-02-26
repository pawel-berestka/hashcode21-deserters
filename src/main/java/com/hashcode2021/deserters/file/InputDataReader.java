package com.hashcode2021.deserters.file;

import com.hashcode2021.deserters.data.InputData;
import com.hashcode2021.deserters.data.algorithm.input.Car;
import com.hashcode2021.deserters.data.algorithm.input.Street;
import com.hashcode2021.deserters.util.Debuggable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class InputDataReader implements Debuggable {
    private InputData getInputDataFromScanner(Scanner scanner) {
        String firstLine = scanner.nextLine().replace("\n", "");

        String[] firstLineParsed = firstLine.split(" ");
        Integer simulationTime = Integer.valueOf(firstLineParsed[0]);
        Integer intersectionCount = Integer.valueOf(firstLineParsed[1]);
        Integer streetCount = Integer.valueOf(firstLineParsed[2]);
        Integer carCount = Integer.valueOf(firstLineParsed[3]);
        Integer pointsPerDestination = Integer.valueOf(firstLineParsed[4]);

        List<Street> streets = new ArrayList<>();
        for(int i=0; i<streetCount; ++i){
            String streetLine = scanner.nextLine().replace("\n", "");
            String[] parsedStreet = streetLine.split(" ");

            Integer startIntersectionIndx = Integer.valueOf(parsedStreet[0]);
            Integer endIntersectionIndx = Integer.valueOf(parsedStreet[1]);
            String streetName = parsedStreet[2];
            Integer streetLength = Integer.valueOf(parsedStreet[3]);
            streets.add(new Street(startIntersectionIndx, endIntersectionIndx, streetName, streetLength));
        }


        Map<String, Street> streetsByName = new HashMap<>();
        for(Street street : streets){
            streetsByName.put(street.getStreetName(), street);
        }

        List<Car> cars = new ArrayList<>();
        for(int i=0; i<carCount; ++i){
            String carLine = scanner.nextLine().replace("\n", "");
            String[] parsedCar = carLine.split(" ");

            Integer numberOfStreets = Integer.valueOf(parsedCar[0]);
            Street firstStreet = streetsByName.get(parsedCar[1]);
            LinkedList<Street> remainingStreets = new LinkedList<>();
            for(int k=2; k<numberOfStreets+1; ++k){
                String streetName = parsedCar[k];
                Street street = streetsByName.get(streetName);
                remainingStreets.addLast(street);
            }

            Car newCar = new Car(i, firstStreet, remainingStreets);
            cars.add(newCar);
            newCar.getCurrentStreet().addCar(newCar);
        }

        return new InputData(simulationTime, intersectionCount, streetCount, carCount, pointsPerDestination, streetsByName, cars);
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
