package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager implements Runnable {
	GamePanel gp;
	public Tile[] tile;
	public static int mapTileNum[][];
	public static int opponenetMapTileNum[][];
	Thread mapThread;

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.ScreenRow][gp.ScreenCol];
		opponenetMapTileNum = new int[gp.ScreenRow][gp.ScreenCol];
		getTileImage();
	}
	public void startTileManagerThread(){
		mapThread = new Thread(this);
		mapThread.start();
	}

	public void getTileImage() {
		try {
			// black
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(new FileInputStream("res/tiles/black.png"));
			// blue
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(new FileInputStream("res/tiles/blue.png"));
			// green
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(new FileInputStream("res/tiles/green.png"));
			// pink
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(new FileInputStream("res/tiles/pink.png"));
			// red
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(new FileInputStream("res/tiles/red.png"));

			tile[5] = new Tile();
			tile[5].image = ImageIO.read(new FileInputStream("res/tiles/red.png"));
			// blue
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(new FileInputStream("res/tiles/blue.png"));
			// green
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(new FileInputStream("res/tiles/green.png"));
			// pink
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(new FileInputStream("res/tiles/pink.png"));

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

	@Override
	public void run() {
		while (mapThread != null) {
			try {
				FileWriter playerMap = new FileWriter("res/maps/playerMap.txt");
				playerMap.write("");
				for (int i = 0; i < gp.ScreenRow; i++) {
					for (int j = 0; j < gp.ScreenCol; j++) {
						playerMap.append((char) (mapTileNum[i][j] + '0'));
						playerMap.append((char) ' ');
					}
					playerMap.append('\n');
				}
				playerMap.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//////////////////////////////////////////////////////////////////////////////////////
			try {
				BufferedReader br = new BufferedReader(new FileReader(new File("res/maps/playerMap.txt")));
				for (int i = 0; i < gp.ScreenRow; i++) {
					String line = br.readLine();
					String numbers[] = line.split(" ");
					for (int j = 0; j < gp.ScreenCol; j++) {
						int num = Integer.parseInt(numbers[j]);
						opponenetMapTileNum[i][j] = num;
					}
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
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

	public void drawOpponent(Graphics2D g2) {
		for (int i = 0; i < gp.ScreenRow; i++) {
			for (int j = 0; j < gp.ScreenCol; j++) {
				int screenX = j * gp.tileSize;
				int screenY = i * gp.tileSize;
				g2.drawImage(tile[opponenetMapTileNum[i][j]].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
		}
	}
}