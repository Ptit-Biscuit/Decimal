package com.epsi.View;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
* Created by Ptit-Biscuit on 15/12/2017.
*
* @version 1.0
* @since 1.0
*/
public final class Window extends JFrame {
	/**
	 * La longueur de la fenêtre.
	 */
	public static final int WIDTH = 500;

	/**
	 * La hauteur de la fenêtre.
	 */
	public static final int HEIGHT = 350;

	/**
	* La seule instance possible de la classe Window (singleton).
	*/
	private static final Window WINDOW = new Window("Decimal game");

	/**
	* Le panneau principal.
	*/
	private JPanel mainPanel;

	/**
	 * Le label représentant le chrono.
	 */
	private JLabel countDown;

	/**
	* Constructeur de la classe Window.
	*
	* @param title Le titre de la fenêtre
	*/
	private Window(final String title) {
		super(title);
		this.initComponents();
		this.initFrame();
	}

	/**
	* Initialisation des composants de la fenêtre.
	*/
	private void initComponents() {
		this.mainPanel = new JPanel(new BorderLayout());

		Random r =new Random();
		float timer = r.nextInt(61);

		this.countDown = new JLabel(String.valueOf(timer));
		this.countDown.setHorizontalAlignment(SwingConstants.CENTER);
		this.countDown.setFont(new Font("Helvetica", Font.PLAIN, 90));

		this.mainPanel.add(this.countDown, BorderLayout.CENTER);
	}

	/**
	* Initialisation de la fenêtre.
	*/
	private void initFrame() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setResizable(false);

		Point centreEcran = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		this.setLocation((int) centreEcran.getX() - (this.getPreferredSize().width / 2),
		(int) centreEcran.getY() - (this.getPreferredSize().height / 2));

		this.setContentPane(this.mainPanel);
		this.pack();
		this.setVisible(true);
	}

	/**
	* Méthode complémentaire au singleton: Getter de la seule instance de Window.
	*
	* @return La seule instance de Window
	*/
	public static Window getWindow() {
		return WINDOW;
	}

	/**
	* Termine la classe Window en libérant tous les objets utilisés.
	*/
	public void close() {
		this.dispose();
	}
}
