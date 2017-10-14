package httf.ui;

import httf.RetroGame;
import httf.map.*;

public class Screen extends Bitmap {
	
	public Maze maze;
	
	public Player player;
	
	public Screen(int width, int height) {
		super(width, height);
		maze = new Maze(RetroGame.WIDTH, RetroGame.HEIGHT, Maze.RANDOM);
		player = new Player(width / 2, height / 2);
	}
	
	public void render() {
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++)
				pixels[x][y] = 0x343434;
		
		draw(maze.getPlayerView(3), 0, 0);
		draw(player.texture, (int) player.x, (int) player.y);
	}
	
}
