package SauceDemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Login {
    //Global Variables
    String BaseURL="https://www.saucedemo.com/";
    WebDriver driver;
    String expectedOutput;
    String actualOutput;

    @BeforeTest
    public void beforeTestMethod() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("========================== < Test Case No: TC001 (Login Functionality) > ==========================");
    }

    //Sub Test Case: STC001 - Testing Login functionality with Correct Credential
    @Test(priority = 1)
    public void correctCredentialLoginTest() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC001 > --------------------------");
        System.out.println("* Test Scenario: Testing Login functionality with Correct Credential");
        //Load login page
        loadLoginPage();

        //Enter correct username
        enterCorrectUserName();

        //Enter correct password
        enterCorrectPassword();

        //Click Login button
        clickLoginButton();

        //Wait 3 secs till product page loads
        Thread.sleep(3000);

        //Verify whether it navigates to product page Correctly
        expectedOutput="Products";
        actualOutput = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        if(actualOutput.equals(expectedOutput)){
            System.out.println("* Test Status: Pass");
        }else{
            System.out.println("* Test Status: Fail");
        }

    }

    //Sub Test Case: STC002 - Testing Login functionality with incorrect Credential
    @Test(priority = 2)
    public void incorrectCredentialLoginTest() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC002 > --------------------------");
        System.out.println("* Test Scenario: Testing Login functionality with incorrect Credential");

        //Navigate to login page
        loadLoginPage();

        //Enter correct username
        enterCorrectUserName();

        //Enter incorrect password
        driver.findElement(By.id("password")).sendKeys("ItsWrongPassword");

        //click login button
        clickLoginButton();

        //wait for 3 secs till page load
        Thread.sleep(3000);

        //Verify whether correct error message displays
        expectedOutput="Epic sadface: Username and password do not match any user in this service";
        actualOutput= driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();

        if(actualOutput.equals(expectedOutput)){
            System.out.println("* Test Status: Pass");
        }else{
            System.out.println("* Test Status: Fail");
        }
    }


    //Sub Test Case: STC003 - Testing Login functionality when enter username only
    @Test(priority = 3)
    public  void onlyUsernameLogin() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC003 > --------------------------");
        System.out.println("* Test Scenario: Testing Login functionality when enter username only");

        //Navigate to login page
        loadLoginPage();

        //enter correct username
        enterCorrectUserName();

        //click login button
        clickLoginButton();

        //Verify whether correct error message displays
        expectedOutput="Epic sadface: Password is required";
        actualOutput= driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]")).getText();

        if(actualOutput.equals(expectedOutput)){
            System.out.println("* Test Status: Pass");
        }else{
            System.out.println("* Test Status: Fail");
        }

    }

    //Sub Test Case: STC004 - Testing Login functionality when enter username only
    @Test(priority = 4)
    public void onlyPasswordLogin() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC004 > --------------------------");
        System.out.println("* Test Scenario: Testing Login functionality when enter password only");

        //Navigate to login page
        loadLoginPage();

        //enter correct password
        enterCorrectPassword();

        //click login button
        clickLoginButton();

        //Verify whether correct error message displays
        expectedOutput="Epic sadface: Username is required";
        actualOutput= driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]")).getText();

        if(actualOutput.equals(expectedOutput)){
            System.out.println("* Test Status: Pass");
        }else{
            System.out.println("* Test Status: Fail");
        }

    }

    //Sub Test Case: STC005 - Testing Login functionality when enter NULL values for username and password
    @Test(priority = 5)
    public void nullUsernamePasswordLogin() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC005 > --------------------------");
        System.out.println("* Test Scenario: Testing Login functionality when enter NULL values for username and password");

        //Navigate to login page
        loadLoginPage();

        //click login button
        clickLoginButton();

        //Verify whether correct error message displays
        expectedOutput="Epic sadface: Username is required";
        actualOutput= driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]")).getText();

        if(actualOutput.equals(expectedOutput)){
            System.out.println("* Test Status: Pass");
        }else{
            System.out.println("* Test Status: Fail");
        }

    }


    @AfterTest
    public void afterTestMethods(){
        System.out.println("-------------------------- < Login tests are completed > --------------------------");
    }


    //Supporting methods
    public void loadLoginPage() throws InterruptedException {
        driver.get(BaseURL);

        //wait for 2 secs till the page loads
        Thread.sleep(2000);
    }

    public void enterCorrectUserName() throws InterruptedException {
        //Enter a correct username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        //wait for 2 secs till page loads
        Thread.sleep(2000);
    }

    public void enterCorrectPassword() throws InterruptedException {
        //Enter a correct password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        //wait for 2 secs till page loads
        Thread.sleep(2000);
    }

    public void clickLoginButton() throws InterruptedException {
        //click login button
        driver.findElement(By.id("login-button")).click();
        //wait for 2 secs till page loads
        Thread.sleep(2000);
    }
}
