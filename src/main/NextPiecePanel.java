package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import tile.TileManager;

public class NextPiecePanel extends JPanel implements Runnable{
    public final int originalTitleSize = 16;
    public final int scale = 2;
    public final int tileSize = originalTitleSize * scale;
    public final int ScreenCol = 4;
    public final int ScreenRow = 4;
    public int screenWidth = tileSize * ScreenCol;
    public int screenHeight = tileSize * ScreenRow;
    
    GamePanel gamePanel;
    Piece currentPiece;
    Piece nextPiece;
    
    int FPS = 60;

    Thread NextPieceThread;
    
    public NextPiecePanel(GamePanel gamePanel) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.gamePanel = gamePanel;
        this.currentPiece = new Piece(gamePanel);
        this.nextPiece = new Piece(gamePanel);
    }
	public void startGameThread() {
		NextPieceThread = new Thread(this);
		NextPieceThread.start();
	}
	
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (NextPieceThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				//update();
				repaint();
				delta--;
			}
		}
	}
	
    public void newPiece() {
    	currentPiece = nextPiece;
    	nextPiece = new Piece(gamePanel);
    }
    public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int screenX = j * gamePanel.tileSize;
                int screenY = i * gamePanel.tileSize;
                g2.drawImage(TileManager.tile[nextPiece.shape[i][j]].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }
		g2.dispose();
	}
}
