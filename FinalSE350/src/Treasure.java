import java.util.Random;

public class Treasure {
	
	int xCell; // x coordinate for treasure
	int yCell; // y coordinate for treasure
	Random rand = new Random();
	OceanMap oceanMap = OceanMap.getInstance(28);
	
	public Treasure() {
		do {
			xCell = rand.nextInt(oceanMap.N);
			yCell = rand.nextInt(oceanMap.N);
			
		}while(oceanMap.getCoordinateValue(xCell, yCell) == 1 || oceanMap.getCoordinateValue(xCell, yCell) == 2 
				|| oceanMap.getCoordinateValue(xCell, yCell) == 3 );
	}
}
