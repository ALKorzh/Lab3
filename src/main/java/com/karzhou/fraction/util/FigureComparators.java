package com.karzhou.fraction.util;

import com.karzhou.fraction.entity.Figure;

import java.util.Comparator;

public class FigureComparators {
    public static final Comparator<Figure> BY_AREA =
            Comparator.comparing(Figure::area);

    public static final Comparator<Figure> BY_PERIMETER =
            Comparator.comparing(Figure::perimeter);

    public static final Comparator<Figure> BY_VOLUME =
            Comparator.comparing(Figure::volume);

    public static final Comparator<Figure> BY_ID =
            Comparator.comparingInt(Figure::getId);
}
