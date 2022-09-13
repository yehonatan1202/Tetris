package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class Application {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Tetris");
		window.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
//		PlayerPanel gamePanel = new PlayerPanel();
//		gamePanel.startGameThread();
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		gbc.gridwidth = 1;
//		gbc.gridheight = 2;
//		window.add(gamePanel, gbc);
//		OpponentPanel opponentgamePanel = new OpponentPanel();
//		opponentgamePanel.startGameThread();
//		gbc.gridx = 1;
//		gbc.gridy = 0;
//		gbc.gridwidth = 1;
//		gbc.gridheight = 2;
//		window.add(opponentgamePanel, gbc);

		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
