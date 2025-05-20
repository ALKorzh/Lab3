package com.karzhou.fraction.service;

import com.karzhou.fraction.entity.Figure;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FigureService {

    // Фильтрация фигур по типу (имя класса, например "Triangle", "Circle" и т.п.)
    public List<Figure> filterByType(List<Figure> figures, String type) {
        if (figures == null || type == null) return new ArrayList<>();
        return figures.stream()
                .filter(f -> f != null && type.equalsIgnoreCase(f.getClass().getSimpleName()))
                .collect(Collectors.toList());
    }

    // Сортировка по площади (по возрастанию)
    public void sortByArea(List<Figure> figures) {
        if (figures == null) return;
        figures.sort(Comparator.comparing(Figure::area));
    }

    // Сортировка по периметру (по возрастанию)
    public void sortByPerimeter(List<Figure> figures) {
        if (figures == null) return;
        figures.sort(Comparator.comparing(Figure::perimeter));
    }

    // Сортировка по объему (по возрастанию)
    public void sortByVolume(List<Figure> figures) {
        if (figures == null) return;
        figures.sort(Comparator.comparing(Figure::volume));
    }

    // Валидация: фигура не null, площадь, периметр и объем >= 0
    public boolean validateFigures(List<Figure> figures) {
        if (figures == null) return false;
        for (Figure f : figures) {
            if (f == null) return false;
            // Проверяем, что area, perimeter и volume не null и >= 0
            if (f.area() == null || f.area().compareTo(BigDecimal.ZERO) < 0) return false;
            if (f.perimeter() == null || f.perimeter().compareTo(BigDecimal.ZERO) < 0) return false;
            if (f.volume() == null || f.volume().compareTo(BigDecimal.ZERO) < 0) return false;
        }
        return true;
    }
}
