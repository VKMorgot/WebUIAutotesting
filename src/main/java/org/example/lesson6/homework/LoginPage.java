package org.example.lesson6.homework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Страница, на которой можно залогиниться на сайт или создать новый аккаунт
 */
public class LoginPage extends Common {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href=\"https://www.livejournal.com/create\"]")
    private List<WebElement> createPost;

    @FindBy(css = ".s-header-item__link--login")
    private WebElement loginBtn;

    @FindBy(css = ".s-header-item__link--login")
    private List<WebElement> loginBtns;

    public List<WebElement> getLoginBtns() {
        return loginBtns;
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }

    public List<WebElement> getCreatePost() {
        return createPost;
    }

    public void waitLoginPage() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(getLoginBtn()));
    }

}
