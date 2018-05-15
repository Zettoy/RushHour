package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickListener implements ActionListener {
	private MainFrame mainFrame;
	
	public ButtonClickListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("New Game")) {
			
		} else if(command.equals("Rules")) {
			
		} else if(command.equals("Leader Board")) {
			
		} else if(command.equals("Quit")) {
			System.exit(0);
		} else if(command.equals("Easy")) {
			
		} else if(command.equals("Medium")) {
			
		} else if(command.equals("Hard")) {
			
		}
		
	}
}
