package zags.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

public class CivillianForm extends Config {

    public CivillianForm(WebDriver driver){
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@id='TextInputField-7']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@id='TextInputField-8']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@id='TextInputField-9']")
    public WebElement fathersName;

    @FindBy(xpath = "//input[@id='TextInputField-10']")
    public WebElement dateOfBirth;

    @FindBy(xpath = "//input[@id='TextInputField-11']")
    public WebElement passportNumber;

    @FindBy(xpath = "//input[@id='TextInputField-12']")
    public WebElement sex;

    @FindBy(xpath = "//div/button[contains(text(), 'Далее')]")
    public WebElement nextButton;

    public void fillCivillForm(String surname, String name, String father, String date, String passport, String gender) {
        lastName.click();
        lastName.sendKeys(surname);

        firstName.click();
        firstName.sendKeys(name);

        fathersName.click();
        fathersName.sendKeys(father);

        dateOfBirth.click();
        dateOfBirth.sendKeys(date);

        passportNumber.click();
        passportNumber.sendKeys(passport);

        sex.click();
        sex.sendKeys(gender);

    }
}
