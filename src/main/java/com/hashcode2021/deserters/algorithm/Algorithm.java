package com.hashcode2021.deserters.algorithm;

import com.hashcode2021.deserters.data.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class Algorithm implements Callable<ScenarioOutput> {
    private final ScenarioInput scenarioInput;

    public Algorithm(ScenarioInput scenarioInput){
        this.scenarioInput = scenarioInput;
    }

    public abstract OutputData calculate(ScenarioInput scenarioInput);

    public abstract String getAlgorithmName();
    @Override
    public final ScenarioOutput call() {
        return createScenarioOutput(calculate(scenarioInput));
    }

    private ScenarioOutput createScenarioOutput(OutputData outputData){
        return new ScenarioOutput(
                scenarioInput.getFileName() + "." + getAlgorithmName() + "." + scenarioInput.getDynamicParameters().getDynamicName() + ".out",
                outputData
        );
    }
}
