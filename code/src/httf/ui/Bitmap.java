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
	
	public void draw(Bitmap map, int xOffs, int yOffs, boolean player) {
//		for(int x = xOffs; x < map.width + xOffs; x++) {
//			for(int y = yOffs; y < map.height + yOffs; y++) { // 0xFFFFFeAA
//				if(map.pixels[x - xOffs][y - yOffs] < 0) {		// < 0 or make better || httf: != 0
//					pixels[x][y] = map.pixels[x - xOffs][y - yOffs];
//					if(player) System.out.println("xOffs : " + xOffs + " :: yOffs : " + yOffs);
//				}
//			}
//		}
		for(int x = 0; x < map.width; x++) {
			for(int y = 0; y < map.height; y++) {
//				if(player) 
//					System.out.println(map.pixels[x][y]);
				if(map.pixels[x][y] < 0) {
					pixels[x + xOffs][y + yOffs] = map.pixels[x][y];
//					if(player) System.out.println("PLAYER");
				}
			}
		}
	}
	
}
