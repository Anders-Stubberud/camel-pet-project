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

    public ScoreCounter (Controller controller) {
        this.controller = controller;
    }

    public void startScoreCounter() {
        scoreCounterTransitionTimeline.setCycleCount(Timeline.INDEFINITE);
        scoreCounterTransitionTimeline.play();
    }

    public void stopScoreCounter() {
        scoreCounterTransitionTimeline.stop();
    }

}
