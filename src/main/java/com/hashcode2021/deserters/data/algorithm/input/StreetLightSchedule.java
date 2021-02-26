package com.hashcode2021.deserters.data.algorithm.input;

import java.util.LinkedList;
import java.util.List;

public class StreetLightSchedule {
    private final List<StreetLightRule> cycle;

    private StreetLightRule currentRule = null;
    private LinkedList<StreetLightRule> nextRules = new LinkedList<>();

    public StreetLightSchedule(List<StreetLightRule> cycle){
        this.cycle = cycle;

        if(cycle.isEmpty()){
            throw new RuntimeException("Cycle cannot be empty.");
        }

        if(1 == cycle.size()) {
            currentRule = cycle.get(0);
            nextRules.addFirst(cycle.get(0));
            currentRule.activate();
        } else {
            currentRule = cycle.get(0);
            currentRule.activate();
            for(int i=1; i<cycle.size(); ++i){
                nextRules.add(cycle.get(i));
            }
        }

        currentRule.getStreet().openStreet();
    }

    public void tick(){
        if(currentRule.getIsActive()) {
            currentRule.tick();
        } else {
            currentRule.getStreet().closeStreet();
            nextRules.addLast(currentRule);

            currentRule = nextRules.removeFirst();
            currentRule.getStreet().openStreet();
        }
    }
}
