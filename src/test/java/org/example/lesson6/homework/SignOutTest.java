package org.example.lesson6.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Проверка выхода с сайта
 */
public class SignOutTest extends CommonTest {

    @Test
    public void sighOutTest() throws PresentException {

        // выходим с сайта
        UserIconElement userIcon = new UserIconElement(getDriver());
        userIcon.logOut();

        // проверяем, что выход произошел
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.waitLoginPage();

        Assertions.assertTrue(userIcon.getUserIcons().size() == 0, "На странице присутствует иконка авторизованного пользователя");

    }
}

