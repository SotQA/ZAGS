package zags.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

public class ApplicantForm extends Config {

    public ApplicantForm(WebDriver driver){
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div/input[@placeholder='Введите фамилию (минимум 2 символа)']")
    public WebElement lastName;

    @FindBy(xpath = "//div/input[@placeholder='Введите имя (минимум 2 символа)']")
    public WebElement firstName;

    @FindBy(xpath = "//div/input[@placeholder='Введите отчество (минимум 5 символов)']")
    public WebElement fatherName;

    @FindBy(xpath = "//div/input[@placeholder='Введите номер телефона (не более 13 символов)']")
    public WebElement phoneNum;

    @FindBy(xpath = "//div/input[@placeholder='Введите номер паспорта (не более 9 символов)']")
    public WebElement passpNum;

    @FindBy(xpath = "//div/input[@placeholder='Введите адрес прописки']")
    public WebElement address;

    @FindBy(xpath = "//div/button[contains(text(), 'Далее')]")
    public WebElement nextButton;

    public void fillApplicantFOrm(String surname, String name, String father, String phone,  String passport, String adress ){
        lastName.click();
        lastName.sendKeys(surname);

        firstName.click();
        firstName.sendKeys(name);

        fatherName.click();
        fatherName.sendKeys(father);

        phoneNum.click();
        phoneNum.sendKeys(phone);

        passpNum.click();
        passpNum.sendKeys(passport);

        address.click();
        address.sendKeys(adress);

    }
}
