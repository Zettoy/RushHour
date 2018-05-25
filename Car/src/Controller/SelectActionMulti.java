package Controller;

import View.MultiPanel;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
* code to manage actions made and appropriately change program state in multiplayer
*/
@SuppressWarnings("serial")
public class SelectActionMulti extends AbstractAction{
	private GameInterface game1;
	private MultiPanel multiPanel;
	private int car;
	
	/**
	* constructor
	* @param game reference to game mediator/ controller in multiplayer
	* @param gamePanel reference to the visuals of the game/map in multiplayer
	* @param car maintains reference to car to refer to when an action is made on said car in multiplayer
	*/
	public SelectActionMulti(GameInterface game1, MultiPanel multiPanel, int car) {
		this.game1 = game1;
		this.multiPanel = multiPanel;
		this.car = car;
	}

	/**
	* if car is selected changes state of game to appropriate selection in multiplayer
	* changes visuals appropriately to action performed in multiplayer
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		game1.selectCar(car);
		multiPanel.repaint();		
	}


}
