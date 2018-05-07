import java.awt.Color;

import javax.swing.JFrame;

public class Main {
	public static void main (String[] args) {
		GameInterface newGame = new Game();
		newGame.gameStart();
		
		JFrame f = new JFrame();
		f.setSize(600, 600);
		f.setVisible(true);
		f.setResizable(false);
		
		GamePanel gamePanel = new GamePanel(newGame);
		f.add(gamePanel);
		gamePanel.setLayout(null);
		gamePanel.setBackground(Color.WHITE);
	}
}
