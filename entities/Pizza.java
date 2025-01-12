package com.dkte.pizzashop.entities;

import java.util.Scanner;

public class Pizza {
	private int mid;
	private String name;
	private String description;
	private double price;

	public Pizza() {
	}

	public Pizza(int mid, String name, String description, double price) {
		this.mid = mid;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void accept(Scanner sc) {
		System.out.print("Enter the name");
		name = sc.next();
		System.out.print("Enter the Description");
		description = sc.next();
		System.out.print("Enter the Price");
		price = sc.nextDouble();
	}

	@Override
	public String toString() {
		return "Pizza [mid=" + mid + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}

}
