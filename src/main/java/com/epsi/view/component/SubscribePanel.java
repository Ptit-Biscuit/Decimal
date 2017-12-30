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

public class SubscribePanel extends JPanel {
	/**
	 * Le pseudo du joueur.
	 */
	private static JTextField pseudo;

	/**
	 * Le mot de passe du joueur.
	 */
	private static JPasswordField password;

	/**
	 * La confirmation du mot de passe.
	 */
	private static JPasswordField confirm;

	/**
	 * Constructeur.
	 */
	public SubscribePanel() {
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
		confirm.setText("");
	}

	private void initComponents() {
		// init subscribe panel
		JPanel subscribePanel = new JPanel(new GridLayout(3, 1, 10, 10));
		subscribePanel.setPreferredSize(new Dimension(320, 102));
		subscribePanel.setBackground(new Color(150, 150, 255));

		// Init pseudo field
		JLabel pseudoLabel = new JLabel("Pseudo :");
		pseudoLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		pseudoLabel.setHorizontalAlignment(JLabel.CENTER);
		subscribePanel.add(pseudoLabel);

		pseudo = new JTextField(15);
		pseudo.setPreferredSize(new Dimension(Window.WIDTH, 25));
		subscribePanel.add(pseudo);

		// init password field
		JLabel passwordLabel = new JLabel("Mot de passe :");
		passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		passwordLabel.setHorizontalAlignment(JLabel.CENTER);
		subscribePanel.add(passwordLabel);

		password = new JPasswordField(15);
		password.setPreferredSize(new Dimension(Window.WIDTH, 25));
		subscribePanel.add(password);

		// init confirm password field
		JLabel confirmPasswordLabel = new JLabel("Confirmation :");
		confirmPasswordLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		confirmPasswordLabel.setHorizontalAlignment(JLabel.CENTER);
		subscribePanel.add(confirmPasswordLabel);

		confirm = new JPasswordField(15);
		confirm.setPreferredSize(new Dimension(Window.WIDTH, 25));
		subscribePanel.add(confirm);

		this.add(subscribePanel);

		// init start button
		SubscribeButton sub = new SubscribeButton("Go !", pseudo, password, confirm);
		sub.setPreferredSize(new Dimension(Window.WIDTH - 250, 40));
		this.add(sub);

		// init back button
		JButton back = new JButton("Retour");
		back.setPreferredSize(new Dimension(Window.WIDTH - 250, 30));
		back.addActionListener(e -> Window.getInstance().showCard("Start"));
		this.add(back);
	}
}
