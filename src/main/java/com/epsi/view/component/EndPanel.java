package com.epsi.view.component;

import com.epsi.App;
import com.epsi.view.Window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EndPanel extends JPanel {
	/**
	 * Constructeur
	 *
	 * @param score Le score du joueur
	 */
	public EndPanel(int score) {
		JLabel title = new JLabel("Score", SwingConstants.CENTER);
		title.setFont(new Font("Helvetica", Font.PLAIN, 60));
		title.setForeground(new Color(255, 255, 255));
		this.add(title, TOP_ALIGNMENT);

		JLabel scoreLabel = new JLabel(String.valueOf(score), SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Helvetica", Font.PLAIN, 50));
		scoreLabel.setForeground(new Color(255, 255, 255));
		scoreLabel.setPreferredSize(new Dimension(Window.WIDTH, 55));
		this.add(scoreLabel);

		this.initComponents();
		this.setBackground(new Color(140, 165, 255));
	}

	/**
	 * Initialisation des composants.
	 */
	private void initComponents() {
		// init end panel
		JPanel endPanel = new JPanel(new GridLayout(3, 1, 10, 5));
		endPanel.setPreferredSize(new Dimension(320, 142));
		endPanel.setBackground(new Color(140, 165, 255));

		// init replay button
		JButton replay = new JButton("Rejouer ?");
		replay.setFont(new Font("Helvetica", Font.PLAIN, 18));
		replay.setBorderPainted(false);
		replay.setFocusPainted(false);
		replay.setBackground(new Color(255, 255, 255));
		replay.setForeground(new Color(140, 165, 255));
		replay.setPreferredSize(new Dimension(Window.WIDTH - 250, 40));
		replay.addActionListener(e -> Window.getInstance().showMainPanel());
		endPanel.add(replay);

		// init disconnect button
		JButton disconnect = new JButton("Déconnexion");
		disconnect.setFont(new Font("Helvetica", Font.PLAIN, 18));
		disconnect.setBorderPainted(false);
		disconnect.setFocusPainted(false);
		disconnect.setBackground(new Color(255, 255, 255));
		disconnect.setForeground(new Color(140, 165, 255));
		disconnect.setPreferredSize(new Dimension(Window.WIDTH - 250, 30));
		disconnect.addActionListener(e -> App.disconnect());
		endPanel.add(disconnect);

		// init quit button
		JButton quit = new JButton("Quitter");
		quit.setFont(new Font("Helvetica", Font.PLAIN, 18));
		quit.setBorderPainted(false);
		quit.setFocusPainted(false);
		quit.setBackground(new Color(255, 255, 255));
		quit.setForeground(new Color(140, 165, 255));
		quit.setPreferredSize(new Dimension(Window.WIDTH - 250, 30));
		quit.addActionListener(e -> System.exit(0));
		endPanel.add(quit);

		this.add(endPanel);
	}
}
