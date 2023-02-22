package zags.Factory;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.BasePage;

public class BirthService extends BasePage {

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
    @Step("1.Fill birth place field.")
    public BirthService fillBirthPlaceField(String birthPlace) {
        getPlaceOfBirth().click();
        getPlaceOfBirth().sendKeys(birthPlace);
        return this;
    }
    @Step("2.Fill mother info field.")
    public BirthService fillMotherInfo(String motherInfo) {
        getMother().click();
        getMother().sendKeys(motherInfo);
        return this;
    }
    @Step("3.Fill father info field.")
    public BirthService fillFatherInfo(String fatherInfo) {
        getFather().click();
        getFather().sendKeys(fatherInfo);
        return this;
    }
}
