package leaderboard;

import java.time.LocalDate;

public class Record implements Comparable<Record>{
    String name;
    int score;
    String date;

    public Record() {
        this.name = "";
        this.score = 0;
        this.date = "----/--/--";
    }

    public Record(int score) {
        this.name = "";
        this.score = score;
        date = LocalDate.now().toString();
    }

    @Override
    public int compareTo(Record o)
    {
        return o.score - this.score;
    }
}