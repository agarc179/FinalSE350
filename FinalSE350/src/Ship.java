import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Ship implements Subject, Power, GamePiece {
	final int dimensions = 28;
	int xCell;
	int yCell;
	int value;
	OceanMap oceanMap;
	int lives;
	int key;

	List<Observer> observers = new LinkedList<Observer>();
	ArrayList<SpecialPowerDecorator> decoratorList = new ArrayList<SpecialPowerDecorator>();
	
	// constructor
	public Ship() {
		this.oceanMap = OceanMap.getInstance(dimensions);
		lives = 1;
		Random rand = new Random();

		do {
			xCell = rand.nextInt(oceanMap.N);
			yCell = rand.nextInt(oceanMap.N);

		}while (oceanMap.getCoordinateValue(xCell, yCell) == 1); // repeat while ship gets placed on an island

	}


	// move to the right
	public void goEast() {
		if(xCell < oceanMap.getMapLength() -1) {
			if(oceanMap.getCoordinateValue(xCell+1, yCell) != 1)
				xCell++;
		}
		notifyObserver();
	}

	// move to the left
	public void goWest() {
		if(xCell > 0) {
			if(oceanMap.getCoordinateValue(xCell-1, yCell) != 1)
				xCell--;
		}
		notifyObserver();
	}

	// move up
	public void goNorth() {
		if(yCell > 0) {
			if(oceanMap.getCoordinateValue(xCell, yCell-1) != 1)
				yCell--;
		}
		notifyObserver();
	}

	// move down
	public void goSouth() {
		if(yCell < oceanMap.getMapLength() - 1) {
			if(oceanMap.getCoordinateValue(xCell, yCell+1) != 1)
				yCell++;
		}
		notifyObserver();
	}
	
	public void addDecorator(SpecialPowerDecorator specialItem) {
		decoratorList.add(specialItem);
	}
	
	public void addKey() {
		key += 1;
	}
	
	public int getKeyValue() {
		return key;
	}
	
	public void addOneLife() {
		lives += 1;
	}
	
	public int getLives() {
		return lives;
	}
	
	public void removeOneLife() {
		if(lives > 0) {
			lives -= 1;
		}
	}

	// register to observer
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);

	}

	// remove from observer
	@Override
	public void removeObserver(Observer o) {
		if(observers.contains(o)) {
			observers.remove(o);
		}

	}

	// notify observer
	@Override
	public void notifyObserver() {
		for(Observer pirateShipObserver: observers) {
			pirateShipObserver.update(this);
		}

	}

	@Override
	public String getDescription() {
		return "Ship";
	}

	@Override
	// No power
	public void power() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	// gets ship location
	public Point getLocation() {
		return new Point(xCell, yCell);
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
