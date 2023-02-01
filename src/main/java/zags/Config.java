package zags;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Config {
    public static WebDriver driver = new ChromeDriver();

    public void fillElement(WebElement element, String info) {
        element.click();
        element.sendKeys(info);
    }

    public void clickButton(WebElement button) {
        button.click();
    }


}
