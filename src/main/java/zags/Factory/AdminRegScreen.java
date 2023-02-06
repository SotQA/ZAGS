package zags.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

public class AdminRegScreen extends Config {
    public AdminRegScreen(WebDriver driver){
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//input)[1]")
    public WebElement lastName;

    @FindBy(xpath = "(//input)[2]")
    public WebElement firstName;

    @FindBy(xpath = "(//input)[3]")
    public WebElement fathersName;

    @FindBy(xpath = "(//input)[4]")
    public WebElement phoneNum;

    @FindBy(xpath = "(//input)[5]")
    public WebElement passportNumber;

    @FindBy(xpath = "(//input)[6]")
    public WebElement dateOfBirth;

    @FindBy(xpath = "//div/button[contains(text(),'Далее')]")
    public WebElement nextButton;

    public void fillAdminForm(String surname, String name, String father, String phone, String passport, String birthDate){
        lastName.click();
        lastName.sendKeys(surname);

        firstName.click();
        firstName.sendKeys(name);

        fathersName.click();
        fathersName.sendKeys(father);

        phoneNum.click();
        phoneNum.sendKeys(phone);

        passportNumber.click();
        passportNumber.sendKeys(passport);

        dateOfBirth.click();
        dateOfBirth.sendKeys(birthDate);
    }
}