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

public class DeathApplicationTest extends Config {

    @BeforeAll
    public static void testsConfiguration() {
        driver.get("https://user:senlatest@regoffice.senla.eu/");
        driver.manage().window().maximize();
    }

    @Test
    public void deathApplicationTest() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        HomePage homePage = new HomePage(driver);
        homePage.getUserLogin().click();

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
        choice.getDeathReg().click();

        CivillianForm civilForm = new CivillianForm(driver);
        civilForm.fillCivillForm("Uzumaki", "Naruto", "Hockage", "22061999", "12345432", "Male");
        Assert.assertEquals(civilForm.getLastName().getAttribute("value"), "Uzumaki");
        Assert.assertEquals(civilForm.getFirstName().getAttribute("value"), "Naruto");
        Assert.assertEquals(civilForm.getFathersName().getAttribute("value"), "Hockage");
//        Assert.assertEquals(civilForm.dateOfBirth.getAttribute("value"), "1999-06-22");
        Assert.assertEquals(civilForm.getPassportNumber().getAttribute("value"), "12345432");
        Assert.assertEquals(civilForm.getSex().getAttribute("value"), "Male");
        civilForm.getNextButton().click();

        DeathService deathService = new DeathService(driver);
        deathService.fillDeathForm("22061999", "Hollywood");
//        Assert.assertEquals(deathService.deathDate.getAttribute("value"), "1999-06-22");
        Assert.assertEquals(deathService.getPlaceOfDeath().getAttribute("value"), "Hollywood");
        deathService.getEndButton().click();

        ApplicationStatus applicationStatus = new ApplicationStatus(driver);
        Assert.assertEquals(applicationStatus.getThanksForApplication().getText(), "Спасибо за обращение!");
        Assert.assertEquals(applicationStatus.getApplicationStatus().getText(), "Ваша заявка отправлена на рассмотрение.");
        applicationStatus.getClosePage().click();


        //Admin checking out the death registration
        homePage.getAdminLogin().click();

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
        } catch (
                TimeoutException exception) {
            System.out.println("The table wasn't loaded in time so the second attempt to load it and to scroll down.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.getLastElement());
        }

        try {
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.getDeathProof(), "Получение свидетельства о смерти"));
            lastPage.getApprove().click();
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.getStatusLastElement(), "Одобрена"));
        } catch (AssertionError error) {
            System.out.println("The button is not correct.");
        }

        Assert.assertEquals(lastPage.getStatusLastElement().getText(), "Одобрена", "The button is incorrect. Must be \"Одобрена\" .");
        Assert.assertEquals(lastPage.getDeathProof().getText(), "Получение свидетельства о смерти");

        lastPage.getCloseButton().click();

        Assert.assertEquals(homePage.getUserLogin().isDisplayed(), homePage.getAdminLogin().isDisplayed());

        driver.navigate().refresh();
    }

//    @AfterAll
//    public static void tearDown() {
//        driver.close();
//        driver.quit();
//    }
}



