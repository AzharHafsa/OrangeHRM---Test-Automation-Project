package SauceDemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckoutOrder {
    //Global Variables
    String baseURL="https://www.saucedemo.com";
    WebDriver driver;
    String expectedResult;
    String actualResult;
    List<Double> doubleListPrice = new ArrayList<>();


    @BeforeTest
    public void beforeTestMethod() throws InterruptedException {
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

        //click checkout button
        driver.findElement(By.id("checkout")).click();

        //Enter user details
        //Enter name for first name
        driver.findElement(By.id("first-name")).sendKeys("Hafsa");

        //Enter name for last name
        driver.findElement(By.id("last-name")).sendKeys("Azhar");

        //Enter name for postal code
        driver.findElement(By.id("postal-code")).sendKeys("373");


        //wait 2 secs
        Thread.sleep(2000);

        //click continue button
        driver.findElement(By.id("continue")).click();

        System.out.println("========================== < Test Case No: TC005 (Checkout Order and Place Order) > ==========================");
    }

    //Sub Test Case: STC001 - Check whether the correct product detail is displayed
    @Test(priority = 1)
    public void checkProductName(){
        System.out.println("-------------------------- < Sub Test Case: STC001 > --------------------------");
        System.out.println("* Test Scenario: Check whether the correct product detail is displayed");

        expectedResult="Sauce Labs Backpack";
        actualResult= driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();

        if (expectedResult.equals(actualResult)){
            System.out.println("* Test Status: Pass");
        }else {
            System.out.println("* Test Status: Fail");
        }
    }

    //Sub Test Case: STC002 - Check total price calculation
    @Test(priority = 2)
    public void totalPriceCalculation(){
        System.out.println("-------------------------- < Sub Test Case: STC002 > --------------------------");
        System.out.println("* Test Scenario: Check total price calculation");

        //Get product price
        String productPriceText= driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]")).getText();

        // Use regex to extract double numbers from the product price string and assign to list
        doubleListPrice = extractDoubleNumbers(productPriceText);
        System.out.println("Product Price: "+ doubleListPrice.get(0));

        //Get tax amount
        String taxText= driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]")).getText();

        // Use regex to extract double numbers from the product tax string and assign to list
        doubleListPrice = extractDoubleNumbers(taxText);
        System.out.println("Tax Price: "+ doubleListPrice.get(1));

        //expected total price
        double expectedTotalPrice= doubleListPrice.get(0) + doubleListPrice.get(1);
        System.out.println("Expected Total Price: "+ expectedTotalPrice);

        //Get actual total price
        String actualPriceText= driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]")).getText();

        // Use regex to extract double numbers from the product tax string and assign to list
        doubleListPrice = extractDoubleNumbers(actualPriceText);
        System.out.println("Actual Total Price: "+ doubleListPrice.get(2));


        //Compare expected prices with actual price
        if(Objects.equals(expectedTotalPrice, doubleListPrice.get(2))){
            System.out.println("* Test Status: Pass");
        }else {
            System.out.println("* Test Status: Fail");
        }
    }

    //Sub Test Case: STC003 - Check whether after clicking on finish button it navigates to complete page
    @Test(priority = 3)
    public void clickFinishBtn() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC003 > --------------------------");
        System.out.println("* Test Scenario: Check whether after clicking on finish button it navigates to complete page");

        //Wait 3 secs
        Thread.sleep(3000);

        //Click finish button
        driver.findElement(By.id("finish")).click();

        //check it navigates to complete page
        expectedResult="Checkout: Complete!";
        actualResult=driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

        if(actualResult.equals(expectedResult)){
            System.out.println("* Test Status: Pass");
        }else {
            System.out.println("* Test Status: Fail");
        }
    }

    //Sub Test Case: STC004 - Check whether after clicking on back home button it navigates to products page
    @Test(priority = 4)
    public void clickBackHomeBtn() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC004 > --------------------------");
        System.out.println("* Test Scenario: Check whether after clicking on back home button it navigates to products page");

        //Wait 3 secs
        Thread.sleep(3000);

        //Click finish button
        driver.findElement(By.id("back-to-products")).click();

        //check it navigates to products page
        expectedResult="Products";
        actualResult=driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

        if(actualResult.equals(expectedResult)){
            System.out.println("* Test Status: Pass");
        }else {
            System.out.println("* Test Status: Fail");
        }
    }


    @AfterTest
    public void afterTestMethod(){
        System.out.println("-------------------------- < Checkout and place order tests are completed > --------------------------");
    }

    //Supporting Methods
    public List<Double> extractDoubleNumbers(String input) {
        Pattern pattern = Pattern.compile("\\b\\d+\\.\\d+\\b");
        Matcher matcher = pattern.matcher(input);

        // Find all matches and store them in a list

        while (matcher.find()) {
            // Add the matched double number to the list
            doubleListPrice.add(Double.valueOf(matcher.group()));
        }

        return doubleListPrice;
    }

}
