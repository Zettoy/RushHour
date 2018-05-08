import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private GameInterface game;
	
	public GamePanel (GameInterface game) {
		this.game = game;
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.addKeyListener(new KeyInput(game, this));
		this.setFocusable(true);
		this.requestFocus();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
	
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setColor(Color.GRAY);
		for (int i = 0; i < 6; i ++) {
			for (int j = 0; j < 6; j ++) {
				g2d.drawRect(i * 70 + 90, j * 70 + 80, 60, 60);
			}
		}
		
		int numCars = game.getMap().getNumCars();
		for (int i = 0; i < numCars; i ++) {
			CarInterface car = game.getMap().getCar(i + Constants.RED);
			Position p = car.getPosition();
			Character direction = car.getDirection();
			int length = car.getLength();
			
			if (car.isRedCar()) {
				g2d.setColor(Color.RED);
				g2d.fillRect(p.getX() * 70 + 90 , p.getY() * 70 + 80, 132, 62);
			} else {
				g2d.setColor(Color.BLUE);
				
				int x = p.getX() * 70 + 90;
				int y = p.getY() * 70 + 80;
				
				if (direction == Constants.HORIZONTAL) {
					int width = 60 * length + 10 * (length - 1) + 2;
					int height = 62;
					g2d.fillRect(x, y, width, height);
				} else if (direction == Constants.VERTICAL) {
					int width = 62;
					int height = 60 * length + 10 * (length - 1) + 2;
					g2d.fillRect(x, y, width, height);
				}
			}

			Character carIdForDisplay = (char) (i + KeyEvent.VK_A);
			
			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.setColor(Color.WHITE);
			g.drawString(carIdForDisplay + "", p.getX() * 70 + 95 , p.getY() * 70 + 105);
			
		}
		
		if (game.isWin()) return;
		
		CarInterface selectedCar = game.getMap().getCar(game.getSelectedCar());
		Position selectedP = selectedCar.getPosition();
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(selectedP.getX() * 70 + 95, selectedP.getY() * 70 + 85, 20, 20);
	}
}