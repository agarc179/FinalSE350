import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OceanExplorer extends Application  {


	final int dimensions = 28; // creating a 28x28 maps
	final int scale = 25; // Scale everything by 25. You can experiment here. 
	Pane root; 
	Ship ship;
	PirateShip pirateShip, pirateShip2;
	Coin coin;
	Treasure treasure;
	Scene scene;
	Image shipImage, pirateShipImage, pirateShipImage2, gameOverImage, coinImage, treasureImage, winImage;
	ImageView shipImageView, pirateShipImageView, pirateShipImageView2, gameOverImageView, coinImageView, treasureImageView, winImageView;
	OceanMap oceanMap = OceanMap.getInstance(dimensions);
	boolean endGame = false;
	int[][] islandMap;
	Random rand = new Random();
	
	ArrayList<PirateShip> pirateShipList = new ArrayList<PirateShip>();
	ArrayList<Image> pirateImageList = new ArrayList<Image>();
	ArrayList<ImageView> pirateImageViewList = new ArrayList<ImageView>();
	
	public void start(Stage oceanStage) throws Exception {

		root = new AnchorPane();
		scene = new Scene(root, 700, 700);
		oceanStage.setScene(scene);
		oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		oceanStage.show();
		startSailing();
		
		islandMap = oceanMap.getMap();
		drawMap();
		drawIslands(oceanMap.getIslands()); // draws the islands
		
		placeShip();
		placePirateShips();
		placeTreasures();
		
		loadShipImage(); // loads the ship image
		loadPirateShipImage(); // loads the pirate ship images
		loadTreasuresImage();
		loadCoinImage();
		loadGameOverImage(); //loads the game over image
		loadWinImage();

	}
	
	

	public void drawMap() {
		for(int x = 0; x < dimensions; x++){
			for(int y = 0; y < dimensions; y++){
				Rectangle rect = new Rectangle(x*scale,y*scale,scale,scale);
				rect.setStroke(Color.BLACK); // We want the black outline
				rect.setFill(Color.PALETURQUOISE); // I like this color better than BLUE
				root.getChildren().add(rect); // Add to the node tree in the pane
			}
		}
	}
	
	// gets each island store in the ArrayList & draws the Islands in the Map
	public void drawIslands(ArrayList<Rectangle> islands) {
		for(Rectangle island: islands) {
			island.setX(island.getX()*scale);
			island.setY(island.getY()*scale);
			root.getChildren().add(island);
		}
	}
	
	public void placeShip() {
		ship = new Ship(oceanMap); // ship object
	}
	
	public void placePirateShips() {
		pirateShip = new PirateShip(oceanMap); // first pirate ship object
		ship.registerObserver(pirateShip);
		
		pirateShip2 = new PirateShip(oceanMap); // second pirate ship object
		ship.registerObserver(pirateShip2);
	}
	
	public void placeTreasures() {
		coin = new Coin(ship);
		treasure = new Treasure();
		
	}

	public void loadShipImage() {
		// Load the ship image
		shipImage = new Image("ship.png", scale, scale, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(ship.getLocation().x * scale);
		shipImageView.setY(ship.getLocation().y * scale);

		// set current ship image (ImageView) to the Pane
		root.getChildren().add(shipImageView);

	}

	public void loadPirateShipImage() {
		// Load the pirate ship image
		pirateShipImage =  new Image("pirate_ship.png", scale, scale, true,true);
		pirateShipImageView = new ImageView(pirateShipImage);
		pirateShipImageView.setX(pirateShip.getPirateShipLocation().x * scale);
		pirateShipImageView.setY(pirateShip.getPirateShipLocation().y * scale);
		oceanMap.oceanGrid[pirateShip.getPirateShipLocation().x][pirateShip.getPirateShipLocation().y] = 2;
		
		// Load the pirate ship 2 image
		pirateShipImage2 =  new Image("pirate_ship.png", scale, scale, true,true);
		pirateShipImageView2 = new ImageView(pirateShipImage2);
		pirateShipImageView2.setX(pirateShip2.getPirateShipLocation2().x * scale);
		pirateShipImageView2.setY(pirateShip2.getPirateShipLocation2().y * scale);
		oceanMap.oceanGrid[pirateShip2.getPirateShipLocation2().x][pirateShip2.getPirateShipLocation2().y] = 2;

		// set current pirate ship image (ImageView) to the Pane
		root.getChildren().add(pirateShipImageView); // set current pirate ship 1 image (ImageView) to the Pane
		root.getChildren().add(pirateShipImageView2); // set current pirate ship 2 image (ImageView) to the Pane
	}
	
	public void loadTreasuresImage() {
		treasureImage = new Image("treasureChest.png", scale, scale, false, false);
		treasureImageView = new ImageView(treasureImage);
		treasureImageView.setX(treasure.getLocation().x * scale);
		treasureImageView.setY(treasure.getLocation().y * scale);
		
		root.getChildren().add(treasureImageView);
	}
	
	public void loadCoinImage() {
		coinImage = new Image("coin.png", scale, scale, true, true);
		coinImageView = new ImageView(coinImage);
		coinImageView.setX(coin.getLocation().x * scale);
		coinImageView.setY(coin.getLocation().y * scale);
		//oceanMap.oceanGrid[coin.getLocation().x][coin.getLocation().y] = 3;
		coin.setCoordinateValue(coin.getLocation().x, coin.getLocation().y, 3);
		
		root.getChildren().add(coinImageView);
	}
	
	// method to load the game over image
	public void loadGameOverImage() {
		gameOverImage = new Image("game_over.png", scale, scale, true, true);
		gameOverImageView = new ImageView(gameOverImage);
		gameOverImageView.setX(pirateShip.getPirateShipLocation().x * scale);
		gameOverImageView.setY(pirateShip.getPirateShipLocation().y * scale);
	}
	
	public void loadWinImage() {
		winImage = new Image("win.png", scale, scale, true, true);
		winImageView = new ImageView(winImage);
		winImageView.setX(treasure.getLocation().x * scale);
		winImageView.setY(treasure.getLocation().y * scale);
	}
	
	// this will set the Game over image (ImageView) to the Pane.
	protected void setGameOverImage() {
		if((root.getChildren().contains(pirateShipImageView) || root.getChildren().contains(pirateShipImageView2)) 
				&& root.getChildren().contains(shipImageView)) {
			
			root.getChildren().remove(pirateShipImageView);
			root.getChildren().remove(pirateShipImageView2);
			root.getChildren().remove(shipImageView);
			root.getChildren().add(gameOverImageView);
			
		}
	}
	
	protected void setGameWinImage() {
		if((root.getChildren().contains(shipImageView)) && root.getChildren().contains(treasureImageView)) {
			root.getChildren().remove(shipImageView);
			root.getChildren().remove(treasureImageView);
			root.getChildren().add(winImageView);
		}
	}
	
	protected void removeCoinImage(){
		if((root.getChildren().contains(coinImageView))) {
			root.getChildren().remove(coinImageView);
		}
	}

	// Handler for the ship
	private void startSailing() {
		// Create a key pressed handler
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if(!endGame) {
					switch(ke.getCode()) {
					case RIGHT:
						ship.goEast();
						break;
					case LEFT:
						ship.goWest();
						break;
					case UP:
						ship.goNorth();
						break;
					case DOWN:
						ship.goSouth();
						break;
					default:
						break;
					}
					shipImageView.setX(ship.getLocation().x * scale);
					shipImageView.setY(ship.getLocation().y * scale);
					
					pirateShipImageView.setX(pirateShip.getPirateShipLocation().x * scale);
					pirateShipImageView.setY(pirateShip.getPirateShipLocation().y * scale);
					//System.out.println("Pirate Ship: " + pirateShip.getPirateShipLocation().x + "," + pirateShip.getPirateShipLocation().y);
					
					pirateShipImageView2.setX(pirateShip2.getPirateShipLocation2().x * scale);
					pirateShipImageView2.setY(pirateShip2.getPirateShipLocation2().y * scale);
					//System.out.println("Pirate Ship2: " + pirateShip2.getPirateShipLocation2().x + "," + pirateShip2.getPirateShipLocation2().y);
					
					if(ship.getLocation().equals(pirateShip.getPirateShipLocation())){
						gameOverImageView.setX(pirateShip.getPirateShipLocation().x * scale);
						gameOverImageView.setY(pirateShip.getPirateShipLocation().y * scale);
					}
					else if(ship.getLocation().equals(pirateShip2.getPirateShipLocation2())) {
						gameOverImageView.setX(pirateShip2.getPirateShipLocation2().x * scale);
						gameOverImageView.setY(pirateShip2.getPirateShipLocation2().y * scale);
					}
					
					if(ship.getLocation().equals(coin.getLocation())) {
						if(coin.getValue() == 3) {
							removeCoinImage();
							coin.power();
							
							// after coin gets collected coin coordinates sets to 0.
							coin.setCoordinateValue(coin.getLocation().x, coin.getLocation().y, 0);
						}
					}
					
					if(ship.getLocation().equals(treasure.getLocation())) {
						setGameWinImage();
						endGame = true;
					}
					
					if((ship.getLocation().equals(pirateShip.getPirateShipLocation()) 
							|| ship.getLocation().equals(pirateShip2.getPirateShipLocation2())) && ship.getLives() > 0){
						ship.removeOneLife();
					}
					
					
					// Check for "end of game" -- Target found!
					if((ship.getLocation().equals(pirateShip.getPirateShipLocation()) 
							|| ship.getLocation().equals(pirateShip2.getPirateShipLocation2())) && ship.lives == 0){
						
						setGameOverImage();
						endGame = true;
					}
					
					
					
					oceanMap.displayMap();
					System.out.println(ship.getLives());
					
//					System.out.println();
//					System.out.println("Ship: " + ship.getShipLocation().x + "," + ship.getShipLocation().y);
//					System.out.println("pirate ship coordinate value: " + oceanMap.oceanGrid[pirateShip.xCell][pirateShip.yCell]);
//					System.out.println();
				}				
			}
		});
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


}
