package com.epsi.view;

import com.epsi.adapter.MyKeyAdapter;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
* Created by Ptit-Biscuit on 15/12/2017.
*
* @version 1.2
* @since 1.0
*/
public class Window extends JFrame {
	/**
	 * La longueur de la fenêtre.
	 */
	public static final int WIDTH = 500;

	/**
	 * La hauteur de la fenêtre.
	 */
	public static final int HEIGHT = 350;

	/**
	* L'unique instance possible de la classe Window (singleton).
	*/
	private static final Window WINDOW = new Window("Decimal");

	/**
	 * Le panneau de login.
	 */
	private LoginPanel loginPanel;

	/**
	* Constructeur de la classe Window.
	*
	* @param title Le titre de la fenêtre
	*/
	private Window(final String title) {
		super(title);

		this.initComponents();
		this.initFrame();

		this.addKeyListener(new MyKeyAdapter());
	}

	/**
	* Initialisation des composants de la fenêtre.
	 */
	private void initComponents() {
		// init loginPanel
		this.loginPanel = new LoginPanel("Decimal");
	}

	/**
	 * Initialisation de la fenêtre.
	 */
	private void initFrame() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setResizable(false);

		Point centreEcran = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		this.setLocation((int) centreEcran.getX() - (WIDTH / 2),
				(int) centreEcran.getY() - (HEIGHT / 2));

		this.setContentPane(this.loginPanel);

		this.pack();
		this.setVisible(true);
	}

	/**
	 * Passage au panneau principal.
	 */
	public void nextPanel() {
		this.setContentPane(MainPanel.getInstance());
		this.revalidate();
		this.requestFocus();
	}

	/**
	* Méthode complémentaire au singleton: Getter de la seule instance de Window.
	*
	* @return La seule instance de Window
	*/
	public static Window getInstance() {
		return WINDOW;
	}
}
