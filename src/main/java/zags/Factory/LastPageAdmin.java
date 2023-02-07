package zags.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zags.Config;

public class LastPageAdmin extends Config {

    public LastPageAdmin(WebDriver driver) {
        this.driver = driver;
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table/tr[last()]/td/div/button[1]")
    private WebElement approve;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    private WebElement closeButton;

    @FindBy(xpath = "//tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")
    private WebElement statusLastElement;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    private WebElement refreshButton;

    @FindBy(xpath = "//button[contains(text(), 'Закрыть')]")
    private WebElement declineButton;

    @FindBy(xpath = "//table/tr[last()]")
    private WebElement lastElement;

    @FindBy(xpath = "//tr[last()]/td[@class='MuiTableCell-root' and contains(text(), 'Получение свидетельства о браке')]")
    private WebElement marriageProof;

    @FindBy(xpath = "//tr[last()]/td[@class='MuiTableCell-root' and contains(text(), 'Получение свидетельства о рождении')]")
    private WebElement birthProof;

    @FindBy(xpath = "//tr[last()]/td[@class='MuiTableCell-root' and contains(text(), 'Получение свидетельства о смерти')]")
    private WebElement deathProof;

    public WebElement getApprove() {
        return approve;
    }

    public WebElement getCloseButton() {
        return closeButton;
    }

    public WebElement getStatusLastElement() {
        return statusLastElement;
    }

    public WebElement getRefreshButton() {
        return refreshButton;
    }

    public WebElement getDeclineButton() {
        return declineButton;
    }

    public WebElement getLastElement() {
        return lastElement;
    }

    public WebElement getMarriageProof() {
        return marriageProof;
    }

    public WebElement getBirthProof() {
        return birthProof;
    }

    public WebElement getDeathProof() {
        return deathProof;
    }
}
