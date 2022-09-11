package main;

import javax.swing.JFrame;

public class Application {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Tetris");

		GamePanel gamePanel = new GamePanel();
		gamePanel.startGameThread();
		window.add(gamePanel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		JFrame opponentWindow = new JFrame();
		opponentWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		opponentWindow.setResizable(false);
		opponentWindow.setTitle("Opponent");

		OpponentGamePanel opponentgamePanel = new OpponentGamePanel();
		opponentgamePanel.startGameThread();
		opponentWindow.add(opponentgamePanel);
		opponentWindow.pack();
		opponentWindow.setLocationRelativeTo(null);
		opponentWindow.setVisible(true);
	}
}
