package com.karzhou.fraction.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Fraction implements Comparable<Fraction> {
    private int numerator;
    private int denominator;

    public static final Fraction ZERO = new Fraction(0, 1);

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) throw new IllegalArgumentException("Denominator can't be zero");
        this.numerator = numerator;
        this.denominator = denominator;
        reduce();
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    private void reduce() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
        // гарантируем положительный знаменатель
        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public Fraction add(Fraction other) {
        int num = this.numerator * other.denominator + other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public Fraction subtract(Fraction other) {
        int num = this.numerator * other.denominator - other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public Fraction multiply(Fraction other) {
        return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
    }

    public Fraction divide(Fraction other) {
        if (other.numerator == 0) throw new ArithmeticException("Division by zero fraction");
        return new Fraction(this.numerator * other.denominator, this.denominator * other.numerator);
    }

    @Override
    public String toString() {
        if (denominator == 1) return String.valueOf(numerator);
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraction)) return false;
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + numerator;
        result = 31 * result + denominator;
        return result;
    }

    @Override
    public int compareTo(Fraction other) {
        long lhs = (long) this.numerator * other.denominator;
        long rhs = (long) other.numerator * this.denominator;
        return Long.compare(lhs, rhs);
    }

    public BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(numerator).divide(BigDecimal.valueOf(denominator), 20, RoundingMode.HALF_UP);
    }
}
