package org.example.lesson6.homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
//        options.addArguments("--headless");
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
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.waitLoginPage();

        // нажимаем на кнопку "Войти"
        loginPage.getLoginBtn().click();

        // ожидаем окна с формой авторизации
        AuthForm authForm = new AuthForm(getDriver());
        authForm.waitAuthForm();

        // ввод логина и пароля
        authForm.authorizeUser(LOGIN, PASSWORD);

        // ожидаем, что пользователь авторизовался
        UserIconElement userIcon = new UserIconElement(getDriver());
        userIcon.waitUserIcon();
    }

    /**
     * Закрываем все страницы браузера после каждого теста
     */
    @AfterEach
    void close() {
        getDriver().quit();
    }
}
