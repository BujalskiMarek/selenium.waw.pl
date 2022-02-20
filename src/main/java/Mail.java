import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;

public class Mail {


    public static void main(String[] args) throws InterruptedException, IOException {

            WebDriverManager.chromedriver().setup();
            ChromeDriver driver = new ChromeDriver();
            driver.get("http://213.108.152.199/roundcube");

            String email = "testerselenium@selenium.waw.pl";
            String password = "tester.selenium1";

            driver.findElementByName("_user").sendKeys(email);
            driver.findElementByName("_pass").sendKeys(password);
            driver.findElementById("rcmloginsubmit").click();

            for (int i = 0; i < 3; i++) {


                czekajka(driver, "//span[@title='trener@selenium.waw.pl']");
                driver.findElementById("rcmbtn107").click();
                czekajka(driver, "//textarea[@name='_to']");

                driver.findElementByName("_to").sendKeys(email);
                driver.findElementByName("_subject").sendKeys("Temat maila " + i);
                driver.findElementByName("_message").sendKeys("Treść wiadomości");

                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("c:\\Folder\\selscreenshot" + i + ".png"));

                driver.findElementById("rcmbtn107").click();
            }

            Thread.sleep(5000);
            driver.quit();
    }

    //Funkcja Czekajaca
    public static void czekajka(WebDriver driver2, String xpath) {
        WebDriverWait wait = new WebDriverWait(driver2, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

}
