package com.epsi.view.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainPanel extends JPanel {
	/**
	 * L'unique instance possible de la classe MainPanel (singleton).
	 */
	private static MainPanel mainPanel = new MainPanel();

	/**
	 * Le label du timer
	 */
	private JLabel countDown;

	/**
	 * Le temps du jeu.
	 */
	private float time = 0.0f;

	/**
	 * L'état du temps (false si négatif).
	 */
	private boolean timerState = true;

	/**
	 * Constructeur.
	 */
	private MainPanel() {
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(145, 255, 164));

		this.initComponents();
	}

	/**
	 * Initialisation des composants.
	 */
	private void initComponents() {
		this.countDown = new JLabel();
		this.resetCountDown();

		this.countDown.setHorizontalAlignment(SwingConstants.CENTER);
		this.countDown.setFont(new Font("Helvetica", Font.PLAIN, 150));
		this.countDown.setForeground(new Color(255, 255, 255));

		this.add(this.countDown, BorderLayout.CENTER);
	}

	/**
	 * Update le countdown.
	 */
	public void updateCountDown() {
		this.time -= 0.001f;
		this.countDown.setText(String.format("%.3f", this.time));
	}

	/**
	 * Reset le countdown.
	 */
	public void resetCountDown() {
		this.time = 1.0f + new Random().nextFloat() * 4;
		this.countDown.setText(String.format("%.3f", this.time));
	}

	/**
	 * Getter du temps.
	 *
	 * @return Le temps
	 */
	public float getTime() {
		return Float.valueOf(this.countDown.getText().replace(',', '.'));
	}

	/**
	 * Getter de l'état du temps.
	 *
	 * @return True si le temps est positif, false sinon
	 */
	public boolean getTimerState() {
		return this.timerState;
	}

	/**
	 * Setter de l'état du temps.
	 *
	 * @param timerState Le nouvel état du temps
	 */
	public void setTimerState(boolean timerState) {
		this.timerState = timerState;
	}

	/**
	 * Méthode complémentaire au singleton: Getter de la seule instance de MainPanel.
	 *
	 * @return La seule instance de MainPanel
	 */
	public static MainPanel getInstance() {
		return mainPanel;
	}
}
