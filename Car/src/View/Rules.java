package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
		Image image = Toolkit.getDefaultToolkit().getImage("./pics/rules.png");
		Image scaledImg = image.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(scaledImg);
		JLabel label = new JLabel("", icon, JLabel.CENTER);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(label, BorderLayout.CENTER);
		this.setBackground(Color.WHITE);
		this.add(label, BorderLayout.CENTER);
		this.add(button, BorderLayout.SOUTH);
	}
}
