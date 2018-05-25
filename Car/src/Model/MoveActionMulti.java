/**
 * The code for what to do when a car is moved in multiplayer
 */

package Model;

import Controller.GameInterface;
import View.MultiPanel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


@SuppressWarnings("serial")
public class MoveActionMulti extends AbstractAction {
	private GameInterface game1;
	private MultiPanel multiPanel;
	private int direction;

	/**
	 * Constructor
	 * @param game1	     The Game for the other player in multiplayer
	 * @param multiPanel The GamePanel for the other player in multiplayer
	 * @param direction  The direction the car is moved where left = 0,
	 *                   right = 1, up = 2 and down = 3
	 */
	public MoveActionMulti(GameInterface game1, MultiPanel multiPanel, int direction) {
		this.game1 = game1;
		this.multiPanel = multiPanel;
		this.direction = direction;
		
	}

	/**
	 * When a move action is preformed on a car
	 * @param e The move action made
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		game1.moveCar(direction);
		multiPanel.repaint();		
	}

}
