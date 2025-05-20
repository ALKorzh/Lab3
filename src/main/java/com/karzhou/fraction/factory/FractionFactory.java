package com.karzhou.fraction.factory;

import com.karzhou.fraction.entity.Fraction;

public class FractionFactory {

    // Создает Fraction из строки формата "num/den" или "num"
    public Fraction createFraction(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Fraction string is empty");
        }

        str = str.trim();

        if (str.contains("/")) {
            String[] parts = str.split("/");
            if (parts.length != 2) throw new IllegalArgumentException("Invalid fraction format: " + str);
            int numerator = Integer.parseInt(parts[0].trim());
            int denominator = Integer.parseInt(parts[1].trim());
            return new Fraction(numerator, denominator);
        } else {
            // Целое число, знаменатель = 1
            int numerator = Integer.parseInt(str);
            return new Fraction(numerator, 1);
        }
    }
}

