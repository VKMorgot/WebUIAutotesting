package org.example.lesson6.homework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchElement extends Common {

    @FindBy(css = ".s-do-item-search-btn")
    private WebElement searchBtn;

    @FindBy(css = ".s-inline-search-input")
    private WebElement searchInput;

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
}
