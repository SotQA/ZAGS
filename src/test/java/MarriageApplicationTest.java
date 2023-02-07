import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import zags.Config;
import zags.Factory.*;

import java.time.Duration;

public class MarriageApplicationTest extends Config {
    @BeforeAll
    public static void testsConfiguration() {
        driver.get("https://user:senlatest@regoffice.senla.eu/");
        driver.manage().window().maximize();
    }

//    @AfterAll
//    public static void closeTest() throws InterruptedException {
//        Thread.sleep(5000);
//        driver.quit();
//    }

    @Test
    public void marriageApplicationTest() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        HomePage homePage = new HomePage(driver);
        homePage.clickButton(homePage.getUserLogin());

        ApplicantForm form = new ApplicantForm(driver);
        form.fillApplicantForm("Uzumaki", "Naruto", "Someone", "123443321", "1234543", "Konoha");
        Assert.assertEquals(form.getLastName().getAttribute("value"), "Uzumaki");
        Assert.assertEquals(form.getFirstName().getAttribute("value"), "Naruto");
        Assert.assertEquals(form.getFatherName().getAttribute("value"), "Someone");
        Assert.assertEquals(form.getPhoneNum().getAttribute("value"), "123443321");
        Assert.assertEquals(form.getPasspNum().getAttribute("value"), "1234543");
        Assert.assertEquals(form.getAddress().getAttribute("value"), "Konoha");
        form.getNextButton().click();

        ServiceChoice choice = new ServiceChoice(driver);
        choice.clickButton(choice.getMarriageReg());

        CivillianForm civilForm = new CivillianForm(driver);
        civilForm.fillCivillForm("Uzumaki", "Naruto", "Hockage", "22061999", "12345432", "Male");
        Assert.assertEquals(civilForm.getLastName().getAttribute("value"), "Uzumaki");
        Assert.assertEquals(civilForm.getFirstName().getAttribute("value"), "Naruto");
        Assert.assertEquals(civilForm.getFathersName().getAttribute("value"), "Hockage");
//        Assert.assertEquals(civilForm.dateOfBirth.getAttribute("value"), "1999-06-22");
        Assert.assertEquals(civilForm.getPassportNumber().getAttribute("value"), "12345432");
        Assert.assertEquals(civilForm.getSex().getAttribute("value"), "Male");
        civilForm.getNextButton().click();

        ServiceInfo service = new ServiceInfo(driver);
        service.fillServiceInfo("22061999", "Uzumaki", "Hjugo", "Hinata", "Hockage", "22061999", "1234432");
//        Assert.assertEquals(service.regDate.getAttribute("value"), "1999-06-22");
        Assert.assertEquals(service.getNewLastName().getAttribute("value"), "Uzumaki");
        Assert.assertEquals(service.getSpouseLastN().getAttribute("value"), "Hjugo");
        Assert.assertEquals(service.getSpouseFirstN().getAttribute("value"), "Hinata");
        Assert.assertEquals(service.getSpouseFatherN().getAttribute("value"), "Hockage");
//        Assert.assertEquals(service.spouseDateOfBirth.getAttribute("value"), "1999-06-22");
        Assert.assertEquals(service.getSpousePasspN().getAttribute("value"), "1234432");
        service.getEndButton().click();

        ApplicationStatus applicationStatus = new ApplicationStatus(driver);
        Assert.assertEquals(applicationStatus.getThanksForApplication().getText(), "Спасибо за обращение!");
        Assert.assertEquals(applicationStatus.getApplicationStatus().getText(), "Ваша заявка отправлена на рассмотрение.");
        applicationStatus.clickButton(applicationStatus.getClosePage());

        //Logging in as an Admin to check out the application registered

        homePage.clickButton(homePage.getAdminLogin());

        AdminRegScreen admin = new AdminRegScreen(driver);
        admin.fillAdminForm("Uzumaki", "Naruto", "1234543", "1234432", "12345543", "22061999");
        Assert.assertEquals(admin.getLastName().getAttribute("value"), "Uzumaki");
        Assert.assertEquals(admin.getFirstName().getAttribute("value"), "Naruto");
        Assert.assertEquals(admin.getFathersName().getAttribute("value"), "1234543");
        Assert.assertEquals(admin.getPhoneNum().getAttribute("value"), "1234432");
        Assert.assertEquals(admin.getPassportNumber().getAttribute("value"), "12345543");
//        Assert.assertEquals(admin.dateOfBirth.getAttribute("value"), "1999-06-22");
        admin.getNextButton().click();

        LastPageAdmin lastPage = new LastPageAdmin(driver);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.getLastElement());
        } catch (TimeoutException exception) {
            System.out.println("The table wasn't loaded in time so the second attempt to load it and to scroll down.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.getLastElement());
        }

        try {
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.getMarriageProof(), "Получение свидетельства о браке"));
            lastPage.getApprove().click();
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.getStatusLastElement(), "Одобрена"));
        } catch (AssertionError error) {
            System.out.println("The button is not correct.");
        }
        Assert.assertEquals(lastPage.getStatusLastElement().getText(), "Одобрена", "The button didn't change its status.");
        Assert.assertEquals(lastPage.getMarriageProof().getText(), "Получение свидетельства о браке");
        lastPage.getCloseButton().click();

        driver.navigate().refresh();
    }
}
