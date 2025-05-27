package com.karzhou.fraction.specification;

import com.karzhou.fraction.entity.Figure;
import java.math.BigDecimal;

public class PerimeterLessSpecification implements Specification {
    private final BigDecimal threshold;

    public PerimeterLessSpecification(BigDecimal threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean isSatisfiedBy(Figure figure) {
        if (figure == null || figure.perimeter() == null) {
            return false;
        }
        return figure.perimeter().compareTo(threshold) < 0;
    }

    public PerimeterLessSpecification(double threshold) {
        this.threshold = BigDecimal.valueOf(threshold);
    }
}
