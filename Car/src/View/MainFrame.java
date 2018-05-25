package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Game;
import Controller.GameInterface;
import Controller.GamePanel;
import Controller.MultiPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JPanel mainMenu;
	private JPanel rules;
	private JPanel difficulty;
	private JPanel leaderBoard;
	private JPanel actualGame;
	private JPanel currentPanel;
	
	private GameInterface game;
	private GameInterface game2;
	private GamePanel gamePanel;
	private MultiPanel multiGame;
	
	public MainFrame() {
		this.setResizable(false);
		mainMenu = new MainMenu(this);
		((MainMenu) mainMenu).startPanel();
		rules = new Rules(this);
		((Rules) rules).startPanel();
		difficulty = new selectDifficulty(this);
		((selectDifficulty) difficulty).startPanel();
		leaderBoard = new ViewLeaderBoard(this);
		((ViewLeaderBoard) leaderBoard).startPanel();
		//SET ACTUAL GAME
		
		game = new Game();
		game2 = new Game();
		gamePanel = new GamePanel(game);
		multiGame = new MultiPanel(game, game2);
		actualGame = gamePanel;
		game.setPanel(gamePanel);
		GameButtonListener gameButtonListener = new GameButtonListener(game, gamePanel);
		gameButtonListener.setMainFrame(this);
		currentPanel = mainMenu;
	}
	
	public void changePanel(JPanel panel) {
			this.setContentPane(panel);
			this.invalidate();
			this.validate();
	}
	
	public void initialise() {
		this.setSize(600, 600);
		//this.setLayout(new BorderLayout());
		this.setTitle("Rush Hour");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Initialize main menu
		this.getContentPane().add(mainMenu, BorderLayout.CENTER);
		this.setCurrentPanel(mainMenu);
		this.setVisible(true);
		this.setResizable(true);
	}
	public GameInterface getGame() {
		return game;
	}
	
	public GameInterface getGame2() {
		return game2;
	}
	
	public JPanel getCurrentPanel() {
		return this.currentPanel;
	}
	
	public void setCurrentPanel(JPanel panel) {
		this.currentPanel = panel;
	}
	
	public JPanel getRules() {
		return rules;
	}
	
	public JPanel getDifficulty() {
		return difficulty;
	}
	
	public JPanel getLeaderBoard() {
		leaderBoard = new ViewLeaderBoard(this);
		((ViewLeaderBoard) leaderBoard).startPanel();
		return leaderBoard;
	}
	
	public JPanel getActualGame() {
		return actualGame;
	}
	
	public JPanel getMultiGame() {
		return multiGame;
	}

	public JPanel getMainMenu() {
		return mainMenu;
	}

	public void restartGame() {
		gamePanel = new GamePanel(game);
		actualGame = gamePanel;
	}
}
