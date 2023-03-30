package project;

// import java.time.Duration;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;

public class CollisionDetector {
    private boolean gameActive;
    private Rectangle box;
    private Rectangle en;
    private Rectangle to;
    private Rectangle tre;

    public CollisionDetector(boolean gameActive, Rectangle box, Rectangle en, Rectangle to, Rectangle tre) {
        this.gameActive = gameActive;
        this.box = box;
        this.en = en;
        this.to = to;
        this.tre = tre;
    }

    public void detectCollisions() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
            if (
                box.getBoundsInParent().intersects(en.localToParent(en.getBoundsInLocal()).getMinX(), en.localToParent(en.getBoundsInLocal()).getMinY(), en.getWidth(), en.getHeight())
                || box.getBoundsInParent().intersects(to.localToParent(to.getBoundsInLocal()).getMinX(), to.localToParent(to.getBoundsInLocal()).getMinY(), to.getWidth(), to.getHeight())
                || box.getBoundsInParent().intersects(tre.localToParent(tre.getBoundsInLocal()).getMinX(), tre.localToParent(tre.getBoundsInLocal()).getMinY(), tre.getWidth(), tre.getHeight())
                ) {
                gameActive = false;
                System.out.println("GAME OVER!!!");
            }
        }));
        
        timeline.setCycleCount(Timeline.INDEFINITE); // repeat indefinitely
        timeline.play(); // start the timeline
    }
}
