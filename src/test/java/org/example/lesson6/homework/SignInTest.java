package org.example.lesson6.homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.example.lesson6.homework.CommonTest.getDriver;

/**
 * Проверка авторизации
 * Нет наследования CommonTest, потому что выполняется подробная проверка формы авторизации
 */
public class SignInTest {

    private static final String SITE = "https://www.livejournal.com";
    private static final String ENTER_BUTTON = "ВОЙТИ";
    private static final String ENTER_BUTTON_OneUpCaseLetter = "Войти";
    private static final String LOGIN = "LJGBTest";
    private static final String PASSWORD = "r6A^%J6AZ.WUWck";
    private static final long TIMEOUT = 10L;
    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        SignInTest.driver = driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @Test
    @DisplayName("Авторизация пользователя")
    @Description("Проверяем авторизацию зарегистрированного пользователя")
    public void signInTest() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");
        setDriver(new ChromeDriver(options));

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));

        try {
            // переходим на тестируемый сайт
            driver.get(SITE);

            // ожидаем кнопку "Войти"
            LoginPage loginPage = new LoginPage(getDriver());
            loginPage.waitLoginPage();

            // проверяем название кнопки
            Assertions.assertEquals(loginPage.getLoginBtn().getText(), ENTER_BUTTON);

            // нажимает на кнопку "Войти"
            loginPage.getLoginBtn().click();

            // ожидаем окно с формой авторизации
            AuthForm authForm = new AuthForm(getDriver());
            authForm.waitAuthForm();
            Assertions.assertEquals(authForm.getActionLogin().getText(), ENTER_BUTTON_OneUpCaseLetter);

            // ввод логина и пароля
            authForm.authorizeUser(LOGIN, PASSWORD);

            // проверка, что пользователь авторизовался
            UserIconElement userIcon = new UserIconElement(getDriver());
            userIcon.waitUserIcon();

            // проверяем, что на странице нет кнопки входа
            Assertions.assertEquals(0, loginPage.getLoginBtns().size(),
                    "На странице присутствует кнопка входа");

            // проверяем, что на странице нет кнопки создания аккаунта
            Assertions.assertEquals(0, loginPage.getCreatePost().size(),
                    "На странице присутствует кнопка создания аккаунта");
        } finally {
            driver.quit();
        }

    }
}

