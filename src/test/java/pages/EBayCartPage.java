package pages;

import static org.testng.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.SeleniumHelper;


public class EBayCartPage extends SeleniumHelper {

     //Cartpage Elements
     public static WebElement getProductQuantityCartpageDropdown() {
        return wait.get().until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@data-test-id='qty-dropdown']")));
    }

    public static WebElement getPriceProduct1Cart() {
        return wait.get().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".total-row:nth-child(2) > .text-display-span > span > span")));
    }

    public static WebElement getPriceItemsQty2() {
        return wait.get().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div/span/span/span")));
    }


    //Cartpage methods

    public static void verifyQtyDropdown(){
    
        //Verify that in the Qty Drop Down List the quantity is “2”
        Select quantityDropdown = new Select(getProductQuantityCartpageDropdown());

        String actualQuantity = quantityDropdown.getFirstSelectedOption().getText();
        String expectedQuantity = "2";
        if(actualQuantity.equals(expectedQuantity)) {
            logger.info("The Quantity of the product in the Qty Drop Down on Cartpage is the same as on PDP: actual - {}, expected: {}", actualQuantity, expectedQuantity);
        } else {
            logger.info("The Quantity of the product in the Qty Drop Down on Cartpage is NOT the same as on PDP - actual: {}, expected: {}", actualQuantity, expectedQuantity);
        }

        //Display the price of the product for Quantity of 1 and 2. The price of the products on PDP/Cart can display in AU$ and in GBP instead only in US$
        
        String actualPrice = getPriceProduct1Cart().getText().replaceAll("[^\\d.,]", "");
        actualPrice = actualPrice.replace(",", ".");
        logger.info("Price for 2 products on Cartpage is: {}", actualPrice);
        String priceProduct1PDP = EBayPLP.getPriceProduct1();
        priceProduct1PDP = priceProduct1PDP.replace(",", ".");
        logger.info("Price for 1 product on PDP/PLP is: {}", priceProduct1PDP);
    }


    public static void verifyPriceQty2Products(){

        //Verify that the price is displayed for “2” items
        getPriceItemsQty2().getText();

        String expectedNumberItemsToPay = "Items (2)";
        logger.info(expectedNumberItemsToPay);
        String actualNumberItemsToPay = getPriceItemsQty2().getText();
        logger.info(actualNumberItemsToPay);
        assertEquals(expectedNumberItemsToPay, actualNumberItemsToPay, "Number of items in Cart is NOT as expected"); 
    }
    
}

