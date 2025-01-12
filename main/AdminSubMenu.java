package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dkte.pizzashop.dao.CustomerDao;
import com.dkte.pizzashop.dao.OrderDao;
import com.dkte.pizzashop.dao.PizzaDao;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Pizza;

public class AdminSubMenu {

	private static void addNewPizza(Scanner sc) {
		Pizza pizza = new Pizza();
		pizza.accept(sc);
		try (PizzaDao pizzaDao = new PizzaDao()) {
			pizzaDao.insertPizza(pizza);
			System.out.println("New pizza added successfully !!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void updatePrice(Scanner sc) {
		System.out.print("Enter the mid - ");
		int mid = sc.nextInt();
		System.out.print("Enter the Price - ");
		double price = sc.nextDouble();
		try (PizzaDao pizzaDao = new PizzaDao()) {
			pizzaDao.updatePrice(mid, price);
			System.out.println("price updated successfully !!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void removePizza(Scanner sc) {
		System.out.print("Enter the mid - ");
		int mid = sc.nextInt();
		try (PizzaDao pizzaDao = new PizzaDao()) {
			pizzaDao.deletePizza(mid);
			System.out.println("price removed successfully !!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void pizzaMenus() {
		List<Pizza> pizzaMenu = new ArrayList<Pizza>();
		try (PizzaDao pizzaDao = new PizzaDao()) {
			pizzaMenu = pizzaDao.selectPizza();
			pizzaMenu.forEach(p -> System.out.println(p));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void displayCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		try (CustomerDao cutomerDao = new CustomerDao()) {
			customers = cutomerDao.selectAllCustomer();
			customers.forEach(cust -> System.out.println(cust));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void displayAllOrders() {
		try (OrderDao orderDao = new OrderDao()) {
			List<List<Object>> orders = orderDao.displayOrders();
			orders.stream().forEach(order -> System.out.println(order));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void calculateTotalPrice() {
		try (OrderDao orderDao = new OrderDao()) {
			double totalPrice = orderDao.calculateTotalPrice();
			System.out.println("Total Price - " + totalPrice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static int menus(Scanner sc) {
		System.out.println("*******************");
		System.out.println("0. Logout");
		System.out.println("1. Add new pizza");
		System.out.println("2. Update price");
		System.out.println("3. Delete pizza");
		System.out.println("4. Display pizza Menu");
		System.out.println("5. Display all customers");
		System.out.println("6. Display all orders");
		System.out.println("7. Calculate total price");
		System.out.print("Enter a choice - ");
		int choice = sc.nextInt();
		System.out.println("*******************");
		return choice;
	}

	public static void adminSubMenu(Scanner sc) {
		int choice = 0;
		while ((choice = menus(sc)) != 0) {
			switch (choice) {
			case 1:
				System.out.println("Add new pizza");
				addNewPizza(sc);
				break;
			case 2:
				System.out.println("Update price");
				updatePrice(sc);
				break;
			case 3:
				System.out.println("Delete pizza");
				removePizza(sc);
				break;
			case 4:
				System.out.println("Display pizza Menu");
				pizzaMenus();
				break;
			case 5:
				System.out.println("Display all customers");
				displayCustomers();
				break;
			case 6:
				System.out.println("Display all orders");
				displayAllOrders();
				break;
			case 7:
				System.out.println("Calculate total price");
				calculateTotalPrice();
				break;
			default:
				System.out.println("Wrong choice ... :(");
				break;
			}
		}
	}
}
