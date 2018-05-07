import java.util.Scanner;

public class Game implements GameInterface {	
	private MapInterface map;
	private MapGeneratorInterface mapGenerator;
	
	public Game () {
		this.mapGenerator = new MapGenerator();
	}
	
	public void gameStart() {
		generateMap();
		//consolePlay();
	}
	
	public void generateMap() {
		mapGenerator.createMap();
		map = mapGenerator.getMap();
	}
	
	public boolean isWin() {
		if (map.getCar(Constants.RED).getPosition().getX() == 4) return true;
		
		return false;
	}
	
	@Override
	public MapInterface getMap() {
		return map;
	}
	
	private void consolePlay() {
		System.out.println("Type your command at console.");
		System.out.println("Example:\"2 D\" move car 2 down once");
		System.out.println();
		map.print();
		System.out.print("Command: ");
		
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String[] command = sc.nextLine().split(" ");
			int carId = Integer.parseInt(command[0]);
			int direction = 0;
			switch(command[1]) {
				case "L":
					direction = Constants.LEFT;
					break;
				case "R":
					direction = Constants.RIGHT;
					break;
				case "U":
					direction = Constants.UP;
					break;
				case "D":
					direction = Constants.DOWN;
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
}
