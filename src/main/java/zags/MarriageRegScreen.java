package zags;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MarriageRegScreen extends Config {
    private WebElement lastName;
    private WebElement firstName;
    private WebElement fathersName;
    private WebElement dateOfBirth;
    private WebElement passportNumber;
    private WebElement sex;

    public WebElement getLastName() {
        return driver.findElement(By.xpath("//div/input[1]"));
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public WebElement getFathersName() {
        return fathersName;
    }

    public WebElement getDateOfBirth() {
        return dateOfBirth;
    }

    public WebElement getPassportNumber() {
        return passportNumber;
    }

    public WebElement getSex() {
        return sex;
    }
}
