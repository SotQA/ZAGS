package zags;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ApplicationsAdminScreen extends Config {
    public WebElement closeButton;
    public WebElement refreshButton;
    public WebElement approveButton;
    public WebElement declineButton;

    public WebElement getCloseButton() {
        return closeButton = driver.findElement(By.xpath("//button[contains(text(), 'Закрыть')]"));
    }

    public WebElement getRefreshButton() {
        return refreshButton = driver.findElement(By.xpath("//button[contains(text(), 'Обновить')]"));
    }

    public WebElement getApproveButton() {
        return approveButton = driver.findElement(By.xpath("//table/tr[last()]/td/div/button[1]"));
    }

    public WebElement getDeclineButton() {
        return declineButton = driver.findElement(By.xpath("//table/tr[last()]/td/div/button[2]"));
    }
}
