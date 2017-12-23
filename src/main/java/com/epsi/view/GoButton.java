package com.epsi.view;

import com.epsi.App;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GoButton extends JButton {
	/**
	 * Constructeur.
	 * @param text Le libelle du bouton
	 * @param pseudoTextField Le champs du pseudo
	 * @param passwordField Le champs du mot de passe
	 */
	public GoButton(String text, JTextField pseudoTextField, JPasswordField passwordField) {
		super(text);

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);

				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					doClick();
				}
			}
		});

		this.addActionListener(e -> {
			boolean ok = true;
			String pseudo = pseudoTextField.getText();
			String password = String.valueOf(passwordField.getPassword());

			if (pseudo.isEmpty() || pseudo.length() > 18) {
				pseudoTextField.setBackground(new Color(255, 189, 189));
				pseudoTextField.setToolTipText("Format incorrect (max. 18 caractères)");
				ok = false;
			} else {
				pseudoTextField.setBackground(Color.WHITE);
				pseudoTextField.setToolTipText("");
			}

			if (password.isEmpty()) {
				passwordField.setBackground(new Color(255, 189, 189));
				passwordField.setToolTipText("Mot de passe vide");
				ok = false;
			} else {
				passwordField.setBackground(Color.WHITE);
				passwordField.setToolTipText("");
			}

			if (ok) {
				App.checkLogin(pseudo, password);
			}
		});
	}
}
