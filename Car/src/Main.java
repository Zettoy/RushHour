
public class Main {
	public static void main (String[] args) {
		MapGenerator map = new MapGenerator();
		map.addCarRed();
		map.addCarOthers('B');
		map.print();
	}
}
