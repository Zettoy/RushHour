package Controller;

import View.GamePanel;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
* code to manage actions made and appropriately change program state
*/
@SuppressWarnings("serial")
public class SelectAction extends AbstractAction{
	private GameInterface game;
	private GamePanel gamePanel;
	private int car;
	
	/**
	* constructor
	* @param game reference to game mediator/ controller
	* @param gamePanel reference to the visuals of the game/map
	* @param car maintains reference to car to refer to when an action is made on said car
	*/
	public SelectAction(GameInterface game, GamePanel gamePanel, int car) {
		this.game = game;
		this.gamePanel = gamePanel;
		this.car = car;
	}
	
	/**
	* if car is selected changes state of game to appropriate selection
	* changes visuals appropriately to action performed
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		game.selectCar(car);
		gamePanel.repaint();
		
	}
	
}