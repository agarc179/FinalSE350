import static org.junit.Assert.*;

import org.junit.Test;

public class Cointest {

	@Test
	public void testPowerUp() {
		Ship ship = new Ship();
		Coin coin = new Coin(ship);
		assertTrue(coin.powerUp instanceof Ship);
	}
	
	@Test
	public void testGetDescription() {
		Ship ship = new Ship();
		Coin coin = new Coin(ship);
		assertEquals(coin.getDescription(), "*");
	}
	
	@Test
	public void testGetLocation() {
		Ship ship = new Ship();
		Coin coin = new Coin(ship);
		assertTrue(coin.getLocation().x == coin.xCell && coin.getLocation().y == coin.yCell);
	}
	
	@Test
	public void testSetCoordinateValue() {
		Ship ship = new Ship();
		Coin coin = new Coin(ship);
		coin.setCoordinateValue(18, 20, 3);
		assertEquals(coin.getValue(), 3);
	}
	
	@Test
	public void testGetObject() {
		Ship ship = new Ship();
		Coin coin = new Coin(ship);
		Coin coin2 = new Coin(ship);
		assertFalse(coin.getObject().equals(coin2));
	}

}
