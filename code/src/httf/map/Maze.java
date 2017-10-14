package httf.map;

import httf.ui.Bitmap;

public class Maze {
	
	
	public Bitmap level;
	
	public Maze() {
		level = new Bitmap(8, 8);
		level.pixels[2][5] = 0xFFFFFF;
	}
	
	
	
}
