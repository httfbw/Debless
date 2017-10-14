package httf.ui;


import httf.RetroGame;
import httf.map.Maze;
import httf.util.ResourceLoader;

public class Screen extends Bitmap {
	
	public Bitmap test;
	
	private Maze maze;
	
	public Screen(int width, int height) {
		super(width, height);
		
		maze = new Maze(RetroGame.WIDTH, RetroGame.HEIGHT, Maze.CRAZY_LABYRINTH);
		
		test = ResourceLoader.loadTexture("grass");
	}
	
	public void render() {
		//draw(test, 0, 0);
		draw(maze.level, 0, 0);
	}
	
}
