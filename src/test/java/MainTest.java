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

        HomePage homePage = new HomePage(driver);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        //Here goes the scenario with marriage registration and then checking out the application from Admin account

        homePage.clickButton(homePage.userLogin);

        ApplicantForm form = new ApplicantForm(driver);
        form.fillApplicantForm("Uzumaki", "Naruto", "Someone", "123443321", "1234543", "Konoha");
        Assert.assertEquals(form.lastName.getAttribute("value"), "Uzumaki");
        Assert.assertEquals(form.firstName.getAttribute("value"), "Naruto");
        Assert.assertEquals(form.fatherName.getAttribute("value"), "Someone");
        Assert.assertEquals(form.phoneNum.getAttribute("value"), "123443321");
        Assert.assertEquals(form.passpNum.getAttribute("value"), "1234543");
        Assert.assertEquals(form.address.getAttribute("value"), "Konoha");

        form.nextButton.click();

        ServiceChoice choice = new ServiceChoice(driver);
        choice.clickButton(choice.marriageReg);

        CivillianForm civilForm = new CivillianForm(driver);
        civilForm.fillCivillForm("Uzumaki", "Naruto", "Hockage", "22061999", "12345432", "Male");
        Assert.assertEquals(civilForm.lastName.getAttribute("value"), "Uzumaki");
        Assert.assertEquals(civilForm.firstName.getAttribute("value"), "Naruto");
        Assert.assertEquals(civilForm.fathersName.getAttribute("value"), "Hockage");
//        Assert.assertEquals(civilForm.dateOfBirth.getAttribute("value"), "1999-06-22");
        Assert.assertEquals(civilForm.passportNumber.getAttribute("value"), "12345432");
        Assert.assertEquals(civilForm.sex.getAttribute("value"), "Male");
        civilForm.nextButton.click();

        ServiceInfo service = new ServiceInfo(driver);
        service.fillServiceInfo("22061999", "Uzumaki", "Hjugo", "Hinata", "Hockage", "22061999", "1234432");
//        Assert.assertEquals(service.regDate.getAttribute("value"), "1999-06-22");
        Assert.assertEquals(service.newLastName.getAttribute("value"), "Uzumaki");
        Assert.assertEquals(service.spouseLastN.getAttribute("value"), "Hjugo");
        Assert.assertEquals(service.spouseFirstN.getAttribute("value"), "Hinata");
        Assert.assertEquals(service.spouseFatherN.getAttribute("value"), "Hockage");
//        Assert.assertEquals(service.spouseDateOfBirth.getAttribute("value"), "1999-06-22");
        Assert.assertEquals(service.spousePasspN.getAttribute("value"), "1234432");
        service.endButton.click();

        ApplicationStatus applicationStatus = new ApplicationStatus(driver);
        Assert.assertEquals(applicationStatus.thanksForApplication.getText(), "Спасибо за обращение!");
        Assert.assertEquals(applicationStatus.applicationStatus.getText(), "Ваша заявка отправлена на рассмотрение.");
        applicationStatus.clickButton(applicationStatus.closePage);

        //Logging in as an Admin to check out the application registered

        homePage.clickButton(homePage.adminLogin);

        AdminRegScreen admin = new AdminRegScreen(driver);
        admin.fillAdminForm("Uzumaki", "Naruto", "1234543", "1234432", "12345543", "22061999");
        Assert.assertEquals(admin.lastName.getAttribute("value"), "Uzumaki");
        Assert.assertEquals(admin.firstName.getAttribute("value"), "Naruto");
        Assert.assertEquals(admin.fathersName.getAttribute("value"), "1234543");
        Assert.assertEquals(admin.phoneNum.getAttribute("value"), "1234432");
        Assert.assertEquals(admin.passportNumber.getAttribute("value"), "12345543");
//        Assert.assertEquals(admin.dateOfBirth.getAttribute("value"), "1999-06-22");
        admin.nextButton.click();

        LastPageAdmin lastPage = new LastPageAdmin(driver);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.lastElement);
        } catch (TimeoutException exception) {
            System.out.println("The table wasn't loaded in time so the second attempt to load it and to scroll down.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.lastElement);
        }

        try {
            lastPage.clickButton(lastPage.approve);
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.status, lastPage.status.getText()));
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.marriageProof, lastPage.marriageProof.getText()));
        } catch (AssertionError error) {
            System.out.println("The button is not correct. This is first approve attempt.");
        }
        Assert.assertEquals(lastPage.status.getText(), "Одобрена", "The button didn't change its status. Attempt number one.");
        Assert.assertEquals(lastPage.marriageProof.getText(), "Получение свидетельства о браке");

        lastPage.clickButton(lastPage.closeButton);

        //The end of marriage registration and checking it out.
        //Start of birth registration and admin approve too.

        driver.navigate().refresh();

        homePage.clickButton(homePage.userLogin);

        form.fillApplicantForm("Uzumaki", "Naruto", "Someone", "123443321", "1234543", "Konoha");
        Assert.assertEquals(form.lastName.getAttribute("value"), "Uzumaki");
        Assert.assertEquals(form.firstName.getAttribute("value"), "Naruto");
        Assert.assertEquals(form.fatherName.getAttribute("value"), "Someone");
        Assert.assertEquals(form.phoneNum.getAttribute("value"), "123443321");
        Assert.assertEquals(form.passpNum.getAttribute("value"), "1234543");
        Assert.assertEquals(form.address.getAttribute("value"), "Konoha");
        form.nextButton.click();

        choice.clickButton(choice.birthReg);

        civilForm.fillCivillForm("Uzumaki", "Naruto", "Hockage", "22061999", "12345432", "Male");
        Assert.assertEquals(civilForm.lastName.getAttribute("value"), "Uzumaki");
        Assert.assertEquals(civilForm.firstName.getAttribute("value"), "Naruto");
        Assert.assertEquals(civilForm.fathersName.getAttribute("value"), "Hockage");
//        Assert.assertEquals(civilForm.dateOfBirth.getAttribute("value"), "1999-06-22");
        Assert.assertEquals(civilForm.passportNumber.getAttribute("value"), "12345432");
        Assert.assertEquals(civilForm.sex.getAttribute("value"), "Male");
        civilForm.nextButton.click();

        BirthService birthService = new BirthService(driver);
        birthService.fillBirthForm("Konoha", "Kinaha", "Hokagefourth");
        Assert.assertEquals("Konoha", birthService.placeOfBirth.getAttribute("value"));
        Assert.assertEquals("Kinaha", birthService.mother.getAttribute("value"));
        Assert.assertEquals("Hokagefourth", birthService.father.getAttribute("value"));
        birthService.endButton.click();

        Assert.assertEquals(applicationStatus.thanksForApplication.getText(), "Спасибо за обращение!");
        Assert.assertEquals(applicationStatus.applicationStatus.getText(), "Ваша заявка отправлена на рассмотрение.");
        applicationStatus.clickButton(applicationStatus.closePage);

        //The end of birth registration
        driver.navigate().refresh();

        //Start of admin checking out the application to approve
        homePage.clickButton(homePage.adminLogin);

        admin.fillAdminForm("Uzumaki", "Naruto", "1234543", "1234432", "12345543", "22061999");
        Assert.assertEquals(admin.lastName.getAttribute("value"), "Uzumaki");
        Assert.assertEquals(admin.firstName.getAttribute("value"), "Naruto");
        Assert.assertEquals(admin.fathersName.getAttribute("value"), "1234543");
        Assert.assertEquals(admin.phoneNum.getAttribute("value"), "1234432");
        Assert.assertEquals(admin.passportNumber.getAttribute("value"), "12345543");
//        Assert.assertEquals(admin.dateOfBirth.getAttribute("value"), "1999-06-22");
        admin.nextButton.click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.lastElement);
        } catch (TimeoutException exception) {
            System.out.println("The table wasn't loaded in time so the second attempt to load it and to scroll down.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.lastElement);
        }

        try {
            lastPage.clickButton(lastPage.approve);
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.status, lastPage.status.getText()));
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.birthProof, lastPage.birthProof.getText()));
        } catch (AssertionError error) {
            System.out.println("The button is not correct. This is the second approve attempt.");
        }
        Assert.assertEquals(lastPage.status.getText(), "Одобрена", "The button didn't change its status. Attempt number two.");
        Assert.assertEquals(lastPage.birthProof.getText(), "Получение свидетельства о рождении");

        //Login with a death certificate and do the same through admin account
        lastPage.closeButton.click();
        //End of admin checking out birth registration
        driver.navigate().refresh();

        //Start of scenario of death registration
        homePage.clickButton(homePage.userLogin);

        form.fillApplicantForm("Uzumaki", "Naruto", "Someone", "123443321", "1234543", "Konoha");
        Assert.assertEquals(form.lastName.getAttribute("value"), "Uzumaki");
        Assert.assertEquals(form.firstName.getAttribute("value"), "Naruto");
        Assert.assertEquals(form.fatherName.getAttribute("value"), "Someone");
        Assert.assertEquals(form.phoneNum.getAttribute("value"), "123443321");
        Assert.assertEquals(form.passpNum.getAttribute("value"), "1234543");
        Assert.assertEquals(form.address.getAttribute("value"), "Konoha");
        form.nextButton.click();

        choice.clickButton(choice.deathReg);

        civilForm.fillCivillForm("Uzumaki", "Naruto", "Hockage", "22061999", "12345432", "Male");
        Assert.assertEquals(civilForm.lastName.getAttribute("value"), "Uzumaki");
        Assert.assertEquals(civilForm.firstName.getAttribute("value"), "Naruto");
        Assert.assertEquals(civilForm.fathersName.getAttribute("value"), "Hockage");
//        Assert.assertEquals(civilForm.dateOfBirth.getAttribute("value"), "1999-06-22");
        Assert.assertEquals(civilForm.passportNumber.getAttribute("value"), "12345432");
        Assert.assertEquals(civilForm.sex.getAttribute("value"), "Male");
        civilForm.nextButton.click();

        DeathService deathService = new DeathService(driver);
        deathService.fillDeathForm("22061999", "Hollywood");
//        Assert.assertEquals(deathService.deathDate.getAttribute("value"), "1999-06-22");
        Assert.assertEquals(deathService.placeOfDeath.getAttribute("value"), "Hollywood");
        deathService.endButton.click();

        Assert.assertEquals(applicationStatus.thanksForApplication.getText(), "Спасибо за обращение!");
        Assert.assertEquals(applicationStatus.applicationStatus.getText(), "Ваша заявка отправлена на рассмотрение.");
        applicationStatus.clickButton(applicationStatus.closePage);

        driver.navigate().refresh();

        //Admin checking out the death registration
        homePage.clickButton(homePage.adminLogin);

        admin.fillAdminForm("Uzumaki", "Naruto", "1234543", "1234432", "12345543", "22061999");
        Assert.assertEquals(admin.lastName.getAttribute("value"), "Uzumaki");
        Assert.assertEquals(admin.firstName.getAttribute("value"), "Naruto");
        Assert.assertEquals(admin.fathersName.getAttribute("value"), "1234543");
        Assert.assertEquals(admin.phoneNum.getAttribute("value"), "1234432");
        Assert.assertEquals(admin.passportNumber.getAttribute("value"), "12345543");
//        Assert.assertEquals(admin.dateOfBirth.getAttribute("value"), "1999-06-22");
        admin.nextButton.click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.lastElement);
        } catch (TimeoutException exception) {
            System.out.println("The table wasn't loaded in time so the second attempt to load it and to scroll down.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.lastElement);
        }

        try {
            lastPage.clickButton(lastPage.approve);
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.status, lastPage.status.getText()));
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.deathProof, lastPage.deathProof.getText()));
        } catch (AssertionError error) {
            System.out.println("The button is not correct. This is the third approve attempt.");
        }
        Assert.assertEquals(lastPage.status.getText(), "Одобрена", "The button didn't change its status. Attempt number three.");
        Assert.assertEquals(lastPage.deathProof.getText(), "Получение свидетельства о смерти");

        lastPage.closeButton.click();

        driver.navigate().refresh();

        Assert.assertEquals(homePage.userLogin.isDisplayed(), homePage.adminLogin.isDisplayed());
    }
}
