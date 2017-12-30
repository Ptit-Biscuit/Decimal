package com.epsi.view.component;

import com.epsi.App;
import com.epsi.adapter.EnterAdapter;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GoButton extends JButton {
	/**
	 * Constructeur.
	 *
	 * @param text Le libelle du bouton
	 * @param pseudoField Le champs du pseudo
	 * @param passwordField Le champs du mot de passe
	 */
	public GoButton(String text, JTextField pseudoField, JPasswordField passwordField) {
		super(text);

		this.setFont(new Font("Helvetica", Font.ITALIC, 20));
		this.addKeyListener(new EnterAdapter());
		this.addActionListener(e -> {
			boolean okPseudo = true;
			boolean okPassword = true;
			String pseudo = pseudoField.getText();
			String password = String.valueOf(passwordField.getPassword());

			if (pseudo.isEmpty() || pseudo.trim().isEmpty()) {
				pseudoField.setBackground(new Color(255, 189, 189));
				pseudoField.setToolTipText("Pseudo vide");
				okPseudo = false;
			} else if (pseudo.length() > 18) {
				pseudoField.setBackground(new Color(255, 189, 189));
				pseudoField.setToolTipText("Format incorrect (max. 18 caractères)");
				okPseudo = false;
			} else {
				pseudoField.setBackground(Color.WHITE);
				pseudoField.setToolTipText("");
			}

			if (password.isEmpty() || password.trim().isEmpty()) {
				passwordField.setBackground(new Color(255, 189, 189));
				passwordField.setToolTipText("Mot de passe vide");
				okPassword = false;
			} else {
				passwordField.setBackground(Color.WHITE);
				passwordField.setToolTipText("");
			}

			if (okPseudo && okPassword) {
				App.checkLogin(pseudo, App.hashPassword(password));
			}
		});
	}
}
