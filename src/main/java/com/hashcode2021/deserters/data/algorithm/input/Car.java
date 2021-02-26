package com.hashcode2021.deserters.data.algorithm.input;

import lombok.Getter;

import java.io.Serializable;
import java.util.LinkedList;

@Getter
public class Car implements Serializable {
    /* LOADED FROM FILE */
    private Integer carIndex;
    private Street currentStreet;
    private Integer remainingStreetTravelTime;
    private final LinkedList<Street> allRemainingStreets;

    private Integer timeWhenReachedDestination;

    /* END */

    public Car(Integer carIndex, Street currentStreet, LinkedList<Street> allRemainingStreets) {
        this.carIndex = carIndex;
        this.currentStreet = currentStreet;
        this.allRemainingStreets = allRemainingStreets;

        this.remainingStreetTravelTime = 0; // wazne, zauwazylismy
    }

    public Boolean isInMotion(){
        return remainingStreetTravelTime > 0;
    }

    public void move(){
        if(remainingStreetTravelTime > 0){
            --remainingStreetTravelTime;
        } else {
            throw new RuntimeException("Car is waiting on lights - cannot move this car.");
        }
    }

    public void enterNewStreet(){
        if(0 == remainingStreetTravelTime){
            currentStreet.removeCar();
            currentStreet = allRemainingStreets.removeFirst();
            remainingStreetTravelTime = currentStreet.getStreetLength(); // maybe -1 ???
            currentStreet.addCar(this);
        } else {
            throw new RuntimeException("Cannot enter next intersection, because current street is not przejechana.");
        }
    }

    public boolean hasReachedTarget(){
        if(0 == remainingStreetTravelTime && allRemainingStreets.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    public void setTimeWhenReachedDestination(Integer timeWhenReachedDestination){
        this.timeWhenReachedDestination = timeWhenReachedDestination;
    }
}
