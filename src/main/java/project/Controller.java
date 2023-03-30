package project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Controller implements Initializable {
    //Kan man ha flere java-klasser som "interagerer" med brukergrensesnittet?

    @FXML
    private Rectangle en, to, tre, box;

    public void handleKeyPress() {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(box);
        translate.setDuration(Duration.millis(300));
        //CycleCount må være 2 fordi hver vei telles som en cycle?
        translate.setCycleCount(2);
        translate.setByY(-100);
        translate.setAutoReverse(true);
        translate.play();
    }
    
    @Override
    //Interfacet Initializable med metoden initialize gjør at controlleren fyrer løs koden i initialize metoden når FXMLloadder.load() avfyres i App-klassen
    public void initialize(URL location, ResourceBundle resources) {
        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(en);
        translate1.setDuration(Duration.millis(5000));
        translate1.setByX(-900);
        translate1.play();

        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(to);
        translate2.setDuration(Duration.millis(5000));
        translate2.setByX(-900);
        translate2.play();

        TranslateTransition translate3 = new TranslateTransition();
        translate3.setNode(tre);
        translate3.setDuration(Duration.millis(5000));
        translate3.setByX(-900);
        translate3.play();
    }

}
