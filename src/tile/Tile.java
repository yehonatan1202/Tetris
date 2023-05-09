package tile;
import javax.swing.ImageIcon;


public class Tile {
	public ImageIcon image;

	public Tile(String path){
			this.image = new ImageIcon(path);
	}
}