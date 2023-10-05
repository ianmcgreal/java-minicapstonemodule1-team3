package com.techelevator;

import java.math.BigDecimal;

public class Cat extends Item {

    private static final String catNoise = "Meow, Meow, Meow!";

    public Cat(String name, BigDecimal price) {
        super(name, price, catNoise);
    }

}
