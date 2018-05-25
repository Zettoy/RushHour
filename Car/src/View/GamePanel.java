package View;
import Controller.*;
import Model.MoveAction;
import Model.GameButtonListener;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.*;

/**
* visual implementatoin of the game during "playing" state
*/
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
	private JLabel pauseLabel;
	private JButton undo;
	//private JButton restart;
	private JButton exit;
	private JButton nextLevel;
	private JButton pause;
	private JButton unpause;

	private Image redCar;
	//private Image blueCarShortVertical;
	private ArrayList<Image> carShortVertical;
	private ArrayList<Image> carShortHorizontal;
	private ArrayList<Image> carLongVertical;
	private ArrayList<Image> carLongHorizontal;

	private boolean isPaused;
	
	private Point mousePoint;
	
	/**
	* constructor
	* @param game reference to the game where the main back-end logic occurs
	* initialises buttons and displays (i.e score, time, backgrounds, map/cars) as appropriate
	*/
	public GamePanel (GameInterface game) {
		game.setPanel(this);
		time = 0;
		this.game = game;
		this.cars = new ArrayList<>();
		isPaused = false;
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		//this.addKeyListener(new KeyInput(game, this));
		this.addMouseListener(new MousePress(game, this));
		this.addMouseMotionListener(new MouseDragDrop(game, this));
		this.setFocusable(true);

		completeLabel = new JLabel("COMPLETE", JLabel.CENTER );
		completeLabel.setBounds(175,65,250,80);
		completeLabel.setForeground(Color.BLACK);
		completeLabel.setFont(new Font("Arial", Font.BOLD,30));
		
		pauseLabel = new JLabel("PAUSED", JLabel.CENTER );
		pauseLabel.setBounds(175,65,250,80);
		pauseLabel.setForeground(Color.BLACK);
		pauseLabel.setFont(new Font("Arial", Font.BOLD,30));

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
		pause = createButton("Pause", 220, 500);
		unpause = createButton("Play", 220, 500);

		readCarImgs();
		bindKeys();
		
		this.requestFocus();

	}
	
	/**
	* function to attach car images from the database to variables
	*/
	private void readCarImgs() {
		redCar = Toolkit.getDefaultToolkit().getImage("./pics/red_car.png");	
		carShortVertical = new ArrayList<>();
		carShortVertical.add(Toolkit.getDefaultToolkit().getImage("./pics/blue_car_short_v.png"));
		carShortVertical.add(Toolkit.getDefaultToolkit().getImage("./pics/deepblue_car_short_v.png"));
		carShortVertical.add(Toolkit.getDefaultToolkit().getImage("./pics/green_car_short_v.png"));
		carShortVertical.add(Toolkit.getDefaultToolkit().getImage("./pics/yellow_car_short_v.png"));
		carShortVertical.add(Toolkit.getDefaultToolkit().getImage("./pics/grey_car_short_v.png"));
			
		carShortHorizontal = new ArrayList<>();
		carShortHorizontal.add(Toolkit.getDefaultToolkit().getImage("./pics/blue_car_short_h.png"));
		carShortHorizontal.add(Toolkit.getDefaultToolkit().getImage("./pics/deepblue_car_short_h.png"));
		carShortHorizontal.add(Toolkit.getDefaultToolkit().getImage("./pics/green_car_short_h.png"));
		carShortHorizontal.add(Toolkit.getDefaultToolkit().getImage("./pics/yellow_car_short_h.png"));
		carShortHorizontal.add(Toolkit.getDefaultToolkit().getImage("./pics/grey_car_short_h.png"));
				
		carLongVertical = new ArrayList<>();
		carLongVertical.add(Toolkit.getDefaultToolkit().getImage("./pics/blue_car_long_v.png"));
		carLongVertical.add(Toolkit.getDefaultToolkit().getImage("./pics/deepblue_car_long_v.png"));
		carLongVertical.add(Toolkit.getDefaultToolkit().getImage("./pics/green_car_long_v.png"));
		carLongVertical.add(Toolkit.getDefaultToolkit().getImage("./pics/yellow_car_long_v.png"));
		carLongVertical.add(Toolkit.getDefaultToolkit().getImage("./pics/grey_car_long_v.png"));
			
		carLongHorizontal = new ArrayList<>();
		carLongHorizontal.add(Toolkit.getDefaultToolkit().getImage("./pics/blue_car_long_h.png"));
		carLongHorizontal.add(Toolkit.getDefaultToolkit().getImage("./pics/deepblue_car_long_h.png"));
		carLongHorizontal.add(Toolkit.getDefaultToolkit().getImage("./pics/green_car_long_h.png"));
		carLongHorizontal.add(Toolkit.getDefaultToolkit().getImage("./pics/yellow_car_long_h.png"));
		carLongHorizontal.add(Toolkit.getDefaultToolkit().getImage("./pics/grey_car_long_h.png"));
				
	}

	/**
	* variable to bind keyboard controls
	*/
	private void bindKeys() {
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "move up");
		this.getActionMap().put("move up", new MoveAction(game, this, Constants.UP));
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "move down");
		this.getActionMap().put("move down", new MoveAction(game, this, Constants.DOWN));
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "move left");
		this.getActionMap().put("move left", new MoveAction(game, this, Constants.LEFT));
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "move right");
		this.getActionMap().put("move right", new MoveAction(game, this, Constants.RIGHT));
		
		for (Character i = 'A'; i <= 'A' + 26; i ++) {
			String key = i.toString();
			int car = (int) i - 'A' + Constants.RED;
			
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);
			this.getActionMap().put(key, new SelectAction(game, this, car));
		}
		
	}

	/**
	* @return cars returns an array list of cars on the board
	*/
	public ArrayList<CarComponent> getCars() {
		return cars;
	}
	
	/**
	* sets the mouse pointer
	*/
	public void setMousePoint(Point mousePoint) {
		this.mousePoint = mousePoint;
	}
	
	/**
	* returns the mouse pointer
	*/
	public Point getMousePoint() {
		return mousePoint;
	}
	
	/**
	* calls functions to change graphics accordingly
	*/
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
	
	/**
	* function to change the graphics of the game visuals
	* includes changing to winning screen when goal state is achieved where final stats (time, score) is displayed and appropriate buttons set
	* or visuals for pause screen
	* or visuals for new state of map/cars after a move has been made or even registered
	*/
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
			Random rand = new Random();
			cars.add(new CarComponent(carId, rand.nextInt(5)));
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
					if (width > 180) carToDraw = carLongHorizontal.get(c.getColor());
					if (width < 180) carToDraw = carShortHorizontal.get(c.getColor());

					g2d.drawImage(carToDraw, x, y, width, height, this);
					c.setEndX(x + width);
					c.setEndY(y + height);
					
				} else if (direction == Constants.VERTICAL) {
					int width = 62;
					int height = 60 * length + 10 * (length - 1) + 2;
					Image carToDraw = null;
					if (height > 180) carToDraw = carLongVertical.get(c.getColor());
					if (height < 180) carToDraw = carShortVertical.get(c.getColor());

					g2d.drawImage(carToDraw, x, y, width, height, this);
					c.setEndX(x + width);
					c.setEndY(y + height);
				}
			}
			
		}

		if(isPaused) {
			g.setColor(Color.BLACK);
			g.fillRect(50 , 50, 500, 500);
			g.setColor(Color.WHITE);
			g.fillRect(75 , 75, 450, 450);

			this.add(pauseLabel);

			timer.stop();
			movesLabel.setHorizontalAlignment(SwingConstants.CENTER);
			movesLabel.setLocation(175,95);
			timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			timeLabel.setLocation(175,125);
			remove(undo);
			remove(pause);
			remove(exit);
			add(unpause);
			unpause.setLocation(220,500);
		} else if (game.isWin()) {
			g.setColor(Color.BLACK);
			g.fillRect(50 , 50, 500, 500);
			g.setColor(Color.WHITE);
			g.fillRect(75 , 75, 450, 450);

			this.add(completeLabel);

			timer.stop();
			movesLabel.setHorizontalAlignment(SwingConstants.CENTER);
			movesLabel.setLocation(175,110);
			timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			timeLabel.setLocation(175,150);
			if (game.isOver()) {
				completeLabel.setText("GAME OVER");
				return;
			}
			add(nextLevel);
			//restart.setLocation(225,350);
			exit.setLocation(225,460);
			remove(undo);
			remove(unpause);
			remove(pause);
		} else if (!timer.isRunning()) {
			//time = 0;
			timer.restart();
			movesLabel.setHorizontalAlignment(SwingConstants.LEFT);
			movesLabel.setLocation(90,25);
			timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
			timeLabel.setLocation(370,25);
			remove(completeLabel);
			remove(pauseLabel);
			remove(nextLevel);
			remove(unpause);
			add(exit);
			add(undo);
			add(pause);
			//restart.setLocation(390,500);
			undo.setLocation(350, 500);
			exit.setLocation(90,500);
			pause.setLocation(220, 500);
		}
		
	}

	/**
	* set game state to be paused
	*/
	public void pause() {
		isPaused = true;
	}
	
	/**
	* set game state to be unpause
	*/
	public void unpause() {
		isPaused = false;
	}

	/**
	* helper function to create a button
	* @param name name of the button to be created as well as name for reference for actions
	* @param x position for button to be set in horizontal axis
	* @param y position for button to be set in vertical axis
	* @return returns button that has been requested to be created
	*/
	private JButton createButton(String name, int x, int y) {
		JButton button = new JButton(name);
		//set images in background
		//Image image = Toolkit.getDefaultToolkit().getImage("./pics/newLights.png");
		//Image scaledImg = image.getScaledInstance(125, 50, Image.SCALE_SMOOTH);
		//ImageIcon icon = new ImageIcon(scaledImg);
		//button.setIcon(icon);
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

	/**
	* restarts the timer for a level
	*/
	public void restartTime() {
		time = 0;
	}
	
	/**
	* removes all current cars in preperation for next level
	*/
	public void nextLevel() {
		cars.clear();
	}

	/**
	* returns the time taken so far on a level in appropriate formar minutes, seconds, milliseconds
	*/
	public Date getTime() {
		try {
			return new SimpleDateFormat("mm:ss:SS").parse(new SimpleDateFormat("mm:ss:SS").format(new Date(time)));
		} catch (ParseException e) {
			return null;
		}
	}
}
