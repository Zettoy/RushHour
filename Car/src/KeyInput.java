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
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.gameRestart();
		} else if (!game.isWin())
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				game.moveCar(Constants.UP);

			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				game.moveCar(Constants.DOWN);

			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				game.moveCar(Constants.LEFT);

			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				game.moveCar(Constants.RIGHT);

			} else if (e.getKeyCode() >= KeyEvent.VK_A &&
					   e.getKeyCode() <= KeyEvent.VK_Z) {
				int carId = e.getKeyCode() - KeyEvent.VK_A + 1;
				game.selectCar(carId);

			} else {
				throw new IllegalStateException();
			}
		
		gamePanel.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
