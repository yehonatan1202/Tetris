package tile;

import java.awt.Graphics2D;

import main.GamePanel;
import main.PlayerPanel;

public class PlayerTileManager extends TileManager {
	// GamePanel gamePanel;
	// public static Tile[] tile;
	public int playerTilesMap[][];
	public PlayerPanel playerPanel;

	public PlayerTileManager(GamePanel gamePanel) {
		super(gamePanel);
		this.playerPanel = this.gamePanel.playerPanel;
		this.playerTilesMap = new int[playerPanel.ScreenRow][playerPanel.ScreenCol];
		getTileImage();
	}

	public void check() {
		int numOfRaws = 0;
		int linesPoints[] = { 0, 40, 100, 300, 1200 };
		int counter;
		for (int i = 0; i < playerPanel.ScreenRow; i++) {
			counter = 0;
			for (int j = 0; j < playerPanel.ScreenCol; j++) {
				if (playerTilesMap[i][j] != 0) {
					counter++;
				}
			}
			if (counter == 10) {
				removeRaw(i);
				numOfRaws++;
			}
		}
		playerPanel.score += linesPoints[numOfRaws] * (playerPanel.level + 1);
		playerPanel.numOfLines += numOfRaws;
		if (playerPanel.numOfLines - playerPanel.level * 10 >= 10) {
			playerPanel.levelUp();
		}
	}

	public void removeRaw(int row) {
		for (int i = row; i > 0; i--) {
			for (int j = 0; j < playerPanel.ScreenCol; j++) {
				playerTilesMap[i][j] = playerTilesMap[i - 1][j];
			}
		}
	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < playerPanel.ScreenRow; i++) {
			for (int j = 0; j < playerPanel.ScreenCol; j++) {
				int screenX = j * gamePanel.tileSize;
				int screenY = i * gamePanel.tileSize;
				g2.drawImage(tile[playerTilesMap[i][j]].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize,
						null);
			}
		}
	}
}
