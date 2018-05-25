package Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* code to keep track of the score of a user during game
*/
public class Score {
    private String name;
    private Date time;
    private int moves;

    /**
    * constructor
    * @param name name of the user
    * @param time how long the user took to complete a level
    * @param moves number of moves teh user took to compelete a level
    */
    public Score(String name, Date time, int moves) {
        this.name = name;
        this.time = time;
        this.moves = moves;
    }

    /**
    * @return returns how long the user took to complete a level
    */
    public Date getTime() {
        return time;
    }

    /**
    * @return returns a string containing number of moves made and time taken
    */
    @Override
    public String toString() {
        return name + ';' +
                new SimpleDateFormat("mm:ss:SS").format(time) + ";" +
                moves + ";";
    }

    /**
    * @return returns username
    */
    public String getName() {
        return name;
    }

    /**
    * @return returns number of moves made
    */
    public int getMoves() {
        return moves;
    }

    /**
    * sets a username for an associated score
    * @param name username to be set
    */
    public void setName(String name) {
        this.name = name;
    }
}
