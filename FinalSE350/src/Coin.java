import java.awt.Point;
import java.util.Random;

public class Coin extends SpecialPowerDecorator {
	
	int xCell; // x coordinate for coin
	int yCell; // y coordinate for coin
	Random rand = new Random();
	OceanMap oceanMap;
	
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
		// TODO Auto-generated method stub
		super.power();
	}
	
	public Point getCoinLocation() {
		return new Point(xCell,yCell);
	}
	
	
	

}
