package com.ravi.cal.Calculator;

import com.ravi.cal.RaviCalculator.Calculator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void twoAndThreeIsFive() {
        Calculator cal = new Calculator(2, 3);
        assertEquals(5, cal.addFunc());
    }

    @Test
    public void twoMinusThreeIsNegativeOne() {
        Calculator cal = new Calculator(2, 3);
        assertEquals(-1, cal.subFunc());  // 2 - 3 = -1
    }

    @Test
    public void threeXThreeIsNine() {
        Calculator cal = new Calculator(3, 3);
        assertEquals(9, cal.mulFunc());
    }
}
