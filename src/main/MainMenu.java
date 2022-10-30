package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class MainMenu extends JPanel {

    public MainMenu() {
        this.setPreferredSize(new Dimension(832, 704));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        repaint();
    }

    public void start() {
        String[] options = { "Solo", "Create", "Join" };
        int mode = JOptionPane.showOptionDialog(null, "Select:",
                "Tetris",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        JFrame window;
        GamePanel gamePanel;
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tetris" + mode);
        window.setLayout(new GridBagLayout());
        gamePanel = new GamePanel(mode);
        gamePanel.setBackground(Color.black);
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        // super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        g2.dispose();

    }
}
