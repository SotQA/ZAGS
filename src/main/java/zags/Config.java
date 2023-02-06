package zags;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Config {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    public static WebDriver driver = new ChromeDriver();

    public void clickButton(WebElement button) {
        button.click();
    }

}
