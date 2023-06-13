package org.example.lesson6.homework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Страница с результатами поиска
 */
public class SearchPage extends Common {

    private final String SEARCH_TITLE = "Поиск по ЖЖ";

    @FindBy(xpath = "//*[@id=\"cse-search-box\"]")
    private WebElement searchBox;

    public String getSEARCH_TITLE() {
        return SEARCH_TITLE;
    }

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ожидаем открытие страницы с результатами поиска
     */
    public void waitingOfSearchResult() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(searchBox));
    }
}
