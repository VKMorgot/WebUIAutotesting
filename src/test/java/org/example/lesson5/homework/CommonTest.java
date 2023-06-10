package org.example.lesson5.homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class CommonTest {

    private static final String SITE = "https://www.livejournal.com";
    private static final String LOGIN = "LJGBTest";
    private static final String PASSWORD = "r6A^%J6AZ.WUWck";
    private static final long TIMEOUT = 10L;
    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        CommonTest.driver = driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Инициализация chrome webdriver
     */
    @BeforeAll
    static void init() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");
        setDriver(new ChromeDriver(options));

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
    }

    /**
     * Переходим на SITE перед каждым тестом и авторизуемся на сайте
     */
    @BeforeEach
    void goTo(){
        Assertions.assertDoesNotThrow(()-> getDriver().navigate().to(SITE), "Страница не доступна");

        // ожидаем кнопку "Войти"
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By
                                .cssSelector(".s-header-item__link--login")));

        // нажимает на кнопку "Войти"
        getDriver().findElement(By.cssSelector(".s-header-item__link--login")).click();

        // ожидаем окна с формой авторизации
        new WebDriverWait(getDriver(), Duration.ZERO).until(ExpectedConditions.visibilityOfElementLocated(By.name("action:login")));

        // ввод логина и пароля
        WebElement loginField = getDriver().findElement(By.id("user"));
        loginField.sendKeys(LOGIN);
        WebElement passField = getDriver().findElement(By.id("lj_loginwidget_password"));
        passField.sendKeys(PASSWORD);
        WebElement enterButton = getDriver().findElement(By.name("action:login"));
        enterButton.click();

        // ожидаем, что пользователь авторизовался
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".s-header-item--user")));
    }

    /**
     * Закрываем все страницы браузера после каждого теста
     */
    @AfterEach
    void close() {
        getDriver().quit();
    }
}
