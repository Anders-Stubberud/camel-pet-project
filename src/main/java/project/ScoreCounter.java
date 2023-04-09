package project;

import javafx.util.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class ScoreCounter {
    
    private Controller controller;
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


}
