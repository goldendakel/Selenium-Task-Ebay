package stepDefinitions;

import io.cucumber.java.en.When;
import pages.EBayPDP;
import utils.SeleniumHelper;

public class EBayPDPSteps extends SeleniumHelper {
    

    //Switch view from “Description” to “Shipping and payments”
    @When("the user navigates to the 'Shipping, returns, and payments' tab on PDP")
    public void the_user_navigates_to_the_shipping_tab_on_PDP() {
        EBayPDP.navigatesShippingTabPDP();
    }

    //Select quantity “2”
    @When("the user updates the quantity of the product on PDP")
        public void the_user_updates_the_quantity_of_the_product_on_PDP() {
        EBayPDP.updateQtyProductPDP();
        }

    //Click “Add to cart ”
    @When("the user adds the product to Cart")
    public void the_user_adds_the_product_to_Cart() {
        EBayPDP.addToCart();
    }
    
}
