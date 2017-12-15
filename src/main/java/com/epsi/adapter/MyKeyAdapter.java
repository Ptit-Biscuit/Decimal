package com.epsi.adapter;

import com.epsi.view.Window;

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

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);

		if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			if (this.isRunning) {
				this.timerTask.cancel();
			} else {
				initTimerTask();
				this.timer.schedule(this.timerTask, 0, 1);
			}

			this.isRunning = !this.isRunning;
		}

		if (e.getKeyChar() == 'r') {
			this.timer.cancel();
			this.timer = new Timer();
			initTimerTask();

			Window.getWindow().resetCountDown();
			Window.getWindow().setMainPanelBackground(new Color(145, 255, 164));

			isRunning = false;
		}
	}

	/**
	 * Initialisation de la tâche associée au timer.
	 */
	private void initTimerTask() {
		this.timerTask = new TimerTask() {
			@Override
			public void run() {
				Window.getWindow().updateCountDown();

				if (Window.getWindow().getTime() < 0) {
					Window.getWindow().setTimerState(false);
					Window.getWindow().setMainPanelBackground(new Color(255, 189, 189));
				}
			}
		};
	}
}
