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
		int carId = 0;
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			direction = Constants.UP;
			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			direction = Constants.DOWN;
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			direction = Constants.LEFT;
			
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			direction = Constants.RIGHT;
			
		} else if (e.getKeyCode() >= KeyEvent.VK_A &&
				   e.getKeyCode() <= KeyEvent.VK_Z) {
			carId = e.getKeyCode() - KeyEvent.VK_A + 1;
			
		} else {
			throw new IllegalStateException();
		}
		
		if (carId == 0) {
			game.moveCar(direction);
		} else {
			game.selectCar(carId);
		}
		gamePanel.repaint();
		
		if (game.isWin()) {
			gamePanel.setFocusable(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
