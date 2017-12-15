package com.epsi.view;

import com.epsi.adapter.MyKeyAdapter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

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
	* Constructeur de la classe Window.
	*
	* @param title Le titre de la fenêtre
	*/
	private Window(final String title) {
		super(title);

		System.out.println(System.getenv("isDev"));

		this.initComponents();
		this.initFrame();

		this.addKeyListener(new MyKeyAdapter());
	}

	/**
	* Initialisation des composants de la fenêtre.
	 */
	public void initComponents() {
		this.mainPanel = new JPanel(new BorderLayout());
		this.mainPanel.setBackground(new Color(145, 255, 164));

		this.countDown = new JLabel();
		this.resetCountDown();

		this.countDown.setHorizontalAlignment(SwingConstants.CENTER);
		this.countDown.setFont(new Font("Helvetica", Font.PLAIN, 150));
		this.countDown.setForeground(new Color(255, 255, 255));

		this.mainPanel.add(this.countDown, BorderLayout.CENTER);
	}

	/**
	 * Initialisation de la fenêtre.
	 */
	public void initFrame() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setResizable(false);

		Point centreEcran = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		this.setLocation((int) centreEcran.getX() - (WIDTH / 2),
				(int) centreEcran.getY() - (HEIGHT / 2));

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

	/**
	 * Setter de la couleur du panneau principal.
	 *
	 * @param mainPanelBackground La nouvelle couleur du panneau principal
	 */
	public void setMainPanelBackground(Color mainPanelBackground) {
		this.mainPanel.setBackground(mainPanelBackground);
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
		return this.time;
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
}
