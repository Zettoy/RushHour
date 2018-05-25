package Controller;
import View.MultiPanel;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
* code to register when a user clicks (especially relevant if car is clicked) in multiplayer
*/
public class MousePressMulti implements MouseListener{
	private GameInterface game2;
	private MultiPanel multiPanel;
	
	/**
	* constructor
	* @param game current version of game being run
	* @param gamePanel visuals of the game for player that will use mouse
	*/
	public MousePressMulti(GameInterface game2, MultiPanel multiPanel) {
		this.game2 = game2;
		this.multiPanel = multiPanel;
	}

	/**
	* function to register clicking of car of player using mouse and change graphics accordingly
	* @param event the clicking event to be registered
	*/
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

	/**
	* function to register release clicking of car of player using mouse and change graphics accordingly
	* @param event the clicking event to be registered
	*/
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
