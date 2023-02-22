package zags.Factory;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.BasePage;

public class DeathService extends BasePage {

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

    public WebElement getDeathDate() {
        return deathDate;
    }

    public WebElement getPlaceOfDeath() {
        return placeOfDeath;
    }

    public WebElement getEndButton() {
        return endButton;
    }
    @Step("1.Fill death date field.")
    public DeathService fillDeathDateField(String deathDate) {
        getDeathDate().click();
        getDeathDate().sendKeys(deathDate);
        return this;
    }
    @Step("2.Fill place of death field.")
    public DeathService fillPlaceOfDeath(String deathPlace) {
        getPlaceOfDeath().click();
        getPlaceOfDeath().sendKeys(deathPlace);
        return this;
    }
}
