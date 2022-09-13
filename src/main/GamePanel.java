package main;

import javax.swing.JPanel;

import file.FileManager;
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
	public OpponentPanel opponenetPanel;
	public NextPiecePanel nextPiecePanel;
	public PlayerTileManager playerTileManager;
	public OpponentTileManager opponentTileManager;

	public KeyHandler keyHandler;

	public GamePanel() {
		this.playerPanel = new PlayerPanel(this);
		this.opponenetPanel = new OpponentPanel(this);
		
		this.playerTileManager = new PlayerTileManager(this);
		this.opponentTileManager = new OpponentTileManager(this);
		
		this.nextPiecePanel = new NextPiecePanel(this);
		this.keyHandler = new KeyHandler();
		playerPanel.addKeyListener(keyHandler);
		
		FileManager fileManager = new FileManager(this);
		
		add(playerPanel);
		add(nextPiecePanel);
		add(opponenetPanel);
		playerPanel.startGameThread();
		opponenetPanel.startGameThread();
		nextPiecePanel.startGameThread();
		fileManager.startGameThread();
	}
}