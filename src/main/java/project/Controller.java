package project;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;

public class Controller implements Initializable {

    @FXML
    private Rectangle normalObstacle, specialObstacle;

    @FXML
    private ImageView player;

    @FXML
    private Polyline hitbox;

    private boolean gameActive;

    private CollisionDetector collisionDetector;
    private PlayerTransition playerTransition;
    private ObstacleTransition obstacleTransition;

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

    public Map<TranslateTransition, Rectangle> getMap() {
        return obstacleTransition.getMap();
    }

    public void startCollisionDetection() {
        collisionDetector = new CollisionDetector(this, hitbox, normalObstacle, specialObstacle);
        collisionDetector.detectCollisions();
    }
    
    @Override
    //Interfacet Initializable med metoden initialize gjør at controlleren fyrer løs koden i initialize metoden når FXMLloadder.load() avfyres i App-klassen
    public void initialize(URL location, ResourceBundle resources) {
        playerTransition = new PlayerTransition(this, player, hitbox);
        playerTransition.startPlayerTransition();
        
        obstacleTransition = new ObstacleTransition(this, normalObstacle, specialObstacle);
        obstacleTransition.startObstacleTransition();
    }
}
