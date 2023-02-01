package zags.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

public class ApplicationStatus extends Config {

    public ApplicationStatus(WebDriver driver) {
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div/span[contains(text(), 'Спасибо за обращение!')]")
    public static WebElement thanksForApplication;

    @FindBy(xpath = "//div/span[contains(text(), 'Ваша заявка отправлена на рассмотрение.')]")
    public static WebElement applicationStatus;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    public static WebElement closePage;

    @FindBy(xpath = "//button[contains(text(), 'Создать новую заявку')]")
    public static WebElement createNewApplication;

    @FindBy(xpath = "//button[contains(text(), 'Обновить')]")
    public static WebElement refreshPage;

}
