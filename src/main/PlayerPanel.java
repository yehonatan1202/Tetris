package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PlayerPanel extends JPanel implements Runnable {
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
	int delay;
	long startTime;
	long lastTime;
	GamePanel gamePanel;
	// Piece currentPiece;

	public PlayerPanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;

		delay = 1000 - (16 * gamePanel.statsPanel.level);
		startTime = System.currentTimeMillis() % 10000 / delay;
		lastTime = startTime;

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}

	public void newPiece() {
		lastTime = startTime;
		gamePanel.nextPiecePanel.newPiece();
	}

	public void gameOver() {
		System.out.println("Game Over!");
		if (gamePanel.isSolo == true) {
			int index = gamePanel.leaderboard.addRecord(gamePanel.statsPanel.score);
			if (index != -1) {
				System.out.println("new record: " + gamePanel.statsPanel.score + "pt");
				String name = JOptionPane.showInputDialog(gamePanel, "What is your name?", "New Record: " + gamePanel.statsPanel.score + "pt", JOptionPane.INFORMATION_MESSAGE);
				if (name == null || name.isEmpty()) {
					name = "Player";
				} else {
					name = name.replaceAll(" ", "_");
				}
				gamePanel.leaderboard.setRecordName(name, index);
			}
			gameThread = null;
			gamePanel.nextPiecePanel.nextPieceThread = null;
		} else {
			gameThread = null;
			gamePanel.nextPiecePanel.nextPieceThread = null;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (gamePanel.lost == true) {
				JOptionPane.showMessageDialog(gamePanel, "You Lost!", "", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(gamePanel, "You Won!", "", JOptionPane.INFORMATION_MESSAGE);
			}
			gamePanel.lost = false;
			gamePanel.clientSever.end();
		}
		JFrame window = (JFrame) (SwingUtilities.getWindowAncestor(gamePanel));
		MainMenu mainMenu = new MainMenu(window);
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
			if (gamePanel.gameRunning == true) {
				currentTime = System.nanoTime();
				delta += (currentTime - lastTime) / drawInterval;
				lastTime = currentTime;
				if (delta >= 1) {
					update();
					repaint();
					delta--;
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
		if (startTime != lastTime) {
			lastTime = startTime;
			gamePanel.nextPiecePanel.currentPiece.drop();
		}
		gamePanel.nextPiecePanel.currentPiece.update();
		gamePanel.nextPiecePanel.shadowPiece = new Piece(gamePanel.nextPiecePanel.currentPiece);
		gamePanel.nextPiecePanel.shadowPiece.shadowUpdate();
		startTime = System.currentTimeMillis() % 10000 / delay;
	}

	public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		gamePanel.playerTileManager.draw(g2);
		gamePanel.nextPiecePanel.currentPiece.draw(g2);
		gamePanel.nextPiecePanel.shadowPiece.draw(g2);
		g2.dispose();

	}
}