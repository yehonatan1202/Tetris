package main;

import java.awt.Graphics2D;
import java.util.Random;

public class Piece {
	GamePanel gp;
	int posX, posY;
	int[][] shape;
	int tile;
	int time = 0;

	public Piece(GamePanel gp) {
		gp.tileM.check();
		this.gp = gp;
		posY = 0;
		posX = gp.ScreenCol / 2;
		Random rand = new Random();
		shape = new int[4][4];
		int rnd = rand.nextInt(7);
		switch (rnd) {
			case 0:
				tile = 1;
				shape[0][0] = 1;
				shape[0][1] = 1;
				shape[0][2] = 1;
				shape[0][3] = 1;

				shape[1][0] = 0;
				shape[1][1] = 0;
				shape[1][2] = 0;
				shape[1][3] = 0;

				shape[2][0] = 0;
				shape[2][1] = 0;
				shape[2][2] = 0;
				shape[2][3] = 0;

				shape[3][0] = 0;
				shape[3][1] = 0;
				shape[3][2] = 0;
				shape[3][3] = 0;
				break;
			case 1:
				tile = 2;
				shape[0][0] = 0;
				shape[0][1] = 0;
				shape[0][2] = 0;
				shape[0][3] = 0;

				shape[1][0] = 1;
				shape[1][1] = 1;
				shape[1][2] = 0;
				shape[1][3] = 0;

				shape[2][0] = 1;
				shape[2][1] = 1;
				shape[2][2] = 0;
				shape[2][3] = 0;

				shape[3][0] = 0;
				shape[3][1] = 0;
				shape[3][2] = 0;
				shape[3][3] = 0;
				break;
			case 2:
				tile = 3;
				shape[0][0] = 0;
				shape[0][1] = 0;
				shape[0][2] = 0;
				shape[0][3] = 0;

				shape[1][0] = 1;
				shape[1][1] = 0;
				shape[1][2] = 0;
				shape[1][3] = 0;

				shape[2][0] = 1;
				shape[2][1] = 1;
				shape[2][2] = 1;
				shape[2][3] = 0;

				shape[3][0] = 0;
				shape[3][1] = 0;
				shape[3][2] = 0;
				shape[3][3] = 0;
				break;
			case 3:
				tile = 4;
				shape[0][0] = 0;
				shape[0][1] = 0;
				shape[0][2] = 0;
				shape[0][3] = 0;

				shape[1][0] = 0;
				shape[1][1] = 0;
				shape[1][2] = 0;
				shape[1][3] = 1;

				shape[2][0] = 0;
				shape[2][1] = 1;
				shape[2][2] = 1;
				shape[2][3] = 1;

				shape[3][0] = 0;
				shape[3][1] = 0;
				shape[3][2] = 0;
				shape[3][3] = 0;
				break;
			case 4:
				tile = 5;
				shape[0][0] = 0;
				shape[0][1] = 1;
				shape[0][2] = 0;
				shape[0][3] = 0;

				shape[1][0] = 1;
				shape[1][1] = 1;
				shape[1][2] = 1;
				shape[1][3] = 0;

				shape[2][0] = 0;
				shape[2][1] = 0;
				shape[2][2] = 0;
				shape[2][3] = 0;

				shape[3][0] = 0;
				shape[3][1] = 0;
				shape[3][2] = 0;
				shape[3][3] = 0;
				break;
			case 5:
				tile = 6;
				shape[0][0] = 0;
				shape[0][1] = 0;
				shape[0][2] = 0;
				shape[0][3] = 0;

				shape[1][0] = 0;
				shape[1][1] = 0;
				shape[1][2] = 1;
				shape[1][3] = 1;

				shape[2][0] = 0;
				shape[2][1] = 1;
				shape[2][2] = 1;
				shape[2][3] = 0;

				shape[3][0] = 0;
				shape[3][1] = 0;
				shape[3][2] = 0;
				shape[3][3] = 0;
				break;
			case 6:
				tile = 7;
				shape[0][0] = 0;
				shape[0][1] = 0;
				shape[0][2] = 0;
				shape[0][3] = 0;

				shape[1][0] = 1;
				shape[1][1] = 1;
				shape[1][2] = 0;
				shape[1][3] = 0;

				shape[2][0] = 0;
				shape[2][1] = 1;
				shape[2][2] = 1;
				shape[2][3] = 0;

				shape[3][0] = 0;
				shape[3][1] = 0;
				shape[3][2] = 0;
				shape[3][3] = 0;
				break;
		}
		if (checkLost() == true) {
			gp.gameOver();
		}
	}

	void rotateClockwise() {
		for (int i = 0; i < 4 / 2; i++) {
			for (int j = i; j < 4 - i - 1; j++) {

				// Swap elements of each cycle
				// in clockwise direction
				int temp = shape[i][j];
				shape[i][j] = shape[4 - 1 - j][i];
				shape[4 - 1 - j][i] = shape[4 - 1 - i][4 - 1 - j];
				shape[4 - 1 - i][4 - 1 - j] = shape[j][4 - 1 - i];
				shape[j][4 - 1 - i] = temp;
			}
		}
	}

	void rotateCounterlockwise() {
		for (int x = 0; x < 4 / 2; x++) {
			for (int y = x; y < 4 - x - 1; y++) {
				// Store current cell in
				// temp variable
				int temp = shape[x][y];
				// Move values from right to top
				shape[x][y] = shape[y][4 - 1 - x];
				// Move values from bottom to right
				shape[y][4 - 1 - x] = shape[4 - 1 - x][4 - 1 - y];
				// Move values from left to bottom
				shape[4 - 1 - x][4 - 1 - y] = shape[4 - 1 - y][x];
				// Assign temp to left
				shape[4 - 1 - y][x] = temp;
			}
		}
	}

	void updateMap() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape[i][j] == 1) {
					gp.tileM.mapTileNum[posY + i - 1][posX + j] = tile;
				}
			}
		}
	}

	boolean edgeCollision() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape[i][j] == 1 && (posX + j < 0 || posX + j > 9 || posY + i > 19
						|| gp.tileM.mapTileNum[posY + i][posX + j] != 0)) {
					return true;
				}
			}
		}
		return false;
	}

	boolean downCollision() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape[i][j] != 0 && (posY + i > 19 || gp.tileM.mapTileNum[posY + i][posX + j] != 0)) {
					updateMap();
					gp.newPiece();
					return true;
				}
			}
		}
		return false;
	}

	boolean checkLost() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape[i][j] != 0 && gp.tileM.mapTileNum[posY + i][posX + j] != 0) {
					return true;
				}
			}
		}
		return false;
	}

	void drop() {
		posY++;
		if (downCollision() == true) {
			return;
		}
	}

	void update() {
		// posY++;
		// if (downCollision()) {
		// return;
		// }
		// posY--;
		if (gp.keyH.downPressed == true) {
			posY++;
			downCollision();
			gp.keyH.downPressed = false;
		}

		if (gp.keyH.rightPressed == true) {
			posX++;
			if (edgeCollision() == true) {
				posX--;
			}
			gp.keyH.rightPressed = false;
		}
		if (gp.keyH.leftPressed == true) {
			posX--;
			if (edgeCollision() == true) {
				posX++;
			}
			gp.keyH.leftPressed = false;
		}
		if (gp.keyH.upPressed == true) {
			gp.keyH.upPressed = false;
			rotateClockwise();
			if (edgeCollision() == true || downCollision() == true) {
				rotateCounterlockwise();
			}
		}
	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape[i][j] != 0) {
					int screenX = (j + posX) * gp.tileSize;
					int screenY = (i + posY) * gp.tileSize;
					g2.drawImage(gp.tileM.tile[tile].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
				}
			}
		}
	}

}
