package zags;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FinalUserScreen extends Config {
    public WebElement reload;
    public WebElement createNewApplication;
    public WebElement closeButton;
    public WebElement thanksElement;
    public WebElement applicationStatus;

    public void refreshing() {
        reload = driver.findElement(By.xpath("//button[contains(text(), 'Обновить')]"));
        reload.click();
    }

    public void creatingNewApplication() {
        createNewApplication = driver.findElement(By.xpath("//button[contains(text(), 'Создать новую заявку')]"));
        createNewApplication.click();
    }

    public void close() {
        closeButton = driver.findElement(By.xpath("//button[contains(text(), 'Закрыть')]"));
        closeButton.click();
    }

    public WebElement getThanksElement() {
        return thanksElement = driver.findElement(By.xpath("//div/span[contains(text(), 'Спасибо за обращение!')]"));
    }

    public WebElement getApplicationStatus() {
        return applicationStatus = driver.findElement(By.xpath("//div/span[contains(text(), 'Ваша заявка отправлена на рассмотрение.')]"));
    }
}
