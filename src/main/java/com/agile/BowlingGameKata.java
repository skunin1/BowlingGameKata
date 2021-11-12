package com.agile;

import java.util.LinkedList;

import com.agile.model.Frame;

public class BowlingGameKata {

    private int maxNoOfFrames = 10;
    private LinkedList<Frame> scoreBoard = new LinkedList<Frame>();

    public boolean validateNoOfFrames() {
        return (scoreBoard.size() < maxNoOfFrames);
    }
//addFrameToScoreBoard
    private int addFrameToScoreBoard(int roll1, int roll2) {
        Frame frame = new Frame();
        frame.setRoll1(roll1);
        frame.setRoll2(roll2);
        if (scoreBoard.size() > 1 && isSpare(scoreBoard.getLast())) {
            scoreBoard.getLast().setFrameScore(getPreviousFrameScore() + roll1);
            frame.setFrameScore(getPreviousFrameScore() + roll1 + roll2);
        }
        else if (scoreBoard.size() > 1 && isStrike(scoreBoard.getLast())) {
            scoreBoard.getLast().setFrameScore(getPreviousFrameScore() + roll1+roll2);
            frame.setFrameScore(getPreviousFrameScore() + roll1 + roll2);
        }
         else
            frame.setFrameScore(getPreviousFrameScore() + roll1 + roll2);
            scoreBoard.add(frame);
        return frame.getFrameScore();
    }

    public int calculateFrameScore(int... frameRolls) {
        if (validateNoOfFrames()
                && eachFramecanKnockMax10Pins(frameRolls[0], frameRolls.length > 1 ? frameRolls[1] : 0))
            return addFrameToScoreBoard(frameRolls[0], frameRolls.length > 1 ? frameRolls[1] : 0);
        else
            return scoreBoard.getLast().getFrameScore();
    }

    private int getPreviousFrameScore() {
        if (scoreBoard.size() > 0)
            return scoreBoard.getLast().getFrameScore();
        else
            return 0;
    }

    public boolean eachFramecanKnockMax10Pins(int roll1, int roll2) {
        return ((roll1 + roll2) <= 10);
    }

    public LinkedList<Frame> getScoreBoard() {
        return scoreBoard;
    }

    public boolean isSpare(Frame frame) {
        return (frame.getRoll1()<10 && (frame.getRoll1() + frame.getRoll2() == 10));
    }

    public boolean isStrike(Frame frame) {
        return frame.getRoll1() == 10;
    }

}
