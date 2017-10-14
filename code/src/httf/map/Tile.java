package httf.map;

import httf.ui.*;
import httf.util.*;

public class Tile {
	
	public static Bitmap floor0 = ResourceLoader.loadTexture("floor0");
	public static Bitmap floor1 = ResourceLoader.loadTexture("floor1");
	public static Bitmap floor2 = ResourceLoader.loadTexture("floor2");
	public static Bitmap wall = ResourceLoader.loadTexture("wall");
	
	public int x;
	public int y;
	public Bitmap tex;
	
	public Tile(int x, int y, Bitmap tex) {
		this.tex = tex;
		this.x = x;
		this.y = y;
	}
	
}
