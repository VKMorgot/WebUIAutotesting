package org.example.lesson6.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Common {

    private final WebDriver driver;

    @FindBy(css = ".mainpage__title")
    private WebElement mainPage;

    protected WebElement getMainPage() {
        return this.mainPage;
    }

    public Common(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver() {
        return this.driver;
    }

    /**
     * Ожидаем загрузки страницы после перехода по ссылке с навигационного бара
     */
    protected void waitPageLoaded() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(getMainPage()));
    }

    /**
     * Ожидание открытия новой вкладки
     * @param originalWindow первоначальная вкладка
     * @throws InterruptedException
     */
    protected void waitNewTabOpened(String originalWindow) throws InterruptedException {
        // ожидаем открытия нового окна
        while (getDriver().getWindowHandles().size() != 2) {
            Thread.sleep(1000);
        }

        // поиск нового окна
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }
}
