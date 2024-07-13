package SauceDemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ViewProductItem {
    //Global variables
    WebDriver driver;
    String BaseURL="https://www.saucedemo.com";
    String expectedResult;
    String actualResult;

    @BeforeTest
    public void beforeTestMethod() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BaseURL);

        //Enter a correct username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        //Enter a correct password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        //wait for 2 secs till the username and password loads
        Thread.sleep(2000);

        //click login button
        driver.findElement(By.id("login-button")).click();

        System.out.println("========================== < Test Case No: TC002 (View Product List) > ==========================");
    }

    //Sub Test Case: STC001 - Testing click on product's name
    @Test(priority = 1)
    public void clickOnProductName() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC001 > --------------------------");
        System.out.println("* Test Scenario: Verifying clicking on product's name");

        //Get the product's name which should be open in a new page
        expectedResult=driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();

        //wait for 3 secs
        Thread.sleep(3000);

        //click on product's name
        driver.findElement(By.id("item_4_title_link")).click();

        //Verify whether the clicked product is displayed
        actualResult=driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")).getText();

        //wait for 2 secs till load
        Thread.sleep(2000);

        if(actualResult.equals(expectedResult)){
            System.out.println("* Test Status: Pass");
        }else{
            System.out.println("* Test Status: Fail");
        }

        //Click Back to products button (Navigate to product page)
        clickBackToProduct();
    }

    //Sub Test Case: STC002 - Testing click on product's image
    @Test(priority = 2)
    public void clickOnProductImage() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC002 > --------------------------");
        System.out.println("* Test Scenario: Verifying clicking on product's image");

        //Get the product's name which should be open in a new page
        expectedResult=driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div")).getText();

        //click on product's image
        driver.findElement(By.xpath("//*[@id=\"item_5_img_link\"]/img")).click();


        //Verify whether the clicked product is displayed
        actualResult=driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")).getText();

        //wait for 2 secs till load
        Thread.sleep(2000);

        if(actualResult.equals(expectedResult)){
            System.out.println("* Test Status: Pass");
        }else{
            System.out.println("* Test Status: Fail");
        }

        //Click Back to products button (Navigate to product page)
        clickBackToProduct();
    }

    //Sub Test Case: STC003 - Verifying item details are same as product page details
    @Test(priority = 3)
    public void checkItemDetails() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC003 > --------------------------");
        System.out.println("* Test Scenario: Verifying item details are same as product page details");

        //Get the product's name in the product page
        String expectedName=driver.findElement(By.xpath("//*[@id=\"item_3_title_link\"]/div")).getText();

        //Get the product's description in the product page
        String expectedDescription=driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[6]/div[2]/div[1]/div")).getText();

        //Get the product's price in the product page
        String expectedPrice=driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[6]/div[2]/div[2]/div")).getText();

        //wait for 2 secs
        Thread.sleep(2000);

        //click on product's image
        driver.findElement(By.xpath("//*[@id=\"item_3_img_link\"]/img")).click();

        //Get the product's name in the product page
        String actualName=driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")).getText();

        //Get the product's description in the product page
        String actualDescription=driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]")).getText();

        //Get the product's price in the product page
        String actualPrice=driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[3]")).getText();

        //wait for 2 secs
        Thread.sleep(2000);

        if(actualName.equals(expectedName) && actualDescription.equals(expectedDescription) && actualPrice.equals(expectedPrice)){
            System.out.println("* Test Status: Pass");
        }else{
            System.out.println("* Test Status: Fail");
        }

        //Click Back to products button (Navigate to product page)
        clickBackToProduct();

    }


    @AfterTest
    public void afterTestMethod(){
        System.out.println("-------------------------- < View product items tests are completed > --------------------------");
    }

    //Supporting Methods
    public void clickBackToProduct() throws InterruptedException {
        driver.findElement(By.id("back-to-products")).click();

        //wait for 2 secs till the product page loads
        Thread.sleep(2000);
    }
}
