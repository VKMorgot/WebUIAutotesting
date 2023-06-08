package org.example;

import lesson4.homework.TriangleApp;
import lesson4.homework.TriangleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TriangleTest {

    /**
     * Проверка корректности вычисления площади треугольника
     * @param a первая сторона
     * @param b вторая сторона
     * @param c третья сторона
     * @param result результат проверки
     * @throws TriangleException исключение треугольника
     */
    @ParameterizedTest
    @CsvSource({"2, 3, 4, 2.90", "7, 7, 7, 21.22", "9, 5, 5, 9.81"})
    void triangleTest(int a, int b, int c, double result) throws TriangleException {
        TriangleApp triangle = new TriangleApp();
        Assertions.assertEquals(result, triangle.trArea(a, b, c));
    }

    /**
     * Проверка, что сумма двух сторон больше третье стороны
     * @param a первая сторона
     * @param b вторая сторона
     * @param c третья сторона
     */
    @ParameterizedTest
    @CsvSource({"5, 5, 5", "10, 2, 1", "3, 4, 100", "1, 10, 6", " 10, 3, 10"})
    void invalidSidesTest(int a, int b, int c) {
        TriangleApp triangle = new TriangleApp();
        Assertions.assertThrows(TriangleException.class, ()-> triangle.trArea(a, b, c));
    }

    /**
     * Проверка отрицательных сторон треугольника
     * @param a первая сторона
     * @param b вторая сторона
     * @param c третья сторона
     */
    @ParameterizedTest
    @CsvSource({"-2, 3, 5", "2, -3, 5", "2, 3, -5", "0, 0, 0", "2, 0, 5"})
    void negativeSidesTest(int a, int b, int c){
        TriangleApp triangle = new TriangleApp();
        Assertions.assertThrows(TriangleException.class, ()-> triangle.trArea(a, b, c));
    }

}
