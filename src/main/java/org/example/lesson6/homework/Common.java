package org.example.lesson6.homework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Common {

    private final WebDriver driver;

    public Common(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver() {
        return this.driver;
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
