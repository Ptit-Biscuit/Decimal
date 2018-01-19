package com.epsi;

import com.epsi.adapter.MyKeyAdapter;
import com.epsi.dao.Dao;
import com.epsi.view.AdminWindow;
import com.epsi.view.Window;
import com.epsi.view.component.LoginPanel;
import com.epsi.view.component.SubscribePanel;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;

/**
 * Classe App
 */
public class App  {
	/**
	 * Le DAO.
	 */
	private static Dao dao;

	/**
	 * Le pseudo du joueur en cours.
	 */
	private static String playerName;

	/**
	 * Main.
	 * @param args Les arguments
	 */
	public static void main(String[] args) {
		System.setProperty("isDev", System.getenv("isDev"));

		if (dao == null || dao.isClosed()) {
			try {
				dao = new Dao();
			} catch (SQLException e) {
				LogManager.getLogger(App.class).fatal("Impossible de se connecter à la BDD", e);
			}
		}

		javax.swing.SwingUtilities.invokeLater(Window::getInstance);
	}

	/**
	 * Ajoute un joueur dans la BDD.
	 *
	 * @param pseudo Le pseudo du joueur
	 * @param password Le mot de passe chiffré
	 */
	public static void addPlayer(String pseudo, String password) {
		if (dao != null && !dao.isClosed()) {
			if (dao.newPlayer(pseudo, password)) {
				playerName = pseudo;
				Window.getInstance().setTitle("Decimal - " + playerName);
				Window.getInstance().showMainPanel();
			} else {
				Window.getInstance().popupInvalidPlayer(pseudo);
			}
		} else {
			Window.getInstance().popupUnreachableDatabase();
			LogManager.getLogger(App.class).warn("Connexion perdue avec la BDD");
		}
	}

	/**
	 * Vérification du login dans la BDD.
	 *
	 * @param pseudo Le pseudo du joueur
	 * @param password Le mot de passe chiffré
	 */
	public static void checkLogin(String pseudo, String password) {
		if (dao != null && !dao.isClosed()) {
			if (dao.validatePlayer(pseudo, password)) {
				playerName = pseudo;
				Window.getInstance().setTitle("Decimal - " + playerName);
				Window.getInstance().showMainPanel();

				if ("true".equals(System.getProperty("isDev"))) {
					javax.swing.SwingUtilities.invokeLater(AdminWindow::getInstance);
				}
			} else {
				Window.getInstance().popupInvalidLogin();
			}
		} else {
			Window.getInstance().popupUnreachableDatabase();
			LogManager.getLogger(App.class).warn("Connexion perdue avec la BDD");
		}
    }

	/**
	 * Fin de la partie : ajout du score dans la BDD.
	 *
	 * @param score Le score du joueur
	 */
	public static void endGame(int score) {
		if (dao != null && !dao.isClosed()) {
			dao.addScore(playerName, score);
		} else {
			Window.getInstance().popupUnreachableDatabase();
			LogManager.getLogger(App.class).warn("Connexion perdue avec la BDD");
		}
	}

	/**
	 * Retour au menu.
	 */
	public static void disconnect() {
		SubscribePanel.clearFields();
		LoginPanel.clearFields();

		MyKeyAdapter.reset();

		Window.getInstance().setTitle("Decimal");
		Window.getInstance().showCard("Start");
		Window.getInstance().revalidate();
	}

	/**
	 * Chiffrage du mot de passe pour la BDD.
	 *
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
	    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
		    LogManager.getLogger(App.class).error("Impossible de chiffrer le mot de passe", e);
	    }

	    return hash.toString();
    }
}
