package View;

import Controller.GameInterface;
import Controller.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButtonListener implements ActionListener {
    private GameInterface game;
    private GamePanel gamePanel;


    public GameButtonListener(GameInterface game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Restart")) {
            game.gameRestart();
            gamePanel.restartTime();
            
        } else if (command.equals("Undo")) {
        	game.undo();
        	
        } else if (command.equals("Exit")) {

        } else if (command.equals("Next Level")) {
        	game.nextLevel();
        	gamePanel.nextLevel();
            gamePanel.restartTime();
        } 
        
        gamePanel.repaint();
    }
}