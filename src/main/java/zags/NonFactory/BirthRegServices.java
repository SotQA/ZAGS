package zags.NonFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import zags.Config;

public class BirthRegServices extends Config {
    public WebElement placeOfBirth;
    public WebElement mother;
    public WebElement father;
    public WebElement finishButton;

    public WebElement getPlaceOfBirth() {
        return placeOfBirth = driver.findElement(By.xpath("//input[@id='TextInputField-25']"));
    }

    public WebElement getMother() {
        return mother = driver.findElement(By.xpath("//input[@id='TextInputField-26']"));
    }

    public WebElement getFather() {
        return father = driver.findElement(By.xpath("//input[@id='TextInputField-27']"));
    }

    public WebElement getFinishButton() {
        return finishButton = driver.findElement(By.xpath("//button[contains(text(), 'Завершить')]"));
    }
}
