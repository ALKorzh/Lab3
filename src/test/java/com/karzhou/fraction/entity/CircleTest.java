package com.karzhou.fraction.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    void testCircleMetricsTogether() {
        Fraction radius = new Fraction(3, 2); // 1.5
        Circle circle = new Circle(radius);

        BigDecimal expectedArea = BigDecimal.valueOf(Math.PI)
                .multiply(BigDecimal.valueOf(1.5 * 1.5))
                .setScale(6, BigDecimal.ROUND_HALF_UP);

        BigDecimal expectedPerimeter = BigDecimal.valueOf(2 * Math.PI * 1.5)
                .setScale(6, BigDecimal.ROUND_HALF_UP);

        assertAll("circle metrics",
                () -> assertEquals(0, circle.area().compareTo(expectedArea), "Area mismatch"),
                () -> assertEquals(0, circle.perimeter().compareTo(expectedPerimeter), "Perimeter mismatch"),
                () -> assertEquals(BigDecimal.ZERO, circle.volume(), "Volume should be zero")
        );
    }
}
