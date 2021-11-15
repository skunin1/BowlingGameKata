package com.agile;

import java.util.LinkedList;

import com.agile.model.Frame;

public class BowlingGameKata {

    private int maxNoOfFrames = 10;
    private LinkedList<Frame> scoreBoard = new LinkedList<Frame>();

    public boolean validateNoOfFrames() {
        return (scoreBoard.size() < maxNoOfFrames);
    }

    // addFrameToScoreBoard
    private int addFrameToScoreBoard(int roll1, int roll2) {
        Frame frame = new Frame();
        frame.setRoll1(roll1);
        frame.setRoll2(roll2);
        checkAndCalculateScoreForStrike(roll1,roll2);       
        checkAndCalculateScoreForSpare(roll1,roll2);
        frame.setFrameScore(getPreviousFrameScore() + roll1 + roll2);
        scoreBoard.add(frame);
        return frame.getFrameScore();
    }

    public int calculateFrameScore(int... frameRolls) {
        if (validateNoOfFrames() 
                && scoreBoard.size() == 9 && isStrike(frameRolls[0])) {
            if(isStrike(scoreBoard.getLast().getRoll1())){
                scoreBoard.getLast().setFrameScore(scoreBoard.getLast().getFrameScore() + frameRolls[1]);
            }
            int scoreTillFrame10 = addFrameToScoreBoard(frameRolls[0],0);
            scoreBoard.getLast().setFrameScore(scoreTillFrame10 + frameRolls[1]+ frameRolls[2]);
            return scoreBoard.getLast().getFrameScore();
        } else if (validateNoOfFrames()
                && eachFramecanKnockMax10Pins(frameRolls[0], frameRolls.length > 1 ? frameRolls[1] : 0)
                && scoreBoard.size() == 9 && isSpare(frameRolls[0], frameRolls.length > 1 ? frameRolls[1] : 0)) {
            int scoreTillFrame10 = addFrameToScoreBoard(frameRolls[0], frameRolls.length > 1 ? frameRolls[1] : 0);
            scoreBoard.getLast().setFrameScore(scoreTillFrame10 + frameRolls[2]);
            return scoreBoard.getLast().getFrameScore();
        } else if (validateNoOfFrames()
                && eachFramecanKnockMax10Pins(frameRolls[0], frameRolls.length > 1 ? frameRolls[1] : 0)) {
            return addFrameToScoreBoard(frameRolls[0], frameRolls.length > 1 ? frameRolls[1] : 0);
        } else {
            return scoreBoard.getLast().getFrameScore();
        }
    }

    private int getPreviousFrameScore() {
        if (!scoreBoard.isEmpty())
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

    public boolean isSpare(int roll1, int roll2) {
        return (roll1 < 10 && (roll1 + roll2 == 10));
    }

    public boolean isStrike(int roll1) {
        return roll1 == 10;
    }

    private int checkAndCalculateScoreForStrike(int roll1,int roll2){
        if (scoreBoard.size() > 1 && isStrike(scoreBoard.getLast().getRoll1())
                && isStrike(scoreBoard.get(scoreBoard.size() - 2).getRoll1())) {
            scoreBoard.get(scoreBoard.size() - 2)
                    .setFrameScore(scoreBoard.get(scoreBoard.size() - 2).getFrameScore() + roll1);

            scoreBoard.getLast().setFrameScore(getPreviousFrameScore() + roll1 + roll1 + roll2);
        } else if (scoreBoard.size() > 0 && isStrike(scoreBoard.getLast().getRoll1())) {
            scoreBoard.getLast().setFrameScore(getPreviousFrameScore() + roll1 + roll2);
        }
        return getPreviousFrameScore() + roll1 + roll2;
    }

    private int checkAndCalculateScoreForSpare(int roll1, int roll2){
        if (scoreBoard.size() > 0 && isSpare(scoreBoard.getLast().getRoll1(), scoreBoard.getLast().getRoll2())) {
            scoreBoard.getLast().setFrameScore(getPreviousFrameScore() + roll1);            
        } 
        return (getPreviousFrameScore() + roll1 + roll2);
    }
}
