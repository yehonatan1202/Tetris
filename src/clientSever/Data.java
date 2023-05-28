package clientSever;

import java.io.Serializable;

import main.GamePanel;
import main.Piece;
import main.ThreadsLock;

public class Data implements Serializable {
	public int playerTilesMap[][];
	public boolean running;
	public boolean lost;
	public int sendRows;
	transient GamePanel gamePanel;

	public Data(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		playerTilesMap = new int[20][10];
	}

	public void upload() {
		synchronized (ThreadsLock.INSTANCE){
			this.running = gamePanel.myRunning;
			this.lost = gamePanel.lost;
	
			this.sendRows = gamePanel.playerPanel.sendRows;
			gamePanel.playerPanel.sendRows = 0;
	
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
	}

	public void download(GamePanel gamePanel) {
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
			}
		}
		for (int i = 0; i < gamePanel.playerPanel.ScreenRow; i++) {
			for (int j = 0; j < gamePanel.playerPanel.ScreenCol; j++) {
				gamePanel.opponentTileManager.opponentTilesMap[i][j] = playerTilesMap[i][j];
			}
		}
		if(lost == true){
			gamePanel.playerPanel.gameOver();
		}
		if(sendRows > 0){
			gamePanel.playerTileManager.addRows(sendRows);
		}
	}
}
