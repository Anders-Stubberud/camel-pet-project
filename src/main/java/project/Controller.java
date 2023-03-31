package project;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Controller implements Initializable {

    @FXML
    private Rectangle en, to, tre;

    @FXML
    private ImageView kamel;

    @FXML
    private Polyline hbox;

    private TranslateTransition translate = new TranslateTransition();
    private TranslateTransition translateHbox = new TranslateTransition();
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
            translateHbox.play();
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
        translate.setDuration(Duration.millis(500));
        translate.setCycleCount(2);
        translate.setByY(-150);
        translate.setAutoReverse(true);
        //Animasjon for hitboxen, så den alltid følger kamelen
        translateHbox.setNode(hbox);
        translateHbox.setDuration(Duration.millis(500));
        translateHbox.setCycleCount(2);
        translateHbox.setByY(-150);
        translateHbox.setAutoReverse(true);

        //Animasjoner for de røde rektanglene
        makeMap();
        for (Map.Entry<TranslateTransition, Rectangle> entry : getMap().entrySet()) {
            entry.getKey().setNode(entry.getValue());
            entry.getKey().setDuration(Duration.millis(30000));
            entry.getKey().setByX(-9000);
            entry.getKey().play();
        }

        gameActive = true;

        //Starter collisionDetection i CollitionDetector-klassen
        collisionDetector = new CollisionDetector(this, hbox, en, to, tre);
        collisionDetector.detectCollisions();
    }
}
