package com.epsi.view;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;
import java.net.MalformedURLException;
import javax.swing.*;

public class AdminWindow extends JFrame {
	/**
	 * L'unique instance possible de la classe AdminWindow (singleton).
	 */
	private static AdminWindow adminWindow = new AdminWindow("Décimal - admin");

	/**
	 * Constructeur.
	 */
	private AdminWindow(String title) {
		super(title);

		this.initComponents();
		this.initFrame();
	}

	/**
	 * Initialisation des composants.
	 */
	private void initComponents() {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new File("res/giphy.gif").toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		JLabel iconLabel = new JLabel("", icon, SwingConstants.CENTER);
		iconLabel.setPreferredSize(new Dimension(Window.WIDTH, icon.getIconHeight()));
		this.add(iconLabel);
	}

	/**
	 * Initialisation de la fenêtre.
	 */
	private void initFrame() {
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		this.setLocationByPlatform(true);
		this.setFocusableWindowState(false);

		this.pack();
		this.setVisible(true);
	}

	/**
	 * Méthode complémentaire au singleton: Getter de la seule instance de AdminWindow.
	 *
	 * @return La seule instance de AdminWindow
	 */
	public static AdminWindow getInstance() {
		return adminWindow;
	}
}
