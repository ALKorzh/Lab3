package com.karzhou.fraction.util;

import com.karzhou.fraction.entity.Fraction;
import com.karzhou.fraction.factory.FractionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FractionFileReader {

    private final FractionFactory factory = new FractionFactory();

    /**
     * Читает дроби из файла resources/fractions.txt
     */
    public ArrayList<Fraction> readFractionsFromResources(String resourcePath) throws IOException {
        ArrayList<Fraction> fractions = new ArrayList<>();

        // Получаем InputStream из classpath
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Разделяем по пробелам и запятым
                    String[] parts = line.split("[,\\s]+");
                    for (String part : parts) {
                        if (!part.trim().isEmpty()) {
                            Fraction fraction = factory.createFraction(part.trim());
                            fractions.add(fraction);
                        }
                    }
                }
            }
        }

        return fractions;
    }
}

