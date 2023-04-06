package project;

import javafx.util.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;

public class ObstacleTransition {
    
    private Controller controller;
    private Rectangle normalObstacle, specialObstacle;
    private TranslateTransition translateNormalObstacle = new TranslateTransition();
    private TranslateTransition translateSpecialObstacle = new TranslateTransition();
    private Map<TranslateTransition, Rectangle> map;
    private int durationObstacles = 3100;
    private Timeline obstacleTransitionTimeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
    { 
        if (normalObstacle.localToParent(normalObstacle.getBoundsInLocal()).getMinX() == -100
        && specialObstacle.localToParent(specialObstacle.getBoundsInLocal()).getMinX() == -100
        ) {
            durationObstacles -= 100;
            spawnNewObstacle(durationObstacles);
        }
    }}));

    public ObstacleTransition(Controller controller, Rectangle normalObstacle, Rectangle specialObstacle) {
        this.controller = controller;
        this.normalObstacle = normalObstacle;
        this.specialObstacle = specialObstacle;
    }

    public void spawnNewObstacle(int durationObstacles) {
        Random random = new Random();
        int decideBetweenNormalOrSpecialObject = random.nextInt(6);
        System.out.println(decideBetweenNormalOrSpecialObject);
        if (decideBetweenNormalOrSpecialObject == 0) {
            specialObstacle.setTranslateX(1000);
            translateSpecialObstacle.setNode(specialObstacle);
            translateSpecialObstacle.setDuration(Duration.millis(durationObstacles));
            translateSpecialObstacle.setByX(-1000);
            translateSpecialObstacle.play();
        }
        else {
            normalObstacle.setTranslateX(1000);
            translateNormalObstacle.setNode(normalObstacle);
            translateNormalObstacle.setDuration(Duration.millis(durationObstacles));
            translateNormalObstacle.setByX(-1000);
            translateNormalObstacle.play();
        }
    }

    private void makeMap() {
        map = new HashMap<>() { {put(translateNormalObstacle, normalObstacle);} {put(translateSpecialObstacle, specialObstacle);} };
    }

    public Map<TranslateTransition, Rectangle> getMap() {
        return map;
    }

    public void startObstacleTransition() {
        makeMap();

        obstacleTransitionTimeline.setCycleCount(Timeline.INDEFINITE);
        obstacleTransitionTimeline.play();
    
        controller.gameStarted();

        controller.startCollisionDetection();
    }

    public void stopObstacleTransition() {
        for (TranslateTransition transition : map.keySet()) {
            transition.stop();
        }
        obstacleTransitionTimeline.stop();
    }

}