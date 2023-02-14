package zags.Factory;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

import java.io.IOException;

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

    @Step("Fill the admin fields")
    public void fillAdminForm(String surname, String name, String father, String phone, String passport, String birthDate) throws IOException {
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

        Allure.addAttachment("Admin form filled", FileUtils.openInputStream(getScreenshotAs()));
    }
}
