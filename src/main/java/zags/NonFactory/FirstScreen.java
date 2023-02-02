package zags.NonFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import zags.Config;

public class FirstScreen extends Config {

    public WebElement userLogin;
    public WebElement adminLogin;

    public WebElement getUserLogin() {
        return userLogin = driver.findElement(By.xpath("//button[contains(text(),'Войти как пользователь')]"));
    }

    public WebElement getAdminLogin() {
        return adminLogin = driver.findElement(By.xpath("//button[contains(text(),'Войти как администратор')]"));
    }
}
