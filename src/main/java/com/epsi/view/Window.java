package com.epsi.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;

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
	 * Le timer du thread.
	 */
	private Timer threadTimer;

	/**
	* Le panneau principal.
	*/
	private JPanel mainPanel;

	/**
	 * Le timer du jeu.
	 */
	private float timer;

	/**
	 * Fin du jeu.
	 */
	private boolean noClick;

	/**
	* Constructeur de la classe Window.
	*
	* @param title Le titre de la fenêtre
	*/
	private Window(final String title) {
		super(title);
		this.initComponents();
		this.initFrame();

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);

				if (e.getKeyChar() == KeyEvent.VK_SPACE)
					noClick = false;
			}
		});
	}

	/**
	* Initialisation des composants de la fenêtre.
	*/
	private void initComponents() {
		this.mainPanel = new JPanel(new BorderLayout());
		this.timer = new Random().nextInt(61);

		this.threadTimer = new Timer();
		noClick = true;

		JLabel countDown = new JLabel(String.valueOf(this.timer));
		countDown.setHorizontalAlignment(SwingConstants.CENTER);
		countDown.setFont(new Font("Helvetica", Font.PLAIN, 90));



		JButton go = new JButton("GO !");

		this.mainPanel.add(countDown, BorderLayout.CENTER);
		this.mainPanel.add(go, BorderLayout.SOUTH);
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
