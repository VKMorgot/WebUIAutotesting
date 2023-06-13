package org.example.lesson6.homework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends Common {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".s-header-item__link--login")
    private WebElement loginBtn;

    public WebElement getLoginBtn() {
        return loginBtn;
    }

    public void waitLoginPage() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(getLoginBtn()));
    }

}
