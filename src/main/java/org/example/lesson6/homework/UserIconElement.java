package org.example.lesson6.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UserIconElement extends Common {

    @FindBy(css = ".s-header-item__link--user")
    private WebElement userIcon;

    @FindBy(css = ".s-header-item--user")
    private List<WebElement> userIcons;

    @FindBy(css = ".s-header-sub-list-item__link--logout")
    private WebElement logoutBtn;

    public List<WebElement> getUserIcons() {
        return userIcons;
    }

    public WebElement getUserIcon() {
        return userIcon;
    }

    public void logOut() {
        new Actions(getDriver())
                .moveToElement(userIcon)
                .pause(Duration.ofSeconds(3))
                .click(logoutBtn)
                .build()
                .perform();
    }

    public UserIconElement(WebDriver driver) {
        super(driver);
    }

    public void waitUserIcon() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(getUserIcon()));
    }
}
