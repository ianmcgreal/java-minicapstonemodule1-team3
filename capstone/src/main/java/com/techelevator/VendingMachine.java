package com.techelevator;

import jdk.swing.interop.SwingInterOpUtils;
import org.junit.vintage.engine.discovery.IsPotentialJUnit4TestClass;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    BigDecimal currentBalance = new BigDecimal(0);
    Map<String, Item> inventory = new HashMap<String, Item>();
    Scanner userInput = new Scanner(System.in);
    File log = new File("C:\\Users\\Student\\workspace\\java-minicapstonemodule1-team3\\capstone","log.txt");

    public VendingMachine() {
        this.readIn();
    }

    public void createLogFile() {
        try {
            log.createNewFile();
        } catch (IOException e) {
            System.err.println("Could not create file.");
        }
    }

    // Read input and create Items from it, add Items to Map
    public void readIn() {
        File input = new File("C:\\Users\\Student\\workspace\\java-minicapstonemodule1-team3\\capstone\\vendingmachine.csv");
        try (Scanner fileInput = new Scanner(input)) {
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                String[] lineContents = line.split("\\|");
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

    //START MENU
    public void printStartMenu() {
        System.out.println("\n(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
        String response = userInput.nextLine();
        if (response.equals("1")) {
            this.printMap();
            this.printStartMenu();
        }
        if (response.equals("2")) {
            this.printPurchaseProcessMenu();
        }
        if (response.equals("3")) {
            System.out.println("Thank you for your business. Goodbye! :)");
            System.exit(0);
            //OPTIONAL SALES REPORT STUFF HERE ?
        }
        else {
            System.out.println("Please choose a valid option");
            this.printStartMenu();
        }
    }

    // PURCHASE MENU
    public void printPurchaseProcessMenu() {
        System.out.println("\nCurrent money provided: $" + this.currentBalance);
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select product");
        System.out.println("(3) Finish Transactions");
        String response = userInput.nextLine();
        if (response.equals("1")) {
            System.out.println("How much money are you inserting? i.e. 1.00.");
            String input = userInput.nextLine();
            if (Double.valueOf(input) < 0) {
                System.out.println("Unable to accept a negative input amount");
                this.printPurchaseProcessMenu();
            }
            try {
                this.feedMoney(input);
            }catch(Exception ex){
                System.err.println("You entered an invalid amount, try again dummy.");

            }finally {
                this.printPurchaseProcessMenu();
            }
        }
        if (response.equals("2")) {
            this.printMap();
            System.out.println("Please enter the item key, i.e. A2");
            String userKey = userInput.nextLine().toUpperCase();
            if(!(inventory.containsKey(userKey))){// if user entered key that exists do this:
                System.err.println("The key you entered does not exist. Try again idiot!");
                this.printPurchaseProcessMenu();
            }
            if(inventory.get(userKey).isSoldOut()){
                System.out.println("This item is sold out. I am deeply sorry. Please choose a different item.");
                this.printPurchaseProcessMenu();
            }
            if(this.currentBalance.subtract(inventory.get(userKey).getPrice()).compareTo(BigDecimal.valueOf(0)) < 0 ){
                System.err.println("You are too poor to purchase this item. Provide more funds or pick something you can afford, pleab.");
                this.printPurchaseProcessMenu();
            }
            this.dispense(inventory.get(userKey));
            this.printPurchaseProcessMenu();

        }
        if (response.equals("3")) {
            this.finishTransaction();
            this.printStartMenu();
        }
        else {
            System.out.println("Please select a valid option");
            this.printPurchaseProcessMenu();
        }
    }

    public void feedMoney(String amount) {
        this.currentBalance = this.currentBalance.add(new BigDecimal(amount));
        printLog("FEED MONEY",new BigDecimal(amount));
    }

    public void dispense(Item out){
        this.currentBalance = this.currentBalance.subtract(out.getPrice());
        System.out.println(out.getNoise());
        System.out.println("You've purchased a " + out.getName() + " for $" + out.getPrice());
        out.setQuantity(out.getQuantity() - 1);
        printLog(out.getName(),out.getPrice());
    }

    public void finishTransaction(){
        BigDecimal totalInPennies = this.currentBalance.multiply(BigDecimal.valueOf(100));
        BigDecimal numOfQuarters;
        BigDecimal numOfDimes;
        BigDecimal numOfNickels;
        BigDecimal numOfPennies;
        BigDecimal[] quarterCalculation = totalInPennies.divideAndRemainder(BigDecimal.valueOf(25));
        numOfQuarters = quarterCalculation[0];
        BigDecimal[] dimeCalculation = quarterCalculation[1].divideAndRemainder(BigDecimal.valueOf(10));
        numOfDimes = dimeCalculation[0];
        BigDecimal[] nickelCalculation = dimeCalculation[1].divideAndRemainder(BigDecimal.valueOf(5));
        numOfNickels = nickelCalculation[0];
        numOfPennies = nickelCalculation[1];
        this.currentBalance = new BigDecimal(0);
        System.out.println("Returned change: ");
        if (numOfQuarters.doubleValue() > 0) {
            if(numOfQuarters.doubleValue() == 1){
                System.out.println(numOfQuarters.toBigInteger() + " quarter");
            } else {
                System.out.println(numOfQuarters.toBigInteger() + " quarters");
            }
        }
        if (numOfDimes.doubleValue() > 0) {
            if(numOfDimes.doubleValue() == 1){
                System.out.println(numOfDimes.toBigInteger() + " dime");
            } else {
                System.out.println(numOfDimes.toBigInteger() + " dimes");
            }
        }
        if (numOfNickels.doubleValue() > 0) {
            if(numOfNickels.doubleValue() == 1){
                System.out.println(numOfNickels.toBigInteger() + " nickel");
            } else {
                System.out.println(numOfNickels.toBigInteger() + " nickels");
            }
        }
        if (numOfPennies.doubleValue() > 0) {
            if(numOfPennies.doubleValue() == 1){
                System.out.println(numOfPennies.toBigInteger() + " penny");
            } else {
                System.out.println(numOfPennies.toBigInteger() + " pennies");
            }
        }
        printLog("GIVE CHANGE", totalInPennies.divide(BigDecimal.valueOf(100)));
    }

    public void printLog(String action,BigDecimal transactionAmount){
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(log,true))){
            writer.println(LocalDate.now() + " " + LocalTime.now() + " " + action + ": " + transactionAmount + " CURRENT BALANCE: " + this.currentBalance );
        } catch(FileNotFoundException e) {
            System.err.println("File not found.");
        }
    }

    public BigDecimal getCurrentBalance() {
        return this.currentBalance;
    }

}
