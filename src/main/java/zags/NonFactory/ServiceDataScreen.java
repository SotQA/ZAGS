package zags.NonFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import zags.Config;

public class ServiceDataScreen extends Config {
    public WebElement dateOfReg;
    public WebElement newLastName;
    public WebElement spouseLastName;
    public WebElement spouseFirstName;
    public WebElement spouseFathersName;
    public WebElement spouseBirthDate;
    public WebElement spousePassportN;
    public WebElement nextButton;

    public WebElement getDateOfReg() {
        return dateOfReg = driver.findElement(By.xpath("//input[@id='TextInputField-13']"));
    }

    public WebElement getNewLastName() {
        return newLastName = driver.findElement(By.xpath("//input[@id='TextInputField-14']"));
    }

    public WebElement getSpouseLastName() {
        return spouseLastName = driver.findElement(By.xpath("//input[@id='TextInputField-15']"));
    }

    public WebElement getSpouseFirstName() {
        return spouseFirstName = driver.findElement(By.xpath("//input[@id='TextInputField-16']"));
    }

    public WebElement getSpouseFathersName() {
        return spouseFathersName = driver.findElement(By.xpath("//input[@id='TextInputField-17']"));
    }

    public WebElement getSpouseBirthDate() {
        return spouseBirthDate = driver.findElement(By.xpath("//input[@id='TextInputField-18']"));
    }

    public WebElement getSpousePassportN() {
        return spousePassportN = driver.findElement(By.xpath("//input[@id='TextInputField-19']"));
    }

    public WebElement getNextButton() {
        return nextButton = driver.findElement(By.xpath("//button[contains(text(), 'Завершить')]"));
    }
}
