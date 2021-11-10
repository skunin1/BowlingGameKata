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
    public void rollAllZeroPins(){
        int noofFrames = 10;
        int actualScore = 0;
        int noOfRolls = noofFrames*2;
        for(int i=0; i<= noOfRolls; i++){
            actualScore = kata.roll(0);
        }
        int expectedScore = 0;
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void rollAllOnePins(){
        int noofFrames = 10;
        int actualScore = 0;
        int noOfRolls = noofFrames*2;
        for(int i=0; i< noOfRolls; i++){
            actualScore = kata.roll(1);
        }
        int expectedScore = 20;
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void scoreForEachFrame(){
       int actualFrameScore = kata.getFrameScore(5,3);
       int expectedFrameScore = 8;
       assertEquals(expectedFrameScore, actualFrameScore);
    }

}