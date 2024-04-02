package stepDefinitions;

import io.cucumber.java.en.When;
import pages.EBayPLP;
import utils.SeleniumHelper;

public class EBayPLPSteps extends SeleniumHelper {
    

    //Then search for Monopoly
    @When("the user searches for 'Monopoly'")
    public void the_user_searches_for_Monopoly() {
       EBayPLP.searchMonopoly();
    }

    //Then click on the first item in order to navigate to details page
    @When("the user selects first of Monopoly Products")
    public void the_user_selects_first_of_Monopoly_Products() {
        EBayPLP.selectMonopolyProduct();
    }
}
