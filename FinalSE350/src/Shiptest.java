import static org.junit.Assert.*;

import org.junit.Test;

public class Shiptest {

	@Test
	public void testAddLifes() {
		Ship ship = new Ship();
		ship.addOneLife();
		ship.addOneLife();
		ship.addOneLife();
		assertTrue(ship.getLives() == 4);
	}
	
	@Test
	public void testRemoveLifes() {
		Ship ship = new Ship();
		ship.addOneLife();
		ship.addOneLife();
		ship.removeOneLife();
		ship.removeOneLife();
		ship.removeOneLife();
		assertEquals(ship.getLives(),0);
	}
	
	@Test
	public void testSetCoordinateValues() {
		Ship ship = new Ship();
		ship.setCoordinateValue(3, 4, 5);
		assertEquals(ship.getValue(),5);
	}
	
	@Test
	public void testGetDescription() {
		Ship ship = new Ship();
		assertTrue(ship.getDescription() == "Ship");
	}
	
	@Test
	public void testGetObject() {
		Ship ship = new Ship();
		Ship ship2 = new Ship();
		assertFalse(ship.getObject().equals(ship2));
	}
	
	@Test
	public void testGetLocation() {
		Ship ship = new Ship();
		assertTrue(ship.getLocation().x == ship.xCell && ship.getLocation().y == ship.yCell);
	}
	
	@Test
	public void testRegisterObserver() {
		Ship ship = new Ship();
		PirateShipFactory bigPirateShipFactory = new BigPirateShipFactory();
		PirateShip bigPirateShip = bigPirateShipFactory.buildPirateShip("BigPirateShip");
		ship.registerObserver(bigPirateShip);
		assertFalse(ship.observers.size() == 0);
	}
	
	@Test
	public void testRemoveObserver() {
		Ship ship = new Ship();
		PirateShipFactory bigPirateShipFactory = new BigPirateShipFactory();
		PirateShip bigPirateShip = bigPirateShipFactory.buildPirateShip("BigPirateShip");
		PirateShipFactory smallPirateShipFactory = new SmallPirateShipFactory();
		PirateShip smallPirateShip = smallPirateShipFactory.buildPirateShip("SmallPirateShip");
		ship.registerObserver(bigPirateShip);
		ship.registerObserver(smallPirateShip);
		ship.removeObserver(bigPirateShip);
		assertTrue(ship.observers.contains(smallPirateShip));
	}

}
