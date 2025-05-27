package com.karzhou.fraction.util;

import com.karzhou.fraction.entity.Fraction;
import com.karzhou.fraction.exception.FractionFileReadException;
import com.karzhou.fraction.factory.FractionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FractionFileReader {

    private final FractionFactory factory = new FractionFactory();

    public ArrayList<Fraction> readFractionsFromResources(String resourcePath) throws FractionFileReadException {
        ArrayList<Fraction> fractions = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new FractionFileReadException("Resource not found: " + resourcePath);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("[,\\s]+");
                    for (String part : parts) {
                        if (!part.trim().isEmpty()) {
                            Fraction fraction = factory.createFraction(part.trim());
                            fractions.add(fraction);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new FractionFileReadException("Error reading fractions from resource: " + resourcePath, e);
        }

        return fractions;
    }
}
