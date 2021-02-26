package com.hashcode2021.deserters.data.algorithm.input;

import com.hashcode2021.deserters.data.algorithm.input.Street;
import lombok.Getter;
import lombok.ToString;

@ToString
public class StreetLightRule {
    private final Street street;
    private final Integer ruleTime;

    private Boolean isActive = false;
    private Integer remainingActiveTime = -111;

    public StreetLightRule(Street street, Integer ruleTime) {
        this.street = street;
        this.ruleTime = ruleTime;
    }

    public void tick(){
        if(-111 == remainingActiveTime){
            throw new RuntimeException("Rule should be start by activate.");
        }

        if(!isActive){
            throw new RuntimeException("Cannot tick inactive rule.");
        }

        if(0 >= remainingActiveTime){ // is greater than 0
            throw new RuntimeException(String.format("Cannot tick rule when negative or zero time: %s.", this));
        }

        --remainingActiveTime;

        if(0 == remainingActiveTime){ // to moze nie dzialac, intelij tak mowi  - obserwowac
            isActive = false;
        }
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void activate(){
        if(isActive){
            throw new RuntimeException(String.format("Rule is already active !@#!@#@!: [%s].", this));
        }

        isActive = true;
        remainingActiveTime = ruleTime;
    }

    public Street getStreet() {
        return street;
    }
}
