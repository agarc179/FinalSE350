import java.awt.Point;
import java.util.Random;

public abstract class PirateShip implements Observer, GamePiece {
	final int dimensions = 28;
	Point shipPosition;
	Random rand = new Random();

	int xCell; // x coordinate for pirate ship
	int yCell; // y coordinate for pirate ship
	int value;
	
	OceanMap oceanMap;
	
	String name;
	
	public String getName() {
		return name;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCoordinateValue(int x, int y, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GamePiece getObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Ship ship) {
		// TODO Auto-generated method stub
	}
	
	
}
