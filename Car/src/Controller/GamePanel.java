package Controller;
import View.GameButtonListener;
import View.InGameLeaderBoard;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private GameInterface game;
	private ArrayList<CarComponent> cars;
	private int time;
	private Timer timer;
	private JLabel movesLabel;
	private JLabel timeLabel;
	private JLabel levelLabel;
	private JLabel completeLabel;
	private JButton undo;
	//private JButton restart;
	private JButton exit;
	private JButton nextLevel;

	private Image redCar;
	private Image blueCarShortVertical;
	private Image blueCarShortHorizontal;
	private Image blueCarLongVertical;
	private Image blueCarLongHorizontal;
	
	private Point mousePoint;
	
	public GamePanel (GameInterface game) {
		time = 0;
		this.game = game;
		this.cars = new ArrayList<>();
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.addKeyListener(new KeyInput(game, this));
		this.addMouseListener(new MousePress(game, this));
		this.addMouseMotionListener(new MouseDragDrop(game, this));
		this.setFocusable(true);

		completeLabel = new JLabel("COMPLETE", JLabel.CENTER );
		completeLabel.setBounds(175,65,250,80);
		completeLabel.setForeground(Color.BLACK);
		completeLabel.setFont(new Font("Arial", Font.BOLD,30));

		timeLabel = new JLabel("Time: " +  time, JLabel.LEFT );
		timer = new Timer(1, actionEvent -> {
				time++;
				timeLabel.setText(("Time: " + new SimpleDateFormat("mm:ss:SSS").format(new Date(time))).substring(0,14));
        });
		timer.start();
		timeLabel.setBounds(370,25,250,80);
		timeLabel.setForeground(Color.BLACK);
		timeLabel.setFont(new Font("Arial", Font.PLAIN,20));
		this.add(timeLabel);
		
		levelLabel = new JLabel("Level " + game.getLevel());
		levelLabel.setBounds(270,25,250,80);
		levelLabel.setForeground(Color.BLACK);
		levelLabel.setFont(new Font("Arial", Font.PLAIN,20));
		this.add(levelLabel);

		movesLabel = new JLabel("Moves Made: " +  0, JLabel.LEFT );
		movesLabel.setBounds(90,25,250,80);
		movesLabel.setForeground(Color.BLACK);
		movesLabel.setFont(new Font("Arial", Font.PLAIN,20));
		this.add(movesLabel);
		
		nextLevel = createButton("Next Level", 225,395);
		remove(nextLevel);
		undo = createButton("Undo", 350, 500);
		exit = createButton("Return", 90, 500);

		redCar = Toolkit.getDefaultToolkit().getImage("./pics/red_car.png");
		blueCarShortVertical = Toolkit.getDefaultToolkit().getImage("./pics/blue_car_short_v.png");
		blueCarShortHorizontal = Toolkit.getDefaultToolkit().getImage("./pics/blue_car_short_h.png");
		blueCarLongVertical = Toolkit.getDefaultToolkit().getImage("./pics/blue_car_long_v.png");
		blueCarLongHorizontal = Toolkit.getDefaultToolkit().getImage("./pics/blue_car_long_h.png");

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
		for (int i = 0; i < 6; i ++) {
			for (int j = 0; j < 6; j ++) {
				if (i == 5 && j == 2) {
					g2d.setColor(Color.RED);
					g2d.fillRect(i * 70 + 88, j * 70 + 78, 64, 64);
					g2d.setColor(Color.WHITE);
					g2d.fillRect(i * 70 + 90, j * 70 + 80, 60, 60);
					g2d.setColor(Color.GRAY);
				} else {
					g2d.drawRect(i * 70 + 90, j * 70 + 80, 60, 60);
				}
			}
		}

		movesLabel.setText("Moves Made: "+ game.getMovesMade());
		levelLabel.setText("Level " + game.getLevel());


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
				g2d.drawImage(redCar, x, y, 132, 62, this);
				c.setEndX(x + 132);
				c.setEndY(y + 62);
				
			} else {
				g2d.setColor(Color.BLUE);
				
				if (direction == Constants.HORIZONTAL) {
					int width = 60 * length + 10 * (length - 1) + 2;
					int height = 62;
					Image carToDraw = null;
					if (width > 180) carToDraw = blueCarLongHorizontal;
					if (width < 180) carToDraw = blueCarShortHorizontal;

					g2d.drawImage(carToDraw, x, y, width, height, this);
					c.setEndX(x + width);
					c.setEndY(y + height);
					
				} else if (direction == Constants.VERTICAL) {
					int width = 62;
					int height = 60 * length + 10 * (length - 1) + 2;
					Image carToDraw = null;
					if (height > 180) carToDraw = blueCarLongVertical;
					if (height < 180) carToDraw = blueCarShortVertical;

					g2d.drawImage(carToDraw, x, y, width, height, this);
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

			this.add(completeLabel);

			timer.stop();
			movesLabel.setHorizontalAlignment(SwingConstants.CENTER);
			movesLabel.setLocation(175,95);
			timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			timeLabel.setLocation(175,125);
			if (game.isOver()) {
				completeLabel.setText("GAME OVER");
				return;
			}
			add(nextLevel);
			//restart.setLocation(225,350);
			exit.setLocation(225,460);
			remove(undo);
		} else if (!timer.isRunning()) {
			time = 0;
			timer.restart();
			movesLabel.setHorizontalAlignment(SwingConstants.LEFT);
			movesLabel.setLocation(90,25);
			timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
			timeLabel.setLocation(370,25);
			remove(completeLabel);
			remove(nextLevel);
			add(undo);
			//restart.setLocation(390,500);
			undo.setLocation(350, 500);
			exit.setLocation(90,500);
		}
		
	}



	private JButton createButton(String name, int x, int y) {
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
		button.addActionListener(new GameButtonListener(game, this));
		//add button to the panel
		this.add(button);
		return button;
	}

	public void restartTime() {
		time = 0;
	}
	
	public void nextLevel() {
		cars.clear();
	}

	public Date getTime() {
		try {
			return new SimpleDateFormat("mm:ss:SS").parse(new SimpleDateFormat("mm:ss:SS").format(new Date(time)));
		} catch (ParseException e) {
			return null;
		}
	}
}
