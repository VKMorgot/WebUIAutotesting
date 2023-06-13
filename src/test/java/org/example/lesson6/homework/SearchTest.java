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

    @Test
    public void searchTest() throws InterruptedException {

        // сохраняем оригинальное окно
        String originalWindow = getDriver().getWindowHandle();

        // нажимаем на значок поиска
        WebElement searchIcon = getDriver().findElement(By.cssSelector(".s-do-item-search-btn"));
        searchIcon.click();

        // вводим текст в поле поиска
        WebElement searchField = getDriver().findElement(By.cssSelector(".s-inline-search-input"));
        searchField.sendKeys("pesen-net");
        searchIcon.click();

        // ожидаем открытия нового окна
        while (getDriver().getWindowHandles().size() != 2) {
            Thread.sleep(1000);
        }

        // поиск нового окна
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }

        // ожидаем загрузки новой страницы
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By
                                .xpath("//*[@id=\"cse-search-box\"]")));

        // проверяем заголовок страницы
        Assertions.assertTrue(getDriver().getTitle().contains(SEARCH_TITLE), "Неверный заголовок страницы: " + getDriver().getTitle());

    }
}
