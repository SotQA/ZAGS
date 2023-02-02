package zags.NonFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import zags.Config;

public class AdminRegScreen extends Config {
    public WebElement lastName;
    public WebElement firstName;
    public WebElement fathersName;
    public WebElement phoneNumber;
    public WebElement passportN;
    public WebElement dateOfBirth;
    public WebElement nextButton;

    public WebElement getLastName() {
        return lastName = driver.findElement(By.xpath("(//input)[1]"));
    }

    public WebElement getFirstName() {
        return firstName = driver.findElement(By.xpath("(//input)[2]"));
    }

    public WebElement getFathersName() {
        return fathersName = driver.findElement(By.xpath("(//input)[3]"));
    }

    public WebElement getPhoneNumber() {
        return phoneNumber = driver.findElement(By.xpath("(//input)[4]"));
    }

    public WebElement getPassportN() {
        return passportN = driver.findElement(By.xpath("(//input)[5]"));
    }

    public WebElement getDateOfBirth() {
        return dateOfBirth = driver.findElement(By.xpath("(//input)[6]"));
    }

    public WebElement getNextButton() {
        return nextButton = driver.findElement(By.xpath("//div/button[contains(text(),'Далее')]"));
    }
}
