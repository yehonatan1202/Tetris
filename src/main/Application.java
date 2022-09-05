package main;

import javax.swing.JFrame;

public class Application {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Tanks");

		GamePanel gamePanel = new GamePanel();
		gamePanel.startGameThread();
		window.add(gamePanel);

		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
