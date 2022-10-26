package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import file.Client;
import file.Server;

import java.awt.GridBagLayout;
import java.awt.Color;

public class MainMenu {
    public MainMenu() {
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

}
