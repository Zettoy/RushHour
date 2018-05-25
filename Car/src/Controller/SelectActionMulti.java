package Controller;

import View.MultiPanel;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class SelectActionMulti extends AbstractAction{
	private GameInterface game1;
	private MultiPanel multiPanel;
	private int car;
	
	public SelectActionMulti(GameInterface game1, MultiPanel multiPanel, int car) {
		this.game1 = game1;
		this.multiPanel = multiPanel;
		this.car = car;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game1.selectCar(car);
		multiPanel.repaint();		
	}


}
