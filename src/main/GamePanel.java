package main;

import javax.swing.JPanel;
import tile.TileManager;

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
	public static TileManager TileManager;

	public KeyHandler keyHandler;

	public GamePanel() {
		playerPanel = new PlayerPanel(this);
		opponenetPanel = new OpponentPanel(this);
		nextPiecePanel = new NextPiecePanel(this);
	}
}