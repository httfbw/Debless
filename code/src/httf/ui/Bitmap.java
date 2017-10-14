package httf.ui;

public class Bitmap {
	
	public int width;
	public int height;
	public int[] pixels;
	
	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
	}
	
	public void draw(Bitmap map, int xOffs, int yOffs) {
		for(int x = xOffs; x < map.width + xOffs; x++) {
			for(int y = yOffs; y < map.height + yOffs; y++) {
				pixels[x + y * width] = map.pixels[(x - xOffs) + (y - yOffs) * map.width];
			}
		}
	}
	
}
