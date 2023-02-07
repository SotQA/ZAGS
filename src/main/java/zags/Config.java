package zags;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Config {
    public static WebDriver driver = new ChromeDriver();
    public WebDriverWait wait;

    public static WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void clickButton(WebElement button) {
        button.click();
    }
}
