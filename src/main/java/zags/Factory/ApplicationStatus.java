package zags.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.BasePage;

public class ApplicationStatus extends BasePage {

    public ApplicationStatus(WebDriver driver) {
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div/span[contains(text(), 'Спасибо за обращение!')]")
    private WebElement thanksForApplication;

    @FindBy(xpath = "//div/span[contains(text(), 'Ваша заявка отправлена на рассмотрение.')]")
    private WebElement applicationStatus;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    private WebElement closePage;

    @FindBy(xpath = "//button[contains(text(), 'Создать новую заявку')]")
    private WebElement createNewApplication;

    @FindBy(xpath = "//button[contains(text(), 'Обновить')]")
    private WebElement refreshPage;

    public WebElement getThanksForApplication() {
        return thanksForApplication;
    }

    public WebElement getApplicationStatus() {
        return applicationStatus;
    }

    public WebElement getClosePage() {
        return closePage;
    }

    public WebElement getCreateNewApplication() {
        return createNewApplication;
    }

    public WebElement getRefreshPage() {
        return refreshPage;
    }
}
