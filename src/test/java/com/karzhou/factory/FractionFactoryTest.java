package com.karzhou.factory;

import com.karzhou.fraction.entity.Fraction;
import com.karzhou.fraction.factory.FractionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionFactoryTest {

    private final FractionFactory factory = new FractionFactory();

    @Test
    void testCreateFractionWithValidStringFraction() {
        Fraction fraction = factory.createFraction("7/3");

        assertAll(
                () -> assertNotNull(fraction),
                () -> assertEquals(7, fraction.getNumerator()),
                () -> assertEquals(3, fraction.getDenominator())
        );
    }

    @Test
    void testCreateFractionWithValidIntegerString() {
        Fraction fraction = factory.createFraction("42");

        assertAll(
                () -> assertNotNull(fraction),
                () -> assertEquals(42, fraction.getNumerator()),
                () -> assertEquals(1, fraction.getDenominator())
        );
    }

    @Test
    void testCreateFractionWithNegativeNumerator() {
        Fraction fraction = factory.createFraction("-5/2");

        assertAll(
                () -> assertNotNull(fraction),
                () -> assertEquals(-5, fraction.getNumerator()),
                () -> assertEquals(2, fraction.getDenominator())
        );
    }

    @Test
    void testCreateFractionWithNegativeDenominator() {
        Fraction fraction = factory.createFraction("3/-4");

        assertAll(
                () -> assertNotNull(fraction),
                () -> assertEquals(-3, fraction.getNumerator()), // предполагается нормализация
                () -> assertEquals(4, fraction.getDenominator())
        );
    }

    @Test
    void testCreateFractionWithZeroNumerator() {
        Fraction fraction = factory.createFraction("0/5");

        assertAll(
                () -> assertNotNull(fraction),
                () -> assertEquals(0, fraction.getNumerator()),
                () -> assertEquals(1, fraction.getDenominator()) // предполагается нормализация
        );
    }

    @Test
    void testCreateFractionWithZeroDenominatorThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                factory.createFraction("3/0"));
    }

    @Test
    void testCreateFractionWithEmptyStringThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                factory.createFraction(""));
    }

    @Test
    void testCreateFractionWithNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                factory.createFraction(null));
    }

    @Test
    void testCreateFractionWithInvalidFormatThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                factory.createFraction("abc/def"));
    }

    @Test
    void testCreateFractionWithTooManySlashesThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                factory.createFraction("1/2/3"));
    }
}