package project;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML
    private ImageView player, gameOverText, normalObstacle, specialObstacle;

    @FXML
    private Polyline hitbox, hitboxNormalObstacle, hitboxSpecialObstacle;

    @FXML
    private Label scoreLabel;

    private Stage stage;
    private boolean gameActive;
    private CollisionDetector collisionDetector;
    private PlayerTransition playerTransition;
    private ObstacleTransition obstacleTransition;
    private ScoreCounter scoreCounter;
    private ScoreController scoreController = new ScoreController();
    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    } 

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public ScoreController getScoreController() {
        return scoreController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public ScoreCounter getScoreCounter() {
        return scoreCounter;
    }

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

    //Putte denne inne i initialize?
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

        scoreCounter = new ScoreCounter(this);
        scoreCounter.startScoreCounter();

        scoreController.setController(this);
    }
}
