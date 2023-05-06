package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import leaderboard.LeaderboardPanel;

public class MainMenu implements ActionListener {
	JFrame window;

	JButton soloButton;
	JButton hostButton;
	JButton joinButton;
	JButton leaderboardButton;

	public MainMenu(JFrame frame) {
		if (frame == null) {
			window = new JFrame("Main Menu");
			window.setSize(new Dimension(832, 704));
			window.setLocationRelativeTo(null);
			window.setResizable(false);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} else {
			window = frame;
			window.getContentPane().removeAll();
			window.setTitle("Main Menu");
		}
		JPanel panel = new JPanel();
		try {
			// load images
			BufferedImage Background = ImageIO.read(new File("res/Menu/background.png"));
			BufferedImage solo = ImageIO.read(new File("res/Menu/solo.png"));
			BufferedImage host = ImageIO.read(new File("res/Menu/host.png"));
			BufferedImage join = ImageIO.read(new File("res/Menu/join.png"));
			BufferedImage leaderboard = ImageIO.read(new File("res/Menu/leaderboard.png"));

			// label
			JLabel backgroundLabel = new JLabel(new ImageIcon(Background));
			backgroundLabel.setLayout(new GridBagLayout());

			// buttons
			soloButton = new JButton();
			hostButton = new JButton();
			joinButton = new JButton();
			leaderboardButton = new JButton();

			// set buttons images
			soloButton.setIcon(new ImageIcon(solo));
			hostButton.setIcon(new ImageIcon(host));
			joinButton.setIcon(new ImageIcon(join));
			leaderboardButton.setIcon(new ImageIcon(leaderboard));

			// set buttons size
			soloButton.setPreferredSize(new Dimension(285, 71));
			hostButton.setPreferredSize(new Dimension(285, 71));
			joinButton.setPreferredSize(new Dimension(285, 71));
			leaderboardButton.setPreferredSize(new Dimension(285, 71));

			// set buttons transparent and remove outline
			soloButton.setContentAreaFilled(false);
			soloButton.setBorderPainted(false);
			hostButton.setContentAreaFilled(false);
			hostButton.setBorderPainted(false);
			joinButton.setContentAreaFilled(false);
			joinButton.setBorderPainted(false);
			leaderboardButton.setContentAreaFilled(false);
			leaderboardButton.setBorderPainted(false);

			// set buttons listener
			soloButton.setActionCommand("solo");
			soloButton.addActionListener(this);
			hostButton.setActionCommand("host");
			hostButton.addActionListener(this);
			joinButton.setActionCommand("join");
			joinButton.addActionListener(this);
			leaderboardButton.setActionCommand("leaderboard");
			leaderboardButton.addActionListener(this);

			panel.add(backgroundLabel);

			// set buttons locations
			GridBagConstraints gbc = new GridBagConstraints();

			// space
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridheight = 1;
			gbc.gridwidth = 3;
			backgroundLabel.add(Box.createVerticalStrut(200), gbc);

			// solo button
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			backgroundLabel.add(soloButton, gbc);

			// space
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.gridheight = 3;
			gbc.gridwidth = 1;
			backgroundLabel.add(Box.createHorizontalStrut(15), gbc);

			// host button
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			backgroundLabel.add(hostButton, gbc);

			// space
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridheight = 1;
			gbc.gridwidth = 3;
			backgroundLabel.add(Box.createVerticalStrut(15), gbc);

			// leaderboard button
			gbc.gridx = 2;
			gbc.gridy = 3;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			backgroundLabel.add(leaderboardButton, gbc);

			// join button
			gbc.gridx = 2;
			gbc.gridy = 1;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			backgroundLabel.add(joinButton, gbc);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		window.add(panel);
		window.setVisible(true);
	}

	public void start(int mode) {
		// window = new JFrame();
		window.getContentPane().removeAll();
		window.setTitle("Tetris" + mode);
		GamePanel gamePanel = new GamePanel(mode);
		gamePanel.setBackground(Color.black);
		window.add(gamePanel);
		gamePanel.playerPanel.requestFocusInWindow();
		window.pack();
		window.setVisible(true);
		window.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				if (gamePanel.isSolo == false) {
					// close client/server
					gamePanel.clientSever.end();
					System.exit(0);
				}
			}

		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "solo":
				start(0);
				break;

			case "host":
				start(1);
				break;

			case "join":
				start(2);
				break;
			case "leaderboard":
				new LeaderboardPanel(window);

		}
	}
}
