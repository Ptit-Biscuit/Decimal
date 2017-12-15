package com.epsi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
	private Connection con;

	public Dao() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}

		this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Decimal?user=root&password=root");
	}

	public boolean addScoreToDatabase(String pseudo, int score) throws SQLException {
		String x = "INSERT INTO score (pseudo, score) VALUES ('" + pseudo + "', '" + score + "');";
		return this.con.prepareStatement(x).execute();
	}

	public boolean isClosed() {
		try {
			return con == null || con.isClosed();
		} catch (SQLException e) {
			return true;
		}
	}
}
