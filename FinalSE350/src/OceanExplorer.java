import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OceanExplorer extends Application  {
	
	final int dimensions = 28; // creating a 28x28 maps
	final int scale = 25; // Scale everything by 25. You can experiment here. 
	Pane root;
	Label livesLabel, keyLabel;
	Button reset;
	Ship ship;
	Coin coin;
	Key key;
	Treasure treasure;
	Scene scene;
	Monster monster;
	Thread monsterThread;
	Image shipImage, bigPirateShipImage, smallPirateShipImage, gameOverImage, coinImage, keyImage, treasureImage, winImage;
	ImageView shipImageView, bigPirateShipImageView, smallPirateShipImageView, gameOverImageView, coinImageView, keyImageView, treasureImageView, winImageView;
	OceanMap oceanMap = OceanMap.getInstance(dimensions);
	boolean endGame = false;
	int[][] islandMap;
	Random rand = new Random();
	int numOfBigPirateShips;
	int numOfSmallPirateShips;
	int numOfCoins;
	
	ArrayList<PirateShip> pirateShipList = new ArrayList<PirateShip>();
	ArrayList<Image> pirateShipImageList = new ArrayList<Image>();
	ArrayList<ImageView> pirateShipImageViewList = new ArrayList<ImageView>();
	
	ArrayList<Coin> coinList = new ArrayList<Coin>();
	ArrayList<Image> coinImageList = new ArrayList<Image>();
	ArrayList<ImageView> coinImageViewList = new ArrayList<ImageView>();
	
	public void start(Stage oceanStage) throws Exception {

		root = new AnchorPane();
		scene = new Scene(root, 800, 700);
		oceanStage.setScene(scene);
		oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		
		monster = new Monster(scale);
		
		//adds the lives display Label
		livesLabel = new Label("Lives: 1");
		livesLabel.setTranslateX(723);
		livesLabel.setTranslateY(50);
		root.getChildren().add(livesLabel);
		
		//adds the key display Label
		keyLabel = new Label("Key: 0");
		keyLabel.setTranslateX(723);
		keyLabel.setTranslateY(100);
		root.getChildren().add(keyLabel);
		
		//adds the reset button and enables the reset of the game
		reset = new Button("Reset Game");
		reset.setTranslateX(705);
		reset.setTranslateY(150);
		root.getChildren().add(reset);
		
		islandMap = oceanMap.getMap();
		drawMap();
		drawIslands(oceanMap.getIslands()); // draws the islands
		monster.addToMap(root.getChildren());
		
		oceanStage.show();
		startSailing();
		
		//creates and starts the multi-threading SeaMonsters
		monsterThread = new Thread(monster);
		monsterThread.start();
		
		//resets the game if the button is pushed
		reset.setOnAction(new EventHandler <ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				resetGame();
			}
		});
		// places GamePieces
		placeShip();
		placePirateShips();
		placeTreasures();
		
		// loads Images
		loadShipImage();
		loadPirateShipImage(); 
		loadTreasuresImage(); 
		loadCoinImage(); 
		loadKeyImage();
		loadGameOverImage();
		loadWinImage();
		
		// set current images (ImageView) to the Pane
		root.getChildren().add(shipImageView);
		
		for(ImageView pirImageView: pirateShipImageViewList) {
			root.getChildren().add(pirImageView);
		}
		
		root.getChildren().add(treasureImageView);
		
		for(ImageView coinImageView: coinImageViewList) {
			root.getChildren().add(coinImageView);
		}
		
		root.getChildren().add(keyImageView);
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void stop(){
		monsterThread.stop();
	}
	
	public void resetGame() {
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
		ship = new Ship(); // ship object
	}
	
	public void placePirateShips() {
		PirateShipFactory bigPirateShipFactory = new BigPirateShipFactory();
		PirateShipFactory smallPirateShipFactory = new SmallPirateShipFactory();
		
		numOfBigPirateShips = 8; // can change how many big pirates to generate
		for(int i = 0; i < numOfBigPirateShips; i++) {
			PirateShip bigPirateShip = bigPirateShipFactory.buildPirateShip("BigPirateShip");
			
			pirateShipList.add(bigPirateShip); // add to pirateShipList new BigPirateShips
		}
		
		numOfSmallPirateShips = 4; // can change how many small pirates to generate
		for(int i = 0; i < numOfSmallPirateShips; i++) {
			PirateShip smallPirateShip = smallPirateShipFactory.buildPirateShip("SmallPirateShip");
			
			pirateShipList.add(smallPirateShip); // add to pirateShipList new SmallPirateShips
		}
		
		for(int i = 0; i < pirateShipList.size(); i++) {
			ship.registerObserver(pirateShipList.get(i));
		}
	}
	
	// puts the decorator power and the treasure in the Map
	public void placeTreasures() {
		numOfCoins = 6; // number of coins to be created
		for(int i = 0; i < numOfCoins; i++) {
			coin = new Coin(ship);
			coinList.add(coin);
		}
		treasure = new Treasure();
		key = new Key(ship);
		
	}

	public void loadShipImage() {
		// Load the ship image
		shipImage = new Image("ship.png", scale, scale, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(ship.getLocation().x * scale);
		shipImageView.setY(ship.getLocation().y * scale);

	}

	public void loadPirateShipImage() {
		// Load the pirate ship image
		bigPirateShipImage = new Image("bigPirateShip.png", scale, scale, true,true);
		smallPirateShipImage = new Image("smallPirateShip.png", scale, scale, true,true);
		
		// Big Pirates Ships
		for(PirateShip pir: pirateShipList) {
			if(pir instanceof BigPirateShip) {
				bigPirateShipImageView = new ImageView(bigPirateShipImage);
				bigPirateShipImageView.setX(pir.getLocation().x * scale);
				bigPirateShipImageView.setY(pir.getLocation().y * scale);
				pirateShipImageViewList.add(bigPirateShipImageView);
				pir.setCoordinateValue(pir.getLocation().x, pir.getLocation().y, 2);
			}
			// small Pirates Ships
			else if(pir instanceof SmallPirateShip) {
				smallPirateShipImageView = new ImageView(smallPirateShipImage);
				smallPirateShipImageView.setX(pir.getLocation().x * scale);
				smallPirateShipImageView.setY(pir.getLocation().y * scale);
				pirateShipImageViewList.add(smallPirateShipImageView);
				pir.setCoordinateValue(pir.getLocation().x, pir.getLocation().y, 2);
			}
		}
	}
	
	public void loadTreasuresImage() {
		treasureImage = new Image("treasureChest.png", scale, scale, false, false);
		treasureImageView = new ImageView(treasureImage);
		treasureImageView.setX(treasure.getLocation().x * scale);
		treasureImageView.setY(treasure.getLocation().y * scale);
	}
	
	// loading coin images
	public void loadCoinImage() {
		coinImage = new Image("coin.png", scale, scale, true, true);
		for(Coin coin: coinList) {
			coinImageView = new ImageView(coinImage);
			coinImageView.setX(coin.getLocation().x * scale);
			coinImageView.setY(coin.getLocation().y * scale);
			coinImageViewList.add(coinImageView);
			coin.setCoordinateValue(coin.getLocation().x, coin.getLocation().y, 3);
		}
	}
	
	public void loadKeyImage() {
		keyImage = new Image("key.png", scale, scale, false, false);
		keyImageView = new ImageView(keyImage);
		keyImageView.setX(key.getLocation().x * scale);
		keyImageView.setY(key.getLocation().y * scale);
		key.setCoordinateValue(key.getLocation().x, key.getLocation().y, 7);
	}
	
	// method to load the game over image
	public void loadGameOverImage() {
		gameOverImage = new Image("game_over.png", scale, scale, true, true);
		gameOverImageView = new ImageView(gameOverImage);
		gameOverImageView.setX(pirateShipList.get(0).getLocation().x * scale);
		gameOverImageView.setY(pirateShipList.get(0).getLocation().y * scale);
	}
	
	// loading the win image
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
		
//		for(MonsterSprite monst: monster.monsterList) {
//			if(root.getChildren().contains(monst)) {
//				root.getChildren().remove(monst);
//			}
//		}
		if(root.getChildren().contains(shipImageView)) {
			root.getChildren().remove(shipImageView);
			root.getChildren().add(gameOverImageView);
			stop();
		}
	}
	
	protected void setGameWinImage() {
		for(ImageView pirImageView: pirateShipImageViewList) {
			if(root.getChildren().contains(pirImageView)) {
				root.getChildren().remove(pirImageView);		
			}
		}
		if((root.getChildren().contains(shipImageView)) && root.getChildren().contains(treasureImageView)) {
			root.getChildren().remove(shipImageView);
			root.getChildren().remove(treasureImageView);
			
			root.getChildren().add(winImageView);
			stop();
		}
	}
	
	// removes coin image from pane according to the one that was touched (that is why the index is passed)
	protected void removeCoinImage(int index){
		if((root.getChildren().contains(coinImageViewList.get(index)))) {
			root.getChildren().remove(coinImageViewList.get(index));
		}
	}
	
	// removes key image from pane 
		protected void removeKeyImage(){
			if((root.getChildren().contains(keyImageView))) {
				root.getChildren().remove(keyImageView);
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
		for(MonsterSprite monst: monster.monsterList) {
			Point monstLoc = new Point(monst.getX(),monst.getY());
			if(ship.getLocation().equals(monstLoc)) {
				gameOverImageView.setX(monst.getX() * scale);
				gameOverImageView.setY(monst.getY() * scale);
			}
			
			// removes a life when ship touches Kraken (only when ship has more 1 or more lives)
			if(ship.getLocation().equals(monstLoc) && ship.getLives() > 0) {
				ship.removeOneLife();
			}
			
			//check for "end game" qualifications
			else if(ship.getLocation().equals(monstLoc) && ship.lives == 0) {
				setGameOverImage();
				endGame = true;
			}
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
			else if(ship.getLocation().equals(pir.getLocation()) && ship.getLives() == 0) {
				setGameOverImage();
				endGame = true;
			}
		}
		
		// adds power when ship collects a coin
		for(int i = 0; i < coinList.size(); i++) {
			Coin currentCoin = coinList.get(i);
			if(ship.getLocation().equals(currentCoin.getLocation())) {
				if(currentCoin.getValue() == 3) {
					removeCoinImage(i);
					currentCoin.power();
					currentCoin.setCoordinateValue(currentCoin.getLocation().x, currentCoin.getLocation().y, 0);
				}
			}
		}
		
		// adds power when ship collects the key
		if(ship.getLocation().equals(key.getLocation())) {
			if(key.getValue() == 7) {
				removeKeyImage();
				key.power();
				key.setCoordinateValue(key.getLocation().x, key.getLocation().y, 0);
			}
		}
		
		// wins game
		if((ship.getLocation().equals(treasure.getLocation())) && ship.getKeyValue() == 1) {
			setGameWinImage();
			endGame = true;
		}
		
		livesLabel.setText("Lives: " + Integer.toString(ship.getLives()));
		keyLabel.setText("Key: " + Integer.toString(ship.getKeyValue()));
		
		oceanMap.displayMap(); // testing purposes
		System.out.println(ship.getLives() + " lives"); // testing purposes
		System.out.println(ship.getKeyValue() + " key"); // testing purposes
		
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
