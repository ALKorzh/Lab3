package com.karzhou.service;

import com.karzhou.fraction.entity.Circle;
import com.karzhou.fraction.entity.Figure;
import com.karzhou.fraction.entity.Fraction;
import com.karzhou.fraction.entity.Triangle;
import com.karzhou.fraction.service.FigureService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FigureServiceTest {

    private final FigureService service = new FigureService();

    @Test
    void testFilterByType() {
        Figure tri = new Triangle(
                new Fraction(3, 1),
                new Fraction(4, 1),
                new Fraction(5, 1)
        );
        Figure circ = new Circle(new Fraction(5, 1));
        List<Figure> figures = Arrays.asList(tri, circ, null);

        List<Figure> filteredTriangles = service.filterByType(figures, "Triangle");
        List<Figure> filteredCircles = service.filterByType(figures, "circle");
        List<Figure> filteredNull = service.filterByType(null, "Triangle");
        List<Figure> filteredTypeNull = service.filterByType(figures, null);

        assertAll(
                () -> assertEquals(1, filteredTriangles.size()),
                () -> assertTrue(filteredTriangles.contains(tri)),
                () -> assertEquals(1, filteredCircles.size()),
                () -> assertTrue(filteredCircles.contains(circ)),
                () -> assertTrue(filteredNull.isEmpty()),
                () -> assertTrue(filteredTypeNull.isEmpty())
        );
    }

    @Test
    void testSortByArea() {
        Figure f1 = new Triangle(
                new Fraction(7, 1),
                new Fraction(8, 1),
                new Fraction(9, 1)
        );
        Figure f2 = new Circle(new Fraction(2, 1));
        Figure f3 = new Triangle(
                new Fraction(3, 1),
                new Fraction(4, 1),
                new Fraction(5, 1)
        );

        List<Figure> list = Arrays.asList(f1, f2, f3);
        service.sortByArea(list);

        assertAll(
                () -> assertEquals(f3, list.get(0)),
                () -> assertEquals(f2, list.get(1)),
                () -> assertEquals(f1, list.get(2))
        );
    }

    @Test
    void testSortByPerimeter() {
        Figure f1 = new Triangle(
                new Fraction(6, 1),
                new Fraction(7, 1),
                new Fraction(8, 1)
        );
        Figure f2 = new Circle(new Fraction(1, 1));
        Figure f3 = new Triangle(
                new Fraction(9, 1),
                new Fraction(10, 1),
                new Fraction(11, 1)
        );

        List<Figure> list = Arrays.asList(f1, f2, f3);
        service.sortByPerimeter(list);

        assertAll(
                () -> assertEquals(f2, list.get(0)),
                () -> assertEquals(f1, list.get(1)),
                () -> assertEquals(f3, list.get(2))
        );
    }

    @Test
    void testSortByVolume() {
        Figure f1 = new Triangle(
                new Fraction(6, 1),
                new Fraction(7, 1),
                new Fraction(8, 1)
        ) {
            @Override
            public java.math.BigDecimal volume() {
                return java.math.BigDecimal.valueOf(3);
            }
        };
        Figure f2 = new Circle(new Fraction(1, 1)) {
            @Override
            public java.math.BigDecimal volume() {
                return java.math.BigDecimal.valueOf(1);
            }
        };
        Figure f3 = new Triangle(
                new Fraction(9, 1),
                new Fraction(10, 1),
                new Fraction(11, 1)
        ) {
            @Override
            public java.math.BigDecimal volume() {
                return java.math.BigDecimal.valueOf(5);
            }
        };

        List<Figure> list = Arrays.asList(f1, f2, f3);
        service.sortByVolume(list);

        assertAll(
                () -> assertEquals(f2, list.get(0)),
                () -> assertEquals(f1, list.get(1)),
                () -> assertEquals(f3, list.get(2))
        );
    }

    @Test
    void testValidateFigures() {
        Figure validFigure = new Triangle(
                new Fraction(3, 1),
                new Fraction(4, 1),
                new Fraction(5, 1)
        );
        Figure invalidFigureNullArea = new Triangle(
                new Fraction(3, 1),
                new Fraction(4, 1),
                new Fraction(5, 1)
        ) {
            @Override
            public java.math.BigDecimal area() {
                return null;
            }
        };
        Figure invalidFigureNegativePerimeter = new Triangle(
                new Fraction(3, 1),
                new Fraction(4, 1),
                new Fraction(5, 1)
        ) {
            @Override
            public java.math.BigDecimal perimeter() {
                return java.math.BigDecimal.valueOf(-1);
            }
        };
        Figure invalidFigureNegativeVolume = new Triangle(
                new Fraction(3, 1),
                new Fraction(4, 1),
                new Fraction(5, 1)
        ) {
            @Override
            public java.math.BigDecimal volume() {
                return java.math.BigDecimal.valueOf(-1);
            }
        };

        assertAll(
                () -> assertTrue(service.validateFigures(Collections.singletonList(validFigure))),
                () -> assertFalse(service.validateFigures(Collections.singletonList(invalidFigureNullArea))),
                () -> assertFalse(service.validateFigures(Collections.singletonList(invalidFigureNegativePerimeter))),
                () -> assertFalse(service.validateFigures(Collections.singletonList(invalidFigureNegativeVolume))),
                () -> assertFalse(service.validateFigures(Collections.singletonList((Figure) null))),
                () -> assertFalse(service.validateFigures(null))
        );
    }
}
