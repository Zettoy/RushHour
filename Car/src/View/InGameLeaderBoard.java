package View;

import Controller.Game;
import Controller.GameInterface;
import Controller.GamePanel;
import Controller.Score;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class InGameLeaderBoard{

    private GameInterface game;
    private GamePanel panel;
    private JLabel score1NameLabel;
    private JLabel score1TimeLabel;
    private JLabel score2NameLabel;
    private JLabel score2TimeLabel;
    private JLabel score3NameLabel;
    private JLabel score3TimeLabel;
    private JLabel leaderBoardLabel;
    private JLabel highScoreLabel;
    private JTextField nameField;
    private JButton submit;
    private JLabel name;


    public InGameLeaderBoard(){
        nameField = new JTextField(10);
        nameField.setBounds(225, 345,150, 30);
    }

    public void showScores(GameInterface game, GamePanel panel, Score[] scores, int position){
        this.game = game;
        this.panel = panel;
        submit = createPlainButton("Submit",400,345);
        leaderBoardLabel = new JLabel("Leader Board");
        leaderBoardLabel.setForeground(Color.BLACK);
        leaderBoardLabel.setBounds(225,215, 150, 30);
        leaderBoardLabel.setFont(new Font("Arial", Font.CENTER_BASELINE,16));
        panel.add(leaderBoardLabel);

        score1NameLabel = addLabel("1. Name: " + scores[0].getName(), 125,245, position - 1);
        score1TimeLabel = addLabel("Time: " + new SimpleDateFormat("mm:ss:SS").format(scores[0].getTime()), 350, 245, position - 1);
        score2NameLabel = addLabel("2. Name: " + scores[1].getName(), 125,245+20, position - 2);
        score2TimeLabel = addLabel("Time: " + new SimpleDateFormat("mm:ss:SS").format(scores[1].getTime()), 350, 245+20, position - 2);
        score3NameLabel = addLabel("3. Name: " + scores[2].getName(), 125,245+20*2, position - 3);
        score3TimeLabel = addLabel("Time: " + new SimpleDateFormat("mm:ss:SS").format(scores[2].getTime()), 350, 245+20*2, position - 3);
        highScoreLabel = new JLabel("New High Score");
        highScoreLabel.setForeground(Color.BLACK);
        highScoreLabel.setBounds(225,310, 150, 30);
        highScoreLabel.setFont(new Font("Arial", Font.CENTER_BASELINE,16));
        panel.add(highScoreLabel);
        highScoreLabel.setVisible(false);
        name = new JLabel("Name: ");
        name.setForeground(Color.BLACK);
        name.setBounds(150,350, 100, 20);
        name.setFont(new Font("Arial", Font.PLAIN,16));
        panel.add(name);
        name.setVisible(false);
        panel.add(nameField);
        nameField.setVisible(false);
        panel.add(submit);
        submit.setVisible(false);
        if(position != 0) {
            highScoreLabel.setVisible(true);
            submit.setVisible(true);
            nameField.setVisible(true);
            name.setVisible(true);

        }
    }

    public void removeLeaderBoard() {
        panel.remove(score1NameLabel);
        panel.remove(score1TimeLabel);
        panel.remove(score2NameLabel);
        panel.remove(score2TimeLabel);
        panel.remove(score3NameLabel);
        panel.remove(score3TimeLabel);
        panel.remove(leaderBoardLabel);
        panel.remove(nameField);
        panel.remove(highScoreLabel);
        panel.remove(name);
        panel.remove(submit);
    }

    private JButton createPlainButton(String name, int x, int y) {
        JButton button = new JButton(name);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        //TODO: MAKE TRANSPARENT
        button.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        button.setActionCommand(name);
        button.setBounds(x, y, 100, 30);
        button.addActionListener(new GameButtonListener(game, panel));
        return button;
    }

    private JLabel addLabel(String text, int xPos, int yPos, int color) {
        JLabel label = new JLabel(text);
        label.setBounds(xPos,yPos,225, 25);
        label.setFont(new Font("Arial", Font.PLAIN,16));
        if(color == 0) {
            label.setForeground(Color.RED);
        } else if (color == 1) {
            label.setForeground(Color.BLACK);
        }
        panel.add(label);
        return label;
    }

    public String getName() {
        if(nameField.getText() == null) {
            nameField.setBackground(Color.RED);
            return null;
        } else {
            return nameField.getText();
        }
    }
}
