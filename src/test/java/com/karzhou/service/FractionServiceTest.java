package com.karzhou.service;


import com.karzhou.fraction.entity.Fraction;
import com.karzhou.fraction.repository.FractionRepository;
import com.karzhou.fraction.service.FractionService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FractionServiceTest {

    private final FractionService service = new FractionService();

    // Простой mock-репозиторий на базе списка
    static class MockFractionRepository extends FractionRepository {
        private final List<Fraction> list;

        public MockFractionRepository(List<Fraction> list) {
            this.list = list;
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public Fraction get(int index) {
            return list.get(index);
        }

        @Override
        public void set(int index, Fraction fraction) {
            list.set(index, fraction);
        }
    }

    @Test
    void testModifyEvenIndices() {
        List<Fraction> fractions = new ArrayList<>(List.of(
                new Fraction(1, 2),
                new Fraction(1, 3),
                new Fraction(3, 4),
                new Fraction(1, 4),
                new Fraction(5, 6)
        ));
        MockFractionRepository repository = new MockFractionRepository(fractions);

        service.modifyEvenIndices(repository);

        assertAll("Check fractions after modifyEvenIndices",
                () -> assertEquals(new Fraction(5, 6), repository.get(0)), // 1/2 + 1/3 = 5/6
                () -> assertEquals(new Fraction(1, 3), repository.get(1)), // unchanged
                () -> assertEquals(new Fraction(1, 1), repository.get(2)), // 3/4 + 1/4 = 1
                () -> assertEquals(new Fraction(1, 4), repository.get(3)), // unchanged
                () -> assertEquals(new Fraction(5, 6), repository.get(4))  // unchanged (no next)
        );
    }

    @Test
    void testAddSubtractMultiplyDivide() {
        Fraction a = new Fraction(3, 4);
        Fraction b = new Fraction(1, 2);

        assertAll("Test arithmetic operations",
                () -> assertEquals(new Fraction(5, 4), service.add(a, b)),       // 3/4 + 1/2 = 5/4
                () -> assertEquals(new Fraction(1, 4), service.subtract(a, b)),  // 3/4 - 1/2 = 1/4
                () -> assertEquals(new Fraction(3, 8), service.multiply(a, b)),  // 3/4 * 1/2 = 3/8
                () -> assertEquals(new Fraction(3, 2), service.divide(a, b))     // 3/4 ÷ 1/2 = 3/2
        );
    }

    @Test
    void testFractionsToString() {
        List<Fraction> fractions = List.of(
                new Fraction(1, 2),
                new Fraction(3, 4),
                new Fraction(5, 6)
        );

        String result = service.fractionsToString(fractions);
        assertEquals("1/2 3/4 5/6", result);
    }
}

