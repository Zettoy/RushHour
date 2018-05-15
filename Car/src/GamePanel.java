import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private GameInterface game;
	private ArrayList<CarComponent> cars;
	
	private Point mousePoint;
	
	public GamePanel (GameInterface game) {
		this.game = game;
		this.cars = new ArrayList<>();
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.addKeyListener(new KeyInput(game, this));
		this.addMouseListener(new MousePress(game, this));
		this.addMouseMotionListener(new MouseDragDrop(game, this));
		this.setFocusable(true);
		this.requestFocus();

	}
	
	public ArrayList<CarComponent> getCars() {
		return cars;
	}
	
	public void setMousePoint(Point mousePoint) {
		this.mousePoint = mousePoint;
	}
	
	public Point getMousePoint() {
		return mousePoint;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
	
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setColor(Color.GRAY);
		for (int i = 0; i < 6; i ++)
			for (int j = 0; j < 6; j ++)
				g2d.drawRect(i * 70 + 90, j * 70 + 80, 60, 60);

		g.setFont(new Font("Arial", Font.PLAIN,20));
		g.setColor(Color.BLACK);
		g.drawString("Moves Made: "+ game.getMovesMade(), 90 , 70);

		int numCars = game.getMap().getNumCars();
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		
		for (int i = 0; i < numCars; i ++) {
			if (cars.size() >= numCars) break;
			
			int carId = i + Constants.RED;
			cars.add(new CarComponent(carId));
		}
		
		for (CarComponent c : cars) {
			CarInterface car = game.getMap().getCar(c.getCarId());
			Character direction = car.getDirection();
			int length = car.getLength();
			
			Position p = car.getPosition();
			int x = p.getX() * 70 + 90;
			int y = p.getY() * 70 + 80;
			c.setStartX(x);
			c.setStartY(y);
			
			if (car.isRedCar()) {
				g2d.setColor(Color.RED);
				g2d.fillRect(x , y, 132, 62);
				c.setEndX(x + 132);
				c.setEndY(y + 62);
				
			} else {
				g2d.setColor(Color.BLUE);
				
				if (direction == Constants.HORIZONTAL) {
					int width = 60 * length + 10 * (length - 1) + 2;
					int height = 62;
					g2d.fillRect(x, y, width, height);
					c.setEndX(x + width);
					c.setEndY(y + height);
					
				} else if (direction == Constants.VERTICAL) {
					int width = 62;
					int height = 60 * length + 10 * (length - 1) + 2;
					g2d.fillRect(x, y, width, height);
					c.setEndX(x + width);
					c.setEndY(y + height);
				}
			}
			
		}

		if (game.isWin()) {
			g.setColor(Color.BLACK);
			g.fillRect(50 , 50, 500, 500);
			g.setColor(Color.WHITE);
			g.fillRect(75 , 75, 450, 450);

			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.CENTER_BASELINE,30));
			g.drawString("COMPLETED",200 , 200);
			g.drawString("Moves Made: "+ game.getMovesMade(),175 , 300);
			return;
		}
		
		CarInterface selectedCar = game.getMap().getCar(game.getSelectedCar());
		if (selectedCar == null) return;
		Position selectedP = selectedCar.getPosition();
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(selectedP.getX() * 70 + 95, selectedP.getY() * 70 + 85, 20, 20);
		
	}
}