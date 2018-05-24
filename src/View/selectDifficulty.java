package View;

//import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class selectDifficulty extends JPanel {
	private MainFrame mainFrame;
	
	public selectDifficulty(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		//this.setLayout(new BorderLayout());
	}
	
	public void startPanel() {
		this.createButton("Easy", 300, 100);
		this.createButton("Medium", 100, 200);
		this.createButton("Hard", 100, 300);
		this.createButton("Main Menu", 100, 400);
	}
	
	private void createButton(String name, int x, int y) {
		JButton button = new JButton(name);
		//set images in background
		Image image = Toolkit.getDefaultToolkit().getImage("./pics/newLights.png");
		Image scaledImg = image.getScaledInstance(150, 50, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(scaledImg);
		button.setIcon(icon);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		//TODO: MAKE TRANSPARENT
		button.setFont(new Font("TimesRoman", Font.BOLD, 19));
		button.setActionCommand(name);
		button.setBounds(400, 100, 100, 40);
		button.setBounds(x, y, 150, 50);
		button.addActionListener(new ButtonClickListener(mainFrame));
		//add button to the panel
		this.add(button);
	}
}
