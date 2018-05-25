package Controller;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MousePressMulti implements MouseListener{
	private GameInterface game2;
	private MultiPanel multiPanel;
	
	public MousePressMulti(GameInterface game2, MultiPanel multiPanel) {
		this.game2 = game2;
		this.multiPanel = multiPanel;
	}

	@Override
	public void mousePressed(MouseEvent event) {
		Point mousePoint = event.getPoint();
		multiPanel.setMousePoint(mousePoint);
		
		for (CarComponent c : multiPanel.getCars()) {
			if ((int) mousePoint.getX() >= c.getStartX() &&
				(int) mousePoint.getY() >= c.getStartY() &&
				(int) mousePoint.getX() <= c.getEndX() &&
				(int) mousePoint.getY() <= c.getEndY()) {
				game2.selectCar(c.getCarId());
				break;
			}
		}
		multiPanel.repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		game2.selectCar(0);
		multiPanel.repaint();		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

}
