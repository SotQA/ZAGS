package zags.Factory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

public class ServiceInfo extends Config {
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

    public void fillServiceInfo(String registrationDate, String lastName,
                                String spouseLastName, String spouseName,
                                String spouseFatherName, String spouseBirthDate,
                                String spousePassortN)
    {
        getRegDate().click();
        getRegDate().sendKeys(registrationDate);

        getNewLastName().click();
        getNewLastName().sendKeys(lastName);

        getSpouseLastN().click();
        getSpouseLastN().sendKeys(spouseLastName);

        getSpouseFirstN().click();
        getSpouseFirstN().sendKeys(spouseName);

        getSpouseFatherN().click();
        getSpouseFatherN().sendKeys(spouseFatherName);

        js.executeScript("arguments[0].scrollIntoView(true);", getSpousePasspN());

        getSpouseDateOfBirth().click();
        getSpouseDateOfBirth().sendKeys(spouseBirthDate);

        getSpousePasspN().click();
        getSpousePasspN().sendKeys(spousePassortN);

    }

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
}
