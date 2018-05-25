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
	
	public MoveAction(GameInterface game, GamePanel gamePanel, int direction) {
		this.game = game;
		this.gamePanel = gamePanel;
		this.direction = direction;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		game.moveCar(direction);
		gamePanel.repaint();
		
	}

}