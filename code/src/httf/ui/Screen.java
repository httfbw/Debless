package httf.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import httf.RetroGame;
import httf.map.Maze;
import httf.map.Player;
import httf.map.Tile;
import httf.util.*;

public class Screen extends Bitmap {
	
	public Maze maze;
	public Player player;
	public List<Tile> tiles;
	
	public Bitmap tv;
	
	public Screen(int width, int height) {
		super(width, height);
//		maze = new Maze(RetroGame.WIDTH/2, RetroGame.HEIGHT/2, Maze.DEPTH_FIRST_SEARCH, 160);
		maze = new Maze(RetroGame.WIDTH / 3, RetroGame.HEIGHT / 3, Maze.DEPTH_FIRST_SEARCH, 160);
		tv = ResourceLoader.loadTexture("oldtv");
		player = new Player(width / 2 - 8, height / 2 - 8);
		tiles = new ArrayList<>();
//		tiles.add(new Tile(0, 0, Tile.wall));
		Random rand = new Random();
		for(int x = 0; x < 11; x++) {
			for(int y = 0; y < 11; y++) {
				if(x == 0 || x == 10 || y == 0 || y == 10) {
//					tiles.add(new Tile(x, y, Tile.wall));
					if(x == 0) {
						tiles.add(new Tile(x, y, Tile.wallLeft));
					}
					else if(x == 10) {
						tiles.add(new Tile(x, y, Tile.wallRight));
					}
					if(y == 0) {
						tiles.add(new Tile(x, y, Tile.wallTop));
					}
					if(y == 10) {
						tiles.add(new Tile(x, y, Tile.wallBottom));
					}
				}
				else {
					int id = rand.nextInt(3);
					Bitmap tex;
					switch(id) {
					case 0:
						tex = Tile.floor0;
						break;
					case 1:
						tex = Tile.floor1;
						break;
					case 2:
						tex = Tile.floor2;
						break;
					default:
						tex = Tile.floor0;
						break;
					}
					tiles.add(new Tile(x, y, tex));
				}
			}
		}
	}
	
	public void render() {
		for(int x = 0; x < width; x++)
			for(int y = 0; y < height; y++)
				pixels[x][y] = 0x343434;
		
		for(int i = 0; i < tiles.size(); i++) {
			draw(tiles.get(i).tex, (width / 2 - 80 - 24) + tiles.get(i).x * 16, (height / 2 - 80 - 14) + tiles.get(i).y * 16, false);
		}
		draw(player.texture, (width / 2 - (88 + 18)) + maze.getXPlayer(), (height / 2 - (88 + 16)) + maze.getYPlayer(), false);
		draw(tv, 0, 0, false);
//		maze.generateDepthFirstSearch();
		draw(maze.level, 0, 0, false);
//		System.out.println(":: Drawing Player");
//		draw(maze.getPlayerView(3), 32, 28, true);
//		System.out.println("// Drawing Player");
	}
	
	public void postProcess() {
		
	}
	
}
