package View;

import Controller.Constants;
import Model.ButtonClickListener;
import Model.LeaderBoard;
import Controller.Score;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class ViewLeaderBoard extends JPanel{
    private MainFrame mainFrame;
    private JLabel diffLabel;
    private JLabel nameLabel;
    private JLabel timeLabel;

    public ViewLeaderBoard(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(null);
        this.setBackground(Color.WHITE);
    }


    public void startPanel() {
        JLabel titleLabel = new JLabel("Leaderboard");
        titleLabel.setBounds(20,5,400, 65);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD,30));
        this.add(titleLabel);
        addDifficultyLabels(Constants.EASY,0);
        addDifficultyLabels(Constants.INTERMEDIATE,1);
        addDifficultyLabels(Constants.HARD,2);
        this.createButton("Main Menu", 200, 500);
    }

    private void addDifficultyLabels(int difficulty, int number) {
        LeaderBoard leaderBoard = new LeaderBoard(3, difficulty);
        Score[] scores = leaderBoard.getScores();
        if(difficulty == Constants.EASY) {
            diffLabel = new JLabel("Easy", JLabel.CENTER );
        } else if (difficulty == Constants.INTERMEDIATE) {
            diffLabel = new JLabel("Medium", JLabel.CENTER );
        } else if (difficulty == Constants.HARD) {
            diffLabel = new JLabel("Hard", JLabel.CENTER );
        }
        diffLabel.setBounds(0, 65 + 115*number,600,40);
        diffLabel.setForeground(Color.BLACK);
        diffLabel.setFont(new Font("Arial", Font.BOLD,20));
        this.add(diffLabel);
        for(int i = 0; i < 3; i++) {
            nameLabel = new JLabel((i+1) + ". Name: " + scores[i].getName());
            nameLabel.setBounds(125,100+20*i + 115*number,225, 25);
            nameLabel.setForeground(Color.BLACK);
            nameLabel.setFont(new Font("Arial", Font.PLAIN,16));
            timeLabel = new JLabel("Time: " + new SimpleDateFormat("mm:ss:SS").format(scores[i].getTime()));
            timeLabel.setBounds(350,100+20*i + 115*number, 500, 20);
            timeLabel.setForeground(Color.BLACK);
            timeLabel.setFont(new Font("Arial", Font.PLAIN,16));
            this.add(timeLabel);
            this.add(nameLabel);
        }
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
        button.setBounds(400, 100, 200, 40);
        button.setBounds(x, y, 200, 50);
        button.addActionListener(new ButtonClickListener(mainFrame));
        //add button to the panel
        this.add(button);
    }

}
