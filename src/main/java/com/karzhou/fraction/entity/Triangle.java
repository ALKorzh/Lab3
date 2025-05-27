package com.karzhou.fraction.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Triangle extends Figure {
    private Fraction sideA;
    private Fraction sideB;
    private Fraction sideC;

    public enum TriangleType {
        EQUILATERAL, ISOSCELES, SCALENE, RIGHT, INVALID
    }

    public Triangle(Fraction sideA, Fraction sideB, Fraction sideC) {
        if (!isValidTriangle(sideA, sideB, sideC))
            throw new IllegalArgumentException("Invalid triangle sides");
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        notifyChange();
    }

    private boolean isValidTriangle(Fraction a, Fraction b, Fraction c) {
        if (a.compareTo(Fraction.ZERO) <= 0 || b.compareTo(Fraction.ZERO) <= 0 || c.compareTo(Fraction.ZERO) <= 0) {
            return false;
        }
        return a.add(b).compareTo(c) > 0 &&
                a.add(c).compareTo(b) > 0 &&
                b.add(c).compareTo(a) > 0;
    }

    public TriangleType getType() {
        if (!isValidTriangle(sideA, sideB, sideC)) return TriangleType.INVALID;

        boolean isEquilateral = sideA.equals(sideB) && sideB.equals(sideC);
        if (isEquilateral) return TriangleType.EQUILATERAL;

        boolean isIsosceles = sideA.equals(sideB) || sideB.equals(sideC) || sideA.equals(sideC);
        boolean isRight = isRightTriangle();

        if (isRight) return TriangleType.RIGHT;
        if (isIsosceles) return TriangleType.ISOSCELES;

        return TriangleType.SCALENE;
    }

    private boolean isRightTriangle() {
        Fraction[] sides = {sideA, sideB, sideC};
        Arrays.sort(sides, (f1, f2) -> f1.toBigDecimal().compareTo(f2.toBigDecimal()));

        Fraction a = sides[0];
        Fraction b = sides[1];
        Fraction c = sides[2];

        BigDecimal lhs = a.multiply(a).toBigDecimal().add(b.multiply(b).toBigDecimal());
        BigDecimal rhs = c.multiply(c).toBigDecimal();

        BigDecimal diff = lhs.subtract(rhs).abs();
        BigDecimal epsilon = new BigDecimal("0.0000000001");
        return diff.compareTo(epsilon) < 0;
    }

    @Override
    public BigDecimal area() {
        BigDecimal p = perimeter().divide(new BigDecimal(2), 10, RoundingMode.HALF_UP);
        BigDecimal a = sideA.toBigDecimal();
        BigDecimal b = sideB.toBigDecimal();
        BigDecimal c = sideC.toBigDecimal();

        BigDecimal areaSquared = p.multiply(p.subtract(a)).multiply(p.subtract(b)).multiply(p.subtract(c));
        if (areaSquared.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return sqrt(areaSquared, 10);
    }

    @Override
    public BigDecimal perimeter() {
        return sideA.toBigDecimal().add(sideB.toBigDecimal()).add(sideC.toBigDecimal());
    }

    @Override
    public BigDecimal volume() {
        return BigDecimal.ZERO;
    }

    public Fraction getSideA() {
        return sideA;
    }

    public void setSideA(Fraction sideA) {
        if (!isValidTriangle(sideA, this.sideB, this.sideC))
            throw new IllegalArgumentException("Invalid triangle sides");
        this.sideA = sideA;
        notifyChange();
    }

    public Fraction getSideB() {
        return sideB;
    }

    public void setSideB(Fraction sideB) {
        if (!isValidTriangle(this.sideA, sideB, this.sideC))
            throw new IllegalArgumentException("Invalid triangle sides");
        this.sideB = sideB;
        notifyChange();
    }

    public Fraction getSideC() {
        return sideC;
    }

    public void setSideC(Fraction sideC) {
        if (!isValidTriangle(this.sideA, this.sideB, sideC))
            throw new IllegalArgumentException("Invalid triangle sides");
        this.sideC = sideC;
        notifyChange();
    }

    @Override
    public String toString() {
        return String.format("Triangle{id=%d, sides=%s, %s, %s, type=%s}",
                getId(), sideA, sideB, sideC, getType());
    }

    private static BigDecimal sqrt(BigDecimal value, int scale) {
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal x1 = new BigDecimal(Math.sqrt(value.doubleValue()));
        BigDecimal TWO = BigDecimal.valueOf(2);

        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = value.divide(x0, scale, RoundingMode.HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(TWO, scale, RoundingMode.HALF_UP);
        }
        return x1;
    }
}
