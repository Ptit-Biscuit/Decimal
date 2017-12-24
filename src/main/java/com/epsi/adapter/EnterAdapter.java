package com.epsi.adapter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

public class EnterAdapter extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			((JButton) e.getComponent()).doClick();
		}
	}
}
