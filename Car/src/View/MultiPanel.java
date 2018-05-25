package View;

import Model.*;
import Controller.*;
import Model.MoveActionMulti;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.*;

/**
* all visuals for the multiplayer
*/
@SuppressWarnings("serial")
public class MultiPanel extends JPanel{
	private GameInterface game1;
	private GameInterface game2;
	private ArrayList<CarComponent> cars;
	private int time;
	private Timer timer;
	private JLabel movesLabel;
	private JLabel movesLabel2;
	private JLabel timeLabel;
	private JLabel timeLabel2;
	private JLabel completeLabel;
	private JLabel completeLabel2;

	private Image redCar;
	private ArrayList<Image> carShortVertical;
	private ArrayList<Image> carShortHorizontal;
	private ArrayList<Image> carLongVertical;
	private ArrayList<Image> carLongHorizontal;

	private String time1;
	private String time2;
	
	private Point mousePoint;

	private JButton menu;
	
	/**
	* constructor
	* initialises buttons and displays (i.e score, time, backgrounds, map/cars) as appropriate
	*/
	public MultiPanel (GameInterface game1, GameInterface game2) {
		time = 0;
		this.game1 = game1;
		this.game2 = game2;
		this.cars = new ArrayList<>();

		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.addMouseListener(new MousePressMulti(game2, this));
		this.addMouseMotionListener(new MouseDragDropMulti(game2, this));
		this.setFocusable(true);

		completeLabel = new JLabel("COMPLETE", JLabel.CENTER );
		completeLabel.setBounds(175,65,250,80);
		completeLabel.setForeground(Color.BLACK);
		completeLabel.setFont(new Font("Arial", Font.BOLD,30));
		completeLabel2 = new JLabel("COMPLETE", JLabel.CENTER );
		completeLabel2.setBounds(775,65,250,80);
		completeLabel2.setForeground(Color.BLACK);
		completeLabel2.setFont(new Font("Arial", Font.BOLD,30));
		
		timeLabel = new JLabel("Time: " +  time, JLabel.LEFT );
		timeLabel2 = new JLabel("Time: " +  time, JLabel.LEFT );
		timer = new Timer(1, actionEvent -> {
				time++;
				timeLabel.setText(("Time: " + new SimpleDateFormat("mm:ss:SSS").format(new Date(time))).substring(0,14));
				timeLabel2.setText(("Time: " + new SimpleDateFormat("mm:ss:SSS").format(new Date(time))).substring(0,14));
        });
		timer.start();
		timeLabel.setBounds(370,25,250,80);
		timeLabel.setForeground(Color.BLACK);
		timeLabel.setFont(new Font("Arial", Font.PLAIN,20));
		this.add(timeLabel);
		timeLabel2.setBounds(970,25,250,80);
		timeLabel2.setForeground(Color.BLACK);
		timeLabel2.setFont(new Font("Arial", Font.PLAIN,20));
		this.add(timeLabel2);

		movesLabel = new JLabel("Moves Made: " +  0, JLabel.LEFT );
		movesLabel.setBounds(90,25,250,80);
		movesLabel.setForeground(Color.BLACK);
		movesLabel.setFont(new Font("Arial", Font.PLAIN,20));
		this.add(movesLabel);
		movesLabel2 = new JLabel("Moves Made: " +  0, JLabel.LEFT );
		movesLabel2.setBounds(690,25,250,80);
		movesLabel2.setForeground(Color.BLACK);
		movesLabel2.setFont(new Font("Arial", Font.PLAIN,20));
		this.add(movesLabel2);
			
		readCarImgs();
		bindKeys();
		
		this.requestFocus();

		menu = createButton("Return", 550, 525);

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
		this.getActionMap().put("move up", new MoveActionMulti(game1, this, Constants.UP));
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "move down");
		this.getActionMap().put("move down", new MoveActionMulti(game1, this, Constants.DOWN));
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "move left");
		this.getActionMap().put("move left", new MoveActionMulti(game1, this, Constants.LEFT));
		
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "move right");
		this.getActionMap().put("move right", new MoveActionMulti(game1, this, Constants.RIGHT));
		
		for (Character i = 'A'; i <= 'A' + 26; i ++) {
			String key = i.toString();
			int car = (int) i - 'A' + Constants.RED;
			
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);
			this.getActionMap().put(key, new SelectActionMulti(game1, this, car));
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
		doDrawing2(g);
	}
	
	/**
	* function to change the graphics of the game visuals FOR PLAYER 1
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
					g2d.fillRect(i * 70 + 88, j * 70 + 78, 65, 65);
					g2d.setColor(Color.WHITE);
					g2d.fillRect(i * 70 + 90, j * 70 + 80, 61, 61);
					g2d.setColor(Color.GRAY);
				} else {
					g2d.drawRect(i * 70 + 90, j * 70 + 80, 60, 60);
				}
			}
		}

		movesLabel.setText("Moves Made: "+ game1.getMovesMade());


		int numCars = game1.getMap().getNumCars();
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		
		for (int i = 0; i < numCars; i ++) {
			if (cars.size() >= numCars) break;
			
			int carId = i + Constants.RED;
			Random rand = new Random();
			cars.add(new CarComponent(carId, rand.nextInt(5)));
		}
		
		for (CarComponent c : cars) {
			CarInterface car = game1.getMap().getCar(c.getCarId());
			Character direction = car.getDirection();
			int length = car.getLength();
			
			Position p = car.getPosition();
			int x = p.getX() * 70 + 90;
			int y = p.getY() * 70 + 80;
			c.setStartX(x);
			c.setStartY(y);
			
			if (car.isRedCar()) {
				g2d.setColor(Color.RED);
				g2d.fillRect(x , y, 131, 61);
				c.setEndX(x + 132);
				c.setEndY(y + 62);
				
			} else {
				g2d.setColor(Color.BLUE);
				
				if (direction == Constants.HORIZONTAL) {
					int width = 60 * length + 10 * (length - 1) + 2;
					int height = 62;
					g2d.fillRect(x, y, width - 1, height - 1);
					c.setEndX(x + width);
					c.setEndY(y + height);
					
				} else if (direction == Constants.VERTICAL) {
					int width = 62;
					int height = 60 * length + 10 * (length - 1) + 2;
					g2d.fillRect(x, y, width - 1, height - 1);
					c.setEndX(x + width);
					c.setEndY(y + height);
				}
			}
			
			Character carIdForDisplay = (char) (c.getCarId() + 'A' - 1);
			
			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.setColor(Color.WHITE);
			g.drawString(carIdForDisplay + "", p.getX() * 70 + 95 , p.getY() * 70 + 105);
			
		}

		boolean processed = false;
		if (game1.isWinMulti()) {
			if (processed == true) return;
			
			g.setColor(Color.BLACK);
			g.fillRect(50 , 50, 500, 500);
			g.setColor(Color.WHITE);
			g.fillRect(75 , 75, 450, 450);

			this.add(completeLabel);

			if (game2.isWinMulti()) timer.stop();
			movesLabel.setHorizontalAlignment(SwingConstants.CENTER);
			movesLabel.setLocation(175,110);
			
			if (processed == false) time1 = timeLabel.getText();
			
			this.remove(timeLabel);
			JLabel completeTimeLabel = new JLabel(time1, JLabel.CENTER);
			completeTimeLabel.setBounds(175, 150, 250, 80);
			completeTimeLabel.setForeground(Color.BLACK);
			completeTimeLabel.setFont(new Font("Arial", Font.PLAIN,20));

			this.add(completeTimeLabel);
			
			processed = true;
		}
		
		CarInterface selectedCar = game1.getMap().getCar(game1.getSelectedCar());
		if (selectedCar == null) return;
		Position selectedP = selectedCar.getPosition();
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(selectedP.getX() * 70 + 95, selectedP.getY() * 70 + 85, 20, 20);

	}
	
	/**
	* function to change the graphics of the game visuals FOR PLAYER 2
	* includes changing to winning screen when goal state is achieved where final stats (time, score) is displayed and appropriate buttons set
	* or visuals for pause screen
	* or visuals for new state of map/cars after a move has been made or even registered
	*/
	private void doDrawing2(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setColor(Color.GRAY);
		for (int i = 0; i < 6; i ++) {
			for (int j = 0; j < 6; j ++) {
				if (i == 5 && j == 2) {
					g2d.setColor(Color.RED);
					g2d.fillRect(i * 70 + 688, j * 70 + 78, 65, 65);
					g2d.setColor(Color.WHITE);
					g2d.fillRect(i * 70 + 690, j * 70 + 80, 61, 61);
					g2d.setColor(Color.GRAY);
				} else {
					g2d.drawRect(i * 70 + 690, j * 70 + 80, 60, 60);
				}
			}
		}

		movesLabel2.setText("Moves Made: "+ game2.getMovesMade());


		int numCars = game2.getMap().getNumCars();
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		
		for (int i = 0; i < numCars; i ++) {
			if (cars.size() >= numCars) break;
			
			int carId = i + Constants.RED;
			Random rand = new Random();
			cars.add(new CarComponent(carId, rand.nextInt(5)));
		}
		
		for (CarComponent c : cars) {
			CarInterface car = game2.getMap().getCar(c.getCarId());
			Character direction = car.getDirection();
			int length = car.getLength();
			
			Position p = car.getPosition();
			int x = p.getX() * 70 + 690;
			int y = p.getY() * 70 + 80;
			c.setStartX(x);
			c.setStartY(y);
			
			if (car.isRedCar()) {
				g2d.drawImage(redCar, x, y, 131, 61, this);
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

					g2d.drawImage(carToDraw, x, y, width - 1, height - 1, this);
					c.setEndX(x + width);
					c.setEndY(y + height);
					
				} else if (direction == Constants.VERTICAL) {
					int width = 62;
					int height = 60 * length + 10 * (length - 1) + 2;
					Image carToDraw = null;
					if (height > 180) carToDraw = carLongVertical.get(c.getColor());
					if (height < 180) carToDraw = carShortVertical.get(c.getColor());

					g2d.drawImage(carToDraw, x, y, width - 1, height - 1, this);
					c.setEndX(x + width);
					c.setEndY(y + height);
				}
			}
			
		}

		boolean processed = false;
		if (game2.isWinMulti()) {
			if (processed == true) return;
			
			g.setColor(Color.BLACK);
			g.fillRect(650 , 50, 500, 500);
			g.setColor(Color.WHITE);
			g.fillRect(675 , 75, 450, 450);

			this.add(completeLabel2);

			if (game1.isWinMulti()) timer.stop();
			movesLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			movesLabel2.setLocation(775,110);
			
			if (processed == false) time2 = timeLabel2.getText();
			
			this.remove(timeLabel2);
			JLabel completeTimeLabel = new JLabel(time2, JLabel.CENTER);
			completeTimeLabel.setBounds(775, 150, 250, 80);
			completeTimeLabel.setForeground(Color.BLACK);
			completeTimeLabel.setFont(new Font("Arial", Font.PLAIN,20));

			this.add(completeTimeLabel);
			
			processed = true;
		}

		
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
		button.setBounds(x, y, 130, 50);
		button.addActionListener(new MultiButtonListener(game1, game2,this));
		//add button to the panel
		this.add(button);
		return button;
	}
}
