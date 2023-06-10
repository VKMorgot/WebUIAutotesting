package org.example.lesson5.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Проверка перехода на вкладку "Новые лица"
 */
public class NewFacesTest extends CommonTest {

    private static final String NEW_FACES = "НОВЫЕ ЛИЦА";
    private static final String NEW_FACES_TITLE = "Новые лица — ЖЖ";

    @Test
    public void newFacesSection() {

        // переходим на раздел "Новые лица"
        WebElement newFaces = getDriver().findElement(By.linkText(NEW_FACES));
        newFaces.click();

        // проверяем, что переход произошел
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mainpage__title")));
        Assertions.assertTrue(getDriver().getTitle().contains(NEW_FACES_TITLE), "Неверный заголовок страницы: " + getDriver().getTitle());

    }
}

