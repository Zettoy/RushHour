package Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Score {
    private String name;
    private Date time;
    private int moves;

    public Score(String name, Date time, int moves) {
        this.name = name;
        this.time = time;
        this.moves = moves;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return name + ';' +
                new SimpleDateFormat("mm:ss:SS").format(time) + ";" +
                moves + ";";
    }

    public String getName() {
        return name;
    }

    public int getMoves() {
        return moves;
    }

    public void setName(String name) {
        this.name = name;
    }
}
