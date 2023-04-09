package project;

import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class ScoreController {

    private Controller controller;
    private Timeline scoreCounterLoadScoreboard = new Timeline(new KeyFrame(Duration.millis(3300), event -> {{
        controller.getStage().close();
        try {
            controller.getPrimaryStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("ScoreApp.fxml"))));
        } catch (IOException e) {
            System.out.println("scoreCounterLoadScoreboard failed");
        }
        controller.getPrimaryStage().show();
    }}));

    @FXML
    private VBox vBox;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void changeFromGameToScoreboard() {
        scoreCounterLoadScoreboard.setCycleCount(1);
        scoreCounterLoadScoreboard.play();
    }

    public void showScoreboard() throws IOException {
        BufferedReader lese;
        try {
            lese = new BufferedReader(new FileReader("src/main/java/project/scoreList.txt"));
            String linje = lese.readLine();

            while (linje != null) {
                System.out.println(linje);
                linje = lese.readLine();
            }

            lese.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
