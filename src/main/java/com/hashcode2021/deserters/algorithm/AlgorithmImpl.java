package com.hashcode2021.deserters.algorithm;

import com.hashcode2021.deserters.data.InputData;
import com.hashcode2021.deserters.data.OutputData;
import com.hashcode2021.deserters.data.ScenarioInput;
import com.hashcode2021.deserters.data.algorithm.input.*;
import com.hashcode2021.deserters.util.Debuggable;
import org.graalvm.compiler.hotspot.replacements.HashCodeSnippets;

import java.util.*;

public class AlgorithmImpl extends Algorithm implements Debuggable {
    public AlgorithmImpl(ScenarioInput scenarioInput) {
        super(scenarioInput);
    }

    @Override
    public OutputData calculate(ScenarioInput scenarioInput) {
        logDebug("Executing algorithm.");
        InputData inputData = scenarioInput.getInputData();


        List<StreetLightSchedule> schedules = new ArrayList<>();

        Integer remainingTime = inputData.getSimulationTime();
        for(Intersection intersection : inputData.getIntersections()){
            Street mostInterestingStreet = null;
            Integer smallestTime = 0;

            for(Street street : intersection.getInputStreets()){
                Map<Car, Integer> carWeights = new HashMap<>();

                for(Car car : street.getCarQueue()){
                    carWeights.put(car, getMaxWeightForCarBeforeLight(car, remainingTime));
                }
            }


        }

        return new OutputData();
    }

    public Integer getMaxWeightForCarBeforeLight(Car car, Integer remainingTime){
        Integer optimisticTimeLeft = 0;

        for(Street remainingStreet : car.getAllRemainingStreets()){
            optimisticTimeLeft += remainingStreet.getStreetLength();
        }

        if(optimisticTimeLeft < remainingTime){
            optimisticTimeLeft = 1000 + (remainingTime - optimisticTimeLeft);
        }

        return optimisticTimeLeft;
    }

    @Override
    public String getAlgorithmName() {
        return "algorithm1";
    }
}
