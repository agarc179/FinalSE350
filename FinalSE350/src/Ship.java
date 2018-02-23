import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Ship implements Subject, Power {

	int xCell;
	int yCell;
	OceanMap oceanMap;

	List<Observer> observers = new LinkedList<Observer>();
	ArrayList<SpecialPowerDecorator> decoratorList = new ArrayList<SpecialPowerDecorator>();
	
	// constructor
	public Ship(OceanMap oceanMap) {
		Random rand = new Random();

		do {
			xCell = rand.nextInt(oceanMap.N);
			yCell = rand.nextInt(oceanMap.N);

		}while (oceanMap.getCoordinateValue(xCell, yCell) == 1); // repeat while ship gets placed on an island

		this.oceanMap = oceanMap;

	}

	// gets ship location
	public Point getShipLocation() {
		return new Point(xCell,yCell);
	}

	// move to the right
	public void goEast() {
		if(xCell < oceanMap.getMap().length -1) {
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
		if(yCell < oceanMap.getMap().length - 1) {
			if(oceanMap.getCoordinateValue(xCell, yCell+1) != 1)
				yCell++;
		}
		notifyObserver();
	}
	
	public void addDecorator(SpecialPowerDecorator specialItem) {
		decoratorList.add(specialItem);
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);

	}

	@Override
	public void removeObserver(Observer o) {
		if(observers.contains(o)) {
			observers.remove(o);
		}

	}

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

}
