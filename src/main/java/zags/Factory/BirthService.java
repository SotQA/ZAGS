package zags.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

public class BirthService extends Config {

    public BirthService(WebDriver driver) {
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='TextInputField-13']")
    private WebElement placeOfBirth;

    @FindBy(xpath = "//input[@id='TextInputField-14']")
    private WebElement mother;

    @FindBy(xpath = "//input[@id='TextInputField-15']")
    private WebElement father;

    @FindBy(xpath = "//button[contains(text(), 'Завершить')]")
    private WebElement endButton;

    public void fillBirthForm(String birthPlace, String motherInfo, String fatherInfo) {
        getPlaceOfBirth().click();
        getPlaceOfBirth().sendKeys(birthPlace);
        getMother().click();
        getMother().sendKeys(motherInfo);
        getFather().click();
        getFather().sendKeys(fatherInfo);
    }

    public WebElement getPlaceOfBirth() {
        return placeOfBirth;
    }

    public WebElement getMother() {
        return mother;
    }

    public WebElement getFather() {
        return father;
    }

    public WebElement getEndButton() {
        return endButton;
    }
}
