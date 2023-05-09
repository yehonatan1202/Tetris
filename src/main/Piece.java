package main;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Random;

import tile.TileManager;

public class Piece implements Serializable {
	public int posX, posY;
	public int[][] shape;
	public int tile;

	transient GamePanel gamePanel;

	public Piece(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.gamePanel.playerTileManager.check();
		posY = 0;
		posX = gamePanel.playerPanel.ScreenCol / 2 - 2;
		Random rand = new Random();
		shape = new int[4][4];
		int rnd = rand.nextInt(7);
		switch (rnd) {
			case 0:
				tile = 1;
				shape = new int[][] {
						{ 0, 0, 0, 0 },
						{ 1, 1, 1, 1 },
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 0 } };

				break;

			case 1:
				tile = 2;
				shape = new int[][] {
						{ 0, 0, 0, 0 },
						{ 0, 1, 1, 0 },
						{ 0, 1, 1, 0 },
						{ 0, 0, 0, 0 } };
				break;

			case 2:
				tile = 3;
				shape = new int[][] {
						{ 0, 0, 0, 0 },
						{ 1, 0, 0, 0 },
						{ 1, 1, 1, 0 },
						{ 0, 0, 0, 0 } };
				break;

			case 3:
				tile = 4;
				shape = new int[][] {
						{ 0, 0, 0, 0 },
						{ 0, 0, 0, 1 },
						{ 0, 1, 1, 1 },
						{ 0, 0, 0, 0 } };
				break;

			case 4:
				tile = 5;
				shape = new int[][] {
						{ 0, 0, 0, 0 },
						{ 0, 1, 0, 0 },
						{ 1, 1, 1, 0 },
						{ 0, 0, 0, 0 } };
				break;

			case 5:
				tile = 6;
				shape = new int[][] {
						{ 0, 0, 0, 0 },
						{ 0, 0, 1, 1 },
						{ 0, 1, 1, 0 },
						{ 0, 0, 0, 0 } };
				break;

			case 6:
				tile = 7;
				shape = new int[][] {
						{ 0, 0, 0, 0 },
						{ 1, 1, 0, 0 },
						{ 0, 1, 1, 0 },
						{ 0, 0, 0, 0 } };
				break;
		}
		if (checkLost() == true) {
			gamePanel.lost = true;
			this.gamePanel.playerPanel.gameOver();
		}
	}

	public Piece(Piece piece) {
		this.gamePanel = piece.gamePanel;
		this.posX = piece.posX;
		this.posY = piece.posY;
		this.shape = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.shape[i][j] = piece.shape[i][j];
			}
		}
		this.tile = piece.tile + 9;
	}

	void rotateClockwise() {
		for (int i = 0; i < 4 / 2; i++) {
			for (int j = i; j < 4 - i - 1; j++) {
				int temp = shape[i][j];
				shape[i][j] = shape[4 - 1 - j][i];
				shape[4 - 1 - j][i] = shape[4 - 1 - i][4 - 1 - j];
				shape[4 - 1 - i][4 - 1 - j] = shape[j][4 - 1 - i];
				shape[j][4 - 1 - i] = temp;
			}
		}
	}

	void rotateCounterlockwise() {
		for (int i = 0; i < 4 / 2; i++) {
			for (int j = i; j < 4 - i - 1; j++) {
				int temp = shape[i][j];
				shape[i][j] = shape[j][4 - 1 - i];
				shape[j][4 - 1 - i] = shape[4 - 1 - i][4 - 1 - j];
				shape[4 - 1 - i][4 - 1 - j] = shape[4 - 1 - j][i];
				shape[4 - 1 - j][i] = temp;
			}
		}
	}

	void updateMap() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape[i][j] == 1) {
					gamePanel.playerTileManager.playerTilesMap[posY + i - 1][posX + j] = tile;
				}
			}
		}
	}

	boolean edgeCollision() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape[i][j] != 0 && (posX + j < 0 || posX + j > 9 || posY + i > 19
						|| gamePanel.playerTileManager.playerTilesMap[posY + i][posX + j] != 0)) {
					return true;
				}
			}
		}
		return false;
	}

	boolean downCollision() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape[i][j] != 0
						&& (posY + i > 19 || gamePanel.playerTileManager.playerTilesMap[posY + i][posX + j] != 0)) {
					return true;
				}
			}
		}
		return false;
	}

	boolean checkLost() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape[i][j] != 0 && gamePanel.playerTileManager.playerTilesMap[posY + i][posX + j] != 0) {
					return true;
				}
			}
		}
		return false;
	}

	void drop() {
		posY++;
		if (downCollision() == true) {
			updateMap();
			gamePanel.playerPanel.newPiece();
			return;
		}
	}

	void shadowUpdate() {
		while (downCollision() == false) {
			posY++;
		}
		posY--;
	}

	void update() {
		if (gamePanel.keyHandler.downPressed == true) {
			posY++;
			if (downCollision() == true) {
				updateMap();
				gamePanel.playerPanel.newPiece();
			}
			gamePanel.keyHandler.downPressed = false;
		}

		if (gamePanel.keyHandler.rightPressed == true) {
			posX++;
			if (edgeCollision() == true) {
				posX--;
			}
			gamePanel.keyHandler.rightPressed = false;
		}
		if (gamePanel.keyHandler.leftPressed == true) {
			posX--;
			if (edgeCollision() == true) {
				posX++;
			}
			gamePanel.keyHandler.leftPressed = false;
		}
		if (gamePanel.keyHandler.upPressed == true) {
			gamePanel.keyHandler.upPressed = false;
			rotateClockwise();
			if (edgeCollision() == true || downCollision() == true) {
				rotateCounterlockwise();
			}
		}
		if (gamePanel.keyHandler.spacePressed == true) {
			while (downCollision() == false) {
				posY++;
				gamePanel.statsPanel.score++;
			}
			updateMap();
			gamePanel.playerPanel.newPiece();
			gamePanel.keyHandler.spacePressed = false;
		}
	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape[i][j] != 0) {
					int screenX = (j + posX) * gamePanel.tileSize;
					int screenY = (i + posY) * gamePanel.tileSize;
					g2.drawImage(TileManager.tile[tile].image.getImage(), screenX, screenY, gamePanel.tileSize,
							gamePanel.tileSize,
							null);
				}
			}
		}
	}

}
