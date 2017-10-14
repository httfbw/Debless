package httf.ui;

import java.awt.Toolkit;

import httf.map.Maze;
import httf.util.ResourceLoader;
import httf.map.Maze;

public class Screen extends Bitmap {
	
	public Bitmap test;
	
	private Maze maze;
	
	public Screen(int width, int height) {
		super(width, height);
		
		maze = new Maze(RetroGame., (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		
		test = ResourceLoader.loadTexture("grass");
	}
	
	public void render() {
		draw(test, 0, 0);
		draw(maze.level, 16, 16);
	}
	
}
