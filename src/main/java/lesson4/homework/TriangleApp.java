package lesson4.homework;

public class TriangleApp {

    /**
     * Вычитаем из одного числа другое
     * @param x - уменьшаемое
     * @param y - вычитаемое
     * @return разность чисел
     */
    private int sub(int x, int y) {
        return x - y;
    }

    /**
     * Определяем периметр треугольника
     * @param a - первая сторона
     * @param b - вторая сторона
     * @param c - третья сторона
     * @return периметр треугольника
     */
    private int trPerimeter(int a, int b, int c) {
        return a + b + c;
    }

    /**
     * Определяем площадь треугольника по трем сторонам
     * @param a - первая сторона
     * @param b - вторая сторона
     * @param c - третья сторона
     * @return площадь треугольника
     */
    public double trArea(int a, int b, int c) {
        int p = trPerimeter(a, b, c);
        return Math.sqrt(p*sub(p,a)*sub(p,b)*sub(p,c));
    }

}
