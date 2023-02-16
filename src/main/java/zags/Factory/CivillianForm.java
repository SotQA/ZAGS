package zags.Factory;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

import java.io.IOException;

public class CivillianForm extends Config {

    public CivillianForm(WebDriver driver){
        Config.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@id='TextInputField-7']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@id='TextInputField-8']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id='TextInputField-9']")
    private WebElement fathersName;

    @FindBy(xpath = "//input[@id='TextInputField-10']")
    private WebElement dateOfBirth;

    @FindBy(xpath = "//input[@id='TextInputField-11']")
    private WebElement passportNumber;

    @FindBy(xpath = "//input[@id='TextInputField-12']")
    private WebElement sex;

    @FindBy(xpath = "//div/button[contains(text(), 'Далее')]")
    private WebElement nextButton;


    @Step("Fill the civill form")
    public void fillCivillForm(String surname, String name, String father, String date, String passport, String gender) throws IOException {
        getLastName().click();
        getLastName().sendKeys(surname);

        getFirstName().click();
        getFirstName().sendKeys(name);

        getFathersName().click();
        getFathersName().sendKeys(father);

        getDateOfBirth().click();
        getDateOfBirth().sendKeys(date);

        getPassportNumber().click();
        getPassportNumber().sendKeys(passport);

        getSex().click();
        getSex().sendKeys(gender);

        Allure.addAttachment("Civilian form filled", FileUtils.openInputStream(getScreenshotAs()));

    }



    public WebElement getLastName() {
        return lastName;
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public WebElement getFathersName() {
        return fathersName;
    }

    public WebElement getDateOfBirth() {
        return dateOfBirth;
    }

    public WebElement getPassportNumber() {
        return passportNumber;
    }

    public WebElement getSex() {
        return sex;
    }

    public WebElement getNextButton() {
        return nextButton;
    }
}
