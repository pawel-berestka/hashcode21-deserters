package com.hashcode2021.deserters.data;

import lombok.Getter;

@Getter
public class ScenarioInput {
    private final String fileName;
    private final InputData inputData;
    private final DynamicParameters dynamicParameters;

    public ScenarioInput(String fileName, InputData inputData, DynamicParameters dynamicParameters) {
        this.fileName = fileName;
        this.inputData = inputData;
        this.dynamicParameters = dynamicParameters;
    }
}
