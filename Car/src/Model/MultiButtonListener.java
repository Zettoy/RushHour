package Model;

import Controller.GameInterface;
import View.GamePanel;
import View.MainFrame;
import View.MultiPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiButtonListener implements ActionListener {
    private static MainFrame mainFrame;
    private GameInterface game1;
    private GameInterface game2;
    private MultiPanel gamePanel;

    /**
     * Constructor
     * @param game1       The current Game being played
     * @param gamePanel  The GamePanel for the current Game
     */
    public MultiButtonListener(GameInterface game1, GameInterface game2, MultiPanel gamePanel) {
        this.game1 = game1;
        this.game2 = game2;
        this.gamePanel = gamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Return")) {
            game1.quit();
            gamePanel.removeAll();
            mainFrame.remove(gamePanel);
            mainFrame.changePanel(mainFrame.getMainMenu());
            mainFrame.restartGame();
            mainFrame.setSize(600, 600);
        }
        gamePanel.repaint();
    }

    public void setMainFrame(MainFrame mainFrame) {
        MultiButtonListener.mainFrame = mainFrame;
    }
}
