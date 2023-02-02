import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import zags.Config;
import zags.NonFactory.*;

import java.time.Duration;

public class TryTest extends Config {

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
    public void smokeTest() throws InterruptedException {
        FirstScreen firstScreen = new FirstScreen();
        Assert.assertEquals(firstScreen.getUserLogin(), driver.findElement(By.xpath("//button[contains(text(),'Войти как пользователь')]")));
        Assert.assertEquals(firstScreen.getAdminLogin(), driver.findElement(By.xpath("//button[contains(text(),'Войти как администратор')]")));
        firstScreen.getUserLogin().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/input[@placeholder='Введите фамилию (минимум 2 символа)']")));

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

        RegistrationScreen regScreen = new RegistrationScreen();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Регистрация брака')]")));
        regScreen.getMarriageRegistration().click();

        MarriageRegScreen screenWithUserInfo = new MarriageRegScreen();
        screenWithUserInfo.getLastName().click();
        screenWithUserInfo.getLastName().sendKeys("Hjugo");
        screenWithUserInfo.getFirstName().click();
        screenWithUserInfo.getFirstName().sendKeys("Hinata");
        screenWithUserInfo.getFirstName().click();
        screenWithUserInfo.getFathersName().sendKeys("Batya");
        screenWithUserInfo.getDateOfBirth().click();
        screenWithUserInfo.getDateOfBirth().sendKeys("05061998");
        screenWithUserInfo.getPassportNumber().click();
        screenWithUserInfo.getPassportNumber().sendKeys("1234567");
        screenWithUserInfo.getSex().click();
        screenWithUserInfo.getSex().sendKeys("Female");
        screenWithUserInfo.getNextButton().click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiDialogContent-root']")));

        ServiceDataScreen service = new ServiceDataScreen();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        service.getDateOfReg().click();
        service.getDateOfReg().sendKeys("30012023");
        service.getNewLastName().click();
        service.getNewLastName().sendKeys("Uzumaki");
        service.getSpouseLastName().click();
        service.getSpouseLastName().sendKeys("Hjugo");
        service.getSpouseFirstName().click();
        service.getSpouseFirstName().sendKeys("Hinata");
        js.executeScript("arguments[0].scrollIntoView(true);", service.getSpousePassportN());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='TextInputField-19']")));

        service.getSpouseFathersName().click();
        service.getSpouseFathersName().sendKeys("Batya");
        service.getSpouseBirthDate().click();
        service.getSpouseBirthDate().sendKeys("22051985");
        service.getSpousePassportN().click();
        service.getSpousePassportN().sendKeys("7654321");
        service.getNextButton().click();

        FinalUserScreen lastPage = new FinalUserScreen();
        Assert.assertEquals("Спасибо за обращение!", lastPage.getThanksElement().getText());
        Assert.assertEquals("Ваша заявка отправлена на рассмотрение.", lastPage.getApplicationStatus().getText());
        lastPage.close();

        firstScreen.getAdminLogin().click();

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

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/table[@class='MuiTable-root MuiTable-stickyHeader']")));

        Thread.sleep(5000);

        WebElement lastElement = driver.findElement(By.xpath("//table/tr[last()]"));
        js.executeScript("arguments[0].scrollIntoView(true);", lastElement);

        Thread.sleep(3000);

        Assert.assertEquals("На рассмотрении", driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")).getText());

        ApplicationsAdminScreen application = new ApplicationsAdminScreen();
        application.getApproveButton().click();

        Thread.sleep(6000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/table[@class='MuiTable-root MuiTable-stickyHeader']")));

        Assert.assertEquals(driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']/span")).getText(),
                driver.findElement(By.xpath("//table/tr[last()]/td/span")).getText());

        application.getCloseButton().click();

        if (firstScreen.getUserLogin().isDisplayed() && firstScreen.getAdminLogin().isDisplayed()) {
            System.out.println("Application is registered!");
        }

        Thread.sleep(10000);

        driver.get("https://user:senlatest@regoffice.senla.eu/");

        Thread.sleep(10000);

        firstScreen.getUserLogin().click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/input[@placeholder='Введите фамилию (минимум 2 символа)']")));

        WebElement lastName = driver.findElement(By.xpath("//div/input[@placeholder='Введите фамилию (минимум 2 символа)']"));
        WebElement firstName = driver.findElement(By.xpath("//div/input[@placeholder='Введите имя (минимум 2 символа)']"));
        WebElement fathersName = driver.findElement(By.xpath("//div/input[@placeholder='Введите отчество (минимум 5 символов)']"));
        WebElement phoneNum = driver.findElement(By.xpath("//div/input[@placeholder='Введите номер телефона (не более 13 символов)']"));
        WebElement passportNum = driver.findElement(By.xpath("//div/input[@placeholder='Введите номер паспорта (не более 9 символов)']"));
        WebElement address = driver.findElement(By.xpath("//div/input[@placeholder='Введите адрес прописки']"));
        WebElement nextButton = driver.findElement(By.xpath("//div/button[contains(text(), 'Далее')]"));

        lastName.click();
        lastName.sendKeys("Chicago");
        firstName.click();
        firstName.sendKeys("Bulls");
        fathersName.click();
        fathersName.sendKeys("SomeonesDad");
        phoneNum.click();
        phoneNum.sendKeys("1234566432");
        passportNum.click();
        passportNum.sendKeys("12345543");
        address.click();
        address.sendKeys("Lenina 10");
        nextButton.click();


        try {
            regScreen.getBirthRegistration().click();
        } catch (StaleElementReferenceException ex) {
            regScreen.getBirthRegistration().click();
        }


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiDialogContent-root']")));

        userForm.getLastName().click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='TextInputField-25']")));


        screenWithUserInfo.getLastName().click();
        screenWithUserInfo.getLastName().sendKeys("Sigma");
        screenWithUserInfo.getFirstName().click();
        screenWithUserInfo.getFirstName().sendKeys("Ligma");
        screenWithUserInfo.getFathersName().click();
        screenWithUserInfo.getFathersName().sendKeys("Balls");
        screenWithUserInfo.getDateOfBirth().click();
        screenWithUserInfo.getDateOfBirth().sendKeys("11341879");
        screenWithUserInfo.getPassportNumber().click();
        screenWithUserInfo.getPassportNumber().sendKeys("123455321");
        screenWithUserInfo.getSex().click();
        screenWithUserInfo.getSex().sendKeys("Cat");
        screenWithUserInfo.getNextButton().click();

        BirthRegServices birthServicesPage = new BirthRegServices();
        birthServicesPage.getPlaceOfBirth().click();
        birthServicesPage.getPlaceOfBirth().sendKeys("Brest");
        birthServicesPage.getMother().click();
        birthServicesPage.getMother().sendKeys("Inna");
        birthServicesPage.getFather().click();
        birthServicesPage.getFather().sendKeys("X3");
        birthServicesPage.getFinishButton().click();

        Assert.assertEquals("Спасибо за обращение!", lastPage.getThanksElement().getText());
        Assert.assertEquals("Ваша заявка отправлена на рассмотрение.", lastPage.getApplicationStatus().getText());

        lastPage.close();

        firstScreen.getAdminLogin().click();

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

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/table[@class='MuiTable-root MuiTable-stickyHeader']")));
        js.executeScript("arguments[0].scrollIntoView(true);", lastElement);

        Assert.assertEquals("Получение свидетельства о рождении", driver.findElement(By.xpath("//table/tr[last()]/td[contains(text(), 'Получение свидетельства о рождении')]")).getText());
        application.getApproveButton().click();

        Thread.sleep(2000);

        Assert.assertEquals("Одобрена", driver.findElement(By.xpath("//table/tr[last()]/td[@class='MuiTableCell-root MuiTableCell-alignCenter']")).getText());

        application.getCloseButton().click();


    }
}
