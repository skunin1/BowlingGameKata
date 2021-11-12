package com.agile;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import com.agile.model.Frame;

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

    
    //shouldPassWhenThereIsASpareInAFrame()
    @Test
    public void shouldReturnTrueWhenItIsSpare()
    {
        //Arrange
        LinkedList<Frame> frames=new LinkedList<Frame>();
        Frame frame1=new Frame();
        frame1.setRoll1(3);
        frame1.setRoll2(7);
        frames.add(frame1);
        //Act
        boolean actual=kata.isSpare(frame1.getRoll1(),frame1.getRoll2());
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
    public void shouldReturnTrueWhenItIsStrikeInAFrame()
    {
        //Arrange
        LinkedList<Frame> frames=new LinkedList<Frame>();
        Frame frame1=new Frame();
        frame1.setRoll1(10);
        frames.add(frame1);
        //act
        boolean actual=kata.isStrike(frame1.getRoll1());
        boolean expected=true;
        //Assert
        assertEquals(expected, actual);
      
    }

    @Test
    public void shouldAddNextFrameRoll1AndRoll2AsBonusToCurrentScoreIfFrameIsAStrike()
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
    @Test
    public void shouldReturnTheExpectedScoreWhenFrameHasStrikeAndSpare()
    {
        //Arrange
        LinkedList<Frame> frames=new LinkedList<Frame>();

        Frame frame1=new Frame();
        frame1.setRoll1(1);
        frame1.setRoll2(4);
        frames.add(frame1);

        Frame frame2=new Frame();
        frame2.setRoll1(3);
        frame2.setRoll2(5);
        frames.add(frame2);

        Frame frame3=new Frame();
        frame3.setRoll1(4);
        frame3.setRoll2(6);
        frames.add(frame3);

        Frame frame4=new Frame();
        frame4.setRoll1(2);
        frame4.setRoll2(0);
        frames.add(frame4);

        Frame frame5=new Frame();
        frame5.setRoll1(10);
        frames.add(frame5);

        Frame frame6=new Frame();
        frame6.setRoll1(4);
        frame6.setRoll2(3);
        frames.add(frame6);
        

        Frame frame7=new Frame();
        frame7.setRoll1(10);
        frames.add(frame7);
        

        Frame frame8=new Frame();
        frame8.setRoll1(10);
        frames.add(frame8);       

        Frame frame9=new Frame();
        frame9.setRoll1(5);
        frame9.setRoll2(3);
        frames.add(frame9);        

        Frame frame10=new Frame();
        frame10.setRoll1(4);
        frame10.setRoll2(5);
        frames.add(frame10);

        //Act
        int[] expectedScoresArray = {5,13,25,27,44,51,76,94,102,111};

        kata.calculateFrameScore(frame1.getRoll1(), frame1.getRoll2());
        kata.calculateFrameScore(frame2.getRoll1(), frame2.getRoll2());
        kata.calculateFrameScore(frame3.getRoll1(), frame3.getRoll2());
        kata.calculateFrameScore(frame4.getRoll1(), frame4.getRoll2());
        kata.calculateFrameScore(frame5.getRoll1());
        kata.calculateFrameScore(frame6.getRoll1(), frame6.getRoll2());
        kata.calculateFrameScore(frame7.getRoll1());
        kata.calculateFrameScore(frame8.getRoll1());
        kata.calculateFrameScore(frame9.getRoll1(), frame9.getRoll2());
        kata.calculateFrameScore(frame10.getRoll1(), frame10.getRoll2());
        int[] actualScoresArray = kata.getScoreBoard().stream().mapToInt(Frame::getFrameScore).toArray();
        //Assert
        assertArrayEquals(expectedScoresArray, actualScoresArray);
    }

    @Test
    public void shouldAddExtraBonusRolltoTenthFrameIfTenthFrameHasSpare()
    {
        LinkedList<Frame> frames=new LinkedList<Frame>();

        Frame frame1=new Frame();
        frame1.setRoll1(1);
        frame1.setRoll2(4);
        frames.add(frame1);

        Frame frame2=new Frame();
        frame2.setRoll1(3);
        frame2.setRoll2(5);
        frames.add(frame2);

        Frame frame3=new Frame();
        frame3.setRoll1(4);
        frame3.setRoll2(6);
        frames.add(frame3);

        Frame frame4=new Frame();
        frame4.setRoll1(2);
        frame4.setRoll2(0);
        frames.add(frame4);

        Frame frame5=new Frame();
        frame5.setRoll1(10);
        frames.add(frame5);

        Frame frame6=new Frame();
        frame6.setRoll1(4);
        frame6.setRoll2(3);
        frames.add(frame6);
        

        Frame frame7=new Frame();
        frame7.setRoll1(10);
        frames.add(frame7);
        

        Frame frame8=new Frame();
        frame8.setRoll1(10);
        frames.add(frame8);       

        Frame frame9=new Frame();
        frame9.setRoll1(5);
        frame9.setRoll2(3);
        frames.add(frame9);        

        Frame frame10=new Frame();
        frame10.setRoll1(7);
        frame10.setRoll2(3);
        frames.add(frame10);

        //Act
        int[] expectedScoresArray = {5,13,25,27,44,51,76,94,102,122};

        kata.calculateFrameScore(frame1.getRoll1(), frame1.getRoll2());
        kata.calculateFrameScore(frame2.getRoll1(), frame2.getRoll2());
        kata.calculateFrameScore(frame3.getRoll1(), frame3.getRoll2());
        kata.calculateFrameScore(frame4.getRoll1(), frame4.getRoll2());
        kata.calculateFrameScore(frame5.getRoll1());
        kata.calculateFrameScore(frame6.getRoll1(), frame6.getRoll2());
        kata.calculateFrameScore(frame7.getRoll1());
        kata.calculateFrameScore(frame8.getRoll1());
        kata.calculateFrameScore(frame9.getRoll1(), frame9.getRoll2());
        kata.calculateFrameScore(frame10.getRoll1(), frame10.getRoll2(),10);
        int[] actualScoresArray = kata.getScoreBoard().stream().mapToInt(Frame::getFrameScore).toArray();
        //Assert         
        assertArrayEquals(expectedScoresArray, actualScoresArray);
    }
     
    @Test
    public void shouldAddExtraBonusRolltoTenthFrameIfTenthFrameHasStrike()
    {
        LinkedList<Frame> frames=new LinkedList<Frame>();

        Frame frame1=new Frame();
        frame1.setRoll1(1);
        frame1.setRoll2(4);
        frames.add(frame1);

        Frame frame2=new Frame();
        frame2.setRoll1(3);
        frame2.setRoll2(5);
        frames.add(frame2);

        Frame frame3=new Frame();
        frame3.setRoll1(4);
        frame3.setRoll2(6);
        frames.add(frame3);

        Frame frame4=new Frame();
        frame4.setRoll1(2);
        frame4.setRoll2(0);
        frames.add(frame4);

        Frame frame5=new Frame();
        frame5.setRoll1(10);
        frames.add(frame5);

        Frame frame6=new Frame();
        frame6.setRoll1(4);
        frame6.setRoll2(3);
        frames.add(frame6);
        

        Frame frame7=new Frame();
        frame7.setRoll1(10);
        frames.add(frame7);
        

        Frame frame8=new Frame();
        frame8.setRoll1(10);
        frames.add(frame8);       

        Frame frame9=new Frame();
        frame9.setRoll1(5);
        frame9.setRoll2(3);
        frames.add(frame9);        

        Frame frame10=new Frame();
        frame10.setRoll1(10);
        frames.add(frame10);

        //Act
        int[] expectedScoresArray = {5,13,25,27,44,51,76,94,102,122};

        kata.calculateFrameScore(frame1.getRoll1(), frame1.getRoll2());
        kata.calculateFrameScore(frame2.getRoll1(), frame2.getRoll2());
        kata.calculateFrameScore(frame3.getRoll1(), frame3.getRoll2());
        kata.calculateFrameScore(frame4.getRoll1(), frame4.getRoll2());
        kata.calculateFrameScore(frame5.getRoll1());
        kata.calculateFrameScore(frame6.getRoll1(), frame6.getRoll2());
        kata.calculateFrameScore(frame7.getRoll1());
        kata.calculateFrameScore(frame8.getRoll1());
        kata.calculateFrameScore(frame9.getRoll1(), frame9.getRoll2());
        kata.calculateFrameScore(frame10.getRoll1(),5,5);
        int[] actualScoresArray = kata.getScoreBoard().stream().mapToInt(Frame::getFrameScore).toArray();
        //Assert         
        assertArrayEquals(expectedScoresArray, actualScoresArray);
    }

}