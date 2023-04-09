package project;

// import java.time.Duration;
import javafx.util.Duration;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Polyline;

public class CollisionDetector {

    private Controller controller;
    private Polyline hitbox, hitboxNormalObstacle, hitboxSpecialObstacle;
    private Timeline collisionDetectionTimeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
        if (
            hitbox.getBoundsInParent().intersects(hitboxNormalObstacle.getBoundsInParent())
            || hitbox.getBoundsInParent().intersects(hitboxSpecialObstacle.getBoundsInParent())
            ) {
            stopTimeline();
            controller.getPlayerTransition().changePlayerGameOver();
            controller.getGameOverText().setOpacity(1);
        }
    }));

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
        controller.getScoreController().changeFromGameToScoreboard();
        try {
            controller.getScoreController().showScoreboard();
        } catch (IOException e) {
            System.out.println("StopTimeLine");
        }
    }

}
