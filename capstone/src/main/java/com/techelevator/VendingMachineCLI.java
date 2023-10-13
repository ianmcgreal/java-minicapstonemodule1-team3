package com.techelevator;


public class VendingMachineCLI {

	VendingMachine v = new VendingMachine();

	public VendingMachineCLI() {
	}

	public void run() {
		v.printStartMenu();
	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}

}
