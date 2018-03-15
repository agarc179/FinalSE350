
public class SmallPirateShipFactory extends PirateShipFactory {

	// creates a new small pirate ship
	PirateShip createPirateShip(String item) {
		if(item.equals("SmallPirateShip")) {
			return new SmallPirateShip();
		}
		return null;
	}

}
