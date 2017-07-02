package com.paigeapp;

import com.paigeapp.gui.MainWindow;

public class Application {
	public static void main(String[] args) {
		try {
			MainWindow frame = new MainWindow();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
