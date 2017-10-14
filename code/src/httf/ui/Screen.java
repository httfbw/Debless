package httf.ui;

import httf.util.ResourceLoader;

public class Screen extends Bitmap {
	
	public Bitmap test;
	
	public Screen(int width, int height) {
		super(width, height);
		
		test = ResourceLoader.loadTexture("grass");
	}
	
	public void render() {
		draw(test, 0, 0);
	}
	
}
