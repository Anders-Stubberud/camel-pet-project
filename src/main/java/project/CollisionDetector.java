package project;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Polyline;

public class CollisionDetector {

    private Controller controller;
    private boolean testFromTest;
    private Polyline hitbox, hitboxNormalObstacle, hitboxSpecialObstacle;
    private Timeline collisionDetectionTimeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
        crashChecker(hitbox, hitboxNormalObstacle, hitboxSpecialObstacle, controller);
    }));

    public void toggleTestFromTest() {
        testFromTest = true;
    }

    public boolean crashChecker(Polyline hitbox, Polyline hitboxNormalObstacle, Polyline hitboxSpecialObstacle, Controller controller) {
        if (
            hitbox.getBoundsInParent().intersects(hitboxNormalObstacle.getBoundsInParent())
            || hitbox.getBoundsInParent().intersects(hitboxSpecialObstacle.getBoundsInParent())
            ) {
                if (!testFromTest) {
                    stopTimeline();
                    controller.getPlayerTransition().changePlayerGameOver();
                    controller.getGameOverText().setOpacity(1);
                }
            return true;
        } return false;
    }

    public CollisionDetector(Controller controller, Polyline hitbox, Polyline hitboxNormalObstacle, Polyline hitboxSpecialObstacle) {
        this.controller = controller;
        this.hitbox = hitbox;
        this.hitboxNormalObstacle = hitboxNormalObstacle;
        this.hitboxSpecialObstacle = hitboxSpecialObstacle;
    }

    //Denne starter timeline-objektet
    public void detectCollisions() {
        collisionDetectionTimeline.setCycleCount(Timeline.INDEFINITE); 
        collisionDetectionTimeline.play(); 
    }

    private void stopTimeline() {
        collisionDetectionTimeline.stop();
        controller.gameOver();
        controller.getObstacleTransition().stopObstacleTransition();
        controller.getScoreCounter().stopScoreCounter();
    }

}