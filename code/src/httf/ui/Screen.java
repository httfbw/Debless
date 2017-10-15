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
	//public boolean genSlow = false;
	
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
		
//		Bitmap playerView = maze.getPlayerView(1);
//		Bitmap borderTex;
//		boolean wall = false;
//		
//		if(playerView.pixels[1][0] == 0xFFFFFFFF) {
//			borderTex = Tile.floor0;
//			wall = true;
//		}
//		else borderTex = Tile.wallRight;
//		if(playerView.pixels[1][2] == 0xFFFFFFFF) {
//			borderTex = Tile.floor0;
//			wall = true;
//		}
//		else borderTex = Tile.wallLeft;
//		if(playerView.pixels[0][1] == 0xFFFFFFFF) {
//			borderTex = Tile.floor0;
//			wall = true;
//		}
//		else borderTex = Tile.wallTop;
//		if(playerView.pixels[2][2] == 0xFFFFFFFF) {
//			borderTex = Tile.floor0;
//			wall = true;
//		}
//		else borderTex = Tile.wallBottom;
//		
//		for(int x = 0; x < 10; x++) {
//			for(int y = 0; y < 10; y++) {
//				if((x == 0 || x == 10 || y == 0 || y == 10) && (!wall)) {
//					draw(borderTex, (width / 2 - 80 - 24) + tiles.get(x + y * 10).x * 16, (height / 2 - 80 - 14) + tiles.get(x + y * 10).y * 16, false);
//				}
//				else {
//					draw(tiles.get(x + y * 10).tex, (width / 2 - 80 - 24) + tiles.get(x + y * 10).x * 16, (height / 2 - 80 - 14) + tiles.get(x + y * 10).y * 16, false);
//				}
// 			}
//		}
		
		for(int i = 0; i < tiles.size(); i++) {
			draw(tiles.get(i).tex, (width / 2 - 80 - 24) + tiles.get(i).x * 16, (height / 2 - 80 - 14) + tiles.get(i).y * 16);
		}
		draw(player.texture, (width / 2 - (88 + 18)) + maze.getXPlayer(), (height / 2 - (88 + 16)) + maze.getYPlayer());
		draw(tv, 0, 0);
		draw(maze.level, 0, 0);
		draw(maze.getPlayerView(5), 32, 28);
	}
	
	public void postProcess() {
		
	}
	
}
