package org.example.lesson3.homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opentest4j.AssertionFailedError;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignInTest {

    private static final String SITE = "https://www.livejournal.com";
    private static final String ENTER_BUTTON = "ВОЙТИ";
    private static final String ENTER_BUTTON_OneUpCaseLetter = "Войти";
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

            // проверяем название кнопки
            WebElement enterButton = driver.findElement(By.cssSelector(".s-header-item__link--login"));
            try {
                assertTrue(enterButton.getText().contains(ENTER_BUTTON));
            } catch (AssertionFailedError error) {
                System.out.println("Ожидаемый текст кнопки входа: " + ENTER_BUTTON);
                System.out.println("Фактический текст кнопки входа: " + enterButton.getText());
            }

            // нажимает на кнопку "Войти"
            enterButton.click();

            // ожидаем окна с формой авторизации
            new WebDriverWait(driver, Duration.ZERO).until(ExpectedConditions.visibilityOfElementLocated(By.id("user")));
            new WebDriverWait(driver, Duration.ZERO).until(ExpectedConditions.visibilityOfElementLocated(By.id("lj_loginwidget_password")));
            WebElement loginButton = driver.findElement(By.name("action:login"));
            try {
                assertTrue(loginButton.getText().contains(ENTER_BUTTON_OneUpCaseLetter));
            } catch (AssertionFailedError error) {
                System.out.println("Ожидаемый текст кнопки логина: " + ENTER_BUTTON_OneUpCaseLetter);
                System.out.println("Фактический текст кнопки логина: " + loginButton.getText());
            }

            // ввод логина и пароля
            WebElement loginField = driver.findElement(By.id("user"));
            loginField.sendKeys(LOGIN);
            WebElement pass_field = driver.findElement(By.id("lj_loginwidget_password"));
            pass_field.sendKeys(PASSWORD);
            WebElement enterButtonAuth = driver.findElement(By.name("action:login"));
            enterButtonAuth.click();

            // проверка, что пользователь авторизовался
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".s-header-item--user")));
            WebElement create_post_button = driver.findElement(By.xpath("//a[@href=\"https://www.livejournal.com/post\"]"));

            List<WebElement> enterButtonCheck = driver.findElements(By.cssSelector(".s-header-item__link--login"));
            if (enterButtonCheck.size() != 0) {
                System.out.println("На странице присутствует кнопка входа");
            }
            List<WebElement> createAccountCheck = driver.findElements(By.xpath("//a[@href=\"https://www.livejournal.com/create\"]"));
            if (createAccountCheck.size() != 0) {
                System.out.println("На странице присутствует кнопка создания аккаунта");
            }
        }
        finally {
            driver.quit();
        }

    }
}

