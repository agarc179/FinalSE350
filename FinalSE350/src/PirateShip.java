import java.awt.Point;
import java.util.Random;

public class PirateShip implements Observer{

	Point shipPosition;

	Random rand = new Random();

	int xCell; // x coordinate for pirate ship
	int yCell; // y coordinate for pirate ship
	
	OceanMap oceanMap;

	// constructor 
	public PirateShip(OceanMap oceanMap) {

		do {
			
			xCell = rand.nextInt(oceanMap.N);
			yCell = rand.nextInt(oceanMap.N);
							
		}while (oceanMap.getCoordinateValue(xCell, yCell) == 1); // repeat while pirate ship gets placed on an island 

		this.oceanMap = oceanMap;
	}

	// gets location for pirate ship 1
	public Point getPirateShipLocation() {
		return new Point(xCell,yCell);
	}
	
	// gets location for pirate ship 2
	public Point getPirateShipLocation2() {
		return new Point(xCell,yCell);
	}
	
	// sets the pirate ship coordinates with a value of 2 in the board
	public void setPirateShipCoordinateValue(int x, int y) {
		oceanMap.oceanGrid[x][y] = 2;
	}

	@Override
	public void update(Ship ship) {
		if(ship instanceof Ship) {
			shipPosition = ((Ship)ship).getLocation();
			movePirateShip();
		}
	}

	// movements for pirate ship
	public void movePirateShip() {

		if(rand.nextInt(4)==1) { //Slow down the pirate ship
			
			// checks for boundaries on the x axis (right of board) for the pirate ship && islands to right
			if(xCell < oceanMap.getMap().length - 1 && oceanMap.getCoordinateValue(xCell+1, yCell) != 1){ 
				if(xCell - shipPosition.x < 0) {
					xCell++;
				}
			}
			
			// checks for boundaries on the x axis (left of board) for the pirate ship && islands to left
			if(xCell > 0 && oceanMap.getCoordinateValue(xCell-1, yCell) != 1) {
				if(xCell - shipPosition.x > 0) {
					xCell--;
				}
			}
			
			// checks for boundaries on the y axis (bottom of board) for the pirate ship && islands bottom
			if(yCell < oceanMap.getMap().length - 1 && oceanMap.getCoordinateValue(xCell, yCell+1) != 1) {
				if(yCell - shipPosition.y < 0) {
					yCell++;
				}
			}
			
			// checks for boundaries on the y axis (top of board) for the pirate ship && islands up
			if(yCell > 0 && oceanMap.getCoordinateValue(xCell, yCell-1) != 1) {
				if(yCell - shipPosition.y > 0) {
					yCell--;
				}
			}
			// update the board with its correct values of pirate ship after each movement
			for(int x = 0; x < oceanMap.oceanGrid.length; x++) {
				for(int y = 0; y < oceanMap.oceanGrid.length; y++) {
					if(oceanMap.oceanGrid[x][y] == 2) {
						oceanMap.oceanGrid[x][y] = 0;
						setPirateShipCoordinateValue(getPirateShipLocation().x, getPirateShipLocation().y);
						setPirateShipCoordinateValue(getPirateShipLocation2().x, getPirateShipLocation2().y);
					}
				}
			}
		}
	}

}
