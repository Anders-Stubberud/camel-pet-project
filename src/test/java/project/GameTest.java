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

public class GameTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {
        controller = new Controller();
    }

    //ScoreCounter sine tester
    @Test
    @DisplayName("Sjekke at spiller begynner med 0 i score")
    public void checkStartScore() {
        ScoreCounter scoreCounter = new ScoreCounter(controller);
        assertEquals(0, scoreCounter.getScore());
    }

    @Test
    @DisplayName("Sjekke at spiller ikke kan legge inn brukernavn med annet enn bokstaver og mellomrom")
    public void checkValidUsername() {
        ScoreCounter scoreCounter = new ScoreCounter(controller);
        String validUsername = "Username";
        String unValidUsername = "Username69";
        assertTrue(scoreCounter.validUsername(validUsername));
        assertFalse(scoreCounter.validUsername(unValidUsername));
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

    //Controller sin test
    @Test
    @DisplayName("Sjekke at toggle game-active fungerer")
    public void checkToggleGameActive() {
        assertFalse(controller.getGameStatus());
        controller.gameStarted();
        assertTrue(controller.getGameStatus());
        controller.gameOver();
        assertFalse(controller.getGameStatus());
    }

}
