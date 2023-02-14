package zags;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class Config {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private static final String URL = ("https://user:senlatest@regoffice.senla.eu/");

    public File getScreenshotAs() {
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return screenshotAs;
    }

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

}
