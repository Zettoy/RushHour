package Controller;
import java.awt.EventQueue;

import View.MainFrame;

class Main {
	public static void main(String[] args) {
		MainFrame base = new MainFrame();
		EventQueue.invokeLater(() -> {
			base.initialise();
        });
	}
}
