package com.agile;

import java.util.LinkedList;

import com.agile.model.Frame;

public class BowlingGameKata {

    private int maxNoOfFrames =10;
    private LinkedList<Frame> bowlingFrames = new LinkedList<Frame>();
    
    public boolean validateNoOfFrames() {
        return (bowlingFrames.size()<maxNoOfFrames);
        
    }

    public int getFrameRollCount() {
        return 2;
    }

    private int getRolledPinsForAFrame(int roll1, int roll2) {
        Frame frame = new Frame();
        frame.setRoll1(roll1);
        frame.setRoll2(roll2);
        frame.setFrameScore(getPreviousFrameScore()+roll1+roll2);
        bowlingFrames.add(frame);
        return frame.getFrameScore();
    }

    public int calculateGameScore(int roll1, int roll2) {
        if(validateNoOfFrames() && eachFramecanKnockMax10Pins(roll1,roll2))
        return getRolledPinsForAFrame(roll1, roll2);
        else
        return bowlingFrames.getLast().getFrameScore();
    }

    public int getPreviousFrameScore(){
        if(bowlingFrames.size()>0)
        return bowlingFrames.getLast().getFrameScore();
        else
        return 0;
    }

    public boolean eachFramecanKnockMax10Pins(int roll1, int roll2) {
        return ((roll1+roll2) <=10);
    }

}
