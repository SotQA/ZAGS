package zags;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegistrationScreen extends Config {
    private WebElement marriageRegistration = driver.findElement(By.xpath("//button[contains(text(), 'Регистрация брака')]"));
    private WebElement birthRegistration = driver.findElement(By.xpath("//div/button[contains(text(), 'Регистрация рождения')]"));
    private WebElement deathRegistration = driver.findElement(By.xpath("//div/button[contains(text(), 'Регистрация смерти')]"));


    public WebElement getMarriageRegistration() {
        return marriageRegistration;
    }

    public WebElement getBirthRegistration() {
        return birthRegistration;
    }

    public WebElement getDeathRegistration() {
        return deathRegistration;
    }
}
