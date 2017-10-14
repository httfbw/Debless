package httf.ui;

import httf.util.*;

public class TextLine {
	
	public String text;
	public Bitmap[] chars;
	public int x;
	public int y;
	
	public TextLine(String text, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.chars = new Bitmap[text.length()];
		for(int i = 0; i < chars.length; i++) {
			String tex = "" + text.charAt(i);
			if(tex.equalsIgnoreCase("'")) tex = "ap";
			else if(tex.equalsIgnoreCase(" ")) tex = "space";
			else if(tex.equalsIgnoreCase("?")) tex = "qm";
			else if(tex.equalsIgnoreCase(":")) tex = "..";
			chars[i] = ResourceLoader.loadTexture("chars/char_" + tex.toLowerCase());
		}
	}
	
	public void render(Bitmap root) {
		for(int i = 0; i < chars.length; i++) {
			root.draw(chars[i], x + i * 8, y);
		}
	}
	
}
