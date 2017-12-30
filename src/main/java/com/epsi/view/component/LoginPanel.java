package com.epsi.view.component;

import com.epsi.view.Window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPanel extends JPanel {
	/**
	 * Le pseudo du joueur.
	 */
	private static JTextField pseudo;

	/**
	 * Le mot de passe du joueur.
	 */
	private static JPasswordField password;

	/**
	 * Constructeur
	 */
	public LoginPanel() {
		JLabel title = new JLabel("Decimal", SwingConstants.CENTER);
		title.setFont(new Font("Helvetica", Font.PLAIN, 90));
		this.add(title, TOP_ALIGNMENT);

		this.initComponents();
		clearFields();
		this.setBackground(new Color(150, 150, 255));
	}

	/**
	 * Initialisation des champs de texte.
	 */
	public static void clearFields() {
		pseudo.setText("");
		password.setText("");
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

		pseudo = new JTextField(15);
		pseudo.setPreferredSize(new Dimension(Window.WIDTH, 25));
		connectionPanel.add(pseudo);

		// init password field
		JLabel passwordLabel = new JLabel("Mot de passe :");
		passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		passwordLabel.setHorizontalAlignment(JLabel.CENTER);
		connectionPanel.add(passwordLabel);

		password = new JPasswordField(15);
		password.setPreferredSize(new Dimension(Window.WIDTH, 25));
		connectionPanel.add(password);

		this.add(connectionPanel);

		// init start button
		GoButton go = new GoButton("Go !", pseudo, password);
		go.setPreferredSize(new Dimension(Window.WIDTH - 250, 40));
		this.add(go);

		// init back button
		JButton back = new JButton("Retour");
		back.setPreferredSize(new Dimension(Window.WIDTH - 250, 30));
		back.addActionListener(e -> Window.getInstance().showCard("Start"));
		this.add(back);
	}
}
