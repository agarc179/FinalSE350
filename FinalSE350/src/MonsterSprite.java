import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class MonsterSprite {
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
	

	
	public void setPositionX(int x){
		monst.setCenterX(x*scalingFactor + (scalingFactor/2));
	}
	
	public void setPositionY(int y){
		monst.setCenterY(y*scalingFactor + (scalingFactor/2));
	}
//  loads the images for the Sea Monster
	public void loadMonstImage() {
	monstImage = new Image("kraken.png", scale, scale, true, true);
	monstImageView = new ImageView(monstImage);
	monstImageView.setX(getX() * scale);
	monstImageView.setY(getY() * scale);
}
	
}
