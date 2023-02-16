package zags;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class Config {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private static final String URL = ("https://user:senlatest@regoffice.senla.eu/");

    private final String FIRST_NAME = "Naruto";
    private final String LAST_NAME = "Uzumaki";
    private final String FATHER_NAME = "Idontknow";
    private final String BIRTH_DATE = "22061999";
    private final String PHONE_NUMBER = "821075913";
    private final String PASSPORT_NUMBER = "1234567";
    private final String GENDER = "Male";
    private final String ADDRESS = "Konoha";

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public String getFATHER_NAME() {
        return FATHER_NAME;
    }

    public String getBIRTH_DATE() {
        return BIRTH_DATE;
    }

    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public String getPASSPORT_NUMBER() {
        return PASSPORT_NUMBER;
    }

    public String getGENDER() {
        return GENDER;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public File getScreenshotAs() {
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return screenshotAs;
    }

    public static String getURL() {
        return URL;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void clickButton(WebElement button) {
        button.click();
    }
}
