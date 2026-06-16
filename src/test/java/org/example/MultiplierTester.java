package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class MultiplierTester {

    Multiplier multiplier = new Multiplier();

    @Tag("fast")
    @DisplayName("Простий тест")
    @Test
    void testMultiply() {
        assertEquals(6, multiplier.multiply(2, 3));
    }


    @Tag("fast")
    @DisplayName("Параметризований тест з 1 параметром(параметри статичні)")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void oneParameterTest(int number) {

        assertEquals(
                number,
                multiplier.multiply(number, 1)
        );
    }

    @Tag("slow")
    @DisplayName("Параметризований тест з набором параметрів(параметри статичні)")
    @ParameterizedTest
    @CsvSource({
            "7,2,14",
            "6,7,42",
            "10,3,30"
    })
    void multipleParametersTest(
            int a,
            int b,
            int expected
    ) {

        assertEquals(
                expected,
                multiplier.multiply(a, b)
        );
    }

    @Tag("slow")
    @DisplayName("Тест динамічний (@TestFactory)")
    @TestFactory
    List<DynamicTest> dynamicTests() {

        return List.of(

                DynamicTest.dynamicTest(
                        "5 * 5 = 25",
                        () -> assertEquals(
                                25,
                                multiplier.multiply(5, 5)
                        )
                ),

                DynamicTest.dynamicTest(
                        "9 * 9 = 81",
                        () -> assertEquals(
                                81,
                                multiplier.multiply(9, 9)
                        )
                )
        );
    }

    @Tag("fast")
    @DisplayName("Тест з Assumptions")
    @Test
    void assumptionTest() {

        assumeTrue(multiplier.multiply(0, 5) == 0);

        assertEquals(25, multiplier.multiply(5, 5));
    }
}