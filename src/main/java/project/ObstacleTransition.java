package project;

import javafx.util.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class ObstacleTransition {
    
    private Controller controller;
    private ImageView normalObstacle, specialObstacle;
    private Polyline hitboxNormalObstacle, hitboxSpecialObstacle;
    private TranslateTransition translateNormalObstacle = new TranslateTransition();
    private TranslateTransition translateSpecialObstacle = new TranslateTransition();
    private TranslateTransition translateHitboxNormalObstacle = new TranslateTransition();
    private TranslateTransition translateHitboxSpecialObstacle = new TranslateTransition();
    private Map<TranslateTransition, ImageView> map;
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

    public ObstacleTransition(Controller controller, ImageView normalObstacle, ImageView specialObstacle, Polyline hitboxNormalObstacle, Polyline hitboxSpecialObstacle) {
        this.controller = controller;
        this.normalObstacle = normalObstacle;
        this.specialObstacle = specialObstacle;
        this.hitboxNormalObstacle = hitboxNormalObstacle;
        this.hitboxSpecialObstacle = hitboxSpecialObstacle;
    }

    public void spawnNewObstacle(int durationObstacles) {
        Random random = new Random();
        int decideBetweenNormalOrSpecialObject = random.nextInt(2);
        System.out.println(decideBetweenNormalOrSpecialObject);
        if (decideBetweenNormalOrSpecialObject == 0) {
            specialObstacle.setTranslateX(1000);
            translateSpecialObstacle.setNode(specialObstacle);
            translateSpecialObstacle.setDuration(Duration.millis(durationObstacles));
            translateSpecialObstacle.setByX(-1000);
            translateSpecialObstacle.play();

            hitboxSpecialObstacle.setTranslateX(985);
            translateHitboxSpecialObstacle.setNode(hitboxSpecialObstacle);
            translateHitboxSpecialObstacle.setDuration(Duration.millis(durationObstacles));
            translateHitboxSpecialObstacle.setByX(-1000);
            translateHitboxSpecialObstacle.play();
        }
        else {
            normalObstacle.setTranslateX(1000);
            translateNormalObstacle.setNode(normalObstacle);
            translateNormalObstacle.setDuration(Duration.millis(durationObstacles));
            translateNormalObstacle.setByX(-1000);
            translateNormalObstacle.play();

            hitboxNormalObstacle.setTranslateX(1000);
            translateHitboxNormalObstacle.setNode(hitboxNormalObstacle);
            translateHitboxNormalObstacle.setDuration(Duration.millis(durationObstacles));
            translateHitboxNormalObstacle.setByX(-1000);
            translateHitboxNormalObstacle.play();
        }
    }

    private void makeMap() {
        map = new HashMap<>() { {put(translateNormalObstacle, normalObstacle);} {put(translateSpecialObstacle, specialObstacle);} };
    }

    public Map<TranslateTransition, ImageView> getMap() {
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
            transition.pause();
        }
        translateHitboxNormalObstacle.pause();
        translateHitboxSpecialObstacle.pause();
        obstacleTransitionTimeline.stop();
    }

}