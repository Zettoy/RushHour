package View;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Rules extends JPanel {
	MainFrame mainFrame;
	
	public Rules(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	public void startPanel() {
		JButton button = new JButton("Main Menu");
		button.setActionCommand("Main Menu");
		button.addActionListener(new ButtonClickListener(mainFrame));
		//TODO
		//ImageIcon image = new ImageIcon("./data/walkthrough.png");
		//JLabel label = new JLabel("", image, JLabel.CENTER);
		//JPanel panel = new JPanel(new BorderLayout());
		//panel.add(label, BorderLayout.CENTER);
		//this.add(label, BorderLayout.NORTH);
		this.add(button, BorderLayout.SOUTH);
	}
}
