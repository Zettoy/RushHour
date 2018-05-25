/**
 * The code for what to do when a car is moved
 */

package Model;

import Controller.GameInterface;
import View.GamePanel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class MoveAction extends AbstractAction {
	private GameInterface game;
	private GamePanel gamePanel;
	private int direction;

	/**
	 * Constructor
	 * @param game      The Game where the car is moved
	 * @param gamePanel The GamePanel where the car is moved
	 * @param direction The direction the car is moved where left = 0,
	 *                  right = 1, up = 2 and down = 3
	 */
	public MoveAction(GameInterface game, GamePanel gamePanel, int direction) {
		this.game = game;
		this.gamePanel = gamePanel;
		this.direction = direction;
		
	}

	/**
	 * When a move action is preformed on a car
	 * @param e The move action made
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		game.moveCar(direction);
		gamePanel.repaint();
		
	}

}