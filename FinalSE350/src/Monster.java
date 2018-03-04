import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class Monster implements Runnable {
	MonsterSprite monsterSprite;
	Boolean running = true;
	int radius;
	Random rand = new Random();
	int scalingFactor;
	MonsterSprite[] monsterList = new MonsterSprite[10];
	public Monster(int scalingFactor) {
		for(int i = 0; i < monsterList.length; i++) {
			int x = rand.nextInt(28);
			int y = rand.nextInt(28);
			monsterList[i] = new MonsterSprite(x,y,scalingFactor);
		}
		this.radius = 10;
		this.scalingFactor = scalingFactor;
	}
	
	public void addToMap(ObservableList<Node> sceneGraph) {
		
		for(MonsterSprite monsterSprite: monsterList) {
		Circle circle = monsterSprite.getMonster();
		System.out.println("Adding monster to pane: " + circle.getCenterX() + " " + circle.getCenterY() + " " + radius);
		sceneGraph.add(circle);
		}
	}
	
	@Override
	public void run() {
	      while (true) {
	      	try {
	  			Thread.sleep(400);
	  		} catch (InterruptedException e) {
	  			e.printStackTrace();
	  		}
	      	for(MonsterSprite monsterSprite: monsterList){
	      		// Move X
	      		int xMove = monsterSprite.getX() + rand.nextInt(3)-1;
	      		if (xMove >=0 && xMove < 28)
	      			monsterSprite.setX(xMove);
	      		// Move Y
	      		int yMove = monsterSprite.getY() + rand.nextInt(3)-1;
	      		if (yMove >=0 && yMove <28)
	      			monsterSprite.setY(yMove);
	      	}
	        }
	}

}
