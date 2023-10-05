package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    BigDecimal currentBalance = new BigDecimal(0);
    Map<String, Item> inventory = new HashMap<String, Item>();
    Scanner userInput = new Scanner(System.in);

    public VendingMachine() {
        this.readIn();
    }

    // Read input and create Items from it, add Items to Map
    public void readIn() {
        File input = new File("C:\\Users\\Student\\workspace\\java-minicapstonemodule1-team3\\capstone\\vendingmachine.csv");
        try (Scanner fileInput = new Scanner(input)) {
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                String[] lineContents = line.split("\\|");
                // System.out.println(lineContents[3]);
                if (lineContents[3].equalsIgnoreCase("Duck")) {
                    Item toy = new Duck(lineContents[1], new BigDecimal(lineContents[2]));
                    inventory.put(lineContents[0], toy);
                }
                else if (lineContents[3].equalsIgnoreCase("Penguin")) {
                    Item toy = new Penguin(lineContents[1], new BigDecimal(lineContents[2]));
                    inventory.put(lineContents[0], toy);
                }
                else if (lineContents[3].equalsIgnoreCase("Cat")) {
                    Item toy = new Cat(lineContents[1], new BigDecimal(lineContents[2]));
                    inventory.put(lineContents[0], toy);
                }
                else if (lineContents[3].equalsIgnoreCase("Pony")) {
                    Item toy = new Pony(lineContents[1], new BigDecimal(lineContents[2]));
                    inventory.put(lineContents[0], toy);
                }
                else {
                    System.err.println("Invalid product class");
                }
            }
        }
        catch (FileNotFoundException f) {
            System.err.println("File Not Found");
            System.exit(1);
        }
    }

    // Displays vending machine contents for user
    public void printMap() {
        for (String key : inventory.keySet()) {
            Item product = inventory.get(key);
            System.out.println(key + " | " + product.getName() + " | $" + product.getPrice() + " | " + product.isSoldOutPrint());
        }
    }

    public void printStartMenu() {
        System.out.println("(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
        String response = userInput.nextLine();
        if (response.equals("1")) {
            this.printMap();
        }
        if (response.equals("2")) {
            //
        }
        if (response.equals("3")) {
            System.exit(0);
        }
    }

    public void printPurchaseProcessMenu() {
        System.out.println("Current money provided: $" + this.currentBalance);
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select product");
        System.out.println("(3) Finish Transactions");
        String response = userInput.nextLine();
        if (response.equals("1")) {
            System.out.println();
        }
        if (response.equals("2")) {

        }
        if (response.equals("3")) {
            //
        }
    }

    public void feedMoney(String amount) {
        this.currentBalance.add(new BigDecimal(amount));
    }



}
