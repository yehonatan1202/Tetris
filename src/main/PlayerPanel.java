package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

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
    int delay = 1000;
    long startTime = System.currentTimeMillis() % 10000 / delay;
    long lastTime = startTime;
    GamePanel gamePanel;
    //Piece currentPiece;
    

    public PlayerPanel(GamePanel gamePanel){
    	this.gamePanel = gamePanel;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void newPiece() {
        lastTime = startTime;
        gamePanel.nextPiecePanel.newPiece();
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
            gamePanel.nextPiecePanel.currentPiece.drop();
        }
        gamePanel.nextPiecePanel.currentPiece.update();
        startTime = System.currentTimeMillis() % 10000 / delay;
    }

    public void paintComponent(Graphics g) {
        // super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        gamePanel.playerTileManager.draw(g2);
        gamePanel.nextPiecePanel.currentPiece.draw(g2);
        g2.dispose();

    }
}
