
public class BigPirateShipFactory extends PirateShipFactory{
	
	// creates new BigPirateShip
	PirateShip createPirateShip(String item) {
		if(item.equals("BigPirateShip")) {
			return new BigPirateShip();
		}
		else return null;
	}
}
