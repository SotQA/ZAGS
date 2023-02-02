package zags.NonFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import zags.Config;

public class MarriageRegScreen extends Config {
    public WebElement lastName;
    public WebElement firstName;
    public WebElement fathersName;
    public WebElement dateOfBirth;
    public WebElement passportNumber;
    public WebElement sex;
    public WebElement nextButton;

    public WebElement getLastName() {
        return lastName = driver.findElement(By.xpath("//input[@id='TextInputField-7']"));
    }

    public WebElement getFirstName() {
        return firstName = driver.findElement(By.xpath("//input[@id='TextInputField-8']"));
    }

    public WebElement getFathersName() {
        return fathersName = driver.findElement(By.xpath("//input[@id='TextInputField-9']"));
    }

    public WebElement getDateOfBirth() {
        return dateOfBirth = driver.findElement(By.xpath("//input[@id='TextInputField-10']"));
    }

    public WebElement getPassportNumber() {
        return passportNumber = driver.findElement(By.xpath("//input[@id='TextInputField-11']"));
    }

    public WebElement getSex() {
        return sex = driver.findElement(By.xpath("//input[@id='TextInputField-12']"));
    }

    public WebElement getNextButton() {
        return nextButton = driver.findElement(By.xpath("//div/button[contains(text(), 'Далее')]"));
    }
}
