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

public class SubscribePanel extends JPanel {
	/**
	 * Constructeur.
	 */
	public SubscribePanel() {
		JLabel title = new JLabel("Decimal", SwingConstants.CENTER);
		title.setFont(new Font("Helvetica", Font.PLAIN, 90));
		this.add(title, TOP_ALIGNMENT);

		this.initComponents();
		this.setBackground(new Color(150, 150, 255));
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

		JTextField pseudo = new JTextField(15);
		pseudo.setPreferredSize(new Dimension(Window.WIDTH, 25));
		subscribePanel.add(pseudo);

		// init password field
		JLabel passwordLabel = new JLabel("Mot de passe :");
		passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		passwordLabel.setHorizontalAlignment(JLabel.CENTER);
		subscribePanel.add(passwordLabel);

		JPasswordField password = new JPasswordField(15);
		password.setPreferredSize(new Dimension(Window.WIDTH, 25));
		subscribePanel.add(password);

		// init confirm password field
		JLabel confirmPasswordLabel = new JLabel("Confirmation :");
		confirmPasswordLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		confirmPasswordLabel.setHorizontalAlignment(JLabel.CENTER);
		subscribePanel.add(confirmPasswordLabel);

		JPasswordField confirm = new JPasswordField(15);
		confirm.setPreferredSize(new Dimension(Window.WIDTH, 25));
		subscribePanel.add(confirm);

		this.add(subscribePanel);

		// init start button
		SubscribeButton sub = new SubscribeButton("Go !", pseudo, password, confirm);
		sub.setPreferredSize(new Dimension(Window.WIDTH - 250, 50));
		this.add(sub);
	}
}
