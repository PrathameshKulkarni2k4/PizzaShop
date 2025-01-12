package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkte.pizzashop.entities.Pizza;
import com.dkte.pizzashop.utils.DBUtil;

public class PizzaDao implements AutoCloseable {

	private Connection connection;

	public PizzaDao() throws SQLException {
		connection = DBUtil.getConnection();
	}

	public List<Pizza> selectPizza() throws SQLException {
		List<Pizza> pizzaMenu = new ArrayList<Pizza>();
		String sql = "select * from menu";
		try (PreparedStatement selectStatement = connection.prepareCall(sql)) {
			ResultSet rs = selectStatement.executeQuery();
			while (rs.next()) {
				Pizza pizza = new Pizza();
				pizza.setMid(rs.getInt(1));
				pizza.setName(rs.getString(2));
				pizza.setDescription(rs.getString(3));
				pizza.setPrice(rs.getDouble(4));
				pizzaMenu.add(pizza);
			}
		}
		return pizzaMenu;
	}

	public void insertPizza(Pizza pizza) throws SQLException {
		String sql = "INSERT INTO menu(name, description, price) VALUES(?,?,?)";
		try (PreparedStatement insertStatement = connection.prepareCall(sql)) {
			insertStatement.setString(1, pizza.getName());
			insertStatement.setString(2, pizza.getDescription());
			insertStatement.setDouble(3, pizza.getPrice());
			insertStatement.executeUpdate();
		}
	}

	public void updatePrice(int mid, double price) throws SQLException {
		String sql = "UPDATE menu SET price = ? WHERE mid = ?";
		try (PreparedStatement updateStatement = connection.prepareCall(sql)) {
			updateStatement.setDouble(1, price);
			updateStatement.setInt(2, mid);
			updateStatement.executeUpdate();
		}
	}

	public void deletePizza(int mid) throws SQLException {
		String sql = "DELETE FROM menu WHERE mid = ?";
		try (PreparedStatement updateStatement = connection.prepareCall(sql)) {
			updateStatement.setInt(1, mid);
			updateStatement.executeUpdate();
		}
	}

	@Override
	public void close() throws SQLException {
		if (connection != null)
			connection.close();
	}

}
