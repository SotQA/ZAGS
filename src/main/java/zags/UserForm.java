package zags;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UserForm extends Config {
    private WebElement firstName = driver.findElement(By.xpath("//div/input[@placeholder='Введите имя (минимум 2 символа)']"));
    private WebElement fathersName = driver.findElement(By.xpath("//div/input[@placeholder='Введите отчество (минимум 5 символов)']"));
    private WebElement phoneNumber = driver.findElement(By.xpath("//div/input[@placeholder='Введите номер телефона (не более 13 символов)']"));
    private WebElement passportNumber = driver.findElement(By.xpath("//div/input[@placeholder='Введите номер паспорта (не более 9 символов)']"));
    private WebElement address = driver.findElement(By.xpath("//div/input[@placeholder='Введите адрес прописки']"));
    private WebElement lastName = driver.findElement(By.xpath("//div/input[@placeholder='Введите фамилию (минимум 2 символа)']"));

    public WebElement getNextButton() {
        return nextButton;
    }

    private WebElement nextButton =  driver.findElement(By.xpath("//div/button[contains(text(), 'Далее')]"));

    public WebElement getLastName() {
        return lastName;
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public WebElement getFathersName() {
        return fathersName;
    }

    public WebElement getPhoneNumber() {
        return phoneNumber;
    }

    public WebElement getPassportNumber() {
        return passportNumber;
    }

    public WebElement getAddress() {
        return address;
    }

    public void fillingTheField(WebElement element, String information) {
        element.click();
        element.sendKeys(information);
    }

    public void clickNextButton(){
        nextButton.click();
    }
}
