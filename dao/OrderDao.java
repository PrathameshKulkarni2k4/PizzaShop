package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkte.pizzashop.entities.Pizza;
import com.dkte.pizzashop.utils.DBUtil;

public class OrderDao implements AutoCloseable {

	private Connection connection;

	public OrderDao() throws SQLException {
		connection = DBUtil.getConnection();
	}

	public void insertOrder(int cid, int mid) throws SQLException {
		String sql = "insert into orders(cid,mid) values(?,?)";
		try (PreparedStatement insertStatement = connection.prepareCall(sql)) {
			insertStatement.setInt(1, cid);
			insertStatement.setInt(2, mid);
			insertStatement.executeUpdate();
		}
	}

	public List<Pizza> displayOrder(int cid) throws SQLException {
		List<Pizza> pizzaMenu = new ArrayList<Pizza>();
		String sql = "select m.* from menu m INNER JOIN orders o on m.mid = o.mid where o.cid = ?";
		try (PreparedStatement selectStatement = connection.prepareCall(sql)) {
			selectStatement.setInt(1, cid);
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

	public List<List<Object>> displayOrders() throws SQLException {
		List<List<Object>> orders = new ArrayList<List<Object>>();
		String sql = "SELECT orders.oid, customer.name, menu.name FROM customer INNER JOIN orders ON customer.cid = orders.cid INNER JOIN menu ON orders.mid = menu.mid ORDER BY orders.oid";
		try (PreparedStatement selectStatement = connection.prepareCall(sql)) {
			ResultSet rs = selectStatement.executeQuery();
			while (rs.next()) {
				List<Object> order = new ArrayList<>();
				order.add(rs.getInt(1));
				order.add(rs.getString(2));
				order.add(rs.getString(3));
				orders.add(order);
			}
		}
		return orders;
	}

	public double calculateTotalPrice() throws SQLException {
		double totalPrice = 0.0;
		String sql = "SELECT SUM(m.price) FROM orders o INNER JOIN menu m ON o.mid = m.mid";
		try (PreparedStatement selectStatement = connection.prepareCall(sql)) {
			ResultSet rs = selectStatement.executeQuery();
			if (rs.next()) {
				totalPrice = rs.getDouble(1);
			} else {
				return 0.0;
			}
		}
		return totalPrice;
	}

	@Override
	public void close() throws SQLException {
		if (connection != null)
			connection.close();
	}

}
