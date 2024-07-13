package SauceDemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class AddToCart {
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


        //Login to the system
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();

        System.out.println("========================== < Test Case No: TC003 (Add/Remove items to Cart) > ==========================");
    }

    //Sub Test Case: STC001 - Check whether the cart icon notifies when product item is added to cart
    @Test(priority = 1)
    public void itemAddCartNotification() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC001 > --------------------------");
        System.out.println("* Test Scenario: Check whether the cart icon notifies when product item is added to cart");

        //click "Add to cart" button
        clickAddToCart();

        //wait for 2 secs till the product added to cart
        Thread.sleep(2000);

        //Check whether the cart icon notifies
        int expectedCartItem = 1;
        int actualCartItem = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText());

        if (Objects.equals(actualCartItem, expectedCartItem)){
            System.out.println("* Test Status: Pass");
        }else{
            System.out.println("* Test Status: Fail");
        }
    }

    //Sub Test Case: STC002 - Check whether after clicking on “Add to cart”, button changes to “Remove”.
    @Test(priority = 2)
    public void changeToRemoveBtn() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC002 > --------------------------");
        System.out.println("* Test Scenario: Check whether after clicking on “Add to cart” button it changes to “Remove” ");

        expectedResult = "Remove";
        actualResult = driver.findElement(By.id("remove-sauce-labs-backpack")).getText();

        if (actualResult.equals(expectedResult)){
            System.out.println("* Test Status: Pass");
        }else{
            System.out.println("* Test Status: Fail");
        }
    }

    //Sub Test Case: STC003 - Check whether the product details are correct in "your cart" page
    @Test(priority = 3)
    public void checkCartDetails(){
        System.out.println("-------------------------- < Sub Test Case: STC003 > --------------------------");
        System.out.println("* Test Scenario: Check whether the product details are correct after adding to the cart");

        //click cart notification icon
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

        //Get the page name
        String expectedPage="Your Cart";
        String actualPage= driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

        //Get the product name in the cart
        String expectedProduct="Sauce Labs Backpack";
        String actualProduct = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();


        //Get the product price
        String expectedPrice= "29.99";
        String actualPrice=driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div")).getText();

        //Get the product description
        String expectedDes="carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
        String actualDes= driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[1]")).getText();

        //check all the details are matched with the expected outcome
        if(expectedPage.equals(actualPage) && expectedProduct.equals(actualProduct) && expectedPrice.equals(expectedPrice) && expectedDes.equals(actualDes)){
            System.out.println("* Test Status: Pass");
        }else{
            System.out.println("* Test Status: Fail");
        }
    }

    //Sub Test Case: STC004 - Check whether after clicking on “Remove” button the item is removed from the cart
    @Test(priority = 4)
    public void removeCartItem() throws InterruptedException {
        System.out.println("-------------------------- < Sub Test Case: STC004 > --------------------------");
        System.out.println("* Test Scenario: Check whether after clicking on “Remove” button the item is removed from the cart");

        //Click remove button
        clickRemoveBtn();

        //wait for 3 secs
        Thread.sleep(3000);

        //check item is removed
        //Idea - if product item is not listed,it is removed successfully
        //Find element and assign to list and check whether list is empty if empty no such element,then it is removed
        List<WebElement> elements = driver.findElements(By.id("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]"));
        //System.out.println(elements.size());
        if (elements.isEmpty()) {
            System.out.println("* Test Status: Pass");
        } else {
            System.out.println("* Test Status: Fail");
        }

    }

    //Sub Test Case: STC005 - Check whether the cart icon notifies when a product item is removed
    @Test(priority = 5)
    public void itemRemoveCartNotification(){
        System.out.println("-------------------------- < Sub Test Case: STC005 > --------------------------");
        System.out.println("* Test Scenario: Check whether the cart icon notifies when a product item is removed");

        //if the span tag for product count is not available - Then the added product has been removed
        List<WebElement> sets=  driver.findElements(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));

        if (sets.isEmpty()){
            System.out.println("* Test Status: Pass");
        }else{
            System.out.println("* Test Status: Fail");
        }

    }

    @AfterTest
    public void afterTestMethod(){
        System.out.println("-------------------------- < Add/Remove items to cart tests are completed > --------------------------");
    }

    //Supporting Methods
    public void loadProductPage() throws InterruptedException {
        driver.get("https://www.saucedemo.com/inventory.html");

        //wait for 2 secs till the page loads
        Thread.sleep(2000);
    }

    public void clickAddToCart() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        //wait 2secs
        Thread.sleep(2000);
    }

    public void clickRemoveBtn() throws InterruptedException {
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        //wait 2secs
        Thread.sleep(2000);
    }
}
