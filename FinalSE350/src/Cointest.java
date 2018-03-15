import static org.junit.Assert.*;

import org.junit.Test;

// testing the coin class
public class Cointest {

	// test the power up
	@Test
	public void testPowerUp() {
		Ship ship = new Ship();
		Coin coin = new Coin(ship);
		assertTrue(coin.powerUp instanceof Ship);
	}
	
	// test get description
	@Test
	public void testGetDescription() {
		Ship ship = new Ship();
		Coin coin = new Coin(ship);
		assertEquals(coin.getDescription(), "*");
	}
	
	// test the location of the coin
	@Test
	public void testGetLocation() {
		Ship ship = new Ship();
		Coin coin = new Coin(ship);
		assertTrue(coin.getLocation().x == coin.xCell && coin.getLocation().y == coin.yCell);
	}
	
	// test set coordinates Value
	@Test
	public void testSetCoordinateValue() {
		Ship ship = new Ship();
		Coin coin = new Coin(ship);
		coin.setCoordinateValue(18, 20, 3);
		assertEquals(coin.getValue(), 3);
	}
	
	// test the object 
	@Test
	public void testGetObject() {
		Ship ship = new Ship();
		Coin coin = new Coin(ship);
		Coin coin2 = new Coin(ship);
		assertFalse(coin.getObject().equals(coin2));
	}

}
