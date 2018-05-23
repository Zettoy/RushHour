package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Constants;

public class ButtonClickListener implements ActionListener {
	private MainFrame mainFrame;
	
	public ButtonClickListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("New Game")) {
			mainFrame.changePanel(mainFrame.getDifficulty());
		} 
		else if(command.equals("Rules")) {
			mainFrame.changePanel(mainFrame.getRules());
		} 
		else if(command.equals("Leader Board")) {
			mainFrame.changePanel(mainFrame.getLeaderBoard());
		} 
		else if(command.equals("Quit")) {
			System.exit(0);
		} 
		else if(command.equals("Main Menu")) {
			mainFrame.changePanel(mainFrame.getMainMenu());
		}
		else if(command.equals("Easy")) {
			//TODO: SET DIFFICULTY LATER
			mainFrame.changePanel(mainFrame.getActualGame());
			mainFrame.getGame().gameStart(Constants.EASY);
		} 
		else if(command.equals("Medium")) {
			//TODO: SET DIFFICULTY LATER
			mainFrame.changePanel(mainFrame.getActualGame());
			mainFrame.getGame().gameStart(Constants.INTERMEDIATE);
		} 
		else if(command.equals("Hard")) {
			//TODO: SET DIFFICULTY LATER
			mainFrame.changePanel(mainFrame.getActualGame());
			mainFrame.getGame().gameStart(Constants.HARD);
		}
		
	}
}
