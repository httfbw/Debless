package httf.ui;

import httf.RetroGame;
import httf.map.Maze;
import httf.map.Player;
import httf.map.Tile;
import java.util.*;

public class Screen extends Bitmap {
	
	public Maze maze;
	public Player player;
	public List<Tile> tiles;
	
	public Screen(int width, int height) {
		super(width, height);
		maze = new Maze(RetroGame.WIDTH, RetroGame.HEIGHT, Maze.CRAZY_LABYRINTH, 160);
		player = new Player(width / 2 - 8, height / 2 - 8);
		tiles = new ArrayList<>();
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 10; y++) {
				tiles.add(new Tile(x, y, Tile.floor));
			}
		}
	}
	
	public void render() {
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++)
				pixels[x][y] = 0x343434;
		
		for(int i = 0; i < tiles.size(); i++) {
			draw(tiles.get(i).tex, tiles.get(i).x * 16, tiles.get(i).y * 16);
		}
		draw(maze.getPlayerView(4), 0, 0);
//		draw(maze.level, 0, 0);
		draw(player.texture, maze.getXPlayer(), maze.getYPlayer());
	}
	
	public void postProcess() {
		// post processing effects
	}
	
}
