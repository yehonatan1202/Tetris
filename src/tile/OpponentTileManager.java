package tile;

import java.awt.Graphics2D;

import main.GamePanel;
import main.OpponentPanel;

public class OpponentTileManager extends TileManager {
	public int opponentTilesMap[][];
	public OpponentPanel opponentPanel;

	public OpponentTileManager(GamePanel gamePanel) {
		super(gamePanel);
		this.opponentPanel = this.gamePanel.opponenetPanel;
		this.opponentTilesMap = new int[opponentPanel.ScreenRow][opponentPanel.ScreenCol];
		getTileImage();
	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < opponentPanel.ScreenRow; i++) {
			for (int j = 0; j < opponentPanel.ScreenCol; j++) {
				int screenX = j * gamePanel.tileSize;
				int screenY = i * gamePanel.tileSize;
				g2.drawImage(tile[opponentTilesMap[i][j]].image.getImage(), screenX, screenY, gamePanel.tileSize,
						gamePanel.tileSize, null);
			}
		}
	}
}
