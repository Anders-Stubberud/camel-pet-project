package project;

import javafx.scene.shape.Rectangle;

public class CollisionDetector {
    private boolean gameActive;
    private Rectangle box;
    private Rectangle en;
    private Controller controller;

    public CollisionDetector(boolean gameActive, Rectangle box, Rectangle en, Controller controller) {
        this.gameActive = gameActive;
        this.box = box;
        this.en = en;
        this.controller = controller;
    }

    public void detectCollisions() {
        // while (gameActive) {
        //     if (box.intersects(en.localToParent(en.getBoundsInLocal()).getMinX(), en.localToParent(en.getBoundsInLocal()).getMinY(), en.getWidth(), en.getHeight())) {
        //         gameActive = false;
        //         System.out.println("GAME OVER!!!");
        //     }
        //     if (controller.blueJumpInProgress) {
                
        //     }
        // }
    }
}
