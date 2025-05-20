package com.karzhou.specification;

import com.karzhou.fraction.entity.Figure;
import com.karzhou.fraction.specification.AreaGreaterSpecification;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AreaGreaterSpecificationTest {

    // Заглушка Figure с заданной площадью и заглушками остальных методов
    static class StubFigure extends Figure {
        private final BigDecimal area;

        public StubFigure(BigDecimal area) {
            this.area = area;
        }

        @Override
        public BigDecimal area() {
            return area;
        }

        @Override
        public BigDecimal perimeter() {
            return BigDecimal.ZERO; // заглушка
        }

        @Override
        public BigDecimal volume() {
            return BigDecimal.ZERO; // заглушка
        }
    }

    @Test
    void testIsSatisfiedBy() {
        BigDecimal threshold = new BigDecimal("10.0");
        AreaGreaterSpecification spec = new AreaGreaterSpecification(threshold);

        Figure figureNullArea = new StubFigure(null);
        Figure figureLess = new StubFigure(new BigDecimal("5"));
        Figure figureEqual = new StubFigure(new BigDecimal("10"));
        Figure figureGreater = new StubFigure(new BigDecimal("15"));

        assertAll("AreaGreaterSpecification tests",
                () -> assertFalse(spec.isSatisfiedBy(null), "null figure should return false"),
                () -> assertFalse(spec.isSatisfiedBy(figureNullArea), "figure with null area should return false"),
                () -> assertFalse(spec.isSatisfiedBy(figureLess), "area less than threshold should return false"),
                () -> assertFalse(spec.isSatisfiedBy(figureEqual), "area equal to threshold should return false"),
                () -> assertTrue(spec.isSatisfiedBy(figureGreater), "area greater than threshold should return true")
        );
    }
}
