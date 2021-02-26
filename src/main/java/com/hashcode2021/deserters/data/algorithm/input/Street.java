package com.hashcode2021.deserters.data.algorithm.input;

import com.hashcode2021.deserters.util.Debuggable;
import lombok.Getter;

import java.io.Serializable;
import java.util.LinkedList;

@Getter
public class Street implements Serializable, Debuggable {
    private final Integer startIntersectionIndx;
    private final Integer endIntersectionIndx;
    private final String streetName;
    private final Integer streetLength;

    private LinkedList<Car> carQueue;
    private Boolean isOpen;


    public Street(Integer startIntersectionIndx, Integer endIntersectionIndx, String streetName, Integer streetLength){
        this.startIntersectionIndx = startIntersectionIndx;
        this.endIntersectionIndx = endIntersectionIndx;
        this.streetName = streetName;
        this.streetLength = streetLength;

        this.carQueue = new LinkedList<>();
        this.isOpen = false;
    }

    public void addCar(Car car){
        carQueue.addFirst(car);
    }

    public void removeCar(){
        Car removedCar = carQueue.removeLast();
        logDebug("Removed car: %s.", removedCar.getCarIndex());
    }

    public Car getFirstCarInQueue(){
        return carQueue.getLast();
    }

    public void openStreet(){
        if(isOpen){
            throw new RuntimeException(String.format("Idiot - street is already open: %s.", streetName));
        }

        logDebug("Opening street: %s.", streetName);
        isOpen = true;
    }

    public void closeStreet(){
        if(!isOpen){
            throw new RuntimeException(String.format("Idiot - street is already closed: %s.", streetName));
        }

        logDebug("Closing street: %s.", streetName);
        isOpen = false;
    }

    public boolean isOpen(){
        return isOpen;
    }

    public LinkedList<Car> getCarQueue(){
        return carQueue;
    }
}
