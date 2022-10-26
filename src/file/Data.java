package file;

import java.io.Serializable;

import main.GamePanel;
import main.Piece;

public class Data implements Serializable {
	transient GamePanel gamePanel;
	public int playerTilesMap[][];

	public Data(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		playerTilesMap = new int[20][10];
	}

	public void upload() {
		for (int i = 0; i < gamePanel.playerPanel.ScreenRow; i++) {
			for (int j = 0; j < gamePanel.playerPanel.ScreenCol; j++) {
				playerTilesMap[i][j] = gamePanel.playerTileManager.playerTilesMap[i][j];
			}
		}
		Piece piece = gamePanel.nextPiecePanel.currentPiece;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (piece.shape[i][j] != 0) {
					playerTilesMap[i + piece.posY][j + piece.posX] = piece.tile;
				}
			}
		}
	}

	public void download(GamePanel gamePanel) {
		for (int i = 0; i < gamePanel.playerPanel.ScreenRow; i++) {
			for (int j = 0; j < gamePanel.playerPanel.ScreenCol; j++) {
				gamePanel.opponentTileManager.opponentTilesMap[i][j] = playerTilesMap[i][j];
			}
		}
		// gamePanel.opponenetPanel.currentPiece = playerCurrentPiece;
	}
}
