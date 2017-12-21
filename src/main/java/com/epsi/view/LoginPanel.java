package com.epsi.view;

import com.epsi.App;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPanel extends JPanel {
	/**
	 * Le champs pour le pseudo.
	 */
	private JTextField pseudo;

	/**
	 * Le champs pour le mot de passe.
	 */
	private JPasswordField password;

	/**
	 * Le bouton pour démarrer le jeu.
	 */
	private JButton go;

	/**
	 * Constructeur de la classe LoginPanel
	 * @param panelTitle Le titre du panneau
	 */
	public LoginPanel(final String panelTitle) {
		this.setBackground(new Color(170, 226, 255));

		JLabel title = new JLabel(panelTitle, SwingConstants.CENTER);
		title.setFont(new Font("Helvetica", Font.PLAIN, 90));
		this.add(title, TOP_ALIGNMENT);

		/*ImageIcon icon = null;
		try {
			icon = new ImageIcon(new File("res/giphy.gif").toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		JLabel iconLabel = new JLabel("", icon, SwingConstants.LEADING);
		this.loginPanel.add(iconLabel);*/

		this.initComponents();
	}

	/**
	 * Initialisation des composants.
	 */
	private void initComponents() {
		JPanel pseudoPanel = new JPanel(new GridLayout(2, 1));
		pseudoPanel.setBackground(new Color(170, 226, 255));

		// Init pseudo textfield
		pseudoPanel.add(new JLabel("Pseudo :"));
		this.pseudo = new JTextField(15);
		this.pseudo.setPreferredSize(new Dimension(Window.WIDTH, 25));
		pseudoPanel.add(this.pseudo);

		// init password passwordfield
		pseudoPanel.add(new JLabel("Mot de passe :"));
		this.password = new JPasswordField(15);
		this.password.setPreferredSize(new Dimension(Window.WIDTH, 25));
		pseudoPanel.add(this.password);

		this.add(pseudoPanel);

		// init start button
		this.go = new JButton("Go !");
		this.go.addActionListener(e -> App.checkLogin(this.pseudo.getText(), String.valueOf(this.password.getPassword())));
		this.go.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					go.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		this.add(this.go);
	}
}
