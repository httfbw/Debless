package httf.ui;

public class Bitmap {
	
	public int width;
	public int height;
	public int[][] pixels;
	
	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width][height];
	}
	
	public void draw(Bitmap map, int xOffs, int yOffs) {
		for(int x = 0; x < map.width; x++) {
			for(int y = 0; y < map.height; y++) {
				if(map.pixels[x][y] < 0) {
					pixels[x + xOffs][y + yOffs] = map.pixels[x][y];
				}
			}
		}
	}
	
}
