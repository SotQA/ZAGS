package zags.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

public class AdminRegScreen extends Config {
    public AdminRegScreen(WebDriver driver) {
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
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

    public void fillAdminForm(String surname, String name, String father, String phone, String passport, String birthDate) {
        getLastName().click();
        getLastName().sendKeys(surname);

        getFirstName().click();
        getFirstName().sendKeys(name);

        getFathersName().click();
        getFathersName().sendKeys(father);

        getPhoneNum().click();
        getPhoneNum().sendKeys(phone);

        getPassportNumber().click();
        getPassportNumber().sendKeys(passport);

        getDateOfBirth().click();
        getDateOfBirth().sendKeys(birthDate);
    }
}
