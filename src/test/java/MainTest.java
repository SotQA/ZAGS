import org.junit.jupiter.api.AfterAll;
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

public class MainTest extends Config {
    @BeforeAll
    public static void testsConfiguration() {
        driver.get("https://user:senlatest@regoffice.senla.eu/");
        driver.manage().window().maximize();

    }

    @AfterAll
    public static void closeTest() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void firstTest() {

        //Creating all the instances of the classes I work with

        HomePage homePage = new HomePage(driver);
        BirthService birthService = new BirthService(driver);
        ApplicantForm form = new ApplicantForm(driver);
        ServiceChoice choice = new ServiceChoice(driver);
        CivillianForm civilForm = new CivillianForm(driver);
        ServiceInfo service = new ServiceInfo(driver);
        ApplicationStatus applicationStatus = new ApplicationStatus(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        AdminRegScreen admin = new AdminRegScreen(driver);
        LastPageAdmin lastPage = new LastPageAdmin(driver);
        DeathService deathService = new DeathService(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        //Here goes the scenario with marriage registration and then checking out the application from Admin account

        homePage.clickButton(homePage.userLogin);

        form.fillApplicantFOrm("Uzumaki", " Naruto", "Someone", "123443321", "1234543", "Konoha");
        form.nextButton.click();

        choice.clickButton(choice.marriageReg);

        civilForm.fillCivillForm("Uzumaki", "Naruto", "Hockage", "22071997", "12345432", "Male");
        civilForm.nextButton.click();

        service.fillServiceInfo("13242003", "Uzumaki", "Hjugo", "Hinata", "Hockage", "23041998", "1234432");
        service.endButton.click();

        Assert.assertEquals("Спасибо за обращение!", applicationStatus.thanksForApplication.getText());
        Assert.assertEquals("Ваша заявка отправлена на рассмотрение.", applicationStatus.applicationStatus.getText());
        applicationStatus.clickButton(applicationStatus.closePage);

        //Logging in as an Admin to check out the application registered

        homePage.clickButton(homePage.adminLogin);

        admin.fillAdminForm("Uzumaki", "Naruto", "1234543", "1234432", "12345543", "22061999");
        admin.nextButton.click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.lastElement);
        } catch (TimeoutException exception) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.lastElement);
            System.out.println("The table wasn't loaded in time so the second attempt to load it and to scroll down.");
        }

//        lastPage.clickButton(lastPage.approve);

        try {
            lastPage.clickButton(lastPage.approve);
            wait.until(ExpectedConditions.textToBePresentInElement
                    (driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")),
                            driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")).getText()));
            Assert.assertEquals("Одобрена", driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")).getText());
            Assert.assertEquals("Получение свидетельства о браке", lastPage.marriageProof.getText());
        } catch (AssertionError error) {
            System.out.println("The button is not correct. This is first approve attempt.");
        }

        lastPage.clickButton(lastPage.closeButton);

        //The end of marriage registration and checking it out.
        //Start of birth registration and admin approve too.

        driver.navigate().refresh();

        homePage.clickButton(homePage.userLogin);

        form.fillApplicantFOrm("Uzumaki", " Naruto", "Someone", "123443321", "1234543", "Konoha");
        form.nextButton.click();

        choice.clickButton(choice.birthReg);

        civilForm.fillCivillForm("Uzumaki", "Naruto", "Hockage", "22071997", "12345432", "Male");
        civilForm.nextButton.click();

        birthService.fillBirthForm("Konoha", "Kinaha", "Hokagefourth");
        birthService.endButton.click();

        Assert.assertEquals("Спасибо за обращение!", applicationStatus.thanksForApplication.getText());
        Assert.assertEquals("Ваша заявка отправлена на рассмотрение.", applicationStatus.applicationStatus.getText());
        applicationStatus.clickButton(applicationStatus.closePage);

        //The end of birth registration
        driver.navigate().refresh();

        //Start of admin checking out the application to approve
        homePage.clickButton(homePage.adminLogin);

        admin.fillAdminForm("Uzumaki", "Naruto", "1234543", "1234432", "12345543", "22061999");
        admin.nextButton.click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.lastElement);
        } catch (TimeoutException exception) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.lastElement);
            System.out.println("The table wasn't loaded in time so the second attempt to load it and to scroll down.");
        }

//        lastPage.clickButton(lastPage.approve);

        try {
            lastPage.clickButton(lastPage.approve);
            wait.until(ExpectedConditions.textToBePresentInElement
                    (driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")),
                            driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")).getText()));
            Assert.assertEquals("Одобрена", driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")).getText());
            Assert.assertEquals("Получение свидетельства о рождении", lastPage.birthProof.getText());
        } catch (AssertionError error) {
            System.out.println("The button is not correct. This is the second approve attempt.");
        }

        //Login with a death certificate and do the same through admin account
        lastPage.closeButton.click();
        //End of admin checking out birth registration
        driver.navigate().refresh();

        //Start of scenario of death registration
        homePage.clickButton(homePage.userLogin);

        form.fillApplicantFOrm("Uzumaki", " Naruto", "Someone", "123443321", "1234543", "Konoha");
        form.nextButton.click();

        choice.clickButton(choice.deathReg);

        civilForm.fillCivillForm("Uzumaki", "Naruto", "Hockage", "22071997", "12345432", "Male");
        civilForm.nextButton.click();

        deathService.fillDeathForm("13011890", "Hollywood");
        deathService.endButton.click();

        Assert.assertEquals("Спасибо за обращение!", applicationStatus.thanksForApplication.getText());
        Assert.assertEquals("Ваша заявка отправлена на рассмотрение.", applicationStatus.applicationStatus.getText());
        applicationStatus.clickButton(applicationStatus.closePage);

        driver.navigate().refresh();

        //Admin checking out the death registration

        homePage.clickButton(homePage.adminLogin);

        admin.fillAdminForm("Uzumaki", "Naruto", "1234543", "1234432", "12345543", "22061999");
        admin.nextButton.click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.lastElement);
        } catch (TimeoutException exception) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.lastElement);
            System.out.println("The table wasn't loaded in time so the second attempt to load it and to scroll down.");
        }

//        lastPage.clickButton(lastPage.approve);

        try {
            lastPage.clickButton(lastPage.approve);
            wait.until(ExpectedConditions.textToBePresentInElement
                    (driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")),
                            driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")).getText()));
            Assert.assertEquals("Одобрена", driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")).getText());
            Assert.assertEquals("Получение свидетельства о смерти", lastPage.deathProof.getText());
        } catch (AssertionError error) {
            System.out.println("The button is not correct. This is the third approve attempt.");
        }

        lastPage.closeButton.click();

        driver.navigate().refresh();

        Assert.assertEquals(homePage.userLogin.isDisplayed(), homePage.adminLogin.isDisplayed());
    }
}
