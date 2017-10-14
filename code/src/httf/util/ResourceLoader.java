package httf.util;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import httf.ui.Bitmap;

public class ResourceLoader {
	
	public static Bitmap loadTexture(String res) {
		try {
			BufferedImage img = ImageIO.read(ResourceLoader.class.getResourceAsStream("/tex/" + res + ".png"));
			Bitmap result = new Bitmap(img.getWidth(), img.getHeight());
			for(int x = 0; x < img.getWidth(); x++) {
				for(int y = 0; y < img.getHeight(); y++) {
					result.pixels[x][y] = img.getRGB(x, y);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
