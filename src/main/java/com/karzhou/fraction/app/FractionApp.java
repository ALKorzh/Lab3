package com.karzhou.fraction.app;

import com.karzhou.fraction.entity.Fraction;
import com.karzhou.fraction.util.FractionFileReader;
import com.karzhou.fraction.exception.FractionFileReadException;

import java.util.ArrayList;

public class FractionApp {

    public static void main(String[] args) {
        FractionFileReader reader = new FractionFileReader();
        try {
            ArrayList<Fraction> fractions = reader.readFractionsFromResources("fractions.txt");

            System.out.println("Loaded fractions:");
            for (int i = 0; i < fractions.size(); i++) {
                System.out.printf("[%d]: %s%n", i, fractions.get(i));
            }

            for (int i = 0; i < fractions.size() - 1; i += 2) {
                Fraction current = fractions.get(i);
                Fraction next = fractions.get(i + 1);
                Fraction sum = current.add(next);
                fractions.set(i, sum);
            }

            System.out.println("\nAfter adding next fraction to even indexed fractions:");
            for (int i = 0; i < fractions.size(); i++) {
                System.out.printf("[%d]: %s%n", i, fractions.get(i));
            }

        } catch (FractionFileReadException e) {
            System.err.println("Error reading fractions file: " + e.getMessage());
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.err.println("Invalid fraction data: " + e.getMessage());
        }
    }
}
