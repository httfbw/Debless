package httf.map;

import httf.ui.*;
import httf.util.*;

public class Tile {
	
	public static Bitmap floor = ResourceLoader.loadTexture("floor");
	public static Bitmap ceiling = ResourceLoader.loadTexture("grass");
	
	public int x;
	public int y;
	public Bitmap tex;
	
	public Tile(int x, int y, Bitmap tex) {
		this.tex = tex;
		this.x = x;
		this.y = y;
	}
	
}
