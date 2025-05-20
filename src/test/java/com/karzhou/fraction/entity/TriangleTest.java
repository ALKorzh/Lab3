package com.karzhou.fraction.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void testValidEquilateralTriangle() {
        Fraction side = new Fraction(3, 1);
        Triangle triangle = new Triangle(side, side, side);
        assertEquals(Triangle.TriangleType.EQUILATERAL, triangle.getType());
    }

    @Test
    void testValidIsoscelesTriangle() {
        Triangle triangle = new Triangle(new Fraction(5, 1), new Fraction(5, 1), new Fraction(3, 1));
        assertEquals(Triangle.TriangleType.ISOSCELES, triangle.getType());
    }

    @Test
    void testValidScaleneTriangle() {
        Triangle triangle = new Triangle(new Fraction(4, 1), new Fraction(5, 1), new Fraction(6, 1));
        assertEquals(Triangle.TriangleType.SCALENE, triangle.getType());
    }

    @Test
    void testValidRightTriangle() {
        Triangle triangle = new Triangle(new Fraction(3, 1), new Fraction(4, 1), new Fraction(5, 1));
        assertEquals(Triangle.TriangleType.RIGHT, triangle.getType());
    }

    @Test
    void testInvalidTriangle() {
        assertThrows(IllegalArgumentException.class, () ->
                new Triangle(new Fraction(1, 1), new Fraction(2, 1), new Fraction(10, 1))
        );
    }

    @Test
    void testAreaOfRightTriangle() {
        Triangle triangle = new Triangle(new Fraction(3, 1), new Fraction(4, 1), new Fraction(5, 1));
        BigDecimal expected = new BigDecimal("6.0000000000");
        assertEquals(0, triangle.area().compareTo(expected));
    }

    @Test
    void testPerimeter() {
        Triangle triangle = new Triangle(new Fraction(3, 1), new Fraction(4, 1), new Fraction(5, 1));
        BigDecimal expected = new BigDecimal("12");
        assertEquals(0, triangle.perimeter().compareTo(expected));
    }

    @Test
    void testVolumeIsAlwaysZero() {
        Triangle triangle = new Triangle(new Fraction(2, 1), new Fraction(2, 1), new Fraction(2, 1));
        assertEquals(BigDecimal.ZERO, triangle.volume());
    }

    @Test
    void testSettersWithValidValues() {
        Triangle triangle = new Triangle(new Fraction(3, 1), new Fraction(4, 1), new Fraction(5, 1));
        triangle.setSideA(new Fraction(6, 1));
        triangle.setSideB(new Fraction(7, 1));
        triangle.setSideC(new Fraction(8, 1));
        assertEquals(Triangle.TriangleType.SCALENE, triangle.getType());
    }

    @Test
    void testSettersWithInvalidValues() {
        Triangle triangle = new Triangle(new Fraction(3, 1), new Fraction(4, 1), new Fraction(5, 1));
        assertThrows(IllegalArgumentException.class, () -> triangle.setSideA(new Fraction(100, 1)));
    }

    @Test
    void testToStringIncludesType() {
        Triangle triangle = new Triangle(new Fraction(3, 1), new Fraction(4, 1), new Fraction(5, 1));
        String str = triangle.toString();
        assertTrue(str.contains("RIGHT"));
    }
}
