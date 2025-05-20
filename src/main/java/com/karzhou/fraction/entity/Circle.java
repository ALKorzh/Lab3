package com.karzhou.fraction.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Circle extends Figure {
    private Fraction radius;

    public Circle(Fraction radius) {
        if (radius == null) throw new IllegalArgumentException("Radius cannot be null");
        if (radius.getNumerator() <= 0) throw new IllegalArgumentException("Radius must be positive");
        this.radius = radius;
        notifyChange();
    }

    public Fraction getRadius() {
        return radius;
    }

    public void setRadius(Fraction radius) {
        if (radius == null) throw new IllegalArgumentException("Radius cannot be null");
        if (radius.getNumerator() <= 0) throw new IllegalArgumentException("Radius must be positive");
        this.radius = radius;
        notifyChange();
    }

    // Возвращает площадь круга как Fraction * PI (как BigDecimal)
    public BigDecimal area() {
        // area = π * r^2
        Fraction rSquared = radius.multiply(radius);
        return fractionToBigDecimal(rSquared).multiply(BigDecimal.valueOf(Math.PI)).setScale(6, RoundingMode.HALF_UP);
    }

    // Периметр = 2 * π * r
    public BigDecimal perimeter() {
        return fractionToBigDecimal(radius)
                .multiply(BigDecimal.valueOf(2 * Math.PI))
                .setScale(6, RoundingMode.HALF_UP);
    }

    // Объем круга 0
    public BigDecimal volume() {
        return BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return String.format("Circle{id=%d, radius=%s}", getId(), radius);
    }

    // Вспомогательный метод для конвертации Fraction в BigDecimal
    private BigDecimal fractionToBigDecimal(Fraction fraction) {
        return BigDecimal.valueOf(fraction.getNumerator())
                .divide(BigDecimal.valueOf(fraction.getDenominator()), 10, RoundingMode.HALF_UP);
    }
}
