package com.hashcode2021.deserters.data;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputData {
    private Integer maxNumberOfSlices;
    private Integer differentTypes;
    private Integer[] pizzas;

    public Integer getMaxNumberOfSlices() {
        return maxNumberOfSlices;
    }

    public void setMaxNumberOfSlices(Integer maxNumberOfSlices) {
        this.maxNumberOfSlices = maxNumberOfSlices;
    }

    public Integer getDifferentTypes() {
        return differentTypes;
    }

    public void setDifferentTypes(Integer differentTypes) {
        this.differentTypes = differentTypes;
    }

    public Integer[] getPizzas() {
        return pizzas;
    }

    public void setPizzas(Integer[] pizzas) {
        this.pizzas = pizzas;
    }

    public String toString() {
        return String.format("Slices: %s, DifferentTypes: %s\n%s", maxNumberOfSlices, differentTypes, Stream.of(pizzas).map(Object::toString).collect(Collectors.joining(",")));
    }
}
