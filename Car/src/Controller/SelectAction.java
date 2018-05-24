package Controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class SelectAction extends AbstractAction{
	private GameInterface game;
	private GamePanel gamePanel;
	private int car;
	
	public SelectAction(GameInterface game, GamePanel gamePanel, int car) {
		this.game = game;
		this.gamePanel = gamePanel;
		this.car = car;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		game.selectCar(car);
		gamePanel.repaint();
		
	}

}