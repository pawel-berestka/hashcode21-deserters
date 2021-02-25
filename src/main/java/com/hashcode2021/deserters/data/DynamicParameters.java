package com.hashcode2021.deserters.data;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DynamicParameters {
    private final String dynamicName;

    public DynamicParameters(String dynamicName){
        this.dynamicName = dynamicName;
    }
}
