package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.GamePanel;

public class FileManager implements Runnable {
	GamePanel gamePanel;
	File playerMap;
	File opponenetMap;
	
	Thread fileThread;
	
	public FileManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.playerMap = new File("res/maps/playerMap.txt");
		//Change Later
		this.opponenetMap = new File("res/maps/playerMap.txt");
	}
    
	public void startGameThread() {
		fileThread = new Thread(this);
		fileThread.start();
    }
	
	@Override
	public void run() {
		while (fileThread != null) {
			try {
				FileWriter playerMapWriter = new FileWriter(playerMap);
				playerMapWriter.write("");
				for (int i = 0; i < gamePanel.playerPanel.ScreenRow; i++) {
					for (int j = 0; j < gamePanel.playerPanel.ScreenCol; j++) {
						playerMapWriter.append((char) (gamePanel.playerTileManager.playerTilesMap[i][j] + '0'));
						playerMapWriter.append((char) ' ');
					}
					playerMapWriter.append('\n');
				}
				playerMapWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//////////////////////////////////////////////////////////////////////////////////////
			try {
				BufferedReader br = new BufferedReader(new FileReader(opponenetMap));
				for (int i = 0; i < gamePanel.playerPanel.ScreenRow; i++) {
					String line = br.readLine();
					String numbers[] = line.split(" ");
					for (int j = 0; j < gamePanel.playerPanel.ScreenCol; j++) {
						int num = Integer.parseInt(numbers[j]);
						gamePanel.opponentTileManager.opponentTilesMap[i][j] = num;
					}
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
