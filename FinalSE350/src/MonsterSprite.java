import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
class MonsterSprite implements OceanSectionInter{
	int x;
	int y;
	Circle monst;
	int scalingFactor;
	int scale = 25;
	int radius = 10;
	Image monstImage;
	ImageView monstImageView;
	MonsterSprite(int x, int y, int scalingFactor){
		this.x = x;
		this.y = y;
		
		//sets dimensions of the new circle "Monster"
		monst = new Circle();
		setPositionX(x);
		setPositionY(y);
		monst.setRadius(radius);
		
		//sets the image for the circle to be the kraken
		Image img = new Image("kraken.png");
		monst.setFill(new ImagePattern(img));
		
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
	
	public void setPositionX(int x){
		monst.setCenterX(x*scalingFactor + (scalingFactor/2));
	}
	
	public void setPositionY(int y){
		monst.setCenterY(y*scalingFactor + (scalingFactor/2));
	}

	@Override
	public boolean inSetion() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
