package Controller;
import java.awt.EventQueue;

import View.MainFrame;

/**
* main functino to initialise entire program
* starts off by intialising main menu screen
*/
class Main {
	public static void main(String[] args) {
		MainFrame base = new MainFrame();
		EventQueue.invokeLater(() -> {
			base.initialise();
        });
	}
}
