package com.techelevator;

import java.math.BigDecimal;

public abstract class Item {

    String name;
    BigDecimal price;
    int quantity = 5;
    String noise;

    public Item(String name, BigDecimal price, String noise) {
        this.name = name;
        this.price = price;
        this.noise = noise;
    }

    public boolean isSoldOut() {
        return quantity <= 0;
    }

    public String isSoldOutPrint() {
        if (this.isSoldOut()) {
            return "SOLD OUT";
        }
        else {
            return "QTY: " + this.quantity;
        }
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getNoise() {
        return this.noise;
    }

}
