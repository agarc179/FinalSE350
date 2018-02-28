
public class SmallPirateShipFactory extends PirateShipFactory {

	PirateShip createPirateShip(String item) {
		if(item.equals("SmallPirateShip")) {
			return new SmallPirateShip();
		}
		return null;
	}

}
