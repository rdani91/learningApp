package example;


import org.junit.Before;
import org.junit.Test;

import hu.rozsa.daniel.learningapplication.eighth.MyMathProvider;

import static org.junit.Assert.assertEquals;


public class FirstTest {

    private MyMathProvider myMathProvider;

    @Before
    public void init() {
        myMathProvider = new MyMathProvider();
    }

    @Test
    public void addTwoPositiveDouble_correctAnswer() {
        assertEquals(4.6, myMathProvider.add(2.2, 2.4), 0.1);
    }
}
