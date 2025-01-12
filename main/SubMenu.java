package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dkte.pizzashop.dao.OrderDao;
import com.dkte.pizzashop.dao.PizzaDao;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Pizza;

public class SubMenu {

	private static void pizzaMenus() {
		List<Pizza> pizzaMenu = new ArrayList<Pizza>();
		try (PizzaDao pizzaDao = new PizzaDao()) {
			pizzaMenu = pizzaDao.selectPizza();
			pizzaMenu.forEach(p -> System.out.println(p));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void orderPlaced(Scanner sc, Customer customer) {
		try (OrderDao orderDao = new OrderDao()) {
			System.out.print("Enter the mid of pizza -");
			int mid = sc.nextInt();
			orderDao.insertOrder(customer.getCid(), mid);
			System.out.println("Order placed... :)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void orderHistory(Scanner sc, Customer customer) {
		try (OrderDao orderDao = new OrderDao()) {
			List<Pizza> pizzaMenu = orderDao.displayOrder(customer.getCid());
			System.out.println("You have ordered pizzas : ");
			pizzaMenu.forEach(p -> System.out.println(p));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static int menus(Scanner sc) {
		System.out.println("*******************");
		System.out.println("0. Logout");
		System.out.println("1. Pizza Menus");
		System.out.println("2. Order a pizza");
		System.out.println("3. Order history");
		System.out.print("Enter a choice - ");
		int choice = sc.nextInt();
		System.out.println("*******************");
		return choice;
	}

	public static void subMenu(Scanner sc, Customer customer) {
		System.out.println("Welcom " + customer.getName());
		int choice = 0;
		while ((choice = menus(sc)) != 0) {
			switch (choice) {
			case 1:
				System.out.println("Pizza menu");
				pizzaMenus();
				break;
			case 2:
				System.out.println("order pizza");
				orderPlaced(sc, customer);

				break;
			case 3:
				System.out.println("order history");
				orderHistory(sc, customer);
				break;
			default:
				System.out.println("Wrong choice ... :(");
				break;
			}
		}
	}
}
