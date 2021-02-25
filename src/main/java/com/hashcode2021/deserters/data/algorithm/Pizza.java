package com.hashcode2021.deserters.data.algorithm;

import java.util.Objects;

public class Pizza {
    private final Integer slices;
    private final Integer index;

    public Pizza(Integer slices, Integer index) {
        this.slices = slices;
        this.index = index;
    }

    public Integer getSlices() {
        return slices;
    }

    public Integer getIndex() {
        return index;
    }

    public String toString() {
        return String.format("Pizza[Slices:%s][Index:%s]", slices, index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return slices.equals(pizza.slices) &&
                index.equals(pizza.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slices, index);
    }
}
