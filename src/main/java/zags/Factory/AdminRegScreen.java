package zags.Factory;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.BasePage;

public class AdminRegScreen extends BasePage {
    public AdminRegScreen(WebDriver driver) {
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//input)[1]")
    private WebElement lastName;

    @FindBy(xpath = "(//input)[2]")
    private WebElement firstName;

    @FindBy(xpath = "(//input)[3]")
    private WebElement fathersName;

    @FindBy(xpath = "(//input)[4]")
    private WebElement phoneNum;

    @FindBy(xpath = "(//input)[5]")
    private WebElement passportNumber;

    @FindBy(xpath = "(//input)[6]")
    private WebElement dateOfBirth;

    @FindBy(xpath = "//div/button[contains(text(),'Далее')]")
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

    public WebElement getPhoneNum() {
        return phoneNum;
    }

    public WebElement getPassportNumber() {
        return passportNumber;
    }

    public WebElement getDateOfBirth() {
        return dateOfBirth;
    }

    public WebElement getNextButton() {
        return nextButton;
    }

    @Step("1.Fill last name field.")
    public AdminRegScreen fillLastName(String lastName) {
        getLastName().click();
        getLastName().sendKeys(lastName);
        return this;
    }

    @Step("2.Fill first name field.")
    public AdminRegScreen fillFirstName(String firstName) {
        getFirstName().click();
        getFirstName().sendKeys(firstName);
        return this;
    }

    @Step("3.Fill father name field.")
    public AdminRegScreen fillFatherName(String fatherName) {
        getFathersName().click();
        getFathersName().sendKeys(fatherName);
        return this;
    }

    @Step("4.Fill phone number field.")
    public AdminRegScreen fillPhoneNumber(String number) {
        getPhoneNum().click();
        getPhoneNum().sendKeys(number);
        return this;
    }

    @Step("5.Fill passport field.")
    public AdminRegScreen fillPassportField(String passport) {
        getPassportNumber().click();
        getPassportNumber().sendKeys(passport);
        return this;
    }

    @Step("6.Fill birth date field.")
    public AdminRegScreen fillBirthDate(String birth) {
        getDateOfBirth().click();
        getDateOfBirth().sendKeys(birth);
        return this;
    }
}
