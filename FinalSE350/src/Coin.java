import java.awt.Point;
import java.util.Random;

public class Coin extends SpecialPowerDecorator implements GamePiece {
	
	int xCell; // x coordinate for coin
	int yCell; // y coordinate for coin
	Random rand = new Random();
	OceanMap oceanMap;
	Ship ship;
	int value;
	
	Power powerUp;
	
	// constructor
	public Coin(Power power) {
		powerUp = power;
		oceanMap = OceanMap.getInstance(28);
		do {
			xCell = rand.nextInt(oceanMap.N);
			yCell = rand.nextInt(oceanMap.N);
			
		}while(oceanMap.getCoordinateValue(xCell, yCell) == 1 || oceanMap.getCoordinateValue(xCell, yCell) == 2);
	}
	
	
	@Override
	public String getDescription() {
		return "*";
	}

	// Method of the power to add life's
	@Override
	public void power() {
		if(powerUp instanceof Ship) {
			((Ship) powerUp).addOneLife();
		}
	}
	
	// coin doesn't move
	@Override
	public void move() {
		// TODO Auto-generated method stub	
	}

	// returns the Point Location
	@Override
	public Point getLocation() {
		return new Point(xCell,yCell);
	}
	
	@Override
	// sets the coin coordinates with a value in the board
	public void setCoordinateValue(int x, int y, int value) {
		this.value = value;
		oceanMap.oceanGrid[x][y] = value;
	}

	// get value
	@Override
	public int getValue() {
		return value;
	}

	@Override
	public GamePiece getObject() {
		return this;
	}


	
	
	

}
