package dk.sdu.mmmi.mmy.common.services;

/**
 * Keeps the score of the running game.
 */
public interface IScoreService {

    /**
     * Adds points to the current score.
     *
     * Pre-condition: points is zero or larger.
     * Post-condition: the score has been raised by points, or is left unchanged
     * if the score could not be reached.
     */
    void addPoints(int points);

    /**
     * Reads the current score.
     *
     * Pre-condition: none.
     * Post-condition: the current score is returned, or 0 if it could not be
     * reached.
     */
    int getScore();
}
