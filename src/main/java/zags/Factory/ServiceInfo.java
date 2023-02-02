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
    public WebElement regDate;

    @FindBy(xpath = "//input[@id='TextInputField-14']")
    public WebElement newLastName;

    @FindBy(xpath = "//input[@id='TextInputField-15']")
    public WebElement spouseLastN;

    @FindBy(xpath = "//input[@id='TextInputField-16']")
    public WebElement spouseFirstN;

    @FindBy(xpath = "//input[@id='TextInputField-17']")
    public WebElement spouseFatherN;

    @FindBy(xpath = "//input[@id='TextInputField-18']")
    public WebElement spouseDateOfBirth;

    @FindBy(xpath = "//input[@id='TextInputField-19']")
    public WebElement spousePasspN;

    @FindBy(xpath = "//button[contains(text(), 'Завершить')]")
    public WebElement endButton;

    public void fillServiceInfo(String registrationDate, String lastName,
                                String spouseLastName, String spouseName,
                                String spouseFatherName, String spouseBirthDate,
                                String spousePassortN)
    {
        regDate.click();
        regDate.sendKeys(registrationDate);

        newLastName.click();
        newLastName.sendKeys(lastName);

        spouseLastN.click();
        spouseLastN.sendKeys(spouseLastName);

        spouseFirstN.click();
        spouseFirstN.sendKeys(spouseName);

        spouseFatherN.click();
        spouseFatherN.sendKeys(spouseFatherName);

        js.executeScript("arguments[0].scrollIntoView(true);", spousePasspN);

        spouseDateOfBirth.click();
        spouseDateOfBirth.sendKeys(spouseBirthDate);

        spousePasspN.click();
        spousePasspN.sendKeys(spousePassortN);

    }
}
