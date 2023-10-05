package com.techelevator;

import java.math.BigDecimal;

public class Penguin extends Item {

    private static final String penguinNoise = "Squawk, Squawk, Whee!";

    public Penguin(String name, BigDecimal price) {
        super(name, price, penguinNoise);
    }

}
