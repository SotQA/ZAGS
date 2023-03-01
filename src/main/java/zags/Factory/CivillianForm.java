package zags.Factory;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.BasePage;

public class CivillianForm extends BasePage {

    public CivillianForm(WebDriver driver) {
        BasePage.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
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

    @Step("1.Fill last name field")
    public CivillianForm fillLastName(String lastName) {
        getLastName().click();
        getLastName().sendKeys(lastName);
        return this;
    }

    @Step("2.Fill first name field.")
    public CivillianForm fillFirstName(String firstName) {
        getFirstName().click();
        getFirstName().sendKeys(firstName);
        return this;
    }

    @Step("3.Fill father name field.")
    public CivillianForm fillFatherNameField(String fatherName) {
        getFathersName().click();
        getFathersName().sendKeys(fatherName);
        return this;
    }

    @Step("4.Fill birth date field.")
    public CivillianForm fillBirthDateField(String birthDate) {
        getDateOfBirth().click();
        getDateOfBirth().sendKeys(birthDate);
        return this;
    }

    @Step("5.Fill passport field.")
    public CivillianForm fillPassportField(String passportNumber) {
        getPassportNumber().click();
        getPassportNumber().sendKeys(passportNumber);
        return this;
    }

    @Step("6.Fill gender field.")
    public void fillGenderField(String gender) {
        getSex().click();
        getSex().sendKeys(gender);
    }
}
