package testPages;

import static org.testng.Assert.*;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.cucumber.java.en.Given;

public class EBayHomepage extends SeleniumHelper {

    @Given("the user is on eBay homepage {string}")
    public void the_user_is_on_eBay_homepage(String userKey) {

        //Navigate to https://ebay.com/
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/test/resources/componentsJson/EBayURLs.json"));

            JSONObject jsonObject = (JSONObject) obj;
            JSONObject userData = (JSONObject) jsonObject.get(userKey);
            getDriver().get((String) userData.get("eBay_Homepage_URL"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Verify that the page is correct and opened
        String expectedUrl = "https://www.ebay.com/";
        String actualUrl = getDriver().getCurrentUrl();
        logger.info("actual URL is: {}", actualUrl);
        logger.info("expected URL is: {}", expectedUrl);
        assertEquals(actualUrl, expectedUrl, "Page URL doesn't match expected URL");
    }


    @Given("the user accepts all cookies")
    public void the_user_accepts_all_cookies() {
       WebElement cookiesConsentButton = wait.get().until(ExpectedConditions.elementToBeClickable(By.id("gdpr-banner-accept")));
       xJs("arguments[0].click();", cookiesConsentButton);
    }


}
