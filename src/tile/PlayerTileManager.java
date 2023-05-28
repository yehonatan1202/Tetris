package tile;

import java.awt.Graphics2D;
import java.util.Random;

import main.GamePanel;
import main.PlayerPanel;

public class PlayerTileManager extends TileManager {
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
		gamePanel.statsPanel.score += linesPoints[numOfRaws] * (gamePanel.statsPanel.level + 1);
		gamePanel.statsPanel.numOfLines += numOfRaws;
		if (gamePanel.statsPanel.numOfLines - gamePanel.statsPanel.level * 10 >= 10) {
			gamePanel.statsPanel.levelUp();
		}
		if (numOfRaws > 1) {
			gamePanel.playerPanel.sendRows = numOfRaws - 1;
		}
	}

	public void removeRaw(int row) {
		for (int i = row; i > 0; i--) {
			for (int j = 0; j < playerPanel.ScreenCol; j++) {
				playerTilesMap[i][j] = playerTilesMap[i - 1][j];
			}
		}
	}

	public void addRows(int numOfRaws) {
		// Check for lost
		for (int j = 0; j < playerPanel.ScreenCol; j++) {
			if (playerTilesMap[numOfRaws][j] != 0) {
				gamePanel.lost = true;
				this.gamePanel.playerPanel.gameOver();
				return;
			}
		}

		// Raise rows
		for (int i = 0; i <= playerPanel.ScreenRow - numOfRaws - 1; i++) {
			for (int j = 0; j < playerPanel.ScreenCol; j++) {
				playerTilesMap[i][j] = playerTilesMap[i + numOfRaws][j];
			}
		}

		// Add rows
		for (int i = playerPanel.ScreenRow - 1; i > playerPanel.ScreenRow - numOfRaws - 1; i--) {
			Random random = new Random();
			int spaceIndex = random.nextInt(0, playerPanel.ScreenCol);
			for (int j = 0; j < playerPanel.ScreenCol; j++) {
				if (j != spaceIndex) {
					playerTilesMap[i][j] = 1;
				} else {
					playerTilesMap[i][j] = 0;
				}
			}
		}

	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < playerPanel.ScreenRow; i++) {
			for (int j = 0; j < playerPanel.ScreenCol; j++) {
				int screenX = j * gamePanel.tileSize;
				int screenY = i * gamePanel.tileSize;
				g2.drawImage(tile[playerTilesMap[i][j]].image.getImage(), screenX, screenY, gamePanel.tileSize,
						gamePanel.tileSize,
						null);
			}
		}
	}
}
