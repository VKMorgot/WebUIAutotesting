package org.example.lesson6.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Проверка работы поиска
 */
public class SearchTest extends CommonTest {

    private static final String SEARCH_STRING = "pesen-net";

    @Test
    public void searchTest() throws InterruptedException {

        // сохраняем оригинальное окно
        String originalWindow = getDriver().getWindowHandle();

        // выполняем поиск на странице
        SearchElement searchElement = new SearchElement(getDriver());
        searchElement.toSearch(SEARCH_STRING);

        // ожидаем открытия новой вкладки
        searchElement.waitNewTabOpened(originalWindow);

        // ожидаем загрузки новой страницы с результатами поиска
        SearchPage searchPage = new SearchPage(getDriver());
        searchPage.waitingOfSearchResult();

        // проверяем заголовок страницы
        Assertions.assertEquals(searchPage.getTitle(), searchPage.getSEARCH_TITLE(),
                "Неверный заголовок страницы: " + searchPage.getTitle());

    }
}
