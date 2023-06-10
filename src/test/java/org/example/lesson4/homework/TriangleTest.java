package org.example.lesson4.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriangleTest {

    static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    /**
     * Проверка корректности вычисления площади треугольника
     * @param a первая сторона
     * @param b вторая сторона
     * @param c третья сторона
     * @param result результат проверки
     * @throws TriangleException исключение треугольника
     */
    @Disabled //отключено, чтобы не мешать тестам из lesson5.homework
    @ParameterizedTest
    @CsvSource({"2, 3, 4, 2.90", "7, 7, 7, 21.22", "9, 5, 5, 9.81"})
    void triangleTest(int a, int b, int c, double result) throws TriangleException {
        TriangleApp triangle = new TriangleApp();
        logger.info("Проверка корректности вычисления");
        logger.info("Стороны: " + a + ", " + b + ", " + c);
        logger.info("Ожидаемая площадь: " + result);
        logger.debug("Фактический результат: " + triangle.trArea(a, b, c));
        Assertions.assertEquals(result, triangle.trArea(a, b, c));
    }

    /**
     * Проверка, что сумма двух сторон больше третье стороны
     * @param a первая сторона
     * @param b вторая сторона
     * @param c третья сторона
     */
    @Disabled //отключено, чтобы не мешать тестам из lesson5.homework
    @ParameterizedTest
    @CsvSource({"10, 2, 1", "3, 4, 100", "1, 10, 6"})
    void invalidSidesTest(int a, int b, int c) {
        TriangleApp triangle = new TriangleApp();
        logger.info("Проверка, что сторона не может быть больше суммы двух других сторон");
        logger.info("Стороны: " + a + ", " + b + ", " + c);
        logger.debug("a + b = " + (a+b) + ", c = " + c);
        logger.debug("a + c = " + (a+c) + ", b = " + b);
        logger.debug("c + b = " + (c+b) + ", a = " + a);
        Assertions.assertThrows(TriangleException.class, ()-> triangle.trArea(a, b, c));
    }

    /**
     * Проверка отрицательных сторон треугольника
     * @param a первая сторона
     * @param b вторая сторона
     * @param c третья сторона
     */
    @Disabled //отключено, чтобы не мешать тестам из lesson5.homework
    @ParameterizedTest
    @CsvSource({"-2, 3, 5", "2, -3, 5", "2, 3, -5", "0, 0, 0", "2, 0, 5"})
    void negativeSidesTest(int a, int b, int c){
        TriangleApp triangle = new TriangleApp();
        logger.info("Проверка, что не может быть нулевых и отрицательных сторон");
        logger.info("Стороны: " + a + ", " + b + ", " + c);
        Assertions.assertThrows(TriangleException.class, ()-> triangle.trArea(a, b, c));
    }

}
