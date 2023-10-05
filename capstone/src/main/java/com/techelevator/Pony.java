package com.techelevator;

import java.math.BigDecimal;

public class Pony extends Item {

    private static final String ponyNoise = "Neigh, Neigh, Yay!";

    public Pony(String name, BigDecimal price) {
        super(name, price, ponyNoise);
    }

}
