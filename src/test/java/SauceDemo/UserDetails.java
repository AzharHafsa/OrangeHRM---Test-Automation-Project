package SauceDemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserDetails{
    //Global variables
    String baseURL="https://www.saucedemo.com";

    WebDriver driver;
    String expectedResult;
    String actualResult;

    @BeforeTest
    public  void beforeTestMethods() throws InterruptedException {
        driver= new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        driver.get(baseURL);


        //Login to the system
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();

        //Add item to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        //Click cart icon
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

        //wait 2 secs till item added to cart
        Thread.sleep(2000);

        System.out.println("========================== < Test Case No: TC004 (Enter User Details) > ==========================");
    }

    //Sub Test Case: STC001 - Check can navigate to your information page when click checkout button.
    @Test(priority = 1)
    public void verifyYourInfoPage() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC001 > --------------------------");
        System.out.println("* Test Scenario: Verify whether the user can navigate to your information page after clicking on checkout button ");

        //click checkout button
        driver.findElement(By.id("checkout")).click();

        //check correct page
        expectedResult = "Checkout: Your Information";
        actualResult = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

        if(expectedResult.equals(actualResult)){
            System.out.println("* Test Status: Pass");
        }else {
            System.out.println("* Test Status: Fail");
        }
    }

    //Sub Test Case: STC002 - Check whether when filling all text boxes and press continue it navigates to next page
    @Test(priority = 2)
    public  void enterAllDetails() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC002 > --------------------------");
        System.out.println("* Test Scenario: Check whether when filling all text boxes and press continue it navigates to next page ");

        //enter first name
        enterFname();

        //enter last name
        enterLname();

        //enter postal code
        enterPostCode();

        //click continue button
        clickContinueBtn();

        //check correct page navigation
        expectedResult="Checkout: Overview";
        actualResult = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

        if (expectedResult.equals(actualResult)){
            System.out.println("* Test Status: Pass");
        }else {
            System.out.println("* Test Status: Fail");
        }

        //back to user details page
        backToUserDetailsPage();
    }

    //Sub Test Case: STC003 - Check whether the error message is displayed for null first name
    @Test(priority = 3)
    public  void nullFname() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC003 > --------------------------");
        System.out.println("* Test Scenario: Check whether the error message is displayed for null first name");

        //enter last name
        enterLname();

        //enter postal code
        enterPostCode();

        //click continue button
        clickContinueBtn();

        //check correct error msg
        expectedResult="Error: First Name is required";
        actualResult = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();


        if (expectedResult.equals(actualResult)){
            System.out.println("* Test Status: Pass");
        }else {
            System.out.println("* Test Status: Fail");
        }

        //back to user details page
        backToUserDetailsPage();
    }

    //Sub Test Case: STC004 - Check whether the error message is displayed for null last name
    @Test(priority = 4)
    public  void nullLname() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC004 > --------------------------");
        System.out.println("* Test Scenario: Check whether the error message is displayed for null last name");

        //enter first name
        enterFname();

        //enter postal code
        enterPostCode();

        //click continue button
        clickContinueBtn();

        //check correct error msg
        expectedResult="Error: Last Name is required";
        actualResult = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();


        if (expectedResult.equals(actualResult)){
            System.out.println("* Test Status: Pass");
        }else {
            System.out.println("* Test Status: Fail");
        }

        //back to user details page
        backToUserDetailsPage();
    }

    //Sub Test Case: STC005 - Check whether the error message is displayed for null passcode
    @Test(priority = 5)
    public  void nullPostCode() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC005 > --------------------------");
        System.out.println("* Test Scenario: Check whether the error message is displayed for null passcode");

        //enter first name
        enterFname();

        //enter last name
        enterLname();

        //click continue button
        clickContinueBtn();

        //check correct error msg
        expectedResult="Error: Postal Code is required";
        actualResult = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();

        if (expectedResult.equals(actualResult)){
            System.out.println("* Test Status: Pass");
        }else {
            System.out.println("* Test Status: Fail");
        }

        //back to user details page
        backToUserDetailsPage();
    }

    @AfterTest
    public void afterTestMethod(){
        System.out.println("-------------------------- < User Details tests are completed > --------------------------");
    }

    //Supporting Methods
    public void enterFname() throws InterruptedException {
        //Enter name for first name
        driver.findElement(By.id("first-name")).sendKeys("Hafsa");

        //wait for 2 secs
        Thread.sleep(2000);
    }

    public void enterLname() throws InterruptedException {
        //Enter name for last name
        driver.findElement(By.id("last-name")).sendKeys("Azhar");

        //wait for 2 secs
        Thread.sleep(2000);
    }

    public void enterPostCode() throws InterruptedException {
        //Enter name for postal code
        driver.findElement(By.id("postal-code")).sendKeys("373");

        //wait for 2 secs
        Thread.sleep(2000);
    }

    public void clickContinueBtn() throws InterruptedException {
        //click continue button
        driver.findElement(By.id("continue")).click();

        //wait for 2 secs
        Thread.sleep(2000);
    }

    public void backToUserDetailsPage() throws InterruptedException {
        //click cancel button
        driver.findElement(By.id("cancel")).click();

        //click cart icon
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

        //click checkout button
        driver.findElement(By.id("checkout")).click();

        //wait for 2 secs
        Thread.sleep(2000);
    }
}
