package pages;

import commonElements.eBayCommonElements;
import utils.SeleniumHelper;

public class EBayHomepage extends SeleniumHelper {

    
    public static void acceptAllCookies() {
        xJs("arguments[0].click();", eBayCommonElements.getCookiesConsentButton());
    }

    //Select from drop down list “Toys”
    public static void navigateToysCategory() {
        getActions().moveToElement(eBayCommonElements.getHomeAndGardenCategory()).perform();
        eBayCommonElements.getToysCategory().click();
    }




}
