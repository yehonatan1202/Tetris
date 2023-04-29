package file;

import java.io.Serializable;

import main.GamePanel;
import main.Piece;

public class Data implements Serializable {
	transient GamePanel gamePanel;
	public int playerTilesMap[][];
	public boolean running;

	public Data(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		playerTilesMap = new int[20][10];
	}

	public void upload() {
		this.running = gamePanel.myRunning;
		for (int i = 0; i < gamePanel.playerPanel.ScreenRow; i++) {
			for (int j = 0; j < gamePanel.playerPanel.ScreenCol; j++) {
				playerTilesMap[i][j] = gamePanel.playerTileManager.playerTilesMap[i][j];
			}
		}
		Piece piece = gamePanel.nextPiecePanel.currentPiece;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (piece.shape[i][j] != 0 && i + piece.posY < 20 && j + piece.posX < 10) {
					playerTilesMap[i + piece.posY][j + piece.posX] = piece.tile;
				}
			}
		}
	}

	public void download(GamePanel gamePanel) {
		// move to a diffrent place should run even when the player/nextPiece thread
		// stopes
		if (gamePanel.keyHandler.escPressed == true) {
			gamePanel.myRunning = !gamePanel.myRunning;
			gamePanel.keyHandler.escPressed = false;
		}

		if (gamePanel.myRunning != gamePanel.gameRunning || gamePanel.gameRunning != running
				|| gamePanel.myRunning != running) {
			if (running == false && gamePanel.myRunning == false) {
				gamePanel.gameRunning = false;
			} else if (running == true && gamePanel.myRunning == true) {
				gamePanel.gameRunning = true;
				// gamePanel.resumeGame();
			}
		}
		for (int i = 0; i < gamePanel.playerPanel.ScreenRow; i++) {
			for (int j = 0; j < gamePanel.playerPanel.ScreenCol; j++) {
				gamePanel.opponentTileManager.opponentTilesMap[i][j] = playerTilesMap[i][j];
			}
		}
		// gamePanel.opponenetPanel.currentPiece = playerCurrentPiece;
	}
}
