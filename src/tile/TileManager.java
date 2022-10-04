package tile;

import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gamePanel;
	public static Tile[] tile;

	public TileManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		tile = new Tile[10];
		getTileImage();
	}

	// public void startTileManagerThread() {
	// mapThread = new Thread(this);
	// mapThread.start();
	// }

	public void getTileImage() {
		try {
			// black
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(new FileInputStream("res/tiles/tile_0.png"));
			// blue
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(new FileInputStream("res/tiles/tile_1.png"));
			// green
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(new FileInputStream("res/tiles/tile_2.png"));
			// pink
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(new FileInputStream("res/tiles/tile_3.png"));
			// red
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(new FileInputStream("res/tiles/tile_4.png"));

			tile[5] = new Tile();
			tile[5].image = ImageIO.read(new FileInputStream("res/tiles/tile_5.png"));
			// blue
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(new FileInputStream("res/tiles/tile_6.png"));
			// green
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(new FileInputStream("res/tiles/tile_7.png"));
			// pink
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(new FileInputStream("res/tiles/tile_8.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}