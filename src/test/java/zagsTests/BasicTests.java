package zagsTests;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import zags.Config;
import zags.Factory.*;

import java.io.IOException;
import java.time.Duration;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

@Feature("Main flow of registration through 3 different users")
public class BasicTests extends Config {

    public static SoftAssert softAssert = new SoftAssert();


    @BeforeAll
    public static void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", "Google Chrome")
                        .put("Browser Version", "110.0.5481.97")
                        .put("URL", getURL())
                        .build(), System.getProperty("user.dir")
                + "/allure-results/");
    }

    @BeforeEach
    public void testsConfiguration() {
        driver.get(getURL());
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        softAssert.assertAll();
    }

    @AfterAll
    public static void tearDownAll() {
        driver.close();
        driver.quit();
    }

    @Disabled
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Marriage registration test")
    @Story("Marriage registration with admin application approve.")
    public void marriageApplicationTest() throws IOException {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        HomePage homePage = new HomePage(driver);
        homePage.clickButton(homePage.getUserLogin());

        ApplicantForm form = new ApplicantForm(driver);
        form.fillApplicantForm("Uzumaki", "Naruto", "Someone", "123443321", "1234543", "Konoha");
        softAssert.assertEquals(form.getLastName().getAttribute("value"), "Uzumaki");
        softAssert.assertEquals(form.getFirstName().getAttribute("value"), "Naruto");
        softAssert.assertEquals(form.getFatherName().getAttribute("value"), "Someone");
        softAssert.assertEquals(form.getPhoneNum().getAttribute("value"), "123443321");
        softAssert.assertEquals(form.getPasspNum().getAttribute("value"), "1234543");
        softAssert.assertEquals(form.getAddress().getAttribute("value"), "Konoha");
        form.getNextButton().click();

        ServiceChoice choice = new ServiceChoice(driver);
        choice.clickButton(choice.getMarriageReg());

        CivillianForm civilForm = new CivillianForm(driver);
        civilForm.fillCivillForm("Uzumaki", "Naruto", "Hockage", "22061999", "12345432", "Male");
        softAssert.assertEquals(civilForm.getLastName().getAttribute("value"), "Uzumaki");
        softAssert.assertEquals(civilForm.getFirstName().getAttribute("value"), "Naruto");
        softAssert.assertEquals(civilForm.getFathersName().getAttribute("value"), "Hockage");
        softAssert.assertEquals(civilForm.getDateOfBirth().getAttribute("value"), "1999-06-22", "Civilform -marriage registration -date of birth field.");
        softAssert.assertEquals(civilForm.getPassportNumber().getAttribute("value"), "12345432");
        softAssert.assertEquals(civilForm.getSex().getAttribute("value"), "Male");
        civilForm.getNextButton().click();

        ServiceInfo service = new ServiceInfo(driver);
        service.fillServiceInfo("22061999", "Uzumaki", "Hjugo", "Hinata", "Hockage", "22061999", "1234432");
        softAssert.assertEquals(service.getRegDate().getAttribute("value"), "1999-06-22", "Serviceinfo -marriage registration -regdate field.");
        softAssert.assertEquals(service.getNewLastName().getAttribute("value"), "Uzumaki");
        softAssert.assertEquals(service.getSpouseLastN().getAttribute("value"), "Hjugo");
        softAssert.assertEquals(service.getSpouseFirstN().getAttribute("value"), "Hinata");
        softAssert.assertEquals(service.getSpouseFatherN().getAttribute("value"), "Hockage");
        softAssert.assertEquals(service.getSpouseDateOfBirth().getAttribute("value"), "1999-06-22", "Serviceinfo -marriage registration -date of birth field.");
        softAssert.assertEquals(service.getSpousePasspN().getAttribute("value"), "1234432");
        service.getEndButton().click();

        ApplicationStatus applicationStatus = new ApplicationStatus(driver);
        softAssert.assertEquals(applicationStatus.getThanksForApplication().getText(), "Спасибо за обращение!");
        softAssert.assertEquals(applicationStatus.getApplicationStatus().getText(), "Ваша заявка отправлена на рассмотрение.");
        applicationStatus.clickButton(applicationStatus.getClosePage());

        //Logging in as an Admin to check out the application registered

        homePage.clickButton(homePage.getAdminLogin());

        AdminRegScreen admin = new AdminRegScreen(driver);
        admin.fillAdminForm("Uzumaki", "Naruto", "1234543", "1234432", "12345543", "22061999");
        softAssert.assertEquals(admin.getLastName().getAttribute("value"), "Uzumaki");
        softAssert.assertEquals(admin.getFirstName().getAttribute("value"), "Naruto");
        softAssert.assertEquals(admin.getFathersName().getAttribute("value"), "1234543");
        softAssert.assertEquals(admin.getPhoneNum().getAttribute("value"), "1234432");
        softAssert.assertEquals(admin.getPassportNumber().getAttribute("value"), "12345543");
        softAssert.assertEquals(admin.getDateOfBirth().getAttribute("value"), "1999-06-22", "Adminregscreen -marriage registration -date of birth field.");
        admin.getNextButton().click();

        LastPageAdmin lastPage = new LastPageAdmin(driver);

        try {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.getLastElement());
        } catch (TimeoutException exception) {
            System.out.println("The table wasn't loaded in time so the second attempt to load it and to scroll down.");
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
            js.executeScript("arguments[0].scrollIntoView(true);", lastPage.getLastElement());
        }

        try {
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.getMarriageProof(), "Получение свидетельства о браке"));
            lastPage.getApprove().click();
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.getStatusLastElement(), "Одобрена"));
        } catch (AssertionError error) {
            System.out.println("The button is not correct.");
        }
        softAssert.assertEquals(lastPage.getStatusLastElement().getText(), "Одобрена", "The button didn't change its status.");
        softAssert.assertEquals(lastPage.getMarriageProof().getText(), "Получение свидетельства о браке");
        lastPage.getCloseButton().click();

        driver.navigate().refresh();

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Birth registration test")
    @Story("Birth registration with admin application approve.")
    public void birthApplicationTest() throws IOException {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        HomePage homePage = new HomePage(driver);
        homePage.clickButton(homePage.getUserLogin());

        ApplicantForm form = new ApplicantForm(driver);
        form.fillApplicantForm("Uzumaki", "Naruto", "Someone", "123443321", "1234543", "Konoha");
        softAssert.assertEquals(form.getLastName().getAttribute("value"), "Uzumaki");
        softAssert.assertEquals(form.getFirstName().getAttribute("value"), "Naruto");
        softAssert.assertEquals(form.getFatherName().getAttribute("value"), "Someone");
        softAssert.assertEquals(form.getPhoneNum().getAttribute("value"), "123443321");
        softAssert.assertEquals(form.getPasspNum().getAttribute("value"), "1234543");
        softAssert.assertEquals(form.getAddress().getAttribute("value"), "Konoha");
        form.getNextButton().click();

        ServiceChoice choice = new ServiceChoice(driver);
        choice.clickButton(choice.getBirthReg());

        CivillianForm civilForm = new CivillianForm(driver);
        civilForm.fillCivillForm("Uzumaki", "Naruto", "Hockage", "22061999", "12345432", "Male");
        softAssert.assertEquals(civilForm.getLastName().getAttribute("value"), "Uzumaki");
        softAssert.assertEquals(civilForm.getFirstName().getAttribute("value"), "Naruto");
        softAssert.assertEquals(civilForm.getFathersName().getAttribute("value"), "Hockage");
        softAssert.assertEquals(civilForm.getDateOfBirth().getAttribute("value"), "1999-06-22", "Civilform -birth registration -date of birth field.");
        softAssert.assertEquals(civilForm.getPassportNumber().getAttribute("value"), "12345432");
        softAssert.assertEquals(civilForm.getSex().getAttribute("value"), "Male");
        civilForm.getNextButton().click();

        BirthService birthService = new BirthService(driver);
        birthService.fillBirthForm("Konoha", "Kinaha", "Hokagefourth");
        softAssert.assertEquals("Konoha", birthService.getPlaceOfBirth().getAttribute("value"));
        softAssert.assertEquals("Kinaha", birthService.getMother().getAttribute("value"));
        softAssert.assertEquals("Hokagefourth", birthService.getFather().getAttribute("value"));
        birthService.getEndButton().click();

        ApplicationStatus applicationStatus = new ApplicationStatus(driver);
        softAssert.assertEquals(applicationStatus.getThanksForApplication().getText(), "Спасибо за обращение!");
        softAssert.assertEquals(applicationStatus.getApplicationStatus().getText(), "Ваша заявка отправлена на рассмотрение.");
        applicationStatus.clickButton(applicationStatus.getClosePage());

        //Start of admin checking out the application to approve
        homePage.clickButton(homePage.getAdminLogin());

        AdminRegScreen admin = new AdminRegScreen(driver);
        admin.fillAdminForm("Uzumaki", "Naruto", "1234543", "1234432", "12345543", "22061999");
        softAssert.assertEquals(admin.getLastName().getAttribute("value"), "Uzumaki");
        softAssert.assertEquals(admin.getFirstName().getAttribute("value"), "Naruto");
        softAssert.assertEquals(admin.getFathersName().getAttribute("value"), "1234543");
        softAssert.assertEquals(admin.getPhoneNum().getAttribute("value"), "1234432");
        softAssert.assertEquals(admin.getPassportNumber().getAttribute("value"), "12345543");
        softAssert.assertEquals(admin.getDateOfBirth().getAttribute("value"), "1999-06-22", "Adminregscreen -birth registration -date of birth field.");
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
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.getBirthProof(), "Получение свидетельства о рождении"));
            lastPage.getApprove().click();
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.getStatusLastElement(), "Одобрена"));

        } catch (AssertionError error) {
            System.out.println("The button is not correct.");
        }
        softAssert.assertEquals(lastPage.getStatusLastElement().getText(), "Одобрена", "The button didn't change its status.");
        softAssert.assertEquals(lastPage.getBirthProof().getText(), "Получение свидетельства о рождении");
        lastPage.getCloseButton().click();

        softAssert.assertEquals(homePage.getUserLogin().isDisplayed(), homePage.getAdminLogin().isDisplayed());

        driver.navigate().refresh();

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Death registration test")
    @Story("Death registration with admin application approve.")
    public void deathApplicationTest() throws IOException {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        HomePage homePage = new HomePage(driver);
        homePage.getUserLogin().click();

        ApplicantForm form = new ApplicantForm(driver);
        form.fillApplicantForm("Uzumaki", "Naruto", "Someone", "123443321", "1234543", "Konoha");
        softAssert.assertEquals(form.getLastName().getAttribute("value"), "Uzumaki");
        softAssert.assertEquals(form.getFirstName().getAttribute("value"), "Naruto");
        softAssert.assertEquals(form.getFatherName().getAttribute("value"), "Someone");
        softAssert.assertEquals(form.getPhoneNum().getAttribute("value"), "123443321");
        softAssert.assertEquals(form.getPasspNum().getAttribute("value"), "1234543");
        softAssert.assertEquals(form.getAddress().getAttribute("value"), "Konoha");
        form.getNextButton().click();

        ServiceChoice choice = new ServiceChoice(driver);
        choice.getDeathReg().click();

        CivillianForm civilForm = new CivillianForm(driver);
        civilForm.fillCivillForm("Uzumaki", "Naruto", "Hockage", "22061999", "12345432", "Male");
        softAssert.assertEquals(civilForm.getLastName().getAttribute("value"), "Uzumaki");
        softAssert.assertEquals(civilForm.getFirstName().getAttribute("value"), "Naruto");
        softAssert.assertEquals(civilForm.getFathersName().getAttribute("value"), "Hockage");
        softAssert.assertEquals(civilForm.getDateOfBirth().getAttribute("value"), "1999-06-22", "Civilform -death registration -date of birth field.");
        softAssert.assertEquals(civilForm.getPassportNumber().getAttribute("value"), "12345432");
        softAssert.assertEquals(civilForm.getSex().getAttribute("value"), "Male");
        civilForm.getNextButton().click();

        DeathService deathService = new DeathService(driver);
        deathService.fillDeathForm("22061999", "Hollywood");
        softAssert.assertEquals(deathService.getDeathDate().getAttribute("value"), "1999-06-22", "Deathservice -death registration -date of death field.");
        softAssert.assertEquals(deathService.getPlaceOfDeath().getAttribute("value"), "Hollywood");
        deathService.getEndButton().click();

        ApplicationStatus applicationStatus = new ApplicationStatus(driver);
        softAssert.assertEquals(applicationStatus.getThanksForApplication().getText(), "Спасибо за обращение!");
        softAssert.assertEquals(applicationStatus.getApplicationStatus().getText(), "Ваша заявка отправлена на рассмотрение.");
        applicationStatus.getClosePage().click();


        //Admin checking out the death registration
        homePage.getAdminLogin().click();

        AdminRegScreen admin = new AdminRegScreen(driver);
        admin.fillAdminForm("Uzumaki", "Naruto", "1234543", "1234432", "12345543", "22061999");
        softAssert.assertEquals(admin.getLastName().getAttribute("value"), "Uzumaki");
        softAssert.assertEquals(admin.getFirstName().getAttribute("value"), "Naruto");
        softAssert.assertEquals(admin.getFathersName().getAttribute("value"), "1234543");
        softAssert.assertEquals(admin.getPhoneNum().getAttribute("value"), "1234432");
        softAssert.assertEquals(admin.getPassportNumber().getAttribute("value"), "12345543");
        softAssert.assertEquals(admin.getDateOfBirth().getAttribute("value"), "1999-06-22", "Adminregscreen -death registration -date of birth field.");
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

        softAssert.assertEquals(lastPage.getStatusLastElement().getText(), "Одобрена", "The button is incorrect. Must be \"Одобрена\" .");
        softAssert.assertEquals(lastPage.getDeathProof().getText(), "Получение свидетельства о смерти");
        lastPage.getCloseButton().click();

        softAssert.assertEquals(homePage.getUserLogin().isDisplayed(), homePage.getAdminLogin().isDisplayed());

        driver.navigate().refresh();
    }
}
