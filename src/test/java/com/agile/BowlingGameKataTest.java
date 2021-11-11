package com.agile;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

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
        kata.calculateGameScore(3, 5);
        kata.calculateGameScore(5, 4);
        kata.calculateGameScore(4, 4);
        kata.calculateGameScore(3, 5);
        kata.calculateGameScore(5, 4);
        kata.calculateGameScore(4, 4);
        kata.calculateGameScore(3, 5);
        kata.calculateGameScore(5, 4);
        kata.calculateGameScore(4, 4);
        kata.calculateGameScore(1, 4);
        kata.calculateGameScore(5, 4);
        kata.calculateGameScore(4, 4);
        kata.calculateGameScore(1, 4);
        int expectedScoreTillCurrentFrame = 80;
        //Act
        int actualScoreTillCurrentFrame = kata.calculateGameScore(4,3);        
        //Assert
        assertEquals(expectedScoreTillCurrentFrame, actualScoreTillCurrentFrame);
    }

    
}