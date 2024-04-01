package testPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.When;

public class EBayPDP extends SeleniumHelper{


    @When("the user navigates to the 'Shipping, returns, and payments' tab on PDP")
    public void the_user_navigates_to_the_tab_on_PDP() {
        WebElement titleProduct1 = wait.get().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Monopoly Go 5 star Sticker/Card -  Golden Blitz Event - Play the Game']")));
        String actualText = titleProduct1.getText();
        if (actualText.contains("Monopoly")) {
            logger.info("Product contains 'Monopoly' in its title on PDP: {}", actualText);
        } else {
            logger.info("Product does not contain 'Monopoly' in its title on PDP: {}", actualText);
        }


        //WebElement shippingReturnsPaymentsTab = wait.get().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div/div/div[2]/span/button/span")));
        //xJs("arguments[0].click();", shippingReturnsPaymentsTab);
    }

    @When("the user adds the product to Cart")
    public void the_user_adds_the_product_to_Cart() {
        xJs("window.scrollTo(0, document.body.scrollHeight / 2)");
        WebElement addToCartButton = wait.get().until(ExpectedConditions.elementToBeClickable(By.cssSelector(".x-atc-action .ux-call-to-action__text")));
        xJs("arguments[0].click();", addToCartButton);
    }
}
