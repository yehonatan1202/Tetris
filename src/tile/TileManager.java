package tile;

import main.GamePanel;

public class TileManager {
	public static Tile[] tile;
	GamePanel gamePanel;

	public TileManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		tile = new Tile[18];
		getTileImage();
	}

	public void getTileImage() {
			for(int i = 0; i < 9; i++){
				tile[i] = new Tile(String.format("res/tiles/tile_%d.png", i));
			}
			for(int i = 1; i < 9; i++){
				tile[i + 9] = new Tile(String.format("res/tiles/shadow_tile_%d.png", i));
			}
	}
}