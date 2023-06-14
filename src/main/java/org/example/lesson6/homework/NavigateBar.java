package org.example.lesson6.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Навигационная панель
 */
public class NavigateBar extends Common {

    private final String NEW_FACES = "НОВЫЕ ЛИЦА";

    @FindBy(linkText = NEW_FACES)
    private WebElement linkNewFaces;

    public NavigateBar(WebDriver driver) {
        super(driver);
    }

    public void navigateToNewFaces() {
        linkNewFaces.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mainpage__title")));
    }
}
