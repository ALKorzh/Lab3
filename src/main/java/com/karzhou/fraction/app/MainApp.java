package com.karzhou.fraction.app;

import com.karzhou.fraction.entity.Figure;
import com.karzhou.fraction.entity.Fraction;
import com.karzhou.fraction.factory.FigureFactory;
import com.karzhou.fraction.repository.FigureRepository;
import com.karzhou.fraction.specification.TriangleTypeSpecification;
import com.karzhou.fraction.entity.Triangle.TriangleType;
import com.karzhou.fraction.specification.AreaGreaterSpecification;
import com.karzhou.fraction.specification.PerimeterLessSpecification;
import com.karzhou.fraction.warehouse.Warehouse;
import com.karzhou.fraction.util.FractionFileReader;
import com.karzhou.fraction.exception.FractionFileReadException;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        FigureRepository repo = new FigureRepository();
        Warehouse warehouse = Warehouse.getInstance();

        Figure t1 = FigureFactory.createTriangle(new Fraction(3,1), new Fraction(4,1), new Fraction(5,1));
        Figure t2 = FigureFactory.createTriangle(new Fraction(6,1), new Fraction(6,1), new Fraction(6,1));
        Figure t3 = FigureFactory.createTriangle(new Fraction(5,1), new Fraction(5,1), new Fraction(8,1));
        Figure c1 = FigureFactory.createCircle(new Fraction(5,1));
        Figure c2 = FigureFactory.createCircle(new Fraction(3,1));

        repo.addFigure(t1);
        repo.addFigure(t2);
        repo.addFigure(t3);
        repo.addFigure(c1);
        repo.addFigure(c2);

        System.out.println("All figures and their metrics:");
        for (Figure f : repo.getFigures()) {
            System.out.println(f);
            System.out.println("Metrics: " + warehouse.getMetrics(f.getId()));
        }

        System.out.println("\nEquilateral triangles:");
        List<Figure> equilateral = repo.query(new TriangleTypeSpecification(TriangleType.EQUILATERAL));
        equilateral.forEach(System.out::println);

        System.out.println("\nFigures with area > 10:");
        List<Figure> largeArea = repo.query(new AreaGreaterSpecification(new Fraction(10,1).toBigDecimal()));
        largeArea.forEach(f -> System.out.printf("%s - area=%.2f\n", f, f.area().doubleValue()));

        System.out.println("\nFigures with perimeter < 20:");
        List<Figure> smallPerimeter = repo.query(new PerimeterLessSpecification(new Fraction(20,1).toBigDecimal()));
        smallPerimeter.forEach(f -> System.out.printf("%s - perimeter=%.2f\n", f, f.perimeter().doubleValue()));

        repo.sortByArea();
        System.out.println("\nFigures sorted by area:");
        for (Figure f : repo.getFigures()) {
            System.out.printf("%s - area=%.2f\n", f, f.area().doubleValue());
        }

        repo.sortByPerimeter();
        System.out.println("\nFigures sorted by perimeter:");
        for (Figure f : repo.getFigures()) {
            System.out.printf("%s - perimeter=%.2f\n", f, f.perimeter().doubleValue());
        }

        System.out.println("\nWorking with Fractions loaded from resource file:");
        FractionFileReader reader = new FractionFileReader();
        List<Fraction> fractions;
        try {
            fractions = reader.readFractionsFromResources("fractions.txt");
            fractions.forEach(f -> System.out.println("Fraction: " + f));
            if (fractions.size() >= 2) {
                Fraction a = fractions.get(0);
                Fraction b = fractions.get(1);
                System.out.printf("\nFraction arithmetic demo with %s and %s:\n", a, b);
                System.out.println("Sum: " + a.add(b));
                System.out.println("Difference: " + a.subtract(b));
                System.out.println("Product: " + a.multiply(b));
                System.out.println("Quotient: " + a.divide(b));
            }
        } catch (FractionFileReadException e) {
            System.err.println("Error reading fractions file: " + e.getMessage());
            fractions = List.of();
        }

        System.out.println("\nValidation of all figures in repository:");
        boolean valid = true;
        for (Figure f : repo.getFigures()) {
            if (f.area().compareTo(new Fraction(0,1).toBigDecimal()) < 0 ||
                    f.perimeter().compareTo(new Fraction(0,1).toBigDecimal()) < 0) {
                valid = false;
                System.out.println("Invalid figure found: " + f);
            }
        }
        System.out.println("All figures valid? " + valid);
    }
}
