package zags.Factory;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.BasePage;

public class ServiceInfo extends BasePage {
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public ServiceInfo(WebDriver driver) {
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='TextInputField-13']")
    private WebElement regDate;

    @FindBy(xpath = "//input[@id='TextInputField-14']")
    private WebElement newLastName;

    @FindBy(xpath = "//input[@id='TextInputField-15']")
    private WebElement spouseLastN;

    @FindBy(xpath = "//input[@id='TextInputField-16']")
    private WebElement spouseFirstN;

    @FindBy(xpath = "//input[@id='TextInputField-17']")
    private WebElement spouseFatherN;

    @FindBy(xpath = "//input[@id='TextInputField-18']")
    private WebElement spouseDateOfBirth;

    @FindBy(xpath = "//input[@id='TextInputField-19']")
    private WebElement spousePasspN;

    @FindBy(xpath = "//button[contains(text(), 'Завершить')]")
    private WebElement endButton;

    public WebElement getRegDate() {
        return regDate;
    }

    public WebElement getNewLastName() {
        return newLastName;
    }

    public WebElement getSpouseLastN() {
        return spouseLastN;
    }

    public WebElement getSpouseFirstN() {
        return spouseFirstN;
    }

    public WebElement getSpouseFatherN() {
        return spouseFatherN;
    }

    public WebElement getSpouseDateOfBirth() {
        return spouseDateOfBirth;
    }

    public WebElement getSpousePasspN() {
        return spousePasspN;
    }

    public WebElement getEndButton() {
        return endButton;
    }

    @Step("1.Fill registration date field")
    public ServiceInfo fillRegDateField(String regDate) {
        getRegDate().click();
        getRegDate().sendKeys(regDate);
        return this;
    }

    @Step("2.Fill new last name field.")
    public ServiceInfo fillNewLastNameField(String lastName) {
        getNewLastName().click();
        getNewLastName().sendKeys(lastName);
        return this;
    }

    @Step("3.Fill spouse last name field.")
    public ServiceInfo fillSpouseLastNameField(String spouseLastName) {
        getSpouseLastN().click();
        getSpouseLastN().sendKeys(spouseLastName);
        return this;
    }

    @Step("4.Fill spouse first name field.")
    public ServiceInfo fillSpouseFirstNameField(String spouseName) {
        getSpouseFirstN().click();
        getSpouseFirstN().sendKeys(spouseName);
        return this;
    }

    @Step("5.Fill spouse father name field.")
    public ServiceInfo fillSpouseFatherNameField(String spouseFatherName) {
        getSpouseFatherN().click();
        getSpouseFatherN().sendKeys(spouseFatherName);
        return this;
    }

    @Step("6.Fill spouse birth date field. ")
    public ServiceInfo fillSpouseBirthDate(String birthDate) {
        getSpouseDateOfBirth().click();
        getSpouseDateOfBirth().sendKeys(birthDate);
        return this;
    }

    @Step("7.Fill spouse passport number field.")
    public ServiceInfo fillSpousePassportNumField(String passportNumber) {
        getSpousePasspN().click();
        getSpousePasspN().sendKeys(passportNumber);
        return this;
    }
}
