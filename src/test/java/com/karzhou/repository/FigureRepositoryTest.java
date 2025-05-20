package com.karzhou.repository;

import com.karzhou.fraction.entity.*;
import com.karzhou.fraction.repository.FigureRepository;
import com.karzhou.fraction.specification.Specification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FigureRepositoryTest {
    private FigureRepository repository;
    private Triangle triangle1;
    private Triangle triangle2;
    private Circle circle;

    @BeforeEach
    void setUp() {
        repository = new FigureRepository();
        triangle1 = new Triangle(new Fraction(3, 1), new Fraction(4, 1), new Fraction(5, 1)); // area = 6
        triangle2 = new Triangle(new Fraction(5, 1), new Fraction(5, 1), new Fraction(6, 1)); // area ≈ 12
        circle = new Circle(new Fraction(2, 1)); // area ≈ 12.566
    }

    @Test
    void testAddFigureAndGetFigures() {
        repository.addFigure(triangle1);
        repository.addFigure(circle);

        List<Figure> figures = repository.getFigures();

        assertAll(
                () -> assertEquals(2, figures.size()),
                () -> assertTrue(figures.contains(triangle1)),
                () -> assertTrue(figures.contains(circle))
        );
    }

    @Test
    void testRemoveFigure() {
        repository.addFigure(triangle1);
        repository.addFigure(circle);
        repository.removeFigure(triangle1);

        List<Figure> figures = repository.getFigures();

        assertAll(
                () -> assertEquals(1, figures.size()),
                () -> assertFalse(figures.contains(triangle1)),
                () -> assertTrue(figures.contains(circle))
        );
    }

    @Test
    void testSortByArea() {
        repository.addFigure(circle);      // area ≈ 12.57
        repository.addFigure(triangle1);   // area = 6
        repository.addFigure(triangle2);   // area ≈ 12

        repository.sortByArea();
        List<Figure> figures = repository.getFigures();

        assertAll(
                () -> assertEquals(triangle1, figures.get(0)),
                () -> assertEquals(triangle2, figures.get(1)),
                () -> assertEquals(circle, figures.get(2))
        );
    }

    @Test
    void testSortByPerimeter() {
        repository.addFigure(triangle2);   // perimeter = 16
        repository.addFigure(triangle1);   // perimeter = 12
        repository.addFigure(circle);      // perimeter = ~12.566

        repository.sortByPerimeter();
        List<Figure> figures = repository.getFigures();

        assertAll(
                () -> assertEquals(triangle1, figures.get(0)),
                () -> assertEquals(circle, figures.get(1)),
                () -> assertEquals(triangle2, figures.get(2))
        );
    }

    @Test
    void testSortByVolume() {
        repository.addFigure(triangle1); // volume = 0
        repository.addFigure(circle);    // volume = 0

        repository.sortByVolume();
        List<Figure> figures = repository.getFigures();

        assertAll(
                () -> assertEquals(0, figures.get(0).volume().compareTo(BigDecimal.ZERO)),
                () -> assertEquals(0, figures.get(1).volume().compareTo(BigDecimal.ZERO))
        );
    }

    @Test
    void testSortById() {
        repository.addFigure(triangle2); // random order
        repository.addFigure(circle);
        repository.addFigure(triangle1);

        repository.sortById();
        List<Figure> figures = repository.getFigures();

        assertAll(
                () -> assertTrue(figures.get(0).getId() <= figures.get(1).getId()),
                () -> assertTrue(figures.get(1).getId() <= figures.get(2).getId())
        );
    }

    @Test
    void testQuerySpecification() {
        repository.addFigure(triangle1);
        repository.addFigure(triangle2);
        repository.addFigure(circle);

        Specification<Figure> areaGreaterThan10 = figure -> figure.area().compareTo(new BigDecimal("10")) > 0;
        List<Figure> result = repository.query(areaGreaterThan10);

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertTrue(result.contains(triangle2)),
                () -> assertTrue(result.contains(circle)),
                () -> assertFalse(result.contains(triangle1))
        );
    }
}

