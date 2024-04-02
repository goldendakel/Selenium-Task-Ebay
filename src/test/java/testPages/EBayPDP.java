package testPages;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import io.cucumber.java.en.When;


public class EBayPDP extends SeleniumHelper{


    @When("the user navigates to the 'Shipping, returns, and payments' tab on PDP")
    public void the_user_navigates_to_the_tab_on_PDP() {
       
        //Handle the PDP opening on a new tab
        ArrayList<String> chromeTabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(chromeTabs.get(1));

        //Verify that the title of the item contains “Monopoly”
        WebElement titleProduct1 = wait.get().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='ux-textspans ux-textspans--BOLD']")));
        String actualText = titleProduct1.getText();
        if (actualText.contains("Monopoly")) {
            logger.info("Product contains 'Monopoly' in its title on PDP: {}", actualText);
        } else {
            logger.info("Product does not contain 'Monopoly' in its title on PDP: {}", actualText);
        }

        //Choose stickers if available. Some products require choosing Stickers to proceed. Handle these cases with exception
        try {
            Select dropdown = new Select(wait.get().until(ExpectedConditions.elementToBeClickable(By.className("x-msku__select-box"))));

            dropdown.selectByIndex(1);
        } catch (Exception e){
            e.printStackTrace();
        }

        //Verify that the price is the same as on the first page
        WebElement priceProduct1PDP = wait.get().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='x-price-primary']//span[1]")));
        String actualPrice = priceProduct1PDP.getText().replaceAll("[^\\d.,]", "");
        if (actualPrice.equals(EBayPLP.getPriceProduct1())) {
            logger.info("The price of the product is the same on PDP as on PLP: {}", actualPrice);
        } else {
            logger.info("The price of the product is NOT the same on PDP as on PLP: {}", actualPrice);
        }

        //The 'Shipping, returns, and payments' tab doesn't always display! Handle the cases of the tab NOT displaying with exception
        //Switch view from “Description” to “Shipping and payments”
        try{
            WebElement shippingReturnsPaymentsTab = wait.get().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div/div/div[2]/span/button/span")));
            shippingReturnsPaymentsTab.click();
    
            //verify that Bulgaria exists in the country drop down list
            WebElement changeCountryDropdown = wait.get().until(ExpectedConditions.elementToBeClickable(By.id("shCountry")));
            Select countrySelectorField = new Select(changeCountryDropdown);
            String actualCountry = countrySelectorField.getFirstSelectedOption().getText();
            String expectedCountry = "Bulgaria";
            if(actualCountry.equals(expectedCountry)) {
                logger.info("Bulgaria exists in the country drop down list: {}", actualCountry);
            } else {
                logger.info("Bulgaria does NOT exist in the country drop down list");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
        //In some cases the first product changes and has only 1 left in stock! Handle the cases of quantity NOT updatable with exception
        //Select quantity “2”
        @When("the user updates the quantity of the product on PDP")
        public void the_user_updates_the_quantity_of_the_product_on_PDP() {
            try {
            WebElement productQuantityPDPField = wait.get().until(ExpectedConditions.elementToBeClickable(By.id("qtyTextBox")));
            productQuantityPDPField.clear();
            productQuantityPDPField.sendKeys("2");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    //Click “Add to cart ”
    @When("the user adds the product to Cart")
    public void the_user_adds_the_product_to_Cart() {
        WebElement addToCartButton = wait.get().until(ExpectedConditions.elementToBeClickable(By.cssSelector(".x-atc-action .ux-call-to-action__text")));
        addToCartButton.click();
    }


}
