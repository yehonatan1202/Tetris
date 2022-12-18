package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import file.Client;
import file.Server;
import tile.OpponentTileManager;
import tile.PlayerTileManager;

public class GamePanel extends JPanel {
	public final int originalTitleSize = 16;
	public final int scale = 2;
	public final int tileSize = originalTitleSize * scale;
	public final int ScreenCol = 26; // playerPanel(10) + opponenetPanel(10) + NextPiecePanel(4) + side pading(2)
	public final int ScreenRow = 22; // panel(20) + sides pading(2)
	public int screenWidth = tileSize * ScreenCol;
	public int screenHeight = tileSize * ScreenRow;

	public PlayerPanel playerPanel;
	public StatsPanel statsPanel;
	public OpponentPanel opponenetPanel;
	public NextPiecePanel nextPiecePanel;
	public PlayerTileManager playerTileManager;
	public OpponentTileManager opponentTileManager;
	public KeyHandler keyHandler;

	public GamePanel(int mode) {
		switch (mode) {
			case 0:
				solo();
				break;

			case 1:
				server();
				break;

			case 2:
				client();
				break;
		}
	}

	void solo() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		statsPanel = new StatsPanel(this);
		this.playerPanel = new PlayerPanel(this);

		this.playerTileManager = new PlayerTileManager(this);

		this.nextPiecePanel = new NextPiecePanel(this);
		this.keyHandler = new KeyHandler();
		playerPanel.addKeyListener(keyHandler);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		add(playerPanel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(nextPiecePanel, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(statsPanel, gbc);

		playerPanel.startGameThread();
		nextPiecePanel.startGameThread();
		statsPanel.startGameThread();
	}

	void server() {
		Server server = new Server(this);
		server.connect();

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		statsPanel = new StatsPanel(this);
		this.playerPanel = new PlayerPanel(this);
		this.opponenetPanel = new OpponentPanel(this);

		this.playerTileManager = new PlayerTileManager(this);
		this.opponentTileManager = new OpponentTileManager(this);

		this.nextPiecePanel = new NextPiecePanel(this);
		this.keyHandler = new KeyHandler();
		playerPanel.addKeyListener(keyHandler);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		add(playerPanel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(statsPanel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(nextPiecePanel, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		add(opponenetPanel, gbc);

		playerPanel.startGameThread();
		opponenetPanel.startGameThread();
		nextPiecePanel.startGameThread();
		server.startServerThread();
		statsPanel.startGameThread();
	}

	void client() {
		Client client = new Client(this);
		client.connect();

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		statsPanel = new StatsPanel(this);
		this.playerPanel = new PlayerPanel(this);
		this.opponenetPanel = new OpponentPanel(this);

		this.playerTileManager = new PlayerTileManager(this);
		this.opponentTileManager = new OpponentTileManager(this);

		this.nextPiecePanel = new NextPiecePanel(this);
		this.keyHandler = new KeyHandler();
		playerPanel.addKeyListener(keyHandler);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		add(playerPanel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(nextPiecePanel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(statsPanel, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		add(opponenetPanel, gbc);

		playerPanel.startGameThread();
		opponenetPanel.startGameThread();
		nextPiecePanel.startGameThread();
		client.startClientThread();
		statsPanel.startGameThread();
	}
}