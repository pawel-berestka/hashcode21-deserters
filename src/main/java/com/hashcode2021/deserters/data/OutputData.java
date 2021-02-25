package com.hashcode2021.deserters.data;

public class OutputData {
    private final Integer typesOfPizzas;
    private final Integer[] pizzasIndexes;

    public OutputData(Integer typesOfPizzas, Integer[] pizzasIndexes) {
        this.typesOfPizzas = typesOfPizzas;
        this.pizzasIndexes = pizzasIndexes;
    }

    public Integer getTypesOfPizzas() {
        return typesOfPizzas;
    }
    public Integer[] getPizzasIndexes() {
        return pizzasIndexes;
    }
}
