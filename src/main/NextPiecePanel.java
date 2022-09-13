package main;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class NextPiecePanel extends JPanel {
    GamePanel gamePanel;
    Piece nextPiece;

    public NextPiecePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.nextPiece = gamePanel.playerPanel.nextPiece;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; i++) {
                int screenX = j * gamePanel.tileSize;
                int screenY = i * gamePanel.tileSize;
                g2.drawImage(GamePanel.TileManager.tile[nextPiece.shape[i][j]].image, screenX, screenY,
                        gamePanel.tileSize, gamePanel.tileSize,
                        null);
            }
        }
        g2.dispose();

    }

}
