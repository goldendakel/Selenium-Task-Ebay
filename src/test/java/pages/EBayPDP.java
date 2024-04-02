package pages;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.SeleniumHelper;


public class EBayPDP extends SeleniumHelper{

    //PDP Elements
    public static WebElement getTitleProduct1() {
        return wait.get().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='ux-textspans ux-textspans--BOLD']")));
    } 

    public static Select getDropdownStickers() {
        return new Select(wait.get().until(ExpectedConditions.elementToBeClickable(By.className("x-msku__select-box"))));
    } 

    public static WebElement getPriceProduct1PDP() {
        return wait.get().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='x-price-primary']//span[1]")));
    }

    public static WebElement getShippingReturnsPaymentsTab() {
       return wait.get().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div/div/div[2]/span/button/span")));
    }

    public static WebElement getChangeCountryDropdown() {
        return wait.get().until(ExpectedConditions.elementToBeClickable(By.id("shCountry")));
    }

    public static WebElement getProductQuantityPDPField() {
        return wait.get().until(ExpectedConditions.elementToBeClickable(By.id("qtyTextBox")));
    }

    public static WebElement addToCartButton() {
        return wait.get().until(ExpectedConditions.elementToBeClickable(By.cssSelector(".x-atc-action .ux-call-to-action__text")));
    }

    
    //PDP methods

    public static void navigatesShippingTabPDP() {
       
        //Handle the PDP opening on a new tab
        ArrayList<String> chromeTabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(chromeTabs.get(1));

        //Verify that the title of the item contains “Monopoly”
        String actualText = getTitleProduct1().getText();
        if (actualText.contains("Monopoly")) {
            logger.info("Product contains 'Monopoly' in its title on PDP: {}", actualText);
        } else {
            logger.info("Product does not contain 'Monopoly' in its title on PDP: {}", actualText);
        }

        //Choose stickers if available. Some products require choosing Stickers to proceed. Handle these cases with exception
        try {
            getDropdownStickers().selectByIndex(1);
        } catch (Exception e){
            e.printStackTrace();
        }

        //Verify that the price is the same as on the first page
        String actualPrice = getPriceProduct1PDP().getText().replaceAll("[^\\d.,]", "");
        if (actualPrice.equals(EBayPLP.getPriceProduct1())) {
            logger.info("The price of the product is the same on PDP as on PLP: {}", actualPrice);
        } else {
            logger.info("The price of the product is NOT the same on PDP as on PLP: {}", actualPrice);
        }

        //The 'Shipping, returns, and payments' tab doesn't always display! Handle the cases of the tab NOT displaying with exception
        //Switch view from “Description” to “Shipping and payments”
        try{
            getShippingReturnsPaymentsTab().click();
    
            //verify that Bulgaria exists in the country drop down list
            Select countrySelectorField = new Select(getChangeCountryDropdown());
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
        public static void updateQtyProductPDP() {
        try {
            getProductQuantityPDPField().clear();
            getProductQuantityPDPField().sendKeys("2");
        } catch (Exception e) {
             e.printStackTrace();
        }
    }

        //Click “Add to cart ”
        public static void addToCart() {
            addToCartButton().click();
        }

}
