
// abstract class for pirate ship factory
public abstract class PirateShipFactory {
	
	abstract PirateShip createPirateShip(String item);
	
	// builds the pirate ship
	public PirateShip buildPirateShip(String type) {
		PirateShip pirateShip = createPirateShip(type);
		System.out.println("----Making a " + pirateShip.getName() + " ----");
		return pirateShip;
	}

}
