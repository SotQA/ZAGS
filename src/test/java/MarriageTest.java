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


public class MarriageTest extends BasePage {

    public static SoftAssert softAssert = new SoftAssert();
    private static final String REG_DATE = "22061999";
    private static final String NEW_LAST_NAME = "Boruto";
    private static final String SPOUSE_LAST_NAME = "Hjugo";
    private static final String SPOUSE_FIRST_NAME = "Hinata";
    private static final String SPOUSE_FATHER_NAME = "Hockage";
    private static final String SPOUSE_PASSPORT = "1234432";

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
    @DisplayName("Marriage registration test")
    @Story("Marriage registration with admin approve.")
    public void marriageApplicationTest() throws IOException {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        HomePage homePage = new HomePage(driver);
        homePage.clickButton(homePage.getUserLogin());

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
        choice.clickButton(choice.getMarriageReg());

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
        softAssert.assertEquals(civilForm.getDateOfBirth().getAttribute("value"), "1999-06-22", "Civilform -birth registration -date of birth field.");
        softAssert.assertEquals(civilForm.getPassportNumber().getAttribute("value"), getPASSPORT_NUMBER());
        softAssert.assertEquals(civilForm.getSex().getAttribute("value"), getGENDER());
        civilForm.getNextButton().click();

        ServiceInfo service = new ServiceInfo(driver);

        service
                .fillRegDateField(REG_DATE)
                .fillNewLastNameField(NEW_LAST_NAME)
                .fillSpouseLastNameField(SPOUSE_LAST_NAME)
                .fillSpouseFirstNameField(SPOUSE_FIRST_NAME)
                .fillSpouseFatherNameField(SPOUSE_FATHER_NAME);

        js.executeScript("arguments[0].scrollIntoView(true);", service.getSpousePasspN());

        service
                .fillSpouseBirthDate(getBIRTH_DATE())
                .fillSpousePassportNumField(getPASSPORT_NUMBER());

        softAssert.assertEquals(service.getRegDate().getAttribute("value"), "1999-06-22", "Serviceinfo -marriage registration -regdate field.");
        softAssert.assertEquals(service.getNewLastName().getAttribute("value"), NEW_LAST_NAME);
        softAssert.assertEquals(service.getSpouseLastN().getAttribute("value"), SPOUSE_LAST_NAME);
        softAssert.assertEquals(service.getSpouseFirstN().getAttribute("value"), SPOUSE_FIRST_NAME);
        softAssert.assertEquals(service.getSpouseFatherN().getAttribute("value"), SPOUSE_FATHER_NAME);
        softAssert.assertEquals(service.getSpouseDateOfBirth().getAttribute("value"), "1999-06-22", "Serviceinfo -marriage registration -date of birth field.");
        softAssert.assertEquals(service.getSpousePasspN().getAttribute("value"), SPOUSE_PASSPORT);
        service.getEndButton().click();

        ApplicationStatus applicationStatus = new ApplicationStatus(driver);
        softAssert.assertEquals(applicationStatus.getThanksForApplication().getText(), "Спасибо за обращение!");
        softAssert.assertEquals(applicationStatus.getApplicationStatus().getText(), "Ваша заявка отправлена на рассмотрение.");
        applicationStatus.clickButton(applicationStatus.getClosePage());

        //Logging in as an Admin to check out the application registered

        homePage.clickButton(homePage.getAdminLogin());

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
}
