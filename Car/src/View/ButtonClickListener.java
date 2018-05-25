/**
 * The ActionListener for all the buttons in the main menu
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Constants;

public class ButtonClickListener implements ActionListener {
	private MainFrame mainFrame;

	/**
	 * Constructor
	 * @param mainFrame The programs mainFrame
	 */
	public ButtonClickListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * The actions for each button press
	 * @param e The name of the button being pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("New Game")) {
			mainFrame.changePanel(mainFrame.getDifficulty());
		} 
		else if(command.equals("Multi players")) {
			mainFrame.setSize(1200, 600);
			mainFrame.changePanel(mainFrame.getMultiGame());
			mainFrame.getGame().gameStart(Constants.INTERMEDIATE);
			mainFrame.getGame2().gameClone(mainFrame.getGame().getMap());
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
			mainFrame.changePanel(mainFrame.getActualGame());
			mainFrame.getGame().gameStart(Constants.EASY);
		} 
		else if(command.equals("Medium")) {
			mainFrame.changePanel(mainFrame.getActualGame());
			mainFrame.getGame().gameStart(Constants.INTERMEDIATE);
		} 
		else if(command.equals("Hard")) {
			mainFrame.changePanel(mainFrame.getActualGame());
			mainFrame.getGame().gameStart(Constants.HARD);
		}
	}
}
