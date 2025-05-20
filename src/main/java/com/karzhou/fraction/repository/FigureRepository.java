package com.karzhou.fraction.repository;

import com.karzhou.fraction.entity.Figure;
import com.karzhou.fraction.observer.Observable;
import com.karzhou.fraction.specification.Specification;
import com.karzhou.fraction.warehouse.Warehouse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FigureRepository {
    private final List<Figure> figures = new ArrayList<>();
    private final Warehouse warehouse = Warehouse.getInstance();

    public void addFigure(Figure figure) {
        figure.addObserver(warehouse);
        figures.add(figure);
        warehouse.update((Observable) figure);
    }

    public void removeFigure(Figure figure) {
        figure.removeObserver(warehouse);
        figures.remove(figure);
        warehouse.removeMetrics(figure.getId());
    }

    // Универсальный запрос по спецификации
    public List<Figure> query(Specification<Figure> spec) {
        return figures.stream()
                .filter(spec::isSatisfiedBy)
                .collect(Collectors.toList());
    }

    // Сортировки с учётом BigDecimal (сравнение через compareTo)
    public void sortByArea() {
        figures.sort(Comparator.comparing(Figure::area));
    }

    public void sortByPerimeter() {
        figures.sort(Comparator.comparing(Figure::perimeter));
    }

    public void sortByVolume() {
        figures.sort(Comparator.comparing(Figure::volume));
    }

    public void sortById() {
        figures.sort(Comparator.comparingInt(Figure::getId));
    }

    public List<Figure> getFigures() {
        return new ArrayList<>(figures);
    }
}
