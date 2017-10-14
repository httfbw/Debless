package httf.ui;

import httf.RetroGame;
import httf.map.Maze;
import httf.util.*;

public class Screen extends Bitmap {
	
	public Maze maze;
	
	public Bitmap player;
	public int xPlayer = 0;
	public int yPlayer = 0;
	
	public Screen(int width, int height) {
		super(width, height);
		maze = new Maze(RetroGame.WIDTH, RetroGame.HEIGHT, Maze.RANDOM);
		player = ResourceLoader.loadTexture("player");
	}
	
	public void render() {
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++)
				pixels[x][y] = 0x343434;
		
		draw(maze.level, 0, 0);
		draw(player, xPlayer, yPlayer);
	}
	
}
