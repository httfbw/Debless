
package httf.map;

import java.util.ArrayList;
import java.util.Random;

import httf.ui.Bitmap;

public class Maze {

	public static final int RANDOM = 0;
	public static final int CRAZY_LABYRINTH = 1;
	public static final int DEPTH_FIRST_SEARCH = 2;

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
		case 2:
			generateDepthFirstSearch();
			break;
		default:
			generateRandom();
		}

		Random rand = new Random();
		xPos = rand.nextInt(width);
		yPos = rand.nextInt(height);

		extraXPos = xPos;
		extraYPos = yPos;

		this.blockSize = blockSize - 1;
		xPlayer = yPlayer = blockSize / 2;

		draw(xPos, yPos, 0xFF0000);
	}

	private int extraXPos;
	private int extraYPos;
	public boolean notDone = true;

	public void generateDepthFirstSearch() {
		//if (notDone) {
			int xPos = this.extraXPos;
			int yPos = this.extraYPos;
			while(notDone) {
			//for (int i = 0; i < 10000; i++) {
				int[] dir = getDirection(xPos, yPos, 0x000000, 2);
				if (dir.length == 1) {
					dir = getDirection(xPos, yPos, 0xFFF000, 1);
					if (dir.length == 1) {
						notDone = false;
						System.out.println("actually done");
					} else {
						level.pixels[xPos][yPos] = 0xFFFFFF;
						xPos += dir[0];
						yPos += dir[1];
						level.pixels[xPos][yPos] = 0xFFFFFF;
						xPos += dir[0];
						yPos += dir[1];
						level.pixels[xPos][yPos] = 0xFFFFFF;
					}
				} else {
					level.pixels[xPos][yPos] = 0xFFF000;
					xPos += dir[0];
					yPos += dir[1];
					level.pixels[xPos][yPos] = 0xFFF000;
					xPos += dir[0];
					yPos += dir[1];
					level.pixels[xPos][yPos] = 0xFFF000;
				}

			}

			this.extraXPos = xPos;
			this.extraYPos = yPos;
		
		
		//draw(this.xPos, this.yPos, 0xFF0000); is after this method
	}

	private int[] getDirection(int xPos, int yPos, int color, int distance) {
		int[] rV = new int[1];
		ArrayList<int[]> possibleDirections = new ArrayList<>();
		int[] dir;
		for (int i = 0; i < 4; i++) {
			dir = convertInt(i);

			if (ableToGo(xPos, yPos, color, dir, distance))
				possibleDirections.add(dir);
		}
		if (possibleDirections.size() == 0)
			return rV;

		int reV = new Random().nextInt(possibleDirections.size());
		return possibleDirections.get(reV);
	}

	private int[] convertInt(int i) {
		int[] rV = new int[1];
		switch (i) {
		case 0:
			rV = new int[] { 1, 0 };
			break;
		case 1:
			rV = new int[] { -1, 0 };
			break;
		case 2:
			rV = new int[] { 0, 1 };
			break;
		case 3:
			rV = new int[] { 0, -1 };
			
			break;
		default:

		}

		
		
		return rV;
	}

	private boolean ableToGo(int xPos, int yPos, int color, int[] dir, int distance) {
		xPos += (dir[0] * distance);
		yPos += (dir[1] * distance);
		try {
			if (level.pixels[xPos][yPos] == color)
				return true;
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		return false;
	}

	private int getRandom() {
		if (new Random().nextBoolean()) {
			return 0xFFFFFF;
		}
		return 0x000000;
	}

	private void generateCrazyLabyrinth() {

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
				}
				yLoad++;
			}
			xLoad++;
		}

		return playerView;
	}

	public void tick(int xVec, int yVec) {
		if(notDone) {
			generateDepthFirstSearch();
			return;
		}
		draw(xPos, yPos, 0xFFFFFF);
		xPlayer += xVec;
		yPlayer += yVec;

		// Old file: /home/christian/workspace/httf/code/src/httf/map/Maze.java
		try {
		if (xPlayer < 0) {
			if (level.pixels[xPos - 1][yPos] != 0x000000) {
				xPos--;
				xPlayer = blockSize;
			} else {
				xPlayer = 0;
			}
		} else if (xPlayer > blockSize) {
			if (level.pixels[xPos + 1][yPos] != 0x000000) {
				xPos++;
				xPlayer = 0;
			} else {
				xPlayer = blockSize;
			}
		}

		if (yPlayer < 0) {
			if (level.pixels[xPos][yPos - 1] != 0x000000) {
				yPos--;
				yPlayer = blockSize;
			} else {
				yPlayer = 0;
			}
		} else if (yPlayer > blockSize) {
			if (level.pixels[xPos][yPos + 1] != 0x000000) {
				yPos++;
				yPlayer = 0;
			} else {
				yPlayer = blockSize;
			}
		}

		draw(xPos, yPos, 0xFF0000);
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("End of Map reached");
		}
		
	}

	public int getXPlayer() {
		return xPlayer;
	}

	public int getYPlayer() {
		return yPlayer;
	}

}
