package org.example.lesson6.homework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchElement extends Common {

    private final String SEARCH_TITLE = "Поиск по ЖЖ";

    public String getSEARCH_TITLE() {
        return SEARCH_TITLE;
    }

    @FindBy(css = ".s-do-item-search-btn")
    private WebElement searchBtn;

    @FindBy(css = ".s-inline-search-input")
    private WebElement searchInput;

    @FindBy(xpath = "//*[@id=\"cse-search-box\"]")
    private WebElement searchBox;

    /**
     * Заголовок страницы с результатами поиска
     * @return заголовок страницы
     */
    public String getTitle() {
        return getDriver().getTitle();
    }

    public SearchElement(WebDriver driver) {
        super(driver);
    }

    /**
     * Поиск текста
     * @param searchString поисковой запрос
     */
    public void toSearch(String searchString) {
        searchBtn.click();
        searchInput.sendKeys(searchString);
        searchBtn.click();
    }

    /**
     * Ожидаем открытие страницы с результатами поиска
     */
    public void waitingOfSearchResult() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(searchBox));
    }
}
