import java.awt.Point;

public interface GamePiece {
	
	public void move();
	public Point getLocation();
	public void setCoordinateValue(int x, int y, int value);
	public int getValue();
	public GamePiece getObject();

}
