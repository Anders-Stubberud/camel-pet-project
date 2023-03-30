package project;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Controller implements Initializable {
    //Kan man ha flere java-klasser som "interagerer" med brukergrensesnittet? Flere controllerklasser?

    @FXML
    private Rectangle en, to, tre, box;

    private TranslateTransition translate = new TranslateTransition();
    private CollisionDetector collisionDetector;
    private boolean gameActive;
    public boolean blueJumpInProgress;

    public void handleKeyPress() {
        if (!blueJumpInProgress) {
            blueJumpInProgress = true;
            translate.setOnFinished(event -> blueJumpInProgress = false);
            translate.play();
        }
    }
    
    @Override
    //Interfacet Initializable med metoden initialize gjør at controlleren fyrer løs koden i initialize metoden når FXMLloadder.load() avfyres i App-klassen
    public void initialize(URL location, ResourceBundle resources) {
        //Må deklarere disse her, animasjonen fungerer ikke dersom de deklareres øverst i filen, vet ikke hvorfor. 
        TranslateTransition translate1 = new TranslateTransition();
        TranslateTransition translate2 = new TranslateTransition();
        TranslateTransition translate3 = new TranslateTransition();
        Map<TranslateTransition, Rectangle> map = new HashMap<>() { {put(translate1, en);} {put(translate2, to);} {put(translate3, tre);} };

        //Animasjon for blå rektangel
        translate.setNode(box);
        translate.setDuration(Duration.millis(300));
        //CycleCount må være 2 fordi hver vei telles som en cycle?
        translate.setCycleCount(2);
        translate.setByY(-100);
        translate.setAutoReverse(true);

        //Animasjoner for de røde rektanglene
        for (Map.Entry<TranslateTransition, Rectangle> entry : map.entrySet()) {
            entry.getKey().setNode(entry.getValue());
            entry.getKey().setDuration(Duration.millis(5000));
            entry.getKey().setByX(-900);
            entry.getKey().play();
        }

        gameActive = true;

        //Starter collisionDetection i CollitionDetector-klassen
        collisionDetector = new CollisionDetector(gameActive, box, en, to, tre);
        collisionDetector.detectCollisions();
    }
}
