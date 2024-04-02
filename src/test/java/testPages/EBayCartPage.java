package testPages;

import static org.testng.Assert.*;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import io.cucumber.java.en.Then;


public class EBayCartPage extends SeleniumHelper {


    @Then("the user verifies the quantity of the product on Cart page {string}")
    public void the_user_verifies_the_quantity_of_the_product_on_Cart_page(String userKey) {
        
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/test/resources/componentsJson/EBayURLs.json"));

            JSONObject jsonObject = (JSONObject) obj;
            JSONObject userData = (JSONObject) jsonObject.get(userKey);
            getDriver().get((String) userData.get("eBay_Cartpage_URL"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Verify that you are on “https://cart.payments.ebay.com/”
        String expectedUrl = "https://cart.payments.ebay.com/";
        String actualUrl = getDriver().getCurrentUrl();
        logger.info("actual URL is: {}", actualUrl);
        logger.info("expected URL is: {}", expectedUrl);
        assertEquals(actualUrl, expectedUrl, "Page URL doesn't match expected URL");
    
        //Verify that in the Qty Drop Down List the quantity is “2”
        WebElement productQuantityCartpageDropdown = wait.get().until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@data-test-id='qty-dropdown']")));
        Select quantityDropdown = new Select(productQuantityCartpageDropdown);

        String actualQuantity = quantityDropdown.getFirstSelectedOption().getText();
        String expectedQuantity = "2";
        if(actualQuantity.equals(expectedQuantity)) {
            logger.info("The Quantity of the product in the Qty Drop Down on Cartpage is the same as on PDP: actual - {}, expected - {}", actualQuantity, expectedQuantity);
        } else {
            logger.info("The Quantity of the product in the Qty Drop Down on Cartpage is NOT the same as on PDP: actual - {}, expected - {}", actualQuantity, expectedQuantity);
        }

        //Display the price of the product for Quantity of 1 and 2. The price of the products on PDP/Cart can display in AU$ and in GBP instead only in US$
        WebElement priceProduct1Cart = wait.get().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".total-row:nth-child(2) > .text-display-span > span > span")));
        
        String actualPrice = priceProduct1Cart.getText().replaceAll("[^\\d.,]", "");
        actualPrice = actualPrice.replace(",", ".");
        logger.info("Price for 2 products on Cartpage is: {}", actualPrice);
        String priceProduct1PDP = EBayPLP.getPriceProduct1();
        priceProduct1PDP = priceProduct1PDP.replace(",", ".");
        logger.info("Price for 1 product on PDP/PLP is: {}", priceProduct1PDP);

        //Verify that the price is displayed for “2” items
        WebElement priceItemsQty2 = wait.get().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div/div/div/div/div/span/span/span")));
        priceItemsQty2.getText();

        String expectedNumberItemsToPay = "Items (2)";
        logger.info(expectedNumberItemsToPay);
        String actualNumberItemsToPay = priceItemsQty2.getText();
        logger.info(actualNumberItemsToPay);
        assertEquals(expectedNumberItemsToPay, actualNumberItemsToPay, "Number of items in Cart is NOT as expected"); 
    }

}

