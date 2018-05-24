import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MousePress implements MouseListener{
	private GameInterface game;
	private GamePanel gamePanel;
	
	public MousePress(GameInterface game, GamePanel gamePanel) {
		this.game = game;
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		Point mousePoint = event.getPoint();
		gamePanel.setMousePoint(mousePoint);
		
		for (CarComponent c : gamePanel.getCars()) {
			if ((int) mousePoint.getX() >= c.getStartX() &&
				(int) mousePoint.getY() >= c.getStartY() &&
				(int) mousePoint.getX() <= c.getEndX() &&
				(int) mousePoint.getY() <= c.getEndY()) {
				game.selectCar(c.getCarId());
				break;
			}
		}
		gamePanel.repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		game.selectCar(0);
		gamePanel.repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

}
