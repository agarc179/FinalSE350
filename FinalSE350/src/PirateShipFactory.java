
public abstract class PirateShipFactory {
	
	abstract PirateShip createPirateShip(String item);
	
	public PirateShip buildPirateShip(String type) {
		PirateShip pirateShip = createPirateShip(type);
		System.out.println("----Making a " + pirateShip.getName() + " ----");
		return pirateShip;
	}

}
