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

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel implements ActionListener {
	JFrame window;

	JButton soloButton;
	JButton hostButton;
	JButton joinButton;

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

			// label
			JLabel backgroundLabel = new JLabel(new ImageIcon(Background));
			backgroundLabel.setLayout(new GridBagLayout());

			// buttons
			soloButton = new JButton();
			hostButton = new JButton();
			joinButton = new JButton();

			// set buttons images
			soloButton.setIcon(new ImageIcon(solo));
			hostButton.setIcon(new ImageIcon(host));
			joinButton.setIcon(new ImageIcon(join));

			// set buttons size
			soloButton.setPreferredSize(new Dimension(285, 71));
			hostButton.setPreferredSize(new Dimension(285, 71));
			joinButton.setPreferredSize(new Dimension(285, 71));

			// set buttons transparent and remove outline
			soloButton.setContentAreaFilled(false);
			soloButton.setBorderPainted(false);
			hostButton.setContentAreaFilled(false);
			hostButton.setBorderPainted(false);
			joinButton.setContentAreaFilled(false);
			joinButton.setBorderPainted(false);

			// set buttons listener
			soloButton.setActionCommand("solo");
			soloButton.addActionListener(this);
			hostButton.setActionCommand("host");
			hostButton.addActionListener(this);
			joinButton.setActionCommand("join");
			joinButton.addActionListener(this);

			panel.add(backgroundLabel);

			// set buttons locations
			GridBagConstraints gbc = new GridBagConstraints();

			// space
			gbc.gridy = 0;
			backgroundLabel.add(Box.createVerticalStrut(200), gbc);

			// solo button
			gbc.gridy = 1;
			backgroundLabel.add(soloButton, gbc);

			// space
			gbc.gridy = 2;
			backgroundLabel.add(Box.createVerticalStrut(20), gbc);

			// host button
			gbc.gridy = 3;
			backgroundLabel.add(hostButton, gbc);

			// space
			gbc.gridy = 4;
			backgroundLabel.add(Box.createVerticalStrut(20), gbc);

			// join button
			gbc.gridy = 5;
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

		}
	}
}
