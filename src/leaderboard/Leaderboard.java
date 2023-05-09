package leaderboard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class Leaderboard {
    int listSize = 5;
    Record recordList[];
    String path = "src/leaderboard/leaderboard";

    public Leaderboard(){
        loadLeaderboard();
    }

    public void loadLeaderboard() {
        recordList = new Record[listSize];
        for (int i = 0; i < listSize; i++) {
            recordList[i] = new Record();
        }
        try {
            int index = 0;
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            while (line != null) {
                String parts[] = line.split(" ");
                recordList[index].name = parts[0];
                recordList[index].score = Integer.parseInt(parts[1]);
                recordList[index].date = parts[2];
                index++;
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveLeaderboard(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for(int i = 0; i < listSize; i++){
                bw.write(recordList[i].name + " " + recordList[i].score + " " + recordList[i].date + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setRecordName(String name, int index) {
        recordList[index].name = name;
        saveLeaderboard();
    }

    public int addRecord(int score) {
        int index = -1;
       if(score > recordList[listSize - 1].score){
        Record newRecord = new Record(score);
        recordList[listSize - 1] = newRecord;
        Arrays.sort(recordList);
        index = Arrays.asList(recordList).indexOf(newRecord);
       }
        saveLeaderboard();
        return index;
    }
}
