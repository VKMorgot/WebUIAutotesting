package org.example.lesson6.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Проверка работы поиска
 */
public class SearchTest extends CommonTest {

    private static final String SEARCH_TITLE = "Поиск по ЖЖ";
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
        SearchElement searchElementNew = new SearchElement(getDriver());
        searchElementNew.waitingOfSearchResult();

        // проверяем заголовок страницы
        Assertions.assertTrue(searchElementNew.getSearchTitle().contains(SEARCH_TITLE), "Неверный заголовок страницы: " + searchElementNew.getSearchTitle());

    }
}
