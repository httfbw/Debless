package httf.map;

import java.util.Random;

import httf.ui.Bitmap;

public class Maze {
	
	
	public Bitmap level;
	
	public Maze(int width, int height) {
		level = new Bitmap(width, height);
		Random rand = new Random();
		for (int x = 0; x < level.pixels.length; x++) {
			for (int y = 0; y < level.pixels[x].length; y++) {
				if(rand.nextBoolean()) {
					level.pixels[x][y] = 0x000000;
				} else {
					level.pixels[x][y] = 0xFFFFFF;
				}
				
			}
		}
		
		
	}
	
	
	
}
