package Controller;
import java.awt.EventQueue;

import View.MainFrame;

class Main {
	/*
	public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
        	new PuzzleUI().start();
        });
	} */
	
	//THIS IS FOR TESTING CLAIRE'S STUFF. ADD STUFF ABOVE BACK IN LATER

	
	public static void main(String[] args) {
		MainFrame base = new MainFrame();
		EventQueue.invokeLater(() -> {
			base.initialise();
        });
	}
}
