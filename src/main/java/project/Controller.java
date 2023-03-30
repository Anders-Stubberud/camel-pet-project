package project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Controller{
    //Kan man ha flere java-klasser som "interagerer" med brukergrensesnittet?

    @FXML
    private Rectangle box;

    public void handleKeyPress() {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(box);
        rotate.setDuration(Duration.millis(1000));
        rotate.setByY(-100);
        rotate.setAutoReverse(true);
        rotate.play();
    }

}
