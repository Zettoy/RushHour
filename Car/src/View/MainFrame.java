package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	JPanel mainMenu;
	JPanel rules;
	JPanel difficulty;
	JPanel leaderBoard;
	JPanel actualGame;
	JPanel currentPanel;
	
	public MainFrame() {
		mainMenu = new MainMenu(this);
		((MainMenu) mainMenu).startPanel();
		
		currentPanel = mainMenu;
	}
	
	public void changePanel(JPanel panel) {
			this.getContentPane().remove(this.getCurrentPanel());
			this.getContentPane().add(panel, BorderLayout.CENTER);
			this.setCurrentPanel(panel);
			this.setVisible(true);
	}
	
	public void initialise() {
		this.setSize(600, 600);
		this.setLayout(new BorderLayout());
		this.setTitle("Rush Hour");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//intialise main menu
		this.getContentPane().add(mainMenu, BorderLayout.CENTER);
		this.setCurrentPanel(mainMenu);
		this.setVisible(true);
	}
	
	public JPanel getCurrentPanel() {
		return this.currentPanel;
	}
	
	public void setCurrentPanel(JPanel panel) {
		this.currentPanel = panel;
	}
}
