package com.hashcode2021.deserters.data;

import com.hashcode2021.deserters.data.algorithm.input.Car;
import com.hashcode2021.deserters.data.algorithm.input.Intersection;
import com.hashcode2021.deserters.data.algorithm.input.Street;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class InputData {
    private final Integer simulationTime;
    private final Integer intesectionCount;
    private final Integer streetCount;
    private final Integer carCount;
    private final Integer pointsPerRD;
    private final Map<String, Street> streetsByName;
    private final List<Car> cars;
    private final List<Intersection> intersections;

    public InputData(Integer simulationTime, Integer intesectionCount, Integer streetCount, Integer carCount, Integer pointsPerRD, Map<String, Street> streetsByName, List<Car> cars) {
        this.simulationTime = simulationTime;
        this.intesectionCount = intesectionCount;
        this.streetCount = streetCount;
        this.carCount = carCount;
        this.pointsPerRD = pointsPerRD;
        this.streetsByName = streetsByName;
        this.cars = cars;


        List<Intersection> intersections = new ArrayList<>();
        for(int i=0; i<intesectionCount; ++i) {
            List<Street> intersectionStreets = new ArrayList<>();
            for (Street street : streetsByName.values()) {
                if (i == street.getEndIntersectionIndx()) {
                    intersectionStreets.add(street);
                }
            }

            intersections.add(new Intersection(i, intersectionStreets));
        }
        this.intersections = intersections;
    }
}
