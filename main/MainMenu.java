package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.dkte.pizzashop.dao.CustomerDao;
import com.dkte.pizzashop.entities.Customer;

public class MainMenu {

	public static Customer login(Scanner sc) {
		System.out.print("Enter the email - ");
		String email = sc.next();
		System.out.print("Enter the password - ");
		String password = sc.next();
		try (CustomerDao customerDao = new CustomerDao()) {
			Customer customer = customerDao.selectCustomer(email, password);
			if (customer != null) {
				System.out.println("Login successful.. :)");
				return customer;
			} else {
				System.out.println("Invalid credintials.. :(");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void registration(Scanner sc) {
		Customer customer = new Customer();
		customer.acceptCustomer(sc);
		try (CustomerDao customerDao = new CustomerDao()) {
			customerDao.insertCustomer(customer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean adminLogin(Scanner sc) {
		System.out.print("Enter the email - ");
		String email = sc.next();
		System.out.print("Enter the password - ");
		String password = sc.next();
		if (email.equals("admin@gmail.com") && password.equals("admin")) {
			return true;
		}
		return false;
	}

	public static int menus(Scanner sc) {
		System.out.println("**********************");
		System.out.println("0. EXIT");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Admin");
		System.out.println("Enter the choice : ");
		int choice = sc.nextInt();
		System.out.println("**********************");
		return choice;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while ((choice = menus(sc)) != 0) {
			switch (choice) {
			case 1:
				System.out.println("Login clicked... ");
				Customer customer;
				if ((customer = login(sc)) != null)
					SubMenu.subMenu(sc, customer);
				break;
			case 2:
				System.out.println("Register clicked... ");
				registration(sc);
				break;
			case 3:
				System.out.println("Admin login clicked... ");
				if (adminLogin(sc))
					AdminSubMenu.adminSubMenu(sc);
				break;

			default:
				System.out.println("Wrong choice ... :(");
				break;
			}
		}
		System.out.println("Thank you for using our application !!!");

	}

}
