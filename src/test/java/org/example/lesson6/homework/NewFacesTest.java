package org.example.lesson6.homework;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Проверка перехода на вкладку "Новые лица"
 */
public class NewFacesTest extends CommonTest {

    @Test
    @DisplayName("Раздел 'Новые лица'")
    @Description("Проверяем корректность перехода на раздел")
    public void newFacesSection() {

        // переходим на раздел "Новые лица"
        NavigateBar navigateBar = new NavigateBar(getDriver());
        navigateBar.navigateToNewFaces();

        // проверяем, что переход произошел
        navigateBar.waitPageLoaded();

        // проверяем заголовок загруженной страницы
        NewFacesPage newFacesPage = new NewFacesPage(getDriver());
        Assertions.assertEquals(newFacesPage.getTitle(), newFacesPage.getNEW_FACES_TITLE(),
                "Неверный заголовок страницы: " + newFacesPage.getTitle());

    }
}

