
public class BigPirateShipFactory extends PirateShipFactory{
	
	PirateShip createPirateShip(String item) {
		if(item.equals("BigPirateShip")) {
			return new BigPirateShip();
		}
		else return null;
	}
}
