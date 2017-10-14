
package httf.map;

import java.util.Random;

import httf.ui.Bitmap;

public class Maze {

	public static final int RANDOM = 0;
	public static final int CRAZY_LABYRINTH = 1;

	private Bitmap level;
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
		
		this.blockSize = blockSize;
		xPlayer = yPlayer = blockSize/2;
		
		
		draw(xPlayer, yPlayer, 0xFF0000);
		System.out.println(xPlayer + " " + yPlayer);
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
		int xLoad = xPlayer - viewingDistance;
		int yStart = yPlayer - viewingDistance;
		int yLoad;

		for (int x = 0; x < playerView.pixels.length; x++) {
			yLoad = yStart;
			for (int y = 0; y < playerView.pixels[x].length; y++) {
				try {
					playerView.pixels[x][y] = level.pixels[xLoad][yLoad];
				} catch (ArrayIndexOutOfBoundsException e) {
					playerView.pixels[x][y] = 0x000000;
					System.out.println("end of map is reached");
				}
				yLoad++;
			}
			xLoad++;
		}

		return playerView;
	}

	public void tick(int xVec, int yVec) {
		draw(xPlayer, yPlayer, 0xFFFFFF);
		xPlayer += xVec;
		yPlayer += yVec;
		System.out.println("new PlayerPos: " + xPlayer + " " + yPlayer);
		
		int xMove = 0;
		if (xPlayer < 0) {
			xPos --;
			xPlayer = blockSize;
			xMove = 1;
		} else if (xPlayer > blockSize) {
			xPos ++;
			xPlayer = 0;
			xMove = -1;
		}
		
		int yMove = 0;
		if (yPlayer < 0) {
			yPos --;
			yPlayer = blockSize;
			yMove = 1;
		} else if (yPlayer > blockSize) {
			yPos ++;
			yPlayer = 0;
			yMove = -1;
		}
		
		if (level.pixels[xPos][yPos] == 0x000000) {
			xPos += xMove;
			if (xPlayer == 0) {
				xPlayer = blockSize;
			} else {
				xPlayer = 0;
			}
			
			yPos += yMove;
			if (yPlayer == 0) {
				yPlayer = blockSize;
			} else {
				yPlayer = 0;
			}
		}
		draw(xPlayer, yPlayer, 0xFF0000);
		
	}
	
	public int getXPlayer() {
		return xPlayer;
	}
	
	public int getYPlayer() {
		return yPlayer;
	}

}

