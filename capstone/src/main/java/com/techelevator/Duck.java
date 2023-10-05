package com.techelevator;

import java.math.BigDecimal;

public class Duck extends Item {

    private static final String duckNoise = "Quack, Quack, Splash!";

    public Duck(String name, BigDecimal price) {
        super(name, price, duckNoise);
    }

}