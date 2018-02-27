import java.awt.Point;
import java.util.Random;

public class PirateShip implements Observer, GamePiece{

	Point shipPosition;

	Random rand = new Random();

	int xCell; // x coordinate for pirate ship
	int yCell; // y coordinate for pirate ship
	int value;
	
	OceanMap oceanMap;
	
	// constructor 
	public PirateShip(OceanMap oceanMap) {

		do {
			
			xCell = rand.nextInt(oceanMap.N);
			yCell = rand.nextInt(oceanMap.N);
							
		}while (oceanMap.getCoordinateValue(xCell, yCell) == 1); // repeat while pirate ship gets placed on an island
		
		this.oceanMap = oceanMap;
	}

	@Override
	public void update(Ship ship) {
		if(ship instanceof Ship) {
			shipPosition = ((Ship)ship).getLocation();
			move();
		}
	}

	@Override
	// movements for pirate ship
	public void move() {
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
						setCoordinateValue(getLocation().x, getLocation().y, 2);
					}
				}
			}
		}
	}

	@Override
	//returns location for pirate ship
	public Point getLocation() {
		return new Point(xCell,yCell);
	}

	@Override
	// sets the pirate ship coordinates with a value
	public void setCoordinateValue(int x, int y, int value) {
		this.value = value;
		oceanMap.oceanGrid[x][y] = value;
		
	}

	@Override
	// returns value
	public int getValue() {
		return value;
	}

	@Override
	public GamePiece getObject() {
		return this;
	}

}
