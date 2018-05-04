import java.util.Scanner;

public class Game implements GameInterface {
	private static final int RED   = 1;
	
	private static final int LEFT  = 0;
	private static final int RIGHT = 1;
	private static final int UP    = 2;
	private static final int DOWN  = 3;
	
	private MapInterface map;
	private MapGeneratorInterface mapGenerator;
	
	public Game () {
		this.mapGenerator = new MapGenerator();
	}
	
	public void gameStart() {
		System.out.println("Type your command at console.");
		System.out.println("Example:\"2 D\" move car 2 down once");
		System.out.println();
		generateMap();
		System.out.print("Command: ");
		
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String[] command = sc.nextLine().split(" ");
			int carId = Integer.parseInt(command[0]);
			int direction = 0;
			switch(command[1]) {
				case "L":
					direction = LEFT;
					break;
				case "R":
					direction = RIGHT;
					break;
				case "U":
					direction = UP;
					break;
				case "D":
					direction = DOWN;
					break;
			}
			map.moveCar(carId, direction);
			System.out.println();
			map.print();
			if (isWin()) {
				System.out.println("Win");
				break;
			}
			System.out.print("Command: ");
		}
		sc.close();
		
	}
	
	public void generateMap() {
		mapGenerator.createMap();
		map = mapGenerator.getMap();
	}
	
	public boolean isWin() {
		if (map.getCar(RED).getPosition().getX() == 4) return true;
		
		return false;
	}
	
}
