package Controller;
import View.GamePanel;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



/**
* code to register when a user clicks (especially relevant if car is clicked)
*/
public class MousePress implements MouseListener{
	private GameInterface game;
	private GamePanel gamePanel;
	
	/**
	* constructor
	* @param game current version of game being run
	* @param gamePanel visuals of the game
	*/
	public MousePress(GameInterface game, GamePanel gamePanel) {
		this.game = game;
		this.gamePanel = gamePanel;
	}
	
	/**
	* function to register clicking of car and change graphics accordingly
	* @param event the clicking event to be registered
	*/
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
	
	/**
	* function to register release clicking of car and change graphics accordingly
	* @param event the clicking event to be registered
	*/
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
