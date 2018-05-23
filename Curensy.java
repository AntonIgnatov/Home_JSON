package com.company;

import java.util.HashMap;

public class Curensy {
    private HashMap<String, Double> rates;


    public Curensy(HashMap<String, Double> rates) {
        this.rates = rates;
    }

    public HashMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, Double> rates) {
        this.rates = rates;
    }
}
