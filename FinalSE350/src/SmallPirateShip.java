import java.awt.Point;

public class SmallPirateShip extends PirateShip {

	// constructor
	public SmallPirateShip() {
		this.oceanMap = OceanMap.getInstance(dimensions);
		name = "Small Pirate Ship";
		
		do {
			
			xCell = rand.nextInt(oceanMap.N);
			yCell = rand.nextInt(oceanMap.N);
							
		}while (oceanMap.getCoordinateValue(xCell, yCell) == 1); // repeat while pirate ship gets placed on an island
		
		
	}


	@Override
	public void move() {
		if(rand.nextInt(4)==1) { //Slow down the pirate ship
			
			// checks for boundaries on the x axis (right of board) for the pirate ship && islands to right
			if(xCell < oceanMap.getMapLength() - 1 && oceanMap.getCoordinateValue(xCell+1, yCell) != 1){ 
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
			if(yCell < oceanMap.getMapLength() - 1 && oceanMap.getCoordinateValue(xCell, yCell+1) != 1) {
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
			for(int x = 0; x < oceanMap.getMapLength(); x++) {
				for(int y = 0; y < oceanMap.getMapLength(); y++) {
					if(oceanMap.oceanGrid[x][y] == 2) {
						oceanMap.oceanGrid[x][y] = 0;
						setCoordinateValue(getLocation().x, getLocation().y, 2);
					}
				}
			}
		}
	}

	// returns Point location
	@Override
	public Point getLocation() {
		return new Point(xCell, yCell);
	}

	// set coordinate value
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

	// updates the pirate ship 
	@Override
	public void update(Ship ship) {
		if(ship instanceof Ship) {
			shipPosition = ((Ship)ship).getLocation();
			move();
		}
	}
	
	

}
