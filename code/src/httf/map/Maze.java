
package httf.map;

import java.util.Random;

import httf.ui.Bitmap;

public class Maze {

	public static final int RANDOM = 0;
	public static final int CRAZY_LABYRINTH = 1;

	public Bitmap level;
	private int xPos;
	private int yPos;
	
	private int blockSize;
	private int xPlayer;
	private int yPlayer;

	public Maze(int width, int height, int type, int blockSize) {
		level = new Bitmap(width, height);
		switch (type) {
		case 1:
			generateCrazyLabyrinth();
			break;
		default:
			generateRandom();
		}

		Random rand = new Random();
		xPos = rand.nextInt(width);
		yPos = rand.nextInt(height);
		
		this.blockSize = blockSize - 1;
		xPlayer = yPlayer = blockSize/2;
		
		
		
		draw(xPos, yPos, 0xFF0000);
//		System.out.println(xPlayer + " " + yPlayer);
	}

	private int getRandom() {
		if (new Random().nextBoolean()) {
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

	public Bitmap getPlayerView(int viewingDistance) {
		int size = (2 * viewingDistance) + 1;
		Bitmap playerView = new Bitmap(size, size);
		int xLoad = xPos - viewingDistance;
		int yStart = yPos - viewingDistance;
		int yLoad;

		for (int x = 0; x < playerView.pixels.length; x++) {
			yLoad = yStart;
			for (int y = 0; y < playerView.pixels[x].length; y++) {
				try {
					playerView.pixels[x][y] = level.pixels[xLoad][yLoad];
				} catch (ArrayIndexOutOfBoundsException e) {
					playerView.pixels[x][y] = 0x000000;
//					System.out.println("end of map is reached");
				}
				yLoad++;
			}
			xLoad++;
		}

		return playerView;
	}

	public void tick(int xVec, int yVec) {
		draw(xPos, yPos, 0xFFFFFF);
		xPlayer += xVec;
		yPlayer += yVec;
		
		// Old file: /home/christian/workspace/httf/code/src/httf/map/Maze.java
		
		if (xPlayer < 0) {
			if(level.pixels[xPos - 1][yPos] != 0x000000) {
				xPos--;
				xPlayer = blockSize;
			} else {
				xPlayer = 0;
			}
		} else if (xPlayer > blockSize) {
			if(level.pixels[xPos + 1][yPos] != 0x000000) {
				xPos++;
				xPlayer = 0;
			} else {
				xPlayer = blockSize;
			}
		}
		
		if (yPlayer < 0) {
			if(level.pixels[xPos][yPos - 1] != 0x000000) {
				yPos--;
				yPlayer = blockSize;
			} else {
				yPlayer = 0;
			}
		} else if (yPlayer > blockSize) {
			if(level.pixels[xPos][yPos + 1] != 0x000000) {
				yPos++;
				yPlayer = 0;
			} else {
				yPlayer = blockSize;
			}
		}
		
		draw(xPos, yPos, 0xFF0000);
//		System.out.println("new PlayerPos: " + xPlayer + " " + yPlayer + " : " + xPos + " " + yPos);
		
	}
	
	public int getXPlayer() {
		return xPlayer;
	}
	
	public int getYPlayer() {
		return yPlayer;
}

}

