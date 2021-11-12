package com.agile;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import com.agile.model.Frame;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class BowlingGameKataTest{

    private BowlingGameKata kata;
    @Before
    public void setUp() throws Exception{
        kata = new BowlingGameKata();
    }

     @Test
    public void BowlingGameShouldHave10Frames(){
        boolean actualFrameCount = kata.validateNoOfFrames();
        boolean expectedFrameCount =true;
        assertEquals(expectedFrameCount, actualFrameCount);
    }

    @Test
    public void shouldPassIfEachFramecanKnockLessThanOrEqualTo10Pins(){
        boolean actualPinsCountPerFrame = kata.eachFramecanKnockMax10Pins(9,1);
        boolean expectedMaxPinsCountPerFrame = true;
        assertEquals(expectedMaxPinsCountPerFrame,actualPinsCountPerFrame);        
    }

    @Test
    public void shouldReturnTheSumOfCurrentFramesTwoRollsAndPreviousFrameScore(){//whatweexpectwhenwhatpassed
        //Arrange
        kata.calculateFrameScore(3, 5);
        kata.calculateFrameScore(5, 4);
        kata.calculateFrameScore(4, 4);
        kata.calculateFrameScore(3, 5);
        kata.calculateFrameScore(5, 4);
        kata.calculateFrameScore(4, 4);
        kata.calculateFrameScore(3, 5);
        kata.calculateFrameScore(5, 4);
        kata.calculateFrameScore(4, 4);
        kata.calculateFrameScore(1, 4);
        kata.calculateFrameScore(5, 4);
        kata.calculateFrameScore(4, 4);
        kata.calculateFrameScore(1, 4);
        int expectedScoreTillCurrentFrame = 80;
        //Act
        int actualScoreTillCurrentFrame = kata.calculateFrameScore(4,3);        
        //Assert
        assertEquals(expectedScoreTillCurrentFrame, actualScoreTillCurrentFrame);
    }

    // shouldPassWhenThereIsASpareInAFrame
    @Test
    public void shouldPassWhenThereIsASpareInAFrame()
    {
        //Arrange
        LinkedList<Frame> frames=new LinkedList<Frame>();
        Frame frame1=new Frame();
        frame1.setRoll1(3);
        frame1.setRoll2(7);
        frames.add(frame1);
        //Act
        boolean actual=kata.isSpare(frame1);
        boolean expected=true;
        //Assert
        assertEquals(expected, actual);

    }
// shouldAddCurrentFrameScoreAndNextOneRollIfFrameIsSpare
    @Test
    public void shouldAddNextOneRollAsBonusToCurrentScoreIfFrameIsSpare()
    {
        //Arrange
        LinkedList<Frame> frames=new LinkedList<Frame>();
        Frame frame1=new Frame();
        frame1.setRoll1(2);
        frame1.setRoll2(7);
        frames.add(frame1);
        Frame frame2=new Frame();
        frame2.setRoll1(3);
        frame2.setRoll2(7);
        frames.add(frame2);
        Frame frame3=new Frame();
        frame3.setRoll1(2);
        frame3.setRoll2(5);
        frames.add(frame3);

        //Act
        int expectedScore=21;
        kata.calculateFrameScore(frame1.getRoll1(), frame1.getRoll2());
        kata.calculateFrameScore(frame2.getRoll1(), frame2.getRoll2());
        kata.calculateFrameScore(frame3.getRoll1(), frame3.getRoll2());

        LinkedList<Frame> scoreBoard=kata.getScoreBoard();
        int actualScore=scoreBoard.get(1).getFrameScore();
        assertEquals(expectedScore,actualScore);
    }
    
    @Test
    public void shouldPassWhenThereIsAStrikeInAFrame()
    {
        //Arrange
        LinkedList<Frame> frames=new LinkedList<Frame>();
        Frame frame1=new Frame();
        frame1.setRoll1(10);
        frames.add(frame1);
        //act
        boolean actual=kata.isStrike(frame1);
        boolean expected=true;
        //Assert
        assertEquals(expected, actual);
      
    }

    @Test
    public void shouldAddNextFrameRollsAsBonusToCurrentScoreIfFrameIsAStrike()
    {
        //Arrange
        LinkedList<Frame> frames=new LinkedList<Frame>();
        Frame frame1=new Frame();
        frame1.setRoll1(2);
        frame1.setRoll2(7);
        frames.add(frame1);
        Frame frame2=new Frame();
        frame2.setRoll1(10);
        frames.add(frame2);
        Frame frame3=new Frame();
        frame3.setRoll1(2);
        frame3.setRoll2(5);
        frames.add(frame3);

        //Act
        int expectedScore=26;
        kata.calculateFrameScore(frame1.getRoll1(), frame1.getRoll2());
        kata.calculateFrameScore(frame2.getRoll1());
        kata.calculateFrameScore(frame3.getRoll1(), frame3.getRoll2());

        LinkedList<Frame> scoreBoard=kata.getScoreBoard();
        int actualScore=scoreBoard.get(1).getFrameScore();
        assertEquals(expectedScore,actualScore);        
    }
}