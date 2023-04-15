package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javafx.animation.Animation;
import javafx.scene.shape.Polyline;

public class GameTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {
        controller = new Controller();
    }

    //Collisiondetector
    @Test
    @DisplayName("Check Collisiondetection detects collitions")
    public void checkCollisiondetector() {
        Polyline hitbox = new Polyline(2, 9, 3, 8);
        Polyline polyline1 = new Polyline(1, 10, 1, 10);
        Polyline polyline2 = new Polyline(100, 100, 100, 100);
        Polyline polyline3 = new Polyline(1000, 1000, 1000, 1000);

        CollisionDetector collisionDetector = new CollisionDetector(controller, hitbox, polyline1, polyline2);

        assertTrue(collisionDetector.crashChecker(hitbox, polyline1, polyline2, controller));

        assertFalse(collisionDetector.crashChecker(hitbox, polyline2, polyline3, controller));
    }

    //Fillagring/lesing sin test
    @Test
    @DisplayName("Sjekke at fillagring og fillesing fungerer")
    public void checkFileTransaction() {
        ScoreCounter scoreCounter = new ScoreCounter(controller);
        List<ScoreComparer> liste = new ArrayList<>(Arrays.asList(
            new ScoreComparer("tre", 3),
            new ScoreComparer("to", 2),
            new ScoreComparer("en", 1)
        ));

        scoreCounter.writeStatsToFile(liste);

        assertTrue(liste.get(0).getName().equals(scoreCounter.getAllDataFromFileToListAndSort().get(0).getName()));

        assertTrue(liste.get(0).getScore() == scoreCounter.getAllDataFromFileToListAndSort().get(0).getScore());
    }

    //ScoreCounter's startscore
    @Test
    @DisplayName("Sjekke at spiller begynner med 0 i score")
    public void checkStartScore() {
        ScoreCounter scoreCounter = new ScoreCounter(controller);
        assertEquals(0, scoreCounter.getScore());
    }

    //ScoreCounter's username check
    @Test
    @DisplayName("Sjekke at spiller ikke kan legge inn brukernavn med annet enn bokstaver og mellomrom")
    public void checkValidUsername() {
        ScoreCounter scoreCounter = new ScoreCounter(controller);
        String validUsername = "Username";
        String unValidUsername = "Username69";
        assertTrue(scoreCounter.validUsername(validUsername));
        assertFalse(scoreCounter.validUsername(unValidUsername));
    }

    //Sjekk for collision timeline i CollisionDetector
    @Test
    @DisplayName("Sørge for at timeline for collisiondetection ikke starter før spillet starter")
    public void checkTimelineNotActiveAtStart() {
        Polyline hitbox = new Polyline(1, 2, 3, 4);
        Polyline polyline1 = new Polyline(10, 10, 10, 10);
        Polyline polyline2 = new Polyline(100, 100, 100, 100);

        CollisionDetector collisionDetector = new CollisionDetector(controller, hitbox, polyline1, polyline2);

        assertTrue(collisionDetector.getcollisionDetectionTimeline().getStatus() == Animation.Status.STOPPED);
    }

    //ScoreComparer sin test
    @Test
    @DisplayName("Sjekke at ScoreComparer sorterer riktig")
    public void checkCorrectSorting() {
        List<ScoreComparer> liste = new ArrayList<>(Arrays.asList(
            new ScoreComparer("tre", 3),
            new ScoreComparer("en", 1), 
            new ScoreComparer("to", 2)
        ));
        Collections.sort(liste);
        assertEquals(3, liste.get(0).getScore());
        assertEquals(2, liste.get(1).getScore());
        assertEquals(1, liste.get(2).getScore());
    }

}
