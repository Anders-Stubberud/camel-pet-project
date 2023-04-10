package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ScoreController implements Initializable {

    private Controller controller;

    @FXML
    private VBox vbox;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void changeFromGameToScoreboard() {
        controller.getStage().close();
        try {
            controller.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("ScoreApp.fxml"))));
        } catch (IOException e) {
            System.out.println("scoreCounterLoadScoreboard failed");
        }
        controller.getStage().show();
    }

    public void showScoreboard() throws IOException {
        BufferedReader lese;
        try {
            lese = new BufferedReader(new FileReader("src/main/java/project/scoreList.txt"));
            String linje = lese.readLine();

            while (linje != null) {
                Label label = new Label(linje);
                label.setPadding(new Insets(0, 0, 0, 150));
                vbox.getChildren().add(label);
                linje = lese.readLine();
            }

            lese.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showScoreboard();
        } catch (IOException e) {
            System.out.println("changeFromGameToScoreboard-showScoreboard");
        }
    }

}
