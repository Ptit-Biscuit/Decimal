package com.epsi.adapter;

import com.epsi.view.MainPanel;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class MyKeyAdapter extends KeyAdapter {
	/**
	 * Le timer.
	 */
	private Timer timer = new Timer("Timer countdown");

	/**
	 * La tâche associée au timer.
	 */
	private TimerTask timerTask;

	/**
	 * Action spacebar.
	 */
	private boolean isRunning = false;

	/**
	 * Constructeur.
	 */
	public MyKeyAdapter() {}

	@Override
	public void keyPressed(KeyEvent e) {
		// Start / Stop timer
		if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			if (this.isRunning) {
				this.timerTask.cancel();
			} else {
				initTimerTask();
				this.timer.schedule(this.timerTask, 0, 1);
			}

			this.isRunning = !this.isRunning;
		}

		// reset timer
		if (e.getKeyChar() == 'r' && !isRunning) {
			this.timer.cancel();
			this.timer = new Timer();
			initTimerTask();

			MainPanel.getInstance().resetCountDown();
			MainPanel.getInstance().setBackground(new Color(145, 255, 164));
		}
	}

	/**
	 * Initialisation de la tâche associée au timer.
	 */
	private void initTimerTask() {
		this.timerTask = new TimerTask() {
			@Override
			public void run() {
				MainPanel.getInstance().updateCountDown();

				if (MainPanel.getInstance().getTime() < 0) {
					MainPanel.getInstance().setTimerState(false);
					MainPanel.getInstance().setBackground(new Color(255, 189, 189));
				}
			}
		};
	}
}
