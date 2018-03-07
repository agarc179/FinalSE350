import java.awt.Point;
import java.util.Random;

public class Key extends SpecialPowerDecorator implements GamePiece {

	int xCell; // x coordinate for key
	int yCell; // y coordinate for key
	Random rand = new Random();
	OceanMap oceanMap;
	Ship ship;
	int value;
	
	Power powerUp;
	
	public Key(Power power) {
		powerUp = power;
		oceanMap = OceanMap.getInstance(28);
		do {
			xCell = rand.nextInt(oceanMap.N);
			yCell = rand.nextInt(oceanMap.N);
		}while(oceanMap.getCoordinateValue(xCell, yCell) == 1 || oceanMap.getCoordinateValue(xCell, yCell) == 2
				|| oceanMap.getCoordinateValue(xCell, yCell) == 3);
	}
	
	
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Key";
	}



	@Override
	public void power() {
		if(powerUp instanceof Ship) {
			((Ship) powerUp).addKey();
		}
	}



	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point getLocation() {
		return new Point(xCell,yCell);
	}

	@Override
	public void setCoordinateValue(int x, int y, int value) {
		this.value = value;
		oceanMap.oceanGrid[x][y] = value;
		
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public GamePiece getObject() {
		return this;
	}

}
