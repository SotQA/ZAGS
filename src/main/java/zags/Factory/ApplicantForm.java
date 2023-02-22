package zags.Factory;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.BasePage;

public class ApplicantForm extends BasePage {

    public ApplicantForm(WebDriver driver) {
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div/input[@placeholder='Введите фамилию (минимум 2 символа)']")
    private WebElement lastName;

    @FindBy(xpath = "//div/input[@placeholder='Введите имя (минимум 2 символа)']")
    private WebElement firstName;

    @FindBy(xpath = "//div/input[@placeholder='Введите отчество (минимум 5 символов)']")
    private WebElement fatherName;

    @FindBy(xpath = "//div/input[@placeholder='Введите номер телефона (не более 13 символов)']")
    private WebElement phoneNum;

    @FindBy(xpath = "//div/input[@placeholder='Введите номер паспорта (не более 9 символов)']")
    private WebElement passpNum;

    @FindBy(xpath = "//div/input[@placeholder='Введите адрес прописки']")
    private WebElement address;

    @FindBy(xpath = "//div/button[contains(text(), 'Далее')]")
    private WebElement nextButton;

    @FindBy(xpath = "//b")
    private WebElement accountTitle;

    @FindBy(xpath = "//span[contains(text(), 'Вы выбрали услугу: ')]")
    private WebElement serviceTitle;

    public WebElement getPageTitle() {
        return serviceTitle;
    }

    public WebElement getServiceTitle() {
        return serviceTitle;
    }

    public WebElement getLastName() {
        return lastName;
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public WebElement getFatherName() {
        return fatherName;
    }

    public WebElement getPhoneNum() {
        return phoneNum;
    }

    public WebElement getPasspNum() {
        return passpNum;
    }

    public WebElement getAddress() {
        return address;
    }

    public WebElement getNextButton() {
        return nextButton;
    }

    @Step("1.Fill last name field")
    public ApplicantForm fillLastName(String lastName) {
        getLastName().click();
        getLastName().sendKeys(lastName);
        return this;
    }

    @Step("2.Fill first name field")
    public ApplicantForm fillFirstName(String firstName) {
        getFirstName().click();
        getFirstName().sendKeys(firstName);
        return this;
    }

    @Step("3.Fill father name field")
    public ApplicantForm fillFatherName(String fatherName) {
        getFatherName().click();
        getFatherName().sendKeys(fatherName);
        return this;
    }

    @Step("4.Fill phone number field")
    public ApplicantForm fillPhoneNumber(String number) {
        getPhoneNum().click();
        getPhoneNum().sendKeys(number);
        return this;
    }

    @Step("5.Fill passport field")
    public ApplicantForm fillPassportField(String passport) {
        getPasspNum().click();
        getPasspNum().sendKeys(passport);
        return this;
    }

    @Step("6.Fill address field")
    public void fillAddressField(String address) {
        getAddress().click();
        getAddress().sendKeys(address);
    }
}
