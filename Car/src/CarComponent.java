
public class CarComponent {
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private int carId;
	
	public CarComponent(int carId) {
		this.carId = carId;
	}
	
	public void setStartX(int startX) {
		this.startX = startX;
	}
	
	public void setStartY(int startY) {
		this.startY = startY;
	}
	
	public void setEndX(int endX) {
		this.endX = endX;
	}
	
	public void setEndY(int endY) {
		this.endY = endY;
	}
	
	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}
	
	public int getEndX() {
		return endX;
	}
	
	public int getEndY() {
		return endY;
	}
	
	public int getCarId() {
		return carId;
	}
}
