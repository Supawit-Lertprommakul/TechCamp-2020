package com.digitalacademy.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    // To define that a certain method is a test method, annotate it with the @Test annotation.
    @Test
    void testAddFunction(){
        int resp = calculator.add(1, 1);

        // assert statements
        assertEquals( 2, resp);
        assertEquals( 4, calculator.add(3, 1));
    }

    @Test
    void testMutiplyFunction() {

        // assert statements
        assertEquals( 4, calculator.multiply(2, 2));
        assertEquals( 6, calculator.multiply(3, 2));
        assertEquals( 6, calculator.multiply(2, 3));
    }

    @Test
    void testDivideFunction() {

        // assert statements
        assertEquals( 1, calculator.divide(2, 2));
        assertEquals( 2, calculator.divide(4, 2));
    }

    @Test
    void testDivideWithArithmeticErrorFunction() {

        // assert statements
        Exception exception = assertThrows(
                ArithmeticException.class, () ->
                        calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }
}
