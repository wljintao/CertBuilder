package org.tao.certbuilder.main;

import org.tao.certbuilder.window.MainWindow;

public class Main {
	public static void main(String[] args) {
		try {
			MainWindow main = new MainWindow();
			main.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
