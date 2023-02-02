package zags.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

public class DeathService extends Config {

    public DeathService(WebDriver driver) {
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='TextInputField-13']")
    public WebElement deathDate;

    @FindBy(xpath = "//input[@id='TextInputField-14']")
    public WebElement placeOfDeath;

    @FindBy(xpath = "//button[contains(text(), 'Завершить')]")
    public WebElement endButton;

    public void fillDeathForm(String date, String place){
        deathDate.click();
        deathDate.sendKeys(date);

        placeOfDeath.click();
        placeOfDeath.sendKeys(place);
    }
}
