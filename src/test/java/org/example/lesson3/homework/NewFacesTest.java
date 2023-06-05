package org.example.lesson3.homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewFacesTest {

    private static final String SITE = "https://www.livejournal.com";
    private static final String LOGIN = "LJGBTest";
    private static final String PASSWORD = "r6A^%J6AZ.WUWck";
    private static final String NEW_FACES = "НОВЫЕ ЛИЦА";
    private static final String NEW_FACES_TITLE = "Новые лица — ЖЖ";

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        try {
            // переходим на тестируемый сайт
            driver.get(SITE);

            // ожидаем кнопку "Войти"
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions
                            .visibilityOfElementLocated(By
                                    .cssSelector(".s-header-item__link--login")));

            // нажимает на кнопку "Войти"
            driver.findElement(By.cssSelector(".s-header-item__link--login")).click();

            // ожидаем окна с формой авторизации
            new WebDriverWait(driver, Duration.ZERO).until(ExpectedConditions.visibilityOfElementLocated(By.name("action:login")));

            // ввод логина и пароля
            WebElement loginField = driver.findElement(By.id("user"));
            loginField.sendKeys(LOGIN);
            WebElement passField = driver.findElement(By.id("lj_loginwidget_password"));
            passField.sendKeys(PASSWORD);
            WebElement enterButton = driver.findElement(By.name("action:login"));
            enterButton.click();

            // ожидаем, что пользователь авторизовался
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".s-header-item--user")));

            // переходим на раздел "Новые лица"
            WebElement newFaces = driver.findElement(By.linkText(NEW_FACES));
            newFaces.click();

            // проверяем, что переход произошел
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.titleIs(NEW_FACES_TITLE));

        }
        finally {
            driver.quit();
        }

    }
}

