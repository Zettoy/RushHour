import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	private GameInterface game;
	private GamePanel gamePanel;
	
	public KeyInput (GameInterface game, GamePanel gamePanel) {
		this.game = game;
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int direction = 0;
		
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				direction = Constants.UP;
				break;
			case KeyEvent.VK_DOWN:
				direction = Constants.DOWN;
				break;
			case KeyEvent.VK_LEFT:
				direction = Constants.LEFT;
				break;
			case KeyEvent.VK_RIGHT:
				direction = Constants.RIGHT;
				break;
		}
		
		game.getMap().moveCar(Constants.RED, direction);
		gamePanel.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
