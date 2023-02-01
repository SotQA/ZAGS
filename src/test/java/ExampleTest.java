import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import zags.Config;
import zags.Factory.*;

public class ExampleTest extends Config {
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
    public void firstTest() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        homePage.clickButton(homePage.userLogin);

        ApplicantForm form = new ApplicantForm(driver);
        form.fillApplicantFOrm("Uzumaki", " Naruto", "Someone", "123443321", "1234543", "Konoha");

        ServiceChoice choice = new ServiceChoice(driver);
        choice.clickButton(choice.marriageReg);

        CivillianForm civilForm = new CivillianForm(driver);
        civilForm.fillCivillForm("Uzumaki", "Naruto", "Hockage", "22071997", "12345432", "Male");

        ServiceInfo service = new ServiceInfo(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        service.fillElement(service.regDate, "22062008");
        service.fillElement(service.newLastName, "Idontknow");
        service.fillElement(service.spouseLastN, "Uzumaki");
        service.fillElement(service.spouseFirstN, "Naruto");
        service.fillElement(service.spouseFatherN, "Idontknow");
        js.executeScript("arguments[0].scrollIntoView(true);", service.spousePasspN);
        service.fillElement(service.spouseDateOfBirth, "22102005");
        service.fillElement(service.spousePasspN, "123432");
        service.clickButton(service.endButton);

        ApplicationStatus status = new ApplicationStatus(driver);
        status.clickButton(status.closePage);

        homePage.clickButton(homePage.adminLogin);

        AdminRegScreen admin = new AdminRegScreen(driver);
        admin.fillElement(admin.lastName, "Uzumaki");
        admin.fillElement(admin.firstName, "Naruto");
        admin.fillElement(admin.fathersName, "Narutovich");
        admin.fillElement(admin.phoneNum, "12345678");
        admin.fillElement(admin.passportNumber, "1234432");
        admin.fillElement(admin.dateOfBirth, "22061999");
        admin.clickButton(admin.nextButton);

        LastPageAdmin lastPage = new LastPageAdmin(driver);
        Thread.sleep(12000);
        js.executeScript("arguments[0].scrollIntoView(true);", lastPage.lastElement);

        lastPage.clickButton(lastPage.lastElement);

        Thread.sleep(6000);

        Assert.assertEquals("На рассмотрении", driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")).getText());

        lastPage.clickButton(lastPage.closeButton);

        driver.navigate().refresh();

        homePage.clickButton(homePage.userLogin);

        form.fillApplicantFOrm("Uzumaki", " Naruto", "Someone", "123443321", "1234543", "Konoha");

        choice.clickButton(choice.birthReg);
        civilForm.fillCivillForm("Uzumaki", "Naruto", "Hockage", "22071997", "12345432", "Male");

        BirthService birthService = new BirthService(driver);
        birthService.fillBirthForm("Konoha", "Kinaha", "Hokagefourth");
    }
}
