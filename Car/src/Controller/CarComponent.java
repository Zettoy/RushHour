package Controller;

/**
* code that allows you to check the state of a car and set appropriately
* with extra functions
*/
public class CarComponent {
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private int carId;
	private int color;
	
	/**
	* constructor
	* @param unique identified for specified car
	* @param color colour of the car
	*/
	public CarComponent(int carId, int color) {
		this.carId = carId;
		this.color = color;
	}
	
	/**
	* sets the starting position on the horizontal axis
	* @param startX desired position on the horizontal axis to place car
	* @pre assumes valid co ordinate on map is passed through
	*/
	public void setStartX(int startX) {
		this.startX = startX;
	}
	
	/**
	* sets the starting position on the vertical axis
	* @param startY desired position on the vertical axis to place car
	* @pre assumes valid co ordinate on map is passed through
	*/
	public void setStartY(int startY) {
		this.startY = startY;
	}
	
	/**
	* sets the ending position on the horizontal axis
	* @param endx desired positon on the horizonal axis to place car
	* @pre assumes valid co ordinate on map is passed through
	*/
	public void setEndX(int endX) {
		this.endX = endX;
	}
	
	/**
	* sets the ending position on the vertical axis
	* @param endy desired position on the horizontal axis to place car
	* @pre assumes valid co ordinate on map is passed through
	*/
	public void setEndY(int endY) {
		this.endY = endY;
	}
	
	/**
	* @return the starting position of car (horizontal co ordinate)
	*/
	public int getStartX() {
		return startX;
	}
	
	/**
	* @return the starting position of car (vertical co ordinate)
	*/
	public int getStartY() {
		return startY;
	}
	
	/**
	* @return the ending position of car (horizontal co ordinate)
	*/
	public int getEndX() {
		return endX;
	}
	
	/**
	*  @return the ending position of car (vertical co ordinate)
	*/
	public int getEndY() {
		return endY;
	}
	
	/**
	* @return returns the car's unique id
	*/
	public int getCarId() {
		return carId;
	}
	
	/**
	* @return returns the colour of the car
	*/
	public int getColor() {
		return color;
	}
}
