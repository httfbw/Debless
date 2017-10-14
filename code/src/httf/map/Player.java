package httf.map;

import httf.ui.*;
import httf.util.*;

public class Player {
	
	public Bitmap texture;
	public float x;
	public float y;
	
	public Player(float x, float y) {
		this.x = x;
		this.y = y;
		this.texture = ResourceLoader.loadTexture("player");
	}
	
}
