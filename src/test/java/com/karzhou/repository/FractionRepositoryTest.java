package com.karzhou.repository;

import com.karzhou.fraction.entity.Fraction;
import com.karzhou.fraction.repository.FractionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FractionRepositoryTest {

    private FractionRepository repository;

    @BeforeEach
    void setUp() {
        repository = new FractionRepository();
    }

    @Test
    void testAddAndGetAllFractions() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(3, 4);
        repository.addFraction(f1);
        repository.addFraction(f2);

        List<Fraction> allFractions = repository.getAll();

        assertAll(
                () -> assertEquals(2, allFractions.size()),
                () -> assertTrue(allFractions.contains(f1)),
                () -> assertTrue(allFractions.contains(f2))
        );
    }

    @Test
    void testGetFractionByIndex() {
        Fraction f1 = new Fraction(5, 6);
        repository.addFraction(f1);

        Fraction fetched = repository.get(0);

        assertAll(
                () -> assertNotNull(fetched),
                () -> assertEquals(f1, fetched)
        );
    }

    @Test
    void testSetFractionAtIndex() {
        Fraction f1 = new Fraction(2, 3);
        Fraction f2 = new Fraction(4, 5);
        repository.addFraction(f1);

        repository.set(0, f2);
        Fraction updated = repository.get(0);

        assertAll(
                () -> assertEquals(1, repository.size()),
                () -> assertEquals(f2, updated)
        );
    }

    @Test
    void testSize() {
        assertAll(
                () -> assertEquals(0, repository.size()),
                () -> {
                    repository.addFraction(new Fraction(1, 1));
                    assertEquals(1, repository.size());
                },
                () -> {
                    repository.addFraction(new Fraction(2, 3));
                    assertEquals(2, repository.size());
                }
        );
    }

    @Test
    void testGetThrowsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> repository.get(0));
    }

    @Test
    void testSetThrowsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> repository.set(0, new Fraction(1, 1)));
    }
}

