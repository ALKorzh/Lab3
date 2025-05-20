package com.karzhou.fraction.repository;

import com.karzhou.fraction.entity.Fraction;

import java.util.ArrayList;
import java.util.List;

public class FractionRepository {
    private final List<Fraction> fractions = new ArrayList<>();

    public void addFraction(Fraction fraction) {
        fractions.add(fraction);
    }

    public List<Fraction> getAll() {
        return new ArrayList<>(fractions);
    }

    public Fraction get(int index) {
        return fractions.get(index);
    }

    public void set(int index, Fraction fraction) {
        fractions.set(index, fraction);
    }

    public int size() {
        return fractions.size();
    }
}

