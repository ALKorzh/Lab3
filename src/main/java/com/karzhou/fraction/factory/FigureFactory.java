package com.karzhou.fraction.factory;

import com.karzhou.fraction.entity.Circle;
import com.karzhou.fraction.entity.Figure;
import com.karzhou.fraction.entity.Triangle;
import com.karzhou.fraction.entity.Fraction;

public class FigureFactory {

    public static Figure createTriangle(Fraction sideA, Fraction sideB, Fraction sideC) {
        return new Triangle(sideA, sideB, sideC);
    }

    public static Figure createCircle(Fraction radius) {
        return new Circle(radius);
    }
}
