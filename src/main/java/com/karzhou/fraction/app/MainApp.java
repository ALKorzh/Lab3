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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MainApp {

    public static void main(String[] args) {
        FigureRepository repo = new FigureRepository();
        Warehouse warehouse = Warehouse.getInstance();

        // --- Создание фигур через фабрику ---
        Figure t1 = FigureFactory.createTriangle(new Fraction(3,1), new Fraction(4,1), new Fraction(5,1));       // разносторонний треугольник
        Figure t2 = FigureFactory.createTriangle(new Fraction(6,1), new Fraction(6,1), new Fraction(6,1));       // равносторонний треугольник
        Figure t3 = FigureFactory.createTriangle(new Fraction(5,1), new Fraction(5,1), new Fraction(8,1));       // равнобедренный треугольник
        Figure c1 = FigureFactory.createCircle(new Fraction(5,1));               // круг с радиусом 5
        Figure c2 = FigureFactory.createCircle(new Fraction(3,1));

        // Добавляем фигуры в репозиторий — автоматически добавляются в Warehouse
        repo.addFigure(t1);
        repo.addFigure(t2);
        repo.addFigure(t3);
        repo.addFigure(c1);
        repo.addFigure(c2);

        // --- Вывод всех фигур и их метрик из Warehouse ---
        System.out.println("All figures and their metrics:");
        for (Figure f : repo.getFigures()) {
            System.out.println(f);
            System.out.println("Metrics: " + warehouse.getMetrics(f.getId()));
        }

        // --- Пример Specification: выбрать все равносторонние треугольники ---
        System.out.println("\nEquilateral triangles:");
        List<Figure> equilateral = repo.query(new TriangleTypeSpecification(TriangleType.EQUILATERAL));
        equilateral.forEach(System.out::println);

        // --- Пример Specification: площадь больше 10 ---
        System.out.println("\nFigures with area > 10:");
        List<Figure> largeArea = repo.query(new AreaGreaterSpecification(new Fraction(10,1).toBigDecimal()));
        largeArea.forEach(f -> System.out.printf("%s - area=%.2f\n", f, f.area().doubleValue()));

        // --- Пример Specification: периметр меньше 20 ---
        System.out.println("\nFigures with perimeter < 20:");
        List<Figure> smallPerimeter = repo.query(new PerimeterLessSpecification(new Fraction(20,1).toBigDecimal()));
        smallPerimeter.forEach(f -> System.out.printf("%s - perimeter=%.2f\n", f, f.perimeter().doubleValue()));

        // --- Сортировка фигур по площади ---
        repo.sortByArea();
        System.out.println("\nFigures sorted by area:");
        for (Figure f : repo.getFigures()) {
            System.out.printf("%s - area=%.2f\n", f, f.area().doubleValue());
        }

        // --- Сортировка фигур по периметру ---
        repo.sortByPerimeter();
        System.out.println("\nFigures sorted by perimeter:");
        for (Figure f : repo.getFigures()) {
            System.out.printf("%s - perimeter=%.2f\n", f, f.perimeter().doubleValue());
        }

        // --- Работа с Fraction: загрузка дробей из файла fractions.txt ---
        System.out.println("\nWorking with Fractions loaded from file:");
        List<Fraction> fractions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("fractions.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("/");
                if (parts.length == 2) {
                    try {
                        int num = Integer.parseInt(parts[0]);
                        int den = Integer.parseInt(parts[1]);
                        Fraction frac = new Fraction(num, den);
                        fractions.add(frac);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid fraction format: " + line);
                    } catch (ArithmeticException e) {
                        System.err.println("Invalid fraction (denominator zero): " + line);
                    }
                } else {
                    System.err.println("Invalid fraction line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading fractions file: " + e.getMessage());
        }

        // --- Выводим все дроби ---
        fractions.forEach(f -> System.out.println("Fraction: " + f));

        // --- Демонстрация арифметики с Fraction ---
        if (fractions.size() >= 2) {
            Fraction a = fractions.get(0);
            Fraction b = fractions.get(1);
            System.out.printf("\nFraction arithmetic demo with %s and %s:\n", a, b);
            System.out.println("Sum: " + a.add(b));
            System.out.println("Difference: " + a.subtract(b));
            System.out.println("Product: " + a.multiply(b));
            System.out.println("Quotient: " + a.divide(b));
        }

        // --- Валидация фигур (площадь, периметр, объем не отрицательны) ---
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
