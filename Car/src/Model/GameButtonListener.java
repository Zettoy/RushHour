/**
 * The ActionListener for all the buttons during the game
 */
package Model;

import Controller.GameInterface;
import View.GamePanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButtonListener implements ActionListener {
    private static MainFrame mainFrame;
    private GameInterface game;
    private GamePanel gamePanel;

    /**
     * Constructor
     * @param game       The current Game being played
     * @param gamePanel  The GamePanel for the current Game
     */
    public GameButtonListener(GameInterface game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
    }

    /**
     * The actions for each button press
     * @param e The name of the button being pressed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Restart")) {
            game.gameRestart();
            gamePanel.restartTime();
            
        } else if (command.equals("Undo")) {
        	game.undo();
        	
        } else if (command.equals("Return")) {
        	game.quit();
            gamePanel.removeAll();
            mainFrame.remove(gamePanel);
            mainFrame.changePanel(mainFrame.getMainMenu());
            mainFrame.restartGame();
        } else if (command.equals("Next Level")) {
        	game.nextLevel();
        	gamePanel.nextLevel();
            gamePanel.restartTime();
        } else if (command.equals("Pause")) {
        	gamePanel.pause();
        } else if (command.equals("Play")) {
        	gamePanel.unpause();
        } else if (command.equals("Submit")) {
            game.saveScore();
        }
        gamePanel.repaint();
    }

    /**
     * Sets the mainFrame for the GameButtonListener
     * @param mainFrame The programs mainFrame
     */
    public void setMainFrame(MainFrame mainFrame) {
        GameButtonListener.mainFrame = mainFrame;
    }
}