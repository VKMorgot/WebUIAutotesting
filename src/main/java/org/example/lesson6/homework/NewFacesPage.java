package org.example.lesson6.homework;

import org.openqa.selenium.WebDriver;

/**
 * Страница "Новые лица"
 */
public class NewFacesPage extends Common {

    private final String NEW_FACES_TITLE = "Новые лица — ЖЖ";

    public String getNEW_FACES_TITLE() {
        return NEW_FACES_TITLE;
    }

    public NewFacesPage(WebDriver driver) {
        super(driver);
    }

}
