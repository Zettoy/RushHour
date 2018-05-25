package Controller;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseDragDrop implements MouseMotionListener {
	private GameInterface game;
	private GamePanel gamePanel;
	
	private int dx;
	private int dy;
	
	public MouseDragDrop(GameInterface game, GamePanel gamePanel) {
		this.game = game;
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		if (game.getSelectedCar() == 0) return;
		
		Point lastPoint = gamePanel.getMousePoint();
		gamePanel.setMousePoint(event.getPoint());
		dx += (int)(gamePanel.getMousePoint().getX() - lastPoint.getX());
		dy += (int)(gamePanel.getMousePoint().getY() - lastPoint.getY());

		if (dx >= 70) {
			game.moveCar(Constants.RIGHT);
			dx = 0;
		}
		if (dx <= -70) {
			game.moveCar(Constants.LEFT);
			dx = 0;
		}
		if (dy >= 70) {
			game.moveCar(Constants.DOWN);
			dy = 0;
		}
		if (dy <= -70) {
			game.moveCar(Constants.UP);
			dy = 0;
		}
		gamePanel.repaint();
		if (game.isWin()) game.selectCar(0);
	}

	
	@Override
	public void mouseMoved(MouseEvent arg0) {}
}
