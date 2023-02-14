package zags;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Config {
    protected static WebDriver driver = new ChromeDriver();
    protected static WebDriverWait wait;
    private static final String URL = ("https://user:senlatest@regoffice.senla.eu/");

    public static String getURL() {
        return URL;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void clickButton(WebElement button) {
        button.click();
    }

    public byte[] screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
