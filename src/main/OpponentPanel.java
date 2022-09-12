package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class OpponentPanel extends JPanel implements Runnable {
    public final int originalTitleSize = 16;
    public final int scale = 2;
    public final int tileSize = originalTitleSize * scale;
    public final int ScreenCol = 10;
    public final int ScreenRow = 20;
    public int screenWidth = tileSize * ScreenCol;
    public int screenHeight = tileSize * ScreenRow;

    int FPS = 60;

    Thread opponentGameThread;
    // Time
    GamePanel gamePanel;

    public OpponentPanel(GamePanel gamePanel){
    	this.gamePanel = gamePanel;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

	public void startGameThread() {
		opponentGameThread = new Thread(this);
		opponentGameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (opponentGameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}

	public void update() {

	}

	public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		gamePanel.opponentTileManager.draw(g2);
		g2.dispose();
	}
}
