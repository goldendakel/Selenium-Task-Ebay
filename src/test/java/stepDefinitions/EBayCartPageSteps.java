package stepDefinitions;

import static org.testng.Assert.assertEquals;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import io.cucumber.java.en.Then;
import pages.EBayCartPage;
import utils.SeleniumHelper;

public class EBayCartPageSteps extends SeleniumHelper {

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
        EBayCartPage.verifyQtyDropdown();

        //Verify that the price is displayed for “2” items
        EBayCartPage.verifyPriceQty2Products();
    }
}
