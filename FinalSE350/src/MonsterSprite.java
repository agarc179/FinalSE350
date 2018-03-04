import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class MonsterSprite {
	int x;
	int y;
	Circle monst;
	int scalingFactor;
	int radius = 10;
	MonsterSprite(int x, int y, int scalingFactor){
		this.x = x;
		this.y = y;
		monst = new Circle();
		setPositionX(x);
		setPositionY(y);
		monst.setRadius(radius);
		this.scalingFactor = scalingFactor;
	}
	
	Circle getMonster() {
		return monst;
	}
	
	void setX(int x){
		this.x = x;
		setPositionX(x);
	}
	
	void setY(int y){
		this.y = y;
		setPositionY(y);
	}
	
	int getX() {
		return x;
	}
	
	int getY() {
		return y;
	}
	
	public void setColor(Circle monst, Color color) {
		monst.setStroke(color);
		monst.setFill(color);
	}
	
	public void setPositionX(int x){
		monst.setCenterX(x*scalingFactor + (scalingFactor/2));
	}
	
	public void setPositionY(int y){
		monst.setCenterY(y*scalingFactor + (scalingFactor/2));
	}
	
}
