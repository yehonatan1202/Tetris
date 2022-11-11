package main;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import javax.swing.JPanel;

public class StatsPanel extends JPanel implements Runnable {
    public final int originalTitleSize = 16;
    public final int scale = 2;
    public final int tileSize = originalTitleSize * scale;
    public final int ScreenCol = 4;
    public final int ScreenRow = 4;
    public int screenWidth = tileSize * ScreenCol;
    public int screenHeight = tileSize * ScreenRow;

    GamePanel gamePanel;

    public int level;
    public int score;
    public int numOfLines;

    int FPS = 60;

    Font font;

    Thread statsThread;

    public StatsPanel(GamePanel gamePanel) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.gamePanel = gamePanel;
        level = 0;
        score = 0;
        load_font();
    }

    public void startGameThread() {
        statsThread = new Thread(this);
        statsThread.start();
    }

    void load_font() {
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/DiaryOfAn8BitMage-lYDD.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void levelUp() {
        level++;
        gamePanel.playerPanel.delay -= 16;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (statsThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                // update();
                repaint();
                delta--;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        g2.setFont(font);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        g2.setColor(Color.white);
        g2.drawString("Level: " + Integer.toString(level), 0, screenHeight / 2);
        g2.drawString("Score: " + Integer.toString(score), 0, (screenHeight / 2) + 20);

        g2.dispose();
    }
}
