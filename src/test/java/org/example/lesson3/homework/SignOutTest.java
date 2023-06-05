package org.example.lesson3.homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SignOutTest {

    private static final String SITE = "https://www.livejournal.com";
    private static final String LOGIN = "LJGBTest";
    private static final String PASSWORD = "r6A^%J6AZ.WUWck";

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

            // выходим с сайта
            WebElement userIcon = driver.findElement(By.cssSelector(".s-header-item__link--user"));
            new Actions(driver)
                    .moveToElement(userIcon)
                    .perform();
            new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".s-header-sub-list-item__link--logout")));
            WebElement logOutButton = driver.findElement(By.cssSelector(".s-header-sub-list-item__link--logout"));
            logOutButton.click();

            // проверяем, что выход произошел
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions
                            .visibilityOfElementLocated(By
                                    .cssSelector(".s-header-item__link--login")));

            List<WebElement> userIconList = driver.findElements(By.cssSelector(".s-header-item--user"));
            if (userIconList.size() != 0) {
                System.out.println("На странице присутствует иконка авторизованного пользователя");
            }
        }
        finally {
            driver.quit();
        }

    }
}

