package zags.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

public class LastPageAdmin extends Config {

    public LastPageAdmin(WebDriver driver) {
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table/tr[last()]/td/div/button[1]")
    public WebElement approve;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    public WebElement closeButton;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    public WebElement status;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    public WebElement refreshButton;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    public WebElement declineButton;

    @FindBy(xpath = "//table/tr[last()]")
    public WebElement lastElement;
}
