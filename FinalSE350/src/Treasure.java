import java.awt.Point;
import java.util.Random;

public class Treasure implements GamePiece {
	
	int xCell; // x coordinate for treasure
	int yCell; // y coordinate for treasure
	int value;
	Random rand = new Random();
	OceanMap oceanMap;
	
	public Treasure() {
		oceanMap = OceanMap.getInstance(28);
		do {
			xCell = rand.nextInt(oceanMap.N);
			yCell = rand.nextInt(oceanMap.N);
			
		}while(oceanMap.getCoordinateValue(xCell, yCell) == 1 || oceanMap.getCoordinateValue(xCell, yCell) == 2 
				|| oceanMap.getCoordinateValue(xCell, yCell) == 3 );
	}

	@Override
	public void move() {
		
	}

	@Override
	public Point getLocation() {
		return new Point(xCell,yCell);
	}


	@Override
	public GamePiece getObject() {
		return this;
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
	
}
