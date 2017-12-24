package com.epsi.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPanel extends JPanel {
	/**
	 * Constructeur
	 */
	public LoginPanel() {
		JLabel title = new JLabel("Decimal", SwingConstants.CENTER);
		title.setFont(new Font("Helvetica", Font.PLAIN, 90));
		this.add(title, TOP_ALIGNMENT);

		/*ImageIcon icon = null;
		try {
			icon = new ImageIcon(new File("res/giphy.gif").toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		JLabel iconLabel = new JLabel("", icon, SwingConstants.LEADING);
		this.add(iconLabel);*/

		this.initComponents();
		this.setBackground(new Color(150, 150, 255));
	}

	/**
	 * Initialisation des composants.
	 */
	private void initComponents() {
		// init connection panel
		JPanel connectionPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		connectionPanel.setPreferredSize(new Dimension(320, 62));
		connectionPanel.setBackground(new Color(150, 150, 255));

		// Init pseudo field
		JLabel pseudoLabel = new JLabel("Pseudo :");
		pseudoLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		pseudoLabel.setHorizontalAlignment(JLabel.CENTER);
		connectionPanel.add(pseudoLabel);

		JTextField pseudo = new JTextField(15);
		pseudo.setPreferredSize(new Dimension(Window.WIDTH, 25));
		connectionPanel.add(pseudo);

		// init password field
		JLabel passwordLabel = new JLabel("Mot de passe :");
		passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		passwordLabel.setHorizontalAlignment(JLabel.CENTER);
		connectionPanel.add(passwordLabel);

		JPasswordField password = new JPasswordField(15);
		password.setPreferredSize(new Dimension(Window.WIDTH, 25));
		connectionPanel.add(password);

		this.add(connectionPanel);

		// init start button
		GoButton go = new GoButton("Go !", pseudo, password);
		go.setPreferredSize(new Dimension(Window.WIDTH - 250, 50));
		this.add(go);
	}
}
