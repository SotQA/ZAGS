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
    public WebElement placeOfBirth;

    @FindBy(xpath = "//input[@id='TextInputField-14']")
    public WebElement mother;

    @FindBy(xpath = "//input[@id='TextInputField-15']")
    public WebElement father;

    @FindBy(xpath = "//button[contains(text(), 'Завершить')]")
    public WebElement endButton;

    public void fillBirthForm(String birthPlace, String motherInfo, String fatherInfo) {
        placeOfBirth.click();
        placeOfBirth.sendKeys(birthPlace);
        mother.click();
        mother.sendKeys(motherInfo);
        father.click();
        father.sendKeys(fatherInfo);
        endButton.click();
    }
}
