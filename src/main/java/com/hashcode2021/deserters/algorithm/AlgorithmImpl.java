package com.hashcode2021.deserters.algorithm;

import com.hashcode2021.deserters.data.InputData;
import com.hashcode2021.deserters.data.OutputData;
import com.hashcode2021.deserters.data.ScenarioInput;
import com.hashcode2021.deserters.data.ScenarioOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class AlgorithmImpl extends Algorithm {
    public AlgorithmImpl(ScenarioInput scenarioInput) {
        super(scenarioInput);
    }

    @Override
    public OutputData calculate(ScenarioInput scenarioInput) {
        int bestScore = 0;
        List<Integer> bestPizzasSet = new ArrayList<>();

        InputData inputData = scenarioInput.getInputData();
        Integer[] pizzas = inputData.getPizzas();
        Integer target = inputData.getMaxNumberOfSlices();

        Integer actualScore = 0;
        List<Integer> usedPizzas = new ArrayList<>(pizzas.length);
        for (int i = pizzas.length - 1; i >= 1; --i) {
            actualScore += pizzas[i];
            usedPizzas.add(i);

            for (int k = i - 1; k >= 0; --k) {
                if ((target - actualScore) - pizzas[k] > 0) {
                    actualScore += pizzas[k];
                    usedPizzas.add(k);
                }
            }

            if (actualScore > bestScore) {
                bestScore = actualScore;
                bestPizzasSet = new ArrayList<>(usedPizzas);
            }
            actualScore = 0;
            usedPizzas = new ArrayList<>(pizzas.length);
        }

        Integer[] result = new Integer[bestPizzasSet.size()];

        return new OutputData(bestPizzasSet.size(), bestPizzasSet.toArray(result));
    }

    @Override
    public String getAlgorithmName() {
        return "algorithm1";
    }
}
