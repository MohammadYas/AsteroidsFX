package dk.sdu.mmmi.mmy.scoringsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreControllerTest {

    @Test
    void addingPointsIncreasesTheScore() {
        ScoreController controller = new ScoreController();

        controller.addScore(10);
        controller.addScore(5);

        assertEquals(15, controller.getScore());
    }

    @Test
    void resetSetsTheScoreBackToZero() {
        ScoreController controller = new ScoreController();

        controller.addScore(30);
        controller.reset();

        assertEquals(0, controller.getScore());
    }
}
