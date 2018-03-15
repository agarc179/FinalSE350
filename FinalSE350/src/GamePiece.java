import java.awt.Point;

// Game piece interface, most of the game pieces will implement this interface
public interface GamePiece {
	
	public void move();
	public Point getLocation();
	public void setCoordinateValue(int x, int y, int value);
	public int getValue();
	public GamePiece getObject();

}
