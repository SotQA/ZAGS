import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import zags.*;

public class SmokeTest extends Config {

    @BeforeAll
    public static void testsConfiguration() {
        driver.get("https://user:senlatest@regoffice.senla.eu/");
        driver.manage().window().maximize();
    }

    @Test
    public void smokeTest() throws InterruptedException {
        FirstScreen firstScreen = new FirstScreen();
        Assert.assertEquals(firstScreen.getUserLogin(), driver.findElement(By.xpath("//button[contains(text(),'Войти как пользователь')]")));
        Assert.assertEquals(firstScreen.getAdminLogin(), driver.findElement(By.xpath("//button[contains(text(),'Войти как администратор')]")));
        firstScreen.getUserLogin().click();

        Thread.sleep(1500);

        UserForm userForm = new UserForm();

        Assert.assertEquals(userForm.getLastName(), driver.findElement(By.xpath("//div/input[@placeholder='Введите фамилию (минимум 2 символа)']")));
        Assert.assertEquals(userForm.getFirstName(), driver.findElement(By.xpath("//div/input[@placeholder='Введите имя (минимум 2 символа)']")));
        Assert.assertEquals(userForm.getFathersName(), driver.findElement(By.xpath("//div/input[@placeholder='Введите отчество (минимум 5 символов)']")));
        Assert.assertEquals(userForm.getPhoneNumber(), driver.findElement(By.xpath("//div/input[@placeholder='Введите номер телефона (не более 13 символов)']")));
        Assert.assertEquals(userForm.getPassportNumber(), driver.findElement(By.xpath("//div/input[@placeholder='Введите номер паспорта (не более 9 символов)']")));
        Assert.assertEquals(userForm.getAddress(), driver.findElement(By.xpath("//div/input[@placeholder='Введите адрес прописки']")));
        Assert.assertEquals(userForm.getNextButton(), driver.findElement(By.xpath("//div/button[contains(text(), 'Далее')]")));

        userForm.fillingTheField(userForm.getLastName(), "Uzumaki");
        userForm.fillingTheField(userForm.getFirstName(), "Naruto");
        userForm.fillingTheField(userForm.getFathersName(), "Akamievich");
        userForm.fillingTheField(userForm.getPhoneNumber(), "123456789");
        userForm.fillingTheField(userForm.getPassportNumber(), "1234567");
        userForm.fillingTheField(userForm.getAddress(), "Konoha");
        userForm.clickNextButton();

        Thread.sleep(2000);

        RegistrationScreen regScreen = new RegistrationScreen();
        regScreen.getMarriageRegistration().click();

        MarriageRegScreen marryScreen = new MarriageRegScreen();
        marryScreen.getLastName().click();
        marryScreen.getLastName().sendKeys("Hjugo");
        marryScreen.getFirstName().click();
        marryScreen.getFirstName().sendKeys("Hinata");
        marryScreen.getFirstName().click();
        marryScreen.getFathersName().sendKeys("Batya");
        marryScreen.getDateOfBirth().click();
        marryScreen.getDateOfBirth().sendKeys("05061998");
        marryScreen.getPassportNumber().click();
        marryScreen.getPassportNumber().sendKeys("1234567");
        marryScreen.getSex().click();
        marryScreen.getSex().sendKeys("Female");
        marryScreen.getNextButton().click();

        Thread.sleep(2000);

        ServiceDataScreen service = new ServiceDataScreen();
        JavascriptExecutor js = (JavascriptExecutor)driver;

        service.getDateOfReg().click();
        service.getDateOfReg().sendKeys("30012023");
        service.getNewLastName().click();
        service.getNewLastName().sendKeys("Uzumaki");
        service.getSpouseLastName().click();
        service.getSpouseLastName().sendKeys("Hjugo");
        service.getSpouseFirstName().click();
        service.getSpouseFirstName().sendKeys("Hinata");
        js.executeScript("arguments[0].scrollIntoView(true);", service.getSpousePassportN());

        Thread.sleep(500);

        service.getSpouseFathersName().click();
        service.getSpouseFathersName().sendKeys("Batya");
        service.getSpouseBirthDate().click();
        service.getSpouseBirthDate().sendKeys("22051985");
        service.getSpousePassportN().click();
        service.getSpousePassportN().sendKeys("7654321");
        service.getNextButton().click();

        FinalUserScreen lastPage = new FinalUserScreen();
        Assert.assertEquals("Спасибо за обращение!" ,lastPage.getThanksElement().getText());
        Assert.assertEquals("Ваша заявка отправлена на рассмотрение." ,lastPage.getApplicationStatus().getText());
        lastPage.close();

        FirstScreen screen = new FirstScreen();
        screen.getAdminLogin().click();

        AdminRegScreen registration = new AdminRegScreen();
        registration.getLastName().click();
        registration.getLastName().sendKeys("Someones");
        registration.getFirstName().click();
        registration.getFirstName().sendKeys("Sergey");
        registration.getFathersName().click();
        registration.getFathersName().sendKeys("SomeonesDad");
        registration.getPhoneNumber().click();
        registration.getPhoneNumber().sendKeys("123456789");
        registration.getPassportN().click();
        registration.getPassportN().sendKeys("987654321");
        registration.getDateOfBirth().click();
        registration.getDateOfBirth().sendKeys("22061999");
        registration.getNextButton().click();

        Thread.sleep(5000);

        WebElement lastElement = driver.findElement(By.xpath("//table/tr[last()]"));
        js.executeScript("arguments[0].scrollIntoView(true);", lastElement);

        Assert.assertEquals("На рассмотрении", driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")).getText());

        ApplicationsAdminScreen application = new ApplicationsAdminScreen();
        application.getApproveButton().click();

        Thread.sleep(1500);

        Assert.assertEquals("Одобрена", driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")).getText());

        application.getCloseButton().click();

        if(screen.getUserLogin().isDisplayed() && screen.getAdminLogin().isDisplayed()){
            System.out.println("All tests had passed!");
        }
    }
}
