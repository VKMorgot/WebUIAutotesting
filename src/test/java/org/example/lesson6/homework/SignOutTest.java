package org.example.lesson6.homework;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Проверка выхода с сайта
 */
public class SignOutTest extends CommonTest {

    @Test
    @DisplayName("Выход с сайта")
    @Description("Проверяем корректность выхода с сайта")
    public void sighOutTest() {

        // выходим с сайта
        UserIconElement userIcon = new UserIconElement(getDriver());
        userIcon.logOut();

        // проверяем, что выход произошел
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.waitLoginPage();

        Assertions.assertEquals(0, userIcon.getUserIcons().size(),
                "На странице присутствует иконка авторизованного пользователя");
    }
}

