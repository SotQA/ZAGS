package zags.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.BasePage;

public class ServiceChoice extends BasePage {

    public ServiceChoice(WebDriver driver) {
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(), 'Регистрация брака')]")
    private WebElement marriageReg;

    @FindBy(xpath = "//div/button[contains(text(), 'Регистрация рождения')]")
    private WebElement birthReg;

    @FindBy(xpath = "//div/button[contains(text(), 'Регистрация смерти')]")
    private WebElement deathReg;

    public WebElement getMarriageReg() {
        return marriageReg;
    }

    public WebElement getBirthReg() {
        return birthReg;
    }

    public WebElement getDeathReg() {
        return deathReg;
    }
}
