package org.example.lesson4.homework;

/**
 * Класс для подсчета площади треугольника по трем известным сторонам
 */
public class TriangleApp {

    /**
     * Проверка, что стороны положительные
     * @param a первая сторона
     * @param b вторая сторона
     * @param c третья сторона
     * @return результат проверки
     */
    private boolean checkSides(int a, int b, int c) {
        return a >= 0 && b >=0 && c>= 0;
    }

    /**
     * Проверка, что сумма двух любых сторон больше третьей стороны
     * @param a первая сторона
     * @param b вторая сторона
     * @param c третья сторона
     * @return результат проверки
     */
    private boolean checkTriangle(int a, int b, int c) {
        return (a + b) > c && (a + c) > b && (b + c) > a;
    }

    /**
     * Вычитаем из одного числа другое
     * @param x уменьшаемое
     * @param y вычитаемое
     * @return разность чисел
     */
    private double sub(double x, int y) {
        return x - y;
    }

    /**
     * Определяем полупериметр треугольника
     * @param a первая сторона
     * @param b вторая сторона
     * @param c третья сторона
     * @return полупериметр треугольника
     */
    private double trPerimeter2(int a, int b, int c) {
        return (a + b + c) / 2.0;
    }

    /**
     * Определяем площадь треугольника по трем сторонам
     * @param a первая сторона
     * @param b вторая сторона
     * @param c третья сторона
     * @return площадь треугольника
     */
    public double trArea(int a, int b, int c) throws TriangleException {
        if (!checkSides(a, b, c)) throw new TriangleException("Отрицательная сторона треугольника");
        if (!checkTriangle(a, b, c)) throw new TriangleException("Длины не соответствуют сторонам треугольника");
        double pp = trPerimeter2(a, b, c);
        double result = Math.sqrt(pp*sub(pp,a)*sub(pp,b)*sub(pp,c));
        double scale = Math.pow(10, 2);
        return Math.round(result * scale) / scale;
    }

}
