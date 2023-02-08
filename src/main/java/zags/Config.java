package zags;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Config {
    protected static WebDriver driver = new ChromeDriver();
    protected static WebDriverWait wait;

    public WebDriverWait getWait() {
        return wait;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void clickButton(WebElement button) {
        button.click();
    }
}
