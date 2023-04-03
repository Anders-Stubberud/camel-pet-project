package project;

import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class PlayerTransition {
    
    private Controller controller;
    private ImageView player;
    private Polyline hitbox;
    public boolean playerJumpInProgress;
    private TranslateTransition translatePlayer = new TranslateTransition();
    private TranslateTransition translateHitbox = new TranslateTransition();

    public PlayerTransition(Controller controller, ImageView player, Polyline hitbox) {
        this.controller = controller;
        this.player = player;
        this.hitbox = hitbox;
    }

    public Polyline getHitBox() {
        return hitbox;
    }

    public void startPlayerTransition() {
        //Animasjon for playeren
        translatePlayer.setNode(player);
        translatePlayer.setDuration(Duration.millis(500));
        translatePlayer.setCycleCount(2);
        translatePlayer.setByY(-150);
        translatePlayer.setAutoReverse(true);

        //Animasjon for hitboxen, så den alltid følger playeren
        translateHitbox.setNode(hitbox);
        translateHitbox.setDuration(Duration.millis(500));
        translateHitbox.setCycleCount(2);
        translateHitbox.setByY(-150);
        translateHitbox.setAutoReverse(true);
    }

    public void handleKeyPressInPlayerTransition() {
        if (!playerJumpInProgress && controller.getGameStatus()) {
            playerJumpInProgress = true;
            translatePlayer.setOnFinished(event -> playerJumpInProgress = false);
            translateHitbox.play();
            translatePlayer.play();
        }
    }

}
