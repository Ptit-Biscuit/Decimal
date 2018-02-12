package com.epsi.adapter;

import com.epsi.view.Window;
import com.epsi.view.component.MainPanel;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;

public class MyKeyAdapter extends KeyAdapter implements MouseListener {
	/**
	 * Le timer.
	 */
	private static Timer timer = new Timer("Timer countdown");

	/**
	 * La tâche associée au timer.
	 */
	private static TimerTask timerTask;

	/**
	 * Action spacebar / enter / left clic.
	 */
	private static boolean isRunning = false;

	@Override
	public void keyPressed(KeyEvent e) {
		// Start / Stop timer
		if (e.getKeyChar() == KeyEvent.VK_SPACE || e.getKeyChar() == KeyEvent.VK_ENTER) {
			this.startAndStop();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			this.startAndStop();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * Start / Stop.
	 */
	private void startAndStop() {
		if (isRunning) {
			timerTask.cancel();
			Window.getInstance().endGame();
		} else {
			initTimerTask();
			timer.scheduleAtFixedRate(timerTask, 0, 1);
		}

		isRunning = !isRunning;
	}

	/**
	 * Reset.
	 */
	public static void reset() {
		isRunning = false;
		timer.cancel();
		timer = new Timer("Timer countdown");
		initTimerTask();

		MainPanel.getInstance().resetCountDown();
		MainPanel.getInstance().setTimerState(true);
		MainPanel.getInstance().setBackground(new Color(145, 255, 164));
	}

	/**
	 * Initialisation de la tâche associée au timer.
	 */
	private static void initTimerTask() {
		timerTask = new TimerTask() {
			@Override
			public void run() {
				MainPanel.getInstance().updateCountDown();

				if (MainPanel.getInstance().getTime() < 0) {
					MainPanel.getInstance().setTimerState(false);
					MainPanel.getInstance().setBackground(new Color(255, 189, 189));
				}

				if (MainPanel.getInstance().getTime() <= -10) {
					timer.cancel();
					Window.getInstance().endGame();
				}
			}
		};
	}
}
