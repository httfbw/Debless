package httf.menu;

import java.util.*;
import httf.ui.*;

public abstract class Menu {
	
	public List<TextLine> texts;
	
	public Menu() {
		texts = new ArrayList<>();
	}
	
	public void render(Bitmap root) {
		// TextLines
		for(TextLine line : texts) {
			line.render(root);
		}
	}
	
}
