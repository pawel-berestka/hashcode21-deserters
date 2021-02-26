package com.hashcode2021.deserters.data.algorithm.input;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class Intersection implements Serializable {
    private final Integer intersectionIndx;
    private final List<Street> inputStreets;
    private StreetLightSchedule schedule;

    public Intersection(Integer intersectionIndx, List<Street> inputStreets) {
        if(inputStreets.isEmpty()){
            throw new RuntimeException("Input streets cannot be empty!!!");
        }

        this.intersectionIndx = intersectionIndx;
        this.inputStreets = inputStreets;
        this.schedule = null;
    }

    public StreetLightSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(StreetLightSchedule schedule) {
        this.schedule = schedule;
    }
}
