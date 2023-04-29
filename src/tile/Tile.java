package tile;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.io.FileInputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Tile {
	public ImageIcon image;

	public Tile(String path){
			this.image = new ImageIcon(path);
	}
}
