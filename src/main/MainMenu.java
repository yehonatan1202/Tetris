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
        int x = JOptionPane.showOptionDialog(null, "Select:",
                "Tetris",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        JFrame window;
        GamePanel gamePanel;
        switch (x) {
            case 0:
                window = new JFrame();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Tetris");
                window.setLayout(new GridBagLayout());
                gamePanel = new GamePanel();
                gamePanel.setBackground(Color.black);
                window.add(gamePanel);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                break;
            case 1:
                window = new JFrame();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Tetris");
                window.setLayout(new GridBagLayout());
                gamePanel = new GamePanel(2);
                gamePanel.setBackground(Color.black);
                window.add(gamePanel);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                Server server = new Server(gamePanel);
                server.connect();
                server.startServerThread();
                break;
            case 2:
                window = new JFrame();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Tetris");
                window.setLayout(new GridBagLayout());
                gamePanel = new GamePanel(2);
                gamePanel.setBackground(Color.black);
                window.add(gamePanel);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                Client client = new Client(gamePanel);
                client.connect();
                client.startClientThread();
                break;
        }
    }

}
