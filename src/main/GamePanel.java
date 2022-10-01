package main;

import javax.swing.JPanel;

import file.FileManager;
import tile.OpponentTileManager;
import tile.PlayerTileManager;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class GamePanel extends JPanel {
	public final int originalTitleSize = 16;
	public final int scale = 2;
	public final int tileSize = originalTitleSize * scale;
	public final int ScreenCol = 26; // playerPanel(10) + opponenetPanel(10) + NextPiecePanel(4) + side pading(2)
	public final int ScreenRow = 22; // panel(20) + sides pading(2)
	public int screenWidth = tileSize * ScreenCol;
	public int screenHeight = tileSize * ScreenRow;

	public PlayerPanel playerPanel;
	public OpponentPanel opponenetPanel;
	public NextPiecePanel nextPiecePanel;
	public PlayerTileManager playerTileManager;
	public OpponentTileManager opponentTileManager;

	public KeyHandler keyHandler;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.playerPanel = new PlayerPanel(this, 0);
		this.opponenetPanel = new OpponentPanel(this);

		this.playerTileManager = new PlayerTileManager(this);
		this.opponentTileManager = new OpponentTileManager(this);

		this.nextPiecePanel = new NextPiecePanel(this);
		this.keyHandler = new KeyHandler();
		playerPanel.addKeyListener(keyHandler);

		FileManager fileManager = new FileManager(this);

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
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		add(opponenetPanel, gbc);

		playerPanel.startGameThread();
		opponenetPanel.startGameThread();
		nextPiecePanel.startGameThread();
		fileManager.startGameThread();
	}
}