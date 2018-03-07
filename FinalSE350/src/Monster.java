import java.awt.Point;
import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class Monster implements Runnable {
	OceanMap oceanMap = OceanMap.getInstance(28);
	MonsterSprite monsterSprite;
	Boolean running = true;
	int radius;
	Random rand = new Random();
	int scale = 25;
	int scalingFactor;
	int xMove, yMove,x,y;
	Point move;
	MonsterSprite[] monsterList = new MonsterSprite[10];
	public Monster(int scalingFactor) {
		for(int i = 0; i < monsterList.length; i++) {
			do {
			x = rand.nextInt(28);
			y = rand.nextInt(28);
			}while(oceanMap.getCoordinateValue(x, y) == 1);//causes Krakens to no longer spawn on islands
			monsterList[i] = new MonsterSprite(x,y,scalingFactor); 
		}
		this.radius = 10;
		this.scalingFactor = scalingFactor;
	}
	
	//adds monsters to the existing map
	public void addToMap(ObservableList<Node> sceneGraph) {
		
		for(MonsterSprite monsterSprite: monsterList) {
		Circle circle = monsterSprite.getMonster();
		sceneGraph.add(circle);
		}
	}

	@Override
	public void run() {
	      while (true) {
	      	try {
	  			Thread.sleep(500);
	  		} catch (InterruptedException e) {
	  			e.printStackTrace();
	  		}
	      	for(MonsterSprite monsterSprite: monsterList){

	      		do {
	      		// Move X
	      		xMove = monsterSprite.getX() + rand.nextInt(3)-1;
	      		//Move Y
	      		yMove = monsterSprite.getY() + rand.nextInt(3)-1;
	      		
	      		}while(oceanMap.islandsCoordinates.containsKey(xMove + "," + yMove)); //Picks new coordinate until the Kraken can no longer move onto a Island
	      		
	      		//Moves the Kraken
	      		if (xMove >=0 && xMove < 28)
	      			monsterSprite.setX(xMove);
	      		if (yMove >=0 && yMove <28)
	      			monsterSprite.setY(yMove);
	      		
	      	}
	        }
	}

}
