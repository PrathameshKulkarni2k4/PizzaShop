package com.dkte.pizzashop.tester;

import java.sql.SQLException;
import java.util.Scanner;

import com.dkte.pizzashop.dao.CustomerDao;
import com.dkte.pizzashop.entities.Customer;

public class CustomerTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Customer customer = new Customer();
		customer.acceptCustomer(sc);
		try (CustomerDao customerDao = new CustomerDao()) {
			customerDao.insertCustomer(customer);
//			System.out.print("Enter the email");
//			String email = sc.next();
//			System.out.print("Enter the password");
//			String password = sc.next();
//			customerDao.selectCustomer(email, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
