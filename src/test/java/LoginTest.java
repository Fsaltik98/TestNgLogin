import Utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriverSolution("chrome");
        driver.get("https://qa.hectorware.com/index.php/login");

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void LoginWithValidInput() throws InterruptedException {

        String email = "Employee1";
        String password = "Employee123";
        driver.findElement(By.id("user")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit-form")).click();
        Thread.sleep(5000);

        String name = driver.findElement(By.cssSelector("span[title='Mike Smith']")).getAttribute("title");
        String expectedName = "Mike Smith";

        Assert.assertEquals(name, expectedName);
    }

    @Test
    public void LoginWithInValidInput() throws InterruptedException {

        String email = "Ferhat";
        String password = "Saltik321";
        driver.findElement(By.id("user")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit-form")).click();
        Thread.sleep(3000);

        String error = driver.findElement(By.xpath("//p[contains(text(),'Wrong username')]")).getText();
        String expectedError = "Wrong username or password.";

        Assert.assertEquals(error, expectedError);
    }


    @Ignore
    @Test
    public void LoginWithEmptyInputs() throws InterruptedException {

        String email = "";
        String password = "";
        driver.findElement(By.id("user")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit-form")).click();
        Thread.sleep(3000);

        String error = driver.findElement(By.xpath("//p[contains(text(),'Wrong username')]")).getText();
        String expectedError = "Wrong username or password.";

        Assert.assertEquals(error, expectedError);
    }

    @Ignore
    @Test
    public void LoginWithNull() throws InterruptedException {

        String email = null;
        String password = null;
        driver.findElement(By.id("user")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit-form")).click();
        Thread.sleep(3000);

        String error = driver.findElement(By.xpath("//p[contains(text(),'Wrong username')]")).getText();
        String expectedError = "Wrong username or password.";

        Assert.assertEquals(error, expectedError);
    }

    @Test
    public void CheckIfTextBoxIsEnabled(){

        boolean isEnabled =  driver.findElement(By.id("user")).isEnabled();
        Assert.assertTrue(isEnabled);

        boolean isEnabledPassword = driver.findElement(By.id("password")).isEnabled();
        Assert.assertTrue(isEnabledPassword);
    }



}
