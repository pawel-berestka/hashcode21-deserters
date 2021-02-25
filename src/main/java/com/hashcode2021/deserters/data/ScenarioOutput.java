package com.hashcode2021.deserters.data;

import lombok.Getter;

@Getter
public class ScenarioOutput {
    private final String fileName;
    private final OutputData outputData;

    public ScenarioOutput(String fileName, OutputData outputData) {
        this.fileName = fileName;
        this.outputData = outputData;
    }
}
