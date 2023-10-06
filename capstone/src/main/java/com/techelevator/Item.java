package com.techelevator;

import java.math.BigDecimal;

public abstract class Item {

    private String name;
    private BigDecimal price;
    private int quantity = 5;
    private String noise;

    public Item(String name, BigDecimal price, String noise) {
        this.name = name;
        this.price = price;
        this.noise = noise;
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
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

}
