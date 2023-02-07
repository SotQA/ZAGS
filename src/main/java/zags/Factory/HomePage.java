package zags.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

public class HomePage extends Config {

    public HomePage(WebDriver driver){
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[contains(text(),'Войти как пользователь')]")
    private WebElement userLogin;

    @FindBy(xpath = "//button[contains(text(),'Войти как администратор')]")
    private WebElement adminLogin;

    public WebElement getUserLogin() {
        return userLogin;
    }

    public WebElement getAdminLogin() {
        return adminLogin;
    }
}
