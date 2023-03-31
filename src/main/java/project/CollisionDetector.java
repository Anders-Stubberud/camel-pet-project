package project;

// import java.time.Duration;
import javafx.util.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;

public class CollisionDetector {
    private Controller controller;
    public boolean fire;
    private Rectangle box;
    private Rectangle en;
    private Rectangle to;
    private Rectangle tre;
    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
        if (
            box.getBoundsInParent().intersects(en.localToParent(en.getBoundsInLocal()).getMinX(), en.localToParent(en.getBoundsInLocal()).getMinY(), en.getWidth(), en.getHeight())
            || box.getBoundsInParent().intersects(to.localToParent(to.getBoundsInLocal()).getMinX(), to.localToParent(to.getBoundsInLocal()).getMinY(), to.getWidth(), to.getHeight())
            || box.getBoundsInParent().intersects(tre.localToParent(tre.getBoundsInLocal()).getMinX(), tre.localToParent(tre.getBoundsInLocal()).getMinY(), tre.getWidth(), tre.getHeight())
            ) {
            stopTimeline();
            System.out.println("GAME OVER!!!");
        }
    }));

    public CollisionDetector(Controller controller, Rectangle box, Rectangle en, Rectangle to, Rectangle tre) {
        this.controller = controller;
        this.box = box;
        this.en = en;
        this.to = to;
        this.tre = tre;
    }

    //Denne starter timeline-objektet
    public void detectCollisions() {
        timeline.setCycleCount(Timeline.INDEFINITE); 
        timeline.play(); 
    }

    private void stopTimeline() {
        timeline.stop();
        controller.gameOver();
        for (TranslateTransition transition : controller.getMap().keySet()) {
            transition.stop();
        }
    }

}
