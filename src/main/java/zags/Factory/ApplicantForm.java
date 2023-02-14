package zags.Factory;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

import java.io.File;
import java.io.IOException;

public class ApplicantForm extends Config {

    File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

    public ApplicantForm(WebDriver driver){
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver,this);
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

    @Step("Applicant form filling")
    public void fillApplicantForm(String surname, String name, String father, String phone, String passport, String adress ) throws IOException {
        getLastName().click();
        getLastName().sendKeys(surname);

        getFirstName().click();
        getFirstName().sendKeys(name);

        getFatherName().click();
        getFatherName().sendKeys(father);

        getPhoneNum().click();
        getPhoneNum().sendKeys(phone);

        getPasspNum().click();
        getPasspNum().sendKeys(passport);

        getAddress().click();
        getAddress().sendKeys(adress);

        Allure.addAttachment("Filled form", FileUtils.openInputStream(screenshotAs));
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
}
