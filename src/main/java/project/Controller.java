package project;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Controller implements Initializable {

    @FXML
    private Rectangle en, to, tre;

    @FXML
    private ImageView kamel;

    private TranslateTransition translate = new TranslateTransition();
    private CollisionDetector collisionDetector;
    private boolean gameActive;
    public boolean kamelJumpInProgress;

    private TranslateTransition translate1 = new TranslateTransition();
    private TranslateTransition translate2 = new TranslateTransition();
    private TranslateTransition translate3 = new TranslateTransition();
    Map<TranslateTransition, Rectangle> map;

    public void handleKeyPress() {
        if (!kamelJumpInProgress && gameActive) {
            kamelJumpInProgress = true;
            translate.setOnFinished(event -> kamelJumpInProgress = false);
            translate.play();
        }
    }

    //Dersom map er variabel i toppen av filen, ettersom det ikke blir instansiert noen Controller
    //når FXMLLoader.load(), så vil variabelen map heller ikke være instansiert. 
    //Kaller dermed metode som først lager map, og så en som returnerer mappet. 
    //Må ha separat lage og returnere metode for å kunne hente samme map til CollisionDetecor sin stopTimeline().
    private void makeMap() {
        map = new HashMap<>() { {put(translate1, en);} {put(translate2, to);} {put(translate3, tre);} };
    }

    public Map<TranslateTransition, Rectangle> getMap() {
        return map;
    }

    public void gameOver() {
        gameActive = false;
    }
    
    @Override
    //Interfacet Initializable med metoden initialize gjør at controlleren fyrer løs koden i initialize metoden når FXMLloadder.load() avfyres i App-klassen
    public void initialize(URL location, ResourceBundle resources) {

        //Animasjon for kamel
        translate.setNode(kamel);
        translate.setDuration(Duration.millis(300));
        //CycleCount må være 2 fordi hver vei telles som en cycle?
        translate.setCycleCount(2);
        translate.setByY(-100);
        translate.setAutoReverse(true);

        //Animasjoner for de røde rektanglene
        makeMap();
        for (Map.Entry<TranslateTransition, Rectangle> entry : getMap().entrySet()) {
            entry.getKey().setNode(entry.getValue());
            entry.getKey().setDuration(Duration.millis(5000));
            entry.getKey().setByX(-900);
            entry.getKey().play();
        }

        gameActive = true;

        //Starter collisionDetection i CollitionDetector-klassen
        collisionDetector = new CollisionDetector(this, kamel, en, to, tre);
        collisionDetector.detectCollisions();
    }
}
