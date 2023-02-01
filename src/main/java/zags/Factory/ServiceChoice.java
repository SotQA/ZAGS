package zags.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

public class ServiceChoice extends Config {

    public ServiceChoice(WebDriver driver){
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[contains(text(), 'Регистрация брака')]")
    public WebElement marriageReg;

    @FindBy(xpath = "//div/button[contains(text(), 'Регистрация рождения')]")
    public WebElement birthReg;

    @FindBy(xpath = "//div/button[contains(text(), 'Регистрация смерти')]")
    public WebElement deathReg;
}
