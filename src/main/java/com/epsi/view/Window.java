package com.epsi.view;

import com.epsi.App;
import com.epsi.adapter.EnterAdapter;
import com.epsi.adapter.MyKeyAdapter;
import com.epsi.view.component.EndPanel;
import com.epsi.view.component.LoginPanel;
import com.epsi.view.component.MainPanel;
import com.epsi.view.component.SubscribePanel;

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
* @version 1.4
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
	public static final int HEIGHT = 360;

	/**
	* L'unique instance possible de la classe Window (singleton).
	*/
	private static Window window = new Window("Decimal");

	/**
	 * Le contenu affiché.
	 */
	private JPanel content;

	/**
	* Constructeur.
	 *
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
		JPanel startPanel = new JPanel();
		startPanel.setBackground(new Color(140, 165, 255));

		JLabel title = new JLabel("Decimal", SwingConstants.CENTER);
		title.setForeground(new Color(255, 255, 255));

		title.setFont(new Font("Helvetica", Font.PLAIN, 90));
		startPanel.add(title, TOP_ALIGNMENT);

		JButton connection = new JButton("Connexion");
		connection.setFont(new Font("Helvetica", Font.PLAIN, 18));
		connection.setBorderPainted(false);
		connection.setFocusPainted(false);
		connection.setBackground(new Color(255, 255, 255));
		connection.setForeground(new Color(140, 165, 255));
		connection.setPreferredSize(new Dimension(WIDTH - 250, 40));
		connection.addKeyListener(new EnterAdapter());
		connection.addActionListener(e -> this.showCard("Connection"));
		startPanel.add(connection);

		JButton subscribe = new JButton("Inscription");
		subscribe.setFont(new Font("Helvetica", Font.PLAIN, 18));
		subscribe.setBorderPainted(false);
		subscribe.setFocusPainted(false);
		subscribe.setBackground(new Color(255, 255, 255));
		subscribe.setForeground(new Color(140, 165, 255));
		subscribe.setPreferredSize(new Dimension(WIDTH - 250, 40));
		subscribe.addKeyListener(new EnterAdapter());
		subscribe.addActionListener(e -> this.showCard("Subscribe"));
		startPanel.add(subscribe);

		this.content = new JPanel(new CardLayout());
		this.content.add(startPanel, "Start");
		this.content.add(new SubscribePanel(), "Subscribe");
		this.content.add(new LoginPanel(), "Connection");
		this.content.add(MainPanel.getInstance(), "Main");
		this.setContentPane(this.content);
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

		this.addKeyListener(new MyKeyAdapter());

		this.pack();
		this.setVisible(true);
	}

	/**
	 * Change le panneau affiché.
	 *
	 * @param cardName Le nom du panneau
	 */
	public void showCard(String cardName) {
		((CardLayout) this.content.getLayout()).show(this.content, cardName);
	}

	/**
	 * Passage au panneau principal.
	 */
	public void showMainPanel() {
		MyKeyAdapter.reset();
		this.showCard("Main");
		this.revalidate();
		this.requestFocus();
	}

	/**
	 * Fin de partie.
	 */
	public void endGame() {
		int score = Math.round((10f + MainPanel.getInstance().getTime()) * 1000);

		if (MainPanel.getInstance().getTimerState()) {
			score = Math.round((10f - MainPanel.getInstance().getTime()) * 1000);
		}

		this.content.add(new EndPanel(score), "End");
		this.showCard("End");
		App.endGame(score);
	}

	/**
	 * Popup si la BDD est inaccessible.
	 */
	public void popupUnreachableDatabase() {
		JOptionPane.showMessageDialog(
				this,
				"Connexion perdue avec la BDD",
				"BDD inaccessible",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Popup si le joueur existe déjà dans la BDD.
	 *
	 * @param pseudo Le pseudo déjà existant
	 */
	public void popupInvalidPlayer(String pseudo) {
		JOptionPane.showMessageDialog(
				this,
				"Le pseudo '" + pseudo + "' existe déjà",
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
	 *
	* @return La seule instance de Window
	*/
	public static Window getInstance() {
		return window;
	}
}
