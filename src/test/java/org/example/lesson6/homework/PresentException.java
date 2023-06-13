package org.example.lesson6.homework;

/**
 * Исключение, возникающее в случае присутствия кнопки на странице
 */
public class PresentException extends Exception{

    public PresentException(String str) {
        super(str);
    }
}
