import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OceanMap {

	private static OceanMap uniqueInstance;
	int scalingFactor = 25;
	int N;
	int[][] oceanGrid = new int[28][28];

	ArrayList<Rectangle> islands = new ArrayList<Rectangle>();
	Map<String, Integer> islandsCoordinates = new HashMap<String, Integer>();

	// constructor
	private OceanMap(int mapSize) {				
		N = mapSize;
		buildIslands();
	}
		
	public static OceanMap getInstance(int size) {
		if(uniqueInstance == null) {
			uniqueInstance = new OceanMap(size);

		}
		return uniqueInstance;
	}
	// Return generated map
	public int[][] getMap(){
		return oceanGrid;
	}
	
	// returns the value of the Coordinate
	public int getCoordinateValue(int x, int y) {
		return oceanGrid[x][y];
	}

	// returns ArrayList of Islands
	public ArrayList<Rectangle> getIslands(){
		return islands;
	}

	// sets island with coordinates and value of 1. It adds each island to ArrayList
	private void setIsland(int x, int y) {
		Rectangle island = new Rectangle(scalingFactor,scalingFactor,scalingFactor,scalingFactor);
		island.setX(x);
		island.setY(y);
		oceanGrid[x][y] = 1;
		island.setStroke(Color.BLACK); // We want the black outline
		island.setFill(Color.GREEN); // Green square
		islands.add(island);
	}

	// builds 30 Islands
	private void buildIslands() {

		Random rand = new Random();
		int islandCounter = 0;

		while(islandCounter < 175) {
			int randX = rand.nextInt(N); // random X coordinate between index [0..27]
			int randY = rand.nextInt(N); // random Y coordinate between index [0..27]
			if(islandsCoordinates.containsKey(randX + "," + randY) == false) {
				islandsCoordinates.put(randX + "," + randY , 1);
				setIsland(randX, randY);
				islandCounter++;
			}
		}
	}

	// prints the board (for testing purposes)
	public void displayMap() {
		System.out.println();
		for(int i = 0; i < oceanGrid.length; i++) {
			for(int j = 0; j < oceanGrid[1].length;  j++) {
				System.out.print(oceanGrid[j][i] + " ");

			}
			System.out.println();	
		}
	}
}
