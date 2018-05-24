import javax.swing.JFrame;

@SuppressWarnings("serial")
public class PuzzleUI extends JFrame{
	private GameInterface game;
	private GamePanel gamePanel;
	
	public PuzzleUI() {
		game = new Game();
		gamePanel = new GamePanel(game);
		
		this.setSize(600, 600);
		this.setVisible(true);
		this.setResizable(false);
		this.add(gamePanel);
	}
	
	public void start() {
		game.gameStart();
	}
}
