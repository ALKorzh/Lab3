package com.karzhou.specification;

import com.karzhou.fraction.entity.Fraction;
import com.karzhou.fraction.entity.Triangle;
import com.karzhou.fraction.entity.Triangle.TriangleType;
import com.karzhou.fraction.entity.Figure;
import com.karzhou.fraction.specification.TriangleTypeSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class TriangleTypeSpecificationTest {

    @Test
    void testIsSatisfiedBy() {
        TriangleTypeSpecification specIsosceles = new TriangleTypeSpecification(TriangleType.ISOSCELES);
        TriangleTypeSpecification specEquilateral = new TriangleTypeSpecification(TriangleType.EQUILATERAL);
        TriangleTypeSpecification specScalene = new TriangleTypeSpecification(TriangleType.SCALENE);
        TriangleTypeSpecification specRight = new TriangleTypeSpecification(TriangleType.RIGHT);

        // Создаем валидные треугольники с разными типами

        Triangle equilateral = new Triangle(
                new Fraction(1, 1),
                new Fraction(1, 1),
                new Fraction(1, 1)
        );

        Triangle isosceles = new Triangle(
                new Fraction(2, 1),
                new Fraction(2, 1),
                new Fraction(3, 1)
        );

        Triangle right = new Triangle(
                new Fraction(3, 1),
                new Fraction(4, 1),
                new Fraction(5, 1)
        );

        // Проверяем корректность определения типов
        assertAll("triangle types",
                () -> assertEquals(TriangleType.EQUILATERAL, equilateral.getType()),
                () -> assertEquals(TriangleType.ISOSCELES, isosceles.getType()),
                () -> assertEquals(TriangleType.RIGHT, right.getType())
        );

        // Проверяем isSatisfiedBy для каждого типа
        assertAll("specification satisfaction",
                () -> assertTrue(specEquilateral.isSatisfiedBy(equilateral)),
                () -> assertFalse(specEquilateral.isSatisfiedBy(isosceles)),
                () -> assertFalse(specEquilateral.isSatisfiedBy(right)),

                () -> assertFalse(specIsosceles.isSatisfiedBy(equilateral)),
                () -> assertTrue(specIsosceles.isSatisfiedBy(isosceles)),
                () -> assertFalse(specIsosceles.isSatisfiedBy(right)),

                () -> assertFalse(specScalene.isSatisfiedBy(equilateral)),
                () -> assertFalse(specScalene.isSatisfiedBy(isosceles)),
                () -> assertFalse(specScalene.isSatisfiedBy(right)),

                () -> assertFalse(specRight.isSatisfiedBy(equilateral)),
                () -> assertFalse(specRight.isSatisfiedBy(isosceles)),
                () -> assertTrue(specRight.isSatisfiedBy(right))
        );

        // Проверяем null и не-треугольники
        Figure nonTriangle = new Figure() {
            @Override
            public BigDecimal area() { return null; }
            @Override
            public BigDecimal perimeter() { return null; }
            @Override
            public BigDecimal volume() { return null; }
        };

        assertAll("null and non-triangle",
                () -> assertFalse(specEquilateral.isSatisfiedBy(null)),
                () -> assertFalse(specEquilateral.isSatisfiedBy(nonTriangle))
        );
    }
}

