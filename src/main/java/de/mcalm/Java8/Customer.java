package de.mcalm.Java8;

public class Customer {

	private String name;
	private int orderAmount;
	public Customer(String name, int age) {
		super();
		this.name = name;
		this.orderAmount = age;
	}
	public String getName() {
		return name;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	
	public String toString() {
		return getName() + " / " + getOrderAmount();
	}
}
