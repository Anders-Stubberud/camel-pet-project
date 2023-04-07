package project;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

public class Controller implements Initializable {

    @FXML
    private ImageView player, gameOverText, normalObstacle, specialObstacle;

    @FXML
    private Polyline hitbox, hitboxNormalObstacle, hitboxSpecialObstacle;

    private boolean gameActive;

    private CollisionDetector collisionDetector;
    private PlayerTransition playerTransition;
    private ObstacleTransition obstacleTransition;

    public ImageView getGameOverText() {
        return gameOverText;
    }

    public CollisionDetector getCollisionDetector() {
        return collisionDetector;
    }

    public PlayerTransition getPlayerTransition() {
        return playerTransition;
    }

    public ObstacleTransition getObstacleTransition() {
        return obstacleTransition;
    }

    public boolean getGameStatus() {
        return gameActive;
    }

    public void gameOver() {
        gameActive = false;
    }

    public void gameStarted() {
        gameActive = true;
    }

    public void handleKeyPress() {
        playerTransition.handleKeyPressInPlayerTransition();
    }

    public Map<TranslateTransition, ImageView> getMap() {
        return obstacleTransition.getMap(); 
    }

    public void startCollisionDetection() {
        collisionDetector = new CollisionDetector(this, hitbox, hitboxNormalObstacle, hitboxSpecialObstacle);
        collisionDetector.detectCollisions();
    }
    
    @Override
    //Interfacet Initializable med metoden initialize gjør at controlleren fyrer løs koden i initialize metoden når FXMLloadder.load() avfyres i App-klassen
    public void initialize(URL location, ResourceBundle resources) {
        playerTransition = new PlayerTransition(this, player, hitbox);
        playerTransition.startPlayerTransition();
        
        obstacleTransition = new ObstacleTransition(this, normalObstacle, specialObstacle, hitboxNormalObstacle, hitboxSpecialObstacle);
        obstacleTransition.startObstacleTransition();
    }
}
