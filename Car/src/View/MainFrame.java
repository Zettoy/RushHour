package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Game;
import Controller.GameInterface;
import Model.GameButtonListener;
import Model.MultiButtonListener;

/**
* code to manage the visuals/frames of all panels that will be used in entire program
*/
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
	
	/**
	* constructor
	* creates all panels and initialises them by storing in a variable in this object
	*/
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
		MultiButtonListener multiButtonListener = new MultiButtonListener(game,game2, multiGame);
		multiButtonListener.setMainFrame(this);
		currentPanel = mainMenu;
	}
	
	/**
	* changes panels from one panel/screen to another
	* @param panel the panel that you wish to switch to
	* @pre assumes valid panel is passed through
	*/
	public void changePanel(JPanel panel) {
			this.setContentPane(panel);
			this.invalidate();
			this.validate();
	}
	
	/**
	* initialises frame that will contain all panels in the program
	*/
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

	/**
	* @return returns game
	*/
	public GameInterface getGame() {
		return game;
	}
	
	/**
	* @return returns game2
	*/
	public GameInterface getGame2() {
		return game2;
	}
	
	/**
	* @return returns current panel that is being displayed
	*/
	public JPanel getCurrentPanel() {
		return this.currentPanel;
	}
	
	/**
	* is passed through a new panel to replace the current panel that is being displayed
	* @param panel the panel to be set
	*/
	public void setCurrentPanel(JPanel panel) {
		this.currentPanel = panel;
	}
	
	/**
	* @return return the rules panel
	*/
	public JPanel getRules() {
		return rules;
	}
	
	/**
	* @return returns the difficulty selection panel
	*/
	public JPanel getDifficulty() {
		return difficulty;
	}
	
	/**
	* @return returns the leaderboard panel
	*/
	public JPanel getLeaderBoard() {
		leaderBoard = new ViewLeaderBoard(this);
		((ViewLeaderBoard) leaderBoard).startPanel();
		return leaderBoard;
	}
	
	/**
	* @return returns the gamep/map panel
	*/
	public JPanel getActualGame() {
		return actualGame;
	}
	
	/**
	* @return returns the multiplayer panel
	*/
	public JPanel getMultiGame() {
		return multiGame;
	}

	/**
	* @return returns the main menu panel
	*/
	public JPanel getMainMenu() {
		return mainMenu;
	}

	/**
	* resets the game panel in the case of restarting a game
	*/
	public void restartGame() {
		gamePanel = new GamePanel(game);
		actualGame = gamePanel;
	}
}
