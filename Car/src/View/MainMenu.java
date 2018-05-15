package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainMenu extends JPanel {
	private MainFrame mainFrame;
	//private ArrayList<JButton> buttonsList;
	
	public MainMenu(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		//this.buttonsList = new ArrayList<JButton>();
		//this.setLayout(new BorderLayout());
		//this.setBackground(Color.darkGray);
		this.initialiseMainMenu();
	}
	
	public void initialiseMainMenu() {
		this.createButton("New Game", 300, 100);
		this.createButton("Rules", 100, 200);
		this.createButton("Leader Board", 100, 300);
		this.createButton("Quit", 100, 400);
	}
	
	private void createButton(String name, int x, int y) {
		JButton button = new JButton(name);
		//Add images later
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
		this.add(button);
	}
}
