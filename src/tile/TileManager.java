package tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.ScreenRow][gp.ScreenCol];
		getTileImage();
	}

	public void getTileImage() {
		try {
			// black
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/black.png"));
			// blue
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/blue.png"));
			// green
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/green.png"));
			// pink
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pink.png"));
			// red
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/red.png"));

			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/red.png"));
			// blue
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/blue.png"));
			// green
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/green.png"));
			// pink
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pink.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void check() {
		int counter;
		for (int i = 0; i < gp.ScreenRow; i++) {
			counter = 0;
			for (int j = 0; j < gp.ScreenCol; j++) {
				if (mapTileNum[i][j] != 0) {
					counter++;
				}
			}
			if (counter == 10) {
				removeRaw(i);
			}
		}
	}

	public void removeRaw(int row) {
		for (int i = row; i > 0; i--) {
			for (int j = 0; j < gp.ScreenCol; j++) {
				mapTileNum[i][j] = mapTileNum[i - 1][j];
			}
		}
	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < gp.ScreenRow; i++) {
			for (int j = 0; j < gp.ScreenCol; j++) {
				int screenX = j * gp.tileSize;
				int screenY = i * gp.tileSize;
				g2.drawImage(tile[mapTileNum[i][j]].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
		}
	}
}