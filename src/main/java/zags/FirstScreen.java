package zags;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FirstScreen extends Config {

    private WebElement userLogin = driver.findElement(By.xpath("//button[contains(text(),'Войти как пользователь')]"));
    private WebElement adminLogin = driver.findElement(By.xpath("//button[contains(text(),'Войти как администратор')]"));

    public WebElement getUserLogin() {
        return userLogin;
    }

    public WebElement getAdminLogin() {
        return adminLogin;
    }


    public void loggingAsUserClick() {
        userLogin.click();
    }

    public void loggingAsAdminClick() {
        adminLogin.click();
    }
}
