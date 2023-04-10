package project;

import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class ScoreCounter {
    
    private Controller controller;
    private List<ScoreComparer> scoreComparerList = new ArrayList<>();
    private int score;
    private Timeline scoreCounterTransitionTimeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {{
        score += 1;
        controller.getScoreLabel().setText("Score: " + Integer.toString(score));
    }}));

    public int getScore() {
        return score;
    }

    public ScoreCounter (Controller controller) {
        this.controller = controller;
    }

    public void startScoreCounter() {
        scoreCounterTransitionTimeline.setCycleCount(Timeline.INDEFINITE);
        scoreCounterTransitionTimeline.play();
    }

    public void stopScoreCounter() {
        scoreCounterTransitionTimeline.stop();
        controller.getInfo1().setOpacity(1);
        controller.getInfo2().setOpacity(1);
        controller.getUserInput().setOpacity(1);
        controller.getSubmit().setOpacity(1);
        controller.getSubmit().setDisable(false);
        controller.getUserInput().setDisable(false);
        controller.getUserInput().requestFocus();
    }

    public void writeStatsToFile() {
        try {
            boolean avoidNewLineFirstTime = true;
            BufferedWriter skrive = new BufferedWriter(new FileWriter("src/main/java/project/scoreList.txt"));
            for (ScoreComparer scoreComparer : scoreComparerList) {
                if (avoidNewLineFirstTime) {
                    skrive.write(scoreComparer.getName() + "-" + scoreComparer.getScore());
                    avoidNewLineFirstTime = false;
                }
                else {
                    skrive.write("\n" + scoreComparer.getName() + "-" + scoreComparer.getScore());
                }
            }
            skrive.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAllDataFromFileToListAndSort() {
        if (controller.getUserInput().getText().matches("[a-zA-Z\\s]+")) {
            BufferedReader lese;
            try {
                lese = new BufferedReader(new FileReader("src/main/java/project/scoreList.txt"));
                String linje = lese.readLine();
    
                while (linje != null) {
                    String [] deler = linje.split("-");
                    scoreComparerList.add(new ScoreComparer(deler[0], Integer.parseInt(deler[1])));
                    linje = lese.readLine();
                }
    
                lese.close();
    
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            scoreComparerList.add(new ScoreComparer(controller.getUserInput().getText(), score));
            Collections.sort(scoreComparerList);
    
            writeStatsToFile();
        }
        else {
            controller.getException().setText("Name can only contain letters and space");
            controller.getUserInput().requestFocus();
            throw new IllegalArgumentException("Name can only contain letters and space");
        }

    }

}