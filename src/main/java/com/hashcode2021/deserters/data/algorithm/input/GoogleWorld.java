package com.hashcode2021.deserters.data.algorithm.input;

import com.hashcode2021.deserters.util.Debuggable;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class GoogleWorld implements Debuggable, Serializable {
    private List<StreetLightSchedule> schedules;
    private List<Car> allCars;
    private Integer remainingTime;
    private Integer simulationTime;

    public GoogleWorld(List<StreetLightSchedule> schedule, List<Car> allCars, Integer simulationTime) {
        this.schedules = schedule;
        this.allCars = allCars;
        this.simulationTime = simulationTime;
        this.remainingTime = simulationTime;
    }

    public void simulate(){
        while(remainingTime > 0){
            logDebug("Starting tick, remainingTime=[%s].", remainingTime);
            tick();
            --remainingTime;
            logDebug("Finished tick, remainingTime=[%s].", remainingTime);
        }

        logDebug("THIS IS THE END!");
    }


    public void tick(){
        for(Car car : allCars){
            logDebug("Iterating car: %s.", car.getCarIndex());

            if(car.hasReachedTarget()){
                continue;
            }

            if(car.isInMotion()){
                car.move();
                if(car.hasReachedTarget()){
                    car.setTimeWhenReachedDestination(simulationTime - remainingTime);
                }
            } else {
                if(car.getCurrentStreet().getIsOpen() && car.getCurrentStreet().getFirstCarInQueue() == car){
                    car.enterNewStreet();
                } else {
                    logDebug("Car=[%s] is waiting on lights. Street=[%s] is closed.",
                            car.getCarIndex(),
                            car.getCurrentStreet().getStreetName()
                    );
                }
            }
        }

        for(StreetLightSchedule schedule : schedules){
            schedule.tick();
        }
    }
}
