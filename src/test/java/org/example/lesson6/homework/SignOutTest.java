package org.example.lesson6.homework;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
            WebElement userIcon = getDriver().findElement(By.cssSelector(".s-header-item__link--user"));
            new Actions(getDriver())
                    .moveToElement(userIcon)
                    .pause(Duration.ofSeconds(3))
                    .click(getDriver().findElement(By.cssSelector(".s-header-sub-list-item__link--logout")))
                    .build()
                    .perform();

            // проверяем, что выход произошел
            new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                    .until(ExpectedConditions
                            .visibilityOfElementLocated(By
                                    .cssSelector(".s-header-item__link--login")));

            List<WebElement> userIconList = getDriver().findElements(By.cssSelector(".s-header-item--user"));
            if (userIconList.size() != 0) {
                throw new PresentException("На странице присутствует иконка авторизованного пользователя");
            }

    }
}

