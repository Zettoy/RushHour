/**
 * The code for saving and reading leader boards from files
 * and manipulating arrays of Score
 * Leader boards have format:
 * (name);(time);(number of moves);
 */
package Model;

import Controller.Score;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LeaderBoard {
    private int maxPositions;
    private Score[] scores;
    private int difficulty;

    /**
     * Constructor
     * @param maxPositions The limit of the number of scores on the leader board
     * @param difficulty   The difficulty of the leader board needed
     */
    public LeaderBoard(int maxPositions, int difficulty) {
        this.maxPositions = maxPositions;
        this.difficulty = difficulty;
        try {
            this.scores = loadLeaderBoard();
        } catch (IOException e) {
            this.scores = null;
        }

    }

    /**
     * Creates new score and adds to leader board in order of time, if it's a high score
     * @param name      Name of user who got high score
     * @param time      Time taken to finish level
     * @param movesMade Number of moves made to finish level
     * @return The position the score in leader, 0 if not on the leader board
     */
    public int addScore(String name, Date time, int movesMade) {
        for( int i = maxPositions - 1; i >= 0; i--) {
            if(!time.before(scores[i].getTime())) {
                // If score not a high score
                if(i == maxPositions -1) {
                    return 0;
                // Score in positions 2 to maxPositions
                } else {
                    Score newScore = new Score(name, time, movesMade);
                    addScorePos(newScore, i + 2);
                    return i+2;
                }
            }
        }
        // Score in position 1
        Score newScore = new Score(name, time, movesMade);
        addScorePos(newScore, 1);
        return 1;
    }

    /**
     * Adds Score to scores array at set position
     * @param newScore Score of the new score to be added
     * @param position Int of the position the score is in the leader board
     */
    private void addScorePos(Score newScore, int position) {
        for(int i = maxPositions - 1; i >= position; i--) {
            scores[i] = scores[i-1];
        }
        scores[position - 1] = newScore;
    }

    /**
     * Loads leader board from a file
     * @return Score[] of Scores from the file
     * @throws IOException
     */
    private Score[] loadLeaderBoard() throws IOException {
        String fileLocation = "./LeaderBoard" + difficulty + ".txt";
        File file;
        file = new File(fileLocation);
        try (Scanner sc = new Scanner(file)) {
            int i = 0;
            Score[] scores = new Score[maxPositions];
            String name;
            Date time;
            int moves;
            Score score;
            sc.useDelimiter(";");
            // Reads from file
            while (sc.hasNext()) {
                name = sc.next();
                time = new SimpleDateFormat("mm:ss:SS").parse(sc.next());
                moves = Integer.parseInt(sc.next());
                score = new Score(name, time, moves);
                scores[i] = score;
                i++;
                sc.nextLine();
            }
            sc.close();
            return scores;
        } catch (FileNotFoundException e) {
            // Creates a file with default high leader board values if one does not exist
            @SuppressWarnings("resource")
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            Score[] scores = new Score[maxPositions];
            Score score;
            for (int i = 0; i < maxPositions; i++) {
                writer.write("----;59:59:99;999;");
                try {
                    score = new Score("----",new SimpleDateFormat("mm:ss:SS").parse("59:59:99"),999);
                } catch (ParseException e1) {
                    return null;
                }
                scores[i] = score;
                writer.newLine();
            }
            writer.close();
            return scores;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Sets new high score to user's inputted name
     * @param name     Name for the high score
     * @param position Position in leader board of high score
     */
    public void setName(String name, int position) {
        scores[position - 1].setName(name);
        saveScore();
    }

    /**
     * Saves a Score[] to a file
     */
    public void saveScore() {
        String fileLocation = "./LeaderBoard" + difficulty + ".txt";
        File file = new File(fileLocation);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < maxPositions; i++) {
                writer.write(scores[i].toString());
                writer.newLine();
            }
            writer.close();
            return;
        } catch (IOException e) {
            return;
        }
    }

    /**
     * Getter
     * @return the array of scores
     */
    public Score[] getScores() {
        return scores;
    }
}

