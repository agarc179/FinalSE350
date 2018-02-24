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
	Coin coin;
	Treasure treasure;
	Scene scene;
	Image shipImage, pirateShipImage, gameOverImage, coinImage, treasureImage, winImage;
	ImageView shipImageView, pirateShipImageView, gameOverImageView, coinImageView, treasureImageView, winImageView;
	OceanMap oceanMap = OceanMap.getInstance(dimensions);
	boolean endGame = false;
	int[][] islandMap;
	Random rand = new Random();
	int numOfPirateShips;
	
	ArrayList<PirateShip> pirateShipList = new ArrayList<PirateShip>();
	ArrayList<Image> pirateShipImageList = new ArrayList<Image>();
	ArrayList<ImageView> pirateShipImageViewList = new ArrayList<ImageView>();
	
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
		
		// places GamePieces
		placeShip();
		placePirateShips();
		placeTreasures();
		
		// loads Images
		loadShipImage();
		loadPirateShipImage(); 
		loadTreasuresImage(); 
		loadCoinImage(); 
		loadGameOverImage();
		loadWinImage(); 
		
		// set current images (ImageView) to the Pane
		root.getChildren().add(shipImageView);
		for(ImageView pirImageView: pirateShipImageViewList) {
			root.getChildren().add(pirImageView);
		}
		root.getChildren().add(treasureImageView);
		root.getChildren().add(coinImageView);

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
		numOfPirateShips = 6; // can change how many pirates to generate
		for(int i = 0; i < numOfPirateShips; i++) {
			pirateShipList.add(new PirateShip(oceanMap)); // add to pirateShipList new PirateShips
		}
		
		for(int i = 0; i < pirateShipList.size(); i++) {
			ship.registerObserver(pirateShipList.get(i));
		}
	}
	
	// puts the decorator power and the treasure in the Map
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

//		// set current ship image (ImageView) to the Pane
//		root.getChildren().add(shipImageView);

	}

	public void loadPirateShipImage() {
		// Load the pirate ship image
		pirateShipImage =  new Image("pirate_ship.png", scale, scale, true,true);
		for(PirateShip pir: pirateShipList) {
			pirateShipImageView = new ImageView(pirateShipImage);
			pirateShipImageView.setX(pir.getLocation().x * scale);
			pirateShipImageView.setY(pir.getLocation().y * scale);
			pirateShipImageViewList.add(pirateShipImageView);
			pir.setCoordinateValue(pir.getLocation().x, pir.getLocation().y, 2);
		}
	}
	
	public void loadTreasuresImage() {
		treasureImage = new Image("treasureChest.png", scale, scale, false, false);
		treasureImageView = new ImageView(treasureImage);
		treasureImageView.setX(treasure.getLocation().x * scale);
		treasureImageView.setY(treasure.getLocation().y * scale);
	}
	
	public void loadCoinImage() {
		coinImage = new Image("coin.png", scale, scale, true, true);
		coinImageView = new ImageView(coinImage);
		coinImageView.setX(coin.getLocation().x * scale);
		coinImageView.setY(coin.getLocation().y * scale);
		coin.setCoordinateValue(coin.getLocation().x, coin.getLocation().y, 3);
	}
	
	// method to load the game over image
	public void loadGameOverImage() {
		gameOverImage = new Image("game_over.png", scale, scale, true, true);
		gameOverImageView = new ImageView(gameOverImage);
		gameOverImageView.setX(pirateShipList.get(0).getLocation().x * scale);
		gameOverImageView.setY(pirateShipList.get(0).getLocation().y * scale);
	}
	
	public void loadWinImage() {
		winImage = new Image("win.png", scale, scale, true, true);
		winImageView = new ImageView(winImage);
		winImageView.setX(treasure.getLocation().x * scale);
		winImageView.setY(treasure.getLocation().y * scale);
	}
	
	// this will set the Game over image (ImageView) to the Pane.
	protected void setGameOverImage() {
		for(ImageView pirImageView: pirateShipImageViewList) {
			if(root.getChildren().contains(pirImageView)) {
				root.getChildren().remove(pirImageView);
			}
		}
		if(root.getChildren().contains(shipImageView)) {
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
	
	// removes coin image from pane
	protected void removeCoinImage(){
		if((root.getChildren().contains(coinImageView))) {
			root.getChildren().remove(coinImageView);
		}
	}
	
	//updates image location displayed on the map after things move inside the singleton map
	protected void updateImageLocation() {
		shipImageView.setX(ship.getLocation().x * scale);
		shipImageView.setY(ship.getLocation().y * scale);
		
		int counter = 0;
		for(ImageView pirImageView: pirateShipImageViewList) {
			pirImageView.setX(pirateShipList.get(counter).getLocation().x * scale);
			pirImageView.setY(pirateShipList.get(counter).getLocation().y * scale);
			counter++;
		}
		
		for(PirateShip pir: pirateShipList) {
			if(ship.getLocation().equals(pir.getLocation())){
				gameOverImageView.setX(pir.getLocation().x * scale);
				gameOverImageView.setY(pir.getLocation().y * scale);
			}
			
			// removes a life when ship touches pirateShip (only when ship has more 1 or more lives)
			if(ship.getLocation().equals(pir.getLocation()) && ship.getLives() > 0) {
				ship.removeOneLife();
			}
			// Check for "end of game"! (Ship has 0 lives) 
			else if(ship.getLocation().equals(pir.getLocation()) && ship.lives == 0) {
				setGameOverImage();
				endGame = true;
			}
		}
		
		// adds power when ship collects coin
		if(ship.getLocation().equals(coin.getLocation())) {
			if(coin.getValue() == 3) {
				removeCoinImage();
				coin.power();
				
				// after coin gets collected coin coordinates sets to 0.
				coin.setCoordinateValue(coin.getLocation().x, coin.getLocation().y, 0);
			}
		}
		
		// wins game
		if(ship.getLocation().equals(treasure.getLocation())) {
			setGameWinImage();
			endGame = true;
		}
		
		oceanMap.displayMap();
		System.out.println(ship.getLives());
		
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
					updateImageLocation();
					
				}				
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}


}
