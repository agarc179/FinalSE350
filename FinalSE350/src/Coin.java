import java.awt.Point;
import java.util.Random;

public class Coin extends SpecialPowerDecorator implements GamePiece {
	
	int xCell; // x coordinate for coin
	int yCell; // y coordinate for coin
	Random rand = new Random();
	OceanMap oceanMap = OceanMap.getInstance(28);
	Ship ship;
	int value;
	
	Power powerUp;
	
	// constructor
	public Coin(Power power) {
		powerUp = power;
		do {
			xCell = rand.nextInt(oceanMap.N);
			yCell = rand.nextInt(oceanMap.N);
			
		}while(oceanMap.getCoordinateValue(xCell, yCell) == 1 || oceanMap.getCoordinateValue(xCell, yCell) == 2);
	}
	

	@Override
	public String getDescription() {
		return "*";
	}

	@Override
	public void power() {
		if(powerUp instanceof Ship) {
			((Ship) powerUp).addOneLife();
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
	// sets the pirate ship coordinates with a value of 2 in the board
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
