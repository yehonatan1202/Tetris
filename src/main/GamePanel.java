package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import clientSever.Client;
import clientSever.ClientServer;
import clientSever.Server;
import leaderboard.Leaderboard;
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

	public boolean isSolo = false;
	public boolean myRunning = true;
	public boolean gameRunning = true;
	public boolean lost = false;

	public PlayerPanel playerPanel;
	public StatsPanel statsPanel;
	public OpponentPanel opponenetPanel;
	public NextPiecePanel nextPiecePanel;
	public PlayerTileManager playerTileManager;
	public OpponentTileManager opponentTileManager;
	public KeyHandler keyHandler;
	public ClientServer clientSever;
	public Leaderboard leaderboard;

	public GamePanel(int mode) {
		switch (mode) {
			case 0:
			isSolo = true;
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
	
	public void resumeGame() {
		playerPanel.startGameThread();
		nextPiecePanel.startGameThread();
		statsPanel.startGameThread();
	}

	private void solo() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		statsPanel = new StatsPanel(this);
		this.playerPanel = new PlayerPanel(this);

		this.playerTileManager = new PlayerTileManager(this);

		this.nextPiecePanel = new NextPiecePanel(this);
		this.keyHandler = new KeyHandler();
		playerPanel.addKeyListener(keyHandler);

		leaderboard = new Leaderboard();
		
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

		resumeGame();
	}

	private void server() {
		clientSever = new Server(this);
		clientSever.connect();

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
		clientSever.startThread();
		statsPanel.startGameThread();
	}

	private void client() {
		clientSever = new Client(this);
		clientSever.connect();

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
		clientSever.startThread();
		statsPanel.startGameThread();
	}
}