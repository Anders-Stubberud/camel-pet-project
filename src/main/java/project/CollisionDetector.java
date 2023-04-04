package project;

// import java.time.Duration;
import javafx.util.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;

public class CollisionDetector {

    private Controller controller;
    private Polyline hitbox;
    private Rectangle normalObstacle, specialObstacle;
    private Timeline collisionDetectionTimeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
        if (
            hitbox.getBoundsInParent().intersects(normalObstacle.localToParent(normalObstacle.getBoundsInLocal()).getMinX(), normalObstacle.localToParent(normalObstacle.getBoundsInLocal()).getMinY(), normalObstacle.getWidth(), normalObstacle.getHeight())
            || hitbox.getBoundsInParent().intersects(specialObstacle.localToParent(specialObstacle.getBoundsInLocal()).getMinX(), specialObstacle.localToParent(specialObstacle.getBoundsInLocal()).getMinY(), specialObstacle.getWidth(), specialObstacle.getHeight())
            ) {
            stopTimeline();
            System.out.println("GAME OVER!!!");
        }
    }));

    public CollisionDetector(Controller controller, Polyline hitbox, Rectangle normalObstacle, Rectangle specialObstacle) {
        this.controller = controller;
        this.hitbox = hitbox;
        this.normalObstacle = normalObstacle;
        this.specialObstacle = specialObstacle;
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
    }

}
