package com.karzhou.fraction.specification;

import com.karzhou.fraction.entity.Figure;
import com.karzhou.fraction.entity.Triangle;

public class TriangleTypeSpecification implements Specification {
    private final Triangle.TriangleType requiredType;

    public TriangleTypeSpecification(Triangle.TriangleType type) {
        this.requiredType = type;
    }

    @Override
    public boolean isSatisfiedBy(Figure figure) {
        if (figure instanceof Triangle) {
            Triangle triangle = (Triangle) figure;
            return triangle.getType() == requiredType;
        }
        return false;
    }
}

