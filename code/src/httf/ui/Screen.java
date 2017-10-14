package httf.ui;

import httf.RetroGame;
import httf.util.ResourceLoader;

public class Screen extends Bitmap {
	
	public Bitmap test;
	public Bitmap erde;
	public Bitmap TVrahmen;
	public int tilenumber;
	
	public Screen(int width, int height) {
		super(width, height);
		
		test = ResourceLoader.loadTexture("grass");
		erde = ResourceLoader.loadTexture("gras");
		TVrahmen = ResourceLoader.loadTexture("Rahmen");
	}
	
	public void render(int PLAYERx, int PLAYERy) {
		Quadratfill(18, test, 16, 10);
		Quaderfill(10, 5, erde, 10, 10);
		draw(erde, PLAYERx, PLAYERy);
		draw(erde, 8, 8);
		draw(TVrahmen, 0, 0);
	}
	/**Quadratfill Tilemenge(wird Quadriert x=y)[int], Textur, Offset auf X[int], Offset auf Y[int] **/
	public void Quadratfill(int tiles, Bitmap texture, int offsetX, int offsetY) {
		for(int j = 0; j < tiles; j++)
		for(int i = 0; i < tiles; i++) {
			draw(texture, offsetX + j * 16, offsetY + i * 16);}
		}
		/**Quaderfill Tiles nach X[int], Tiles nach Y[int], Textur, Offset auf X[int], Offset auf Y[int] **/
		public void Quaderfill(int tilesX, int tilesY, Bitmap texture, int offsetX, int offsetY) {
			for(int j = 0; j < tilesX; j++)
			for(int i = 0; i < tilesY; i++) {
				draw(texture, offsetX + j * 16, offsetY + i * 16);
			}
	}
	
	
}
