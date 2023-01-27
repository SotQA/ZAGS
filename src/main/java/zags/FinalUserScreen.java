package zags;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FinalUserScreen extends Config{
    private WebElement reload;
    private WebElement createNewApplication;
    private WebElement closeButton;

    public void refreshing(){
        reload = driver.findElement(By.xpath("//button[contains(text(), 'Обновить')]"));
        reload.click();
    }

    public void creatingNewApplication(){
        createNewApplication = driver.findElement(By.xpath("//button[contains(text(), 'Создать новую заявку')]"));
        createNewApplication.click();
    }

    public void close(){
        closeButton = driver.findElement(By.xpath("//button[contains(text(), 'Закрыть')]"));
        closeButton.click();
    }
}
