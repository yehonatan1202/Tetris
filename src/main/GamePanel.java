package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	public final int originalTitleSize = 16;
	public final int scale = 2;
	public final int tileSize = originalTitleSize * scale;
	public final int ScreenCol = 10;
	public final int ScreenRow = 20;
	public int screenWidth = tileSize * ScreenCol;
	public int screenHeight = tileSize * ScreenRow;

	int FPS = 60;

	Thread gameThread;
	// Time
	int delay = 1000;
	long startTime = System.currentTimeMillis() % 10000 / delay;
	long lastTime = startTime;
	public TileManager tileM = new TileManager(this);
	Piece piece = new Piece(this);
	Piece nextPiece = new Piece(this);
	KeyHandler keyH = new KeyHandler();;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		// this.addMouseMotionListener(mouseH);
		// this.addMouseListener(mouseH);
		this.setFocusable(true);
		tileM.startTileManagerThread();
	}

	public void newPiece() {
		lastTime = startTime;
		piece = nextPiece;
		nextPiece = new Piece(this);
	}

	public void gameOver() {
		System.out.println("lost!");
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
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
		if (startTime != lastTime) {
			lastTime = startTime;
			piece.drop();
		}
		piece.update();
		startTime = System.currentTimeMillis() % 10000 / delay;
	}

	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		tileM.draw(g2);
		piece.draw(g2);
		g2.dispose();

	}
}
