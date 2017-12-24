package com.epsi.view;

import com.epsi.adapter.EnterAdapter;
import com.epsi.adapter.MyKeyAdapter;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
* Created by Ptit-Biscuit on 15/12/2017.
*
* @version 1.3
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
	 * Le contenu affiché.
	 */
	private JPanel content;

	/**
	 * Le panneau de départ.
	 */
	private JPanel startPanel;

	/**
	* Constructeur.
	* @param title Le titre de la fenêtre
	*/
	private Window(String title) {
		super(title);

		this.initComponents();
		this.initFrame();
	}

	/**
	 * Initialisation des composants.
	 */
	private void initComponents() {
		this.startPanel = new JPanel();
		this.startPanel.setBackground(new Color(150, 150, 255));

		JLabel title = new JLabel("Decimal", SwingConstants.CENTER);
		title.setFont(new Font("Helvetica", Font.PLAIN, 90));
		this.startPanel.add(title, TOP_ALIGNMENT);

		JButton connection = new JButton("Connexion");
		connection.setPreferredSize(new Dimension(WIDTH - 250, 50));
		connection.addKeyListener(new EnterAdapter());
		connection.addActionListener(e -> this.showCard("Connection"));
		this.startPanel.add(connection);

		JButton subscribe = new JButton("Inscription");
		subscribe.setPreferredSize(new Dimension(WIDTH - 250, 50));
		subscribe.addKeyListener(new EnterAdapter());
		subscribe.addActionListener(e -> this.showCard("Subscribe"));
		this.startPanel.add(subscribe);
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

		this.content = new JPanel(new CardLayout());
		this.content.add(this.startPanel, "Start");
		this.content.add(new SubscribePanel(), "Subscribe");
		this.content.add(new LoginPanel(), "Connection");
		this.setContentPane(this.content);

		this.addKeyListener(new MyKeyAdapter());

		this.pack();
		this.setVisible(true);
	}

	/**
	 * Change le panneau affiché.
	 * @param cardName Le nom du panneau
	 */
	private void showCard(String cardName) {
		((CardLayout) this.content.getLayout()).show(this.content, cardName);
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
	 * Popup si le joueur existe déjà dans la BDD.
	 */
	public void popupInvalidPlayer() {
		JOptionPane.showMessageDialog(
				this,
				"Le pseudo existe déjà",
				"Pseudo incorrect",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Popup si le pseudo / mot de passe est incorrect.
	 */
	public void popupInvalidLogin() {
		JOptionPane.showMessageDialog(
				this,
				"Le pseudo / mot de passe est incorrect",
				"Login incorrect",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	* Méthode complémentaire au singleton: Getter de la seule instance de Window.
	* @return La seule instance de Window
	*/
	public static Window getInstance() {
		return WINDOW;
	}
}
