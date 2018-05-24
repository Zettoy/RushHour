package Controller;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LeaderBoard {
    private int maxPositions;
    private Score[] scores;
    private int difficulty;

    public LeaderBoard(int maxPositions, int difficulty) {
        this.maxPositions = maxPositions;
        this.difficulty = difficulty;
        try {
            this.scores = loadLeaderBoard();
        } catch (IOException e) {
            this.scores = null;
        }

    }

    public int addScore(String name, Date time, int movesMade) {
        for( int i = maxPositions - 1; i >= 0; i--) {
            if(!time.before(scores[i].getTime())) {
                if(i == maxPositions -1) {
                    return 0;
                } else {
                    Score newScore = new Score(name, time, movesMade);
                    addScorePos(newScore, i + 2);
                    return i+2;
                }
            }
        }
        Score newScore = new Score(name, time, movesMade);
        addScorePos(newScore, 1);
        return 1;
    }

    private void addScorePos(Score newScore, int position) {
        for(int i = maxPositions - 1; i >= position; i--) {
            scores[i] = scores[i-1];

        }
        scores[position - 1] = newScore;
    }

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

    public Score[] getScores() {
        return scores;
    }
}

