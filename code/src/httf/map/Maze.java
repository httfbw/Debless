package httf.map;

import java.util.Random;

import httf.ui.Bitmap;

public class Maze {

	public static final int RANDOM = 0;
	public static final int CRAZY_LABYRINTH = 1;

	public Bitmap level;
	public int xPlayer;
	public int yPlayer;
	
	public Maze(int width, int height, int type) {
		level = new Bitmap(width, height);
		switch (type) {
		case 1:
			generateCrazyLabyrinth();
			break;
		default:
			generateRandom();
		}
		
		Random rand = new Random();
		xPlayer = rand.nextInt(width);
		yPlayer = rand.nextInt(height);
		draw(xPlayer, yPlayer, 0xFF0000);
		System.out.println(xPlayer + " " + yPlayer);
	}
	
	private int getRandom() {
		if(new Random().nextBoolean()) {
			return 0xFFFFFF;
		}
		return 0x000000;
	}

	private void generateCrazyLabyrinth() {
		Random rand = new Random();
		
		for (int x = 0; x < level.pixels.length; x += 3) {
			for (int y = 0; y < level.pixels[x].length; y += 3) {
				level.pixels[x][y] = 0x000000;
				level.pixels[x + 1][y] = getRandom();
				level.pixels[x + 2][y] = 0x000000;
				
				level.pixels[x][y + 1] = getRandom();
				level.pixels[x + 1][y + 1] = 0xFFFFFF;
				level.pixels[x + 2][y + 1] = getRandom();
				
				level.pixels[x][y + 2] = 0x000000;
				level.pixels[x + 1][y + 2] = getRandom();
				level.pixels[x + 2][y + 2] = 0x000000;
				
				

			}
		}
		
	}

	private void generateRandom() {
		Random rand = new Random();
		for (int x = 0; x < level.pixels.length; x++) {
			for (int y = 0; y < level.pixels[x].length; y++) {
				if (rand.nextBoolean()) {
					level.pixels[x][y] = 0x000000;
				} else {
					level.pixels[x][y] = 0xFFFFFF;
				}

			}
		}
	}
	
	
	private void draw(int x, int y, int color) {
		level.pixels[x][y] = color;
	}
	
	public void tick(int xPos, int yPos) {
		draw(xPlayer, yPlayer, 0xFFFFFF);
		xPlayer = xPos;
		yPlayer = yPos;
		draw(xPlayer, yPlayer, 0xFF0000);
	}

}
