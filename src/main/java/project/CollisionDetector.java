package project;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Polyline;

public class CollisionDetector {

    private Controller controller;
    private Polyline hitbox, hitboxNormalObstacle, hitboxSpecialObstacle;
    private Timeline collisionDetectionTimeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
        crashChecker(hitbox, hitboxNormalObstacle, hitboxSpecialObstacle, controller);
    }));

    public Timeline getcollisionDetectionTimeline() {
        return collisionDetectionTimeline;
    }

    public boolean crashChecker(Polyline hitbox, Polyline hitboxNormalObstacle, Polyline hitboxSpecialObstacle, Controller controller) {
        if (
            hitbox.getBoundsInParent().intersects(hitboxNormalObstacle.getBoundsInParent())
            || hitbox.getBoundsInParent().intersects(hitboxSpecialObstacle.getBoundsInParent())
            ) {
            stopTimeline();
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

    public void stopTimeline() {
        if (controller.getGameStatus()) {
            collisionDetectionTimeline.stop();
            controller.gameOver();
            controller.getObstacleTransition().stopObstacleTransition();
            controller.getScoreCounter().stopScoreCounter();
            controller.getPlayerTransition().changePlayerGameOver();
            controller.getGameOverText().setOpacity(1);
        }
    }

}