package com.karzhou.specification;

import com.karzhou.fraction.entity.Figure;
import com.karzhou.fraction.specification.PerimeterLessSpecification;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PerimeterLessSpecificationTest {

    // Простая заглушка Figure для тестов
    static class StubFigure extends Figure {
        private final BigDecimal perimeter;

        StubFigure(BigDecimal perimeter) {
            super();
            this.perimeter = perimeter;
        }

        @Override
        public BigDecimal area() {
            return BigDecimal.ZERO;
        }

        @Override
        public BigDecimal perimeter() {
            return perimeter;
        }

        @Override
        public BigDecimal volume() {
            return BigDecimal.ZERO;
        }
    }

    @Test
    void testIsSatisfiedBy() {
        PerimeterLessSpecification spec = new PerimeterLessSpecification(BigDecimal.valueOf(10));

        Figure f1 = new StubFigure(BigDecimal.valueOf(5));
        Figure f2 = new StubFigure(BigDecimal.valueOf(10));
        Figure f3 = new StubFigure(BigDecimal.valueOf(15));
        Figure fNullPerimeter = new StubFigure(null);

        assertAll(
                () -> assertTrue(spec.isSatisfiedBy(f1), "Perimeter 5 < 10, should be true"),
                () -> assertFalse(spec.isSatisfiedBy(f2), "Perimeter 10 == 10, should be false"),
                () -> assertFalse(spec.isSatisfiedBy(f3), "Perimeter 15 > 10, should be false"),
                () -> assertFalse(spec.isSatisfiedBy(fNullPerimeter), "Null perimeter, should be false"),
                () -> assertFalse(spec.isSatisfiedBy(null), "Null figure, should be false")
        );
    }

    @Test
    void testConstructorWithDouble() {
        PerimeterLessSpecification spec = new PerimeterLessSpecification(7.5);

        Figure f1 = new StubFigure(BigDecimal.valueOf(5));
        Figure f2 = new StubFigure(BigDecimal.valueOf(8));

        assertAll(
                () -> assertTrue(spec.isSatisfiedBy(f1), "Perimeter 5 < 7.5, should be true"),
                () -> assertFalse(spec.isSatisfiedBy(f2), "Perimeter 8 > 7.5, should be false")
        );
    }
}

