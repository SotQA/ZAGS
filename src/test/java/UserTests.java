import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import zags.*;

public class UserTests extends Config {


    @BeforeAll
    public static void testsConfiguration() {
        driver = new ChromeDriver();
        driver.get("https://user:senlatest@regoffice.senla.eu/");
        driver.manage().window().maximize();
    }

    @Test
    public void firstScreenTest() throws InterruptedException {
        FirstScreen firstScreen = new FirstScreen();
        Assert.assertEquals(firstScreen.getUserLogin(), driver.findElement(By.xpath("//button[contains(text(),'Войти как пользователь')]")));
        Assert.assertEquals(firstScreen.getAdminLogin(), driver.findElement(By.xpath("//button[contains(text(),'Войти как администратор')]")));
        firstScreen.loggingAsUserClick();
        Thread.sleep(1500);
    }

    @Test
    public void userFormScreenTest() throws InterruptedException {
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

        Thread.sleep(2000);
    }

    @Test
    public void registrationScreenTest() throws InterruptedException {

        MarriageRegScreen marryScr = new MarriageRegScreen();
        marryScr.getLastName().click();
        marryScr.getLastName().sendKeys("Hjuga");


    }
}
