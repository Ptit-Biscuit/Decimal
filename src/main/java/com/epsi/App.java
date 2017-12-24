package com.epsi;

import com.epsi.dao.Dao;
import com.epsi.view.Window;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App  {
	/**
	 * Le DAO.
	 */
	private static Dao dao;

	public static void main(String[] args) {
		if (dao == null || dao.isClosed()) {
			try {
				dao = new Dao();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		javax.swing.SwingUtilities.invokeLater(Window::getInstance);
	}

	/**
	 * Vérification du login dans la BDD.
	 * @param pseudo Le pseudo du joueur
	 * @param password Le mot de passe chiffré
	 */
	public static void checkLogin(String pseudo, String password) {
		if (!dao.isClosed()) {
			if (dao.validatePlayer(pseudo, password)) {
				Window.getInstance().nextPanel();
			} else {
				Window.getInstance().popupInvalidLogin();
			}
		} else {
			System.out.println("Connexion perdue avec la BDD");
		}
    }

	/**
	 * Ajoute un joueur dans la BDD.
	 * @param pseudo Le pseudo du joueur
	 * @param password Le mot de passe chiffré
	 */
	public static void addPlayer(String pseudo, String password) {
		if (!dao.isClosed()) {
			if (dao.newPlayer(pseudo, password)) {
				Window.getInstance().nextPanel();
			} else {
				Window.getInstance().popupInvalidPlayer();
			}
		} else {
			System.out.println("Connexion perdue avec la BDD");
		}
    }

	/**
	 * Chiffrage du mot de passe pour la BDD.
	 * @param password Le mot de passe
	 * @return Le mot de passe chiffré
	 */
	public static String hashPassword(String password) {
	    StringBuilder hash = new StringBuilder();

	    try {
		    String salt = password + "LeagueOfLegend";
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(salt.getBytes("UTF-8"));
		    byte[] bytes = md.digest();

		    for (byte b : bytes) {
			    hash.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		    }
	    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
		    e1.printStackTrace();
	    }

	    return hash.toString();
    }
}
