import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;




public class Registration {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://selenium.waw.pl/");

        String username = "testerselenium";
        String password = "tester.selenium1";

        driver.findElementById("rcmloginuser").sendKeys(username);
        driver.findElementById("password").sendKeys(password);
        driver.findElementById("confirm_password").sendKeys(password);

        new Select(driver.findElementByName("birthday-day")).selectByIndex(1);
        new Select(driver.findElementByName("birthday-month")).selectByVisibleText("January");
        new Select(driver.findElementByName("birthday-year")).selectByVisibleText("2000");

        driver.findElementByCssSelector("input[value='M']").click();
        driver.findElementByName("phone").sendKeys("123456789");
        driver.findElementById("rcmloginsubmit").click();

        String message = driver.findElementByCssSelector(".notice").getText();

        if (message.equalsIgnoreCase("Stworzono użytkownika "+username+"@selenium.waw.pl")) {
            System.out.println("Sukces!");
        }
        else System.out.println("Coś poszło nie tak: "+message);

    }
}
