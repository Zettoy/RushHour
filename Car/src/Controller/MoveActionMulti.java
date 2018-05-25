package Controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class MoveActionMulti extends AbstractAction {
	private GameInterface game1;
	private MultiPanel multiPanel;
	private int direction;
	
	public MoveActionMulti(GameInterface game1, MultiPanel multiPanel, int direction) {
		this.game1 = game1;
		this.multiPanel = multiPanel;
		this.direction = direction;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		game1.moveCar(direction);
		multiPanel.repaint();		
	}

}
