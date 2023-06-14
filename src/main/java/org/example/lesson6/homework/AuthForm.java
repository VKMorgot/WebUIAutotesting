package org.example.lesson6.homework;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthForm extends Common{
    public AuthForm(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "user")
    private WebElement user;

    @FindBy(id = "lj_loginwidget_password")
    private WebElement password;

    @FindBy(name = "action:login")
    private WebElement actionLogin;

    public WebElement getActionLogin() {
        return actionLogin;
    }

    public void waitAuthForm() {
        new WebDriverWait(getDriver(), Duration.ZERO)
                .until(ExpectedConditions.visibilityOf(getActionLogin()));
    }

    public void authorizeUser(String login, String password) {

        Actions authorizeUser = new Actions(getDriver());
        authorizeUser
                .sendKeys(this.user, login)
                .sendKeys(this.password, password)
                .click(this.actionLogin)
                .build()
                .perform();
    }

}
