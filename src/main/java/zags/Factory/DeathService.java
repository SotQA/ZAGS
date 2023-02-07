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
    private WebElement deathDate;

    @FindBy(xpath = "//input[@id='TextInputField-14']")
    private WebElement placeOfDeath;

    @FindBy(xpath = "//button[contains(text(), 'Завершить')]")
    private WebElement endButton;

    public void fillDeathForm(String date, String place){
        getDeathDate().click();
        getDeathDate().sendKeys(date);

        getPlaceOfDeath().click();
        getPlaceOfDeath().sendKeys(place);
    }

    public WebElement getDeathDate() {
        return deathDate;
    }

    public WebElement getPlaceOfDeath() {
        return placeOfDeath;
    }

    public WebElement getEndButton() {
        return endButton;
    }
}
