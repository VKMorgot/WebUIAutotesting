package org.example.lesson6.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Проверка перехода на вкладку "Новые лица"
 */
public class NewFacesTest extends CommonTest {

    @Test
    public void newFacesSection() {

        // переходим на раздел "Новые лица"
        NavigateBar navigateBar = new NavigateBar(getDriver());
        navigateBar.navigateToNewFaces();

        // проверяем, что переход произошел
        navigateBar.waitPageLoaded();

        // проверяем заголовок загруженной страницы
        NewFacesPage newFacesPage = new NewFacesPage(getDriver());
        Assertions.assertTrue(newFacesPage.getTitle().contains(newFacesPage.getNEW_FACES_TITLE()), "Неверный заголовок страницы: " + newFacesPage.getTitle());

    }
}

