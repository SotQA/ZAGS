import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import zags.BasePage;
import zags.Factory.*;

import java.io.IOException;
import java.time.Duration;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class DeathTest extends BasePage {

    public static SoftAssert softAssert = new SoftAssert();
    private static final String PLACE_OF_DEATH = "Highway";
    private static final String DATE_OF_DEATH = "22061999";


    @BeforeAll
    public static void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", "Google Chrome")
                        .put("Browser Version", "110.0.5481.97")
                        .put("URL", getURL())
                        .put("Stand", "Test")
                        .build(), System.getProperty("user.dir")
                        + "/allure-results/");
    }

    @BeforeAll
    public static void testsConfiguration() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        BasePage.driver.get(getURL());
        BasePage.driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        softAssert.assertAll();
    }

    @AfterAll
    public static void tearDownAll() {
        getDriver().quit();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Death registration test")
    @Story("Death registration with admin approve.")
    public void deathApplicationTest() throws IOException {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        HomePage homePage = new HomePage(driver);
        homePage.getUserLogin().click();

        ApplicantForm form = new ApplicantForm(driver);

        form
                .fillLastName(getLAST_NAME())
                .fillFirstName(getFIRST_NAME())
                .fillFatherName(getFATHER_NAME())
                .fillPhoneNumber(getPHONE_NUMBER())
                .fillPassportField(getPASSPORT_NUMBER())
                .fillAddressField(getADDRESS());

        Allure.addAttachment("Applicant form filled", FileUtils.openInputStream(getScreenshotAs()));

        softAssert.assertEquals(form.getLastName().getAttribute("value"), getLAST_NAME());
        softAssert.assertEquals(form.getFirstName().getAttribute("value"), getFIRST_NAME());
        softAssert.assertEquals(form.getFatherName().getAttribute("value"), getFATHER_NAME());
        softAssert.assertEquals(form.getPhoneNum().getAttribute("value"), getPHONE_NUMBER());
        softAssert.assertEquals(form.getPasspNum().getAttribute("value"), getPASSPORT_NUMBER());
        softAssert.assertEquals(form.getAddress().getAttribute("value"), getADDRESS());
        form.getNextButton().click();

        ServiceChoice choice = new ServiceChoice(driver);
        choice.getDeathReg().click();

        CivillianForm civilForm = new CivillianForm(driver);

        civilForm
                .fillLastName(getLAST_NAME())
                .fillFirstName(getFIRST_NAME())
                .fillFatherNameField(getFATHER_NAME())
                .fillBirthDateField(getBIRTH_DATE())
                .fillPassportField(getPASSPORT_NUMBER())
                .fillGenderField(getGENDER());

        Allure.addAttachment("Civilian form filled", FileUtils.openInputStream(getScreenshotAs()));

        softAssert.assertEquals(civilForm.getLastName().getAttribute("value"), getLAST_NAME());
        softAssert.assertEquals(civilForm.getFirstName().getAttribute("value"), getFIRST_NAME());
        softAssert.assertEquals(civilForm.getFathersName().getAttribute("value"), getFATHER_NAME());
        softAssert.assertEquals(civilForm.getDateOfBirth().getAttribute("value"), "1999-06-22");
        softAssert.assertEquals(civilForm.getPassportNumber().getAttribute("value"), getPASSPORT_NUMBER());
        softAssert.assertEquals(civilForm.getSex().getAttribute("value"), getGENDER());
        civilForm.getNextButton().click();

        DeathService deathService = new DeathService(driver);

        deathService
                .fillDeathDateField(DATE_OF_DEATH)
                .fillPlaceOfDeath(PLACE_OF_DEATH);

        softAssert.assertEquals(deathService.getDeathDate().getAttribute("value"), "1999-06-22");
        softAssert.assertEquals(deathService.getPlaceOfDeath().getAttribute("value"), "Hollywood");
        deathService.getEndButton().click();

        ApplicationStatus applicationStatus = new ApplicationStatus(driver);
        softAssert.assertEquals(applicationStatus.getThanksForApplication().getText(), "?????????????? ???? ??????????????????!");
        softAssert.assertEquals(applicationStatus.getApplicationStatus().getText(), "???????? ???????????? ???????????????????? ???? ????????????????????????.");
        applicationStatus.getClosePage().click();

        //Admin checking out the death registration
        homePage.getAdminLogin().click();

        AdminRegScreen admin = new AdminRegScreen(driver);
        admin
                .fillLastName(getLAST_NAME())
                .fillFirstName(getFIRST_NAME())
                .fillFatherName(getFATHER_NAME())
                .fillPhoneNumber(getPHONE_NUMBER())
                .fillPassportField(getPASSPORT_NUMBER())
                .fillBirthDate(getBIRTH_DATE());

        Allure.addAttachment("Admin form filled", FileUtils.openInputStream(getScreenshotAs()));

        softAssert.assertEquals(admin.getLastName().getAttribute("value"), getLAST_NAME());
        softAssert.assertEquals(admin.getFirstName().getAttribute("value"), getFIRST_NAME());
        softAssert.assertEquals(admin.getFathersName().getAttribute("value"), getFATHER_NAME());
        softAssert.assertEquals(admin.getPhoneNum().getAttribute("value"), getPHONE_NUMBER());
        softAssert.assertEquals(admin.getPassportNumber().getAttribute("value"), getPASSPORT_NUMBER());
        softAssert.assertEquals(admin.getDateOfBirth().getAttribute("value"), "1999-06-22");
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
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.getDeathProof(), "?????????????????? ?????????????????????????? ?? ????????????"));
            lastPage.getApprove().click();
            wait.until(ExpectedConditions.textToBePresentInElement(lastPage.getStatusLastElement(), "????????????????"));
        } catch (AssertionError error) {
            System.out.println("The button is incorrect.");
        }

        softAssert.assertEquals(lastPage.getStatusLastElement().getText(), "????????????????", "The button is incorrect. Must be \"????????????????\" .");
        softAssert.assertEquals(lastPage.getDeathProof().getText(), "?????????????????? ?????????????????????????? ?? ????????????");
        lastPage.getCloseButton().click();

        softAssert.assertEquals(homePage.getUserLogin().isDisplayed(), homePage.getAdminLogin().isDisplayed());

        driver.navigate().refresh();
    }
}
