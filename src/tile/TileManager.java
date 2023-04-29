package tile;

import main.GamePanel;

public class TileManager {
	GamePanel gamePanel;
	public static Tile[] tile;

	public TileManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		tile = new Tile[18];
		getTileImage();
	}

	// public void startTileManagerThread() {
	// mapThread = new Thread(this);
	// mapThread.start();
	// }

	public void getTileImage() {
			// black
			for(int i = 0; i < 9; i++){
				tile[i] = new Tile(String.format("res/tiles/tile_%d.png", i));
			}
			for(int i = 1; i < 9; i++){
				tile[i + 9] = new Tile(String.format("res/tiles/shadow_tile_%d.png", i));
			}
	}
}