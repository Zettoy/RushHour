package Controller;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseDragDropMulti implements MouseMotionListener {
	private GameInterface game2;
	private MultiPanel multiPanel;
	
	private int dx;
	private int dy;
	public MouseDragDropMulti(GameInterface game2, MultiPanel multiPanel) {
		this.game2 = game2;
		this.multiPanel = multiPanel;
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		if (game2.getSelectedCar() == 0) return;
		
		Point lastPoint = multiPanel.getMousePoint();
		multiPanel.setMousePoint(event.getPoint());
		dx += (int)(multiPanel.getMousePoint().getX() - lastPoint.getX());
		dy += (int)(multiPanel.getMousePoint().getY() - lastPoint.getY());

		if (dx >= 70) {
			game2.moveCar(Constants.RIGHT);
			dx = 0;
		}
		if (dx <= -70) {
			game2.moveCar(Constants.LEFT);
			dx = 0;
		}
		if (dy >= 70) {
			game2.moveCar(Constants.DOWN);
			dy = 0;
		}
		if (dy <= -70) {
			game2.moveCar(Constants.UP);
			dy = 0;
		}
		multiPanel.repaint();
		if (game2.isWin()) game2.selectCar(0);

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

}
