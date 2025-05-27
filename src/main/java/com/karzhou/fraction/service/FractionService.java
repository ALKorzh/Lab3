package com.karzhou.fraction.service;

import com.karzhou.fraction.entity.Fraction;
import com.karzhou.fraction.repository.FractionRepository;

import java.util.List;

public class FractionService {

    public void modifyEvenIndices(FractionRepository repository) {
        int size = repository.size();
        for (int i = 0; i < size - 1; i += 2) {
            Fraction current = repository.get(i);
            Fraction next = repository.get(i + 1);
            Fraction result = current.add(next);
            repository.set(i, result);
        }
    }

    public Fraction add(Fraction a, Fraction b) {
        return a.add(b);
    }

    public Fraction subtract(Fraction a, Fraction b) {
        return a.subtract(b);
    }

    public Fraction multiply(Fraction a, Fraction b) {
        return a.multiply(b);
    }

    public Fraction divide(Fraction a, Fraction b) {
        return a.divide(b);
    }

    public String fractionsToString(List<Fraction> fractions) {
        StringBuilder sb = new StringBuilder();
        for (Fraction f : fractions) {
            sb.append(f.toString()).append(" ");
        }
        return sb.toString().trim();
    }
}
