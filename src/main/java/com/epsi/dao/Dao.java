package com.epsi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
	/**
	 * La connexion avec la BDD.
	 */
	private Connection con;

	/**
	 * Constructeur de la classe Dao.
	 * @throws SQLException Si erreur avec la BDD
	 */
	public Dao() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}

		this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Decimal?user=root&password=root");
	}

	/**
	 * Ajoute le pseudo et le score dans la BDD.
	 * @param pseudo Le pseudo du joueur
	 * @param score Le score du joueur
	 * @return True si la requête a été exécutée, false sinon
	 * @throws SQLException Si erreur avec la BDD
	 */
	public boolean addScoreToDatabase(String pseudo, int score) throws SQLException {
		String x = "INSERT INTO score (pseudo, score) VALUES ('" + pseudo + "', '" + score + "');";
		return this.con.prepareStatement(x).execute();
	}

	/**
	 * Fermeture de la connexion avec la BDD.
	 * @return True si la connexion est fermée, false sinon
	 */
	public boolean isClosed() {
		try {
			return con == null || con.isClosed();
		} catch (SQLException e) {
			return true;
		}
	}
}
