package project;

import javafx.util.Duration;
import java.util.HashMap;
import java.util.Map;
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
    private Timeline obstacleTransitionTimeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
    { 
        //Sjekke om noen av obstaclene er "out of bounds", isåfall sette ett av dem ut i spill. 
    }}));

    public ObstacleTransition(Controller controller, Rectangle normalObstacle, Rectangle specialObstacle) {
        this.controller = controller;
        this.normalObstacle = normalObstacle;
        this.specialObstacle = specialObstacle;
    }

    private void makeMap() {
        map = new HashMap<>() { {put(translateNormalObstacle, normalObstacle);} {put(translateSpecialObstacle, specialObstacle);} };
    }

    public Map<TranslateTransition, Rectangle> getMap() {
        return map;
    }

    public void startObstacleTransition() {
        makeMap();

        for (Map.Entry<TranslateTransition, Rectangle> entry : getMap().entrySet()) {
            entry.getKey().setNode(entry.getValue());
            entry.getKey().setDuration(Duration.millis(30000));
            entry.getKey().setByX(-9000);
            entry.getKey().play();
        }
    
        controller.gameStarted();

        controller.startCollisionDetection();
    }

    public void stopObstacleTransition() {
        for (TranslateTransition transition : map.keySet()) {
            transition.stop();
        }
    }

}
