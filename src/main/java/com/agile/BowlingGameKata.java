package com.agile;

public class BowlingGameKata {

    int score=0;
    public int roll(int pinsRolled) {
        score += pinsRolled;
        return score;
    }
    public int getFrameScore(int roll1, int roll2) {
        score = roll1+roll2;
        return score;
    }

}
