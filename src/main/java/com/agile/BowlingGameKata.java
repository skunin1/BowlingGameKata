package com.agile;

import java.util.LinkedList;

import com.agile.model.Frame;

public class BowlingGameKata {

    private int maxNoOfFrames = 10;
    private LinkedList<Frame> bowlingFrames = new LinkedList<Frame>();

    public boolean validateNoOfFrames() {
        return (bowlingFrames.size() < maxNoOfFrames);
    }

    private int getRolledPinsForAFrame(int roll1, int roll2) {
        Frame frame = new Frame();
        frame.setRoll1(roll1);
        frame.setRoll2(roll2);
        if (bowlingFrames.size() > 1 && isSpare(bowlingFrames.getLast())) {
            bowlingFrames.getLast().setFrameScore(getPreviousFrameScore() + roll1);
            frame.setFrameScore(getPreviousFrameScore() + roll1 + roll2);
        }
        else if (bowlingFrames.size() > 1 && isStrike(bowlingFrames.getLast())) {
            bowlingFrames.getLast().setFrameScore(getPreviousFrameScore() + roll1+roll2);
            frame.setFrameScore(getPreviousFrameScore() + roll1 + roll2);
        }
         else
            frame.setFrameScore(getPreviousFrameScore() + roll1 + roll2);
        bowlingFrames.add(frame);
        return frame.getFrameScore();
    }

    public int calculateFrameScore(int... frameRolls) {
        if (validateNoOfFrames()
                && eachFramecanKnockMax10Pins(frameRolls[0], frameRolls.length > 1 ? frameRolls[1] : 0))
            return getRolledPinsForAFrame(frameRolls[0], frameRolls.length > 1 ? frameRolls[1] : 0);
        else
            return bowlingFrames.getLast().getFrameScore();
    }

    public int getPreviousFrameScore() {
        if (bowlingFrames.size() > 0)
            return bowlingFrames.getLast().getFrameScore();
        else
            return 0;
    }

    public boolean eachFramecanKnockMax10Pins(int roll1, int roll2) {
        return ((roll1 + roll2) <= 10);
    }

    public LinkedList<Frame> getBowlingFrames() {
        return bowlingFrames;
    }

    public boolean isSpare(Frame frame) {
        return (frame.getRoll1()<10 && (frame.getRoll1() + frame.getRoll2() == 10));
    }

    public boolean isStrike(Frame frame) {
        return frame.getRoll1() == 10;
    }

}
