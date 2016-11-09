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
        assertEquals(4.6, myMathProvider.add(4.0, 0.6), 0.01);
    }

    @Test
    public void absPositiveNumber_correctAnswer() throws Exception {
        assertEquals(3.2, myMathProvider.abs(3.2), 0.01);
    }

    @Test
    public void absNegativeNumber_correctAnswer() throws Exception {
        assertEquals(3.2, myMathProvider.abs(-3.2), 0.01);

    }


    @Test(expected = IllegalArgumentException.class)
    public void divideZero_exeptionThrown() throws Exception {
        myMathProvider.divide(3.2, 0);
    }
}
