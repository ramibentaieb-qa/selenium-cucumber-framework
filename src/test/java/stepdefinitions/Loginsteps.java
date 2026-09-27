package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

import java.time.Duration;

public class Loginsteps {
    private WebDriver driver;
    //POM
    private LoginPage loginPage;

    @Before
    public void SetUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--diasbla-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        //here I initiate page object
        loginPage = new LoginPage(driver);
    }

    @Given("the user is on the SauceDemo login page")
    public void navigateToLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("the user enters username {string} and password {string}")
    public void enterCredentials(String username, String password) {
//        driver.findElement(By.id("user-name")).sendKeys(username);
//        driver.findElement(By.id("password")).sendKeys(password);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("clicks on the login button")
    public void clickLoginButton() {
//        driver.findElement(By.id("login-button")).click();

        loginPage.clickLoginButton();
    }

    @Then("the user should see {string}")
    public void VerifyLogin(String expectedResult) {
//        if (expectedResult.contains("Products")) {
//            String title = driver.findElement(By.className("title")).getText();
//            Assert.assertEquals(expectedResult, title);
//        } else {
//            String errorMessage = driver.findElement(By.cssSelector("#login_button_container h3")).getText();
//            Assert.assertTrue(errorMessage.contains("Epic sadface"));
//        }

        if (expectedResult.contains("Products")) {
            Assert.assertEquals(expectedResult, loginPage.getPageTitle());
        } else  {
            Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface"));
        }

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }




















}
