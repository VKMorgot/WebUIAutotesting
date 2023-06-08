package org.example.lesson4.homework;

/**
 * Исключение, возникающее в случае заданных некорректных сторон треугольника
 */
public class TriangleException extends Exception {

    public TriangleException(String str) {
        super(str);
    }
}
