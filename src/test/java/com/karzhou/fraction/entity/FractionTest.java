package com.karzhou.fraction.entity;

import com.karzhou.fraction.entity.Fraction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    @Test
    void testConstructorReductionAndSign() {
        assertAll("Reduction and sign handling",
                () -> assertEquals(new Fraction(1, 2), new Fraction(2, 4)),
                () -> assertEquals(new Fraction(-1, 2), new Fraction(1, -2)),
                () -> assertEquals(new Fraction(1, 2), new Fraction(-2, -4)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Fraction(1, 0))
        );
    }

    @Test
    void testBasicOperations() {
        Fraction a = new Fraction(1, 2);
        Fraction b = new Fraction(1, 3);

        assertAll("Add, Subtract, Multiply, Divide",
                () -> assertEquals(new Fraction(5, 6), a.add(b)),
                () -> assertEquals(new Fraction(1, 6), a.subtract(b)),
                () -> assertEquals(new Fraction(1, 6), a.multiply(b)),
                () -> assertEquals(new Fraction(3, 2), a.divide(b)),
                () -> assertThrows(ArithmeticException.class, () -> a.divide(Fraction.ZERO))
        );
    }

    @Test
    void testCompareToEqualsHashCode() {
        Fraction a = new Fraction(2, 4);
        Fraction b = new Fraction(1, 2);
        Fraction c = new Fraction(3, 4);

        assertAll("Equals and compareTo",
                () -> assertEquals(a, b),
                () -> assertEquals(0, a.compareTo(b)),
                () -> assertTrue(a.compareTo(c) < 0),
                () -> assertTrue(c.compareTo(a) > 0),
                () -> assertEquals(b.hashCode(), a.hashCode())
        );
    }

    @Test
    void testToStringAndToBigDecimal() {
        Fraction a = new Fraction(3, 1);
        Fraction b = new Fraction(3, 2);

        assertAll("String and BigDecimal",
                () -> assertEquals("3", a.toString()),
                () -> assertEquals("3/2", b.toString()),
                () -> assertEquals(new BigDecimal("1.5"), b.toBigDecimal().setScale(1))
        );
    }
}

