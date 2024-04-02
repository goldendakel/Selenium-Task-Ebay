package commonElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.SeleniumHelper;

public class eBayCommonElements extends SeleniumHelper{

    public static WebElement getHomeAndGardenCategory() {
        return wait.get().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-currenttabindex='7']//a")));
    }    

    public static WebElement getToysCategory() {
       return wait.get().until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@href='https://www.ebay.com/b/Toys-Hobbies/220/bn_1865497'])[2]")));
    } 
    
    public static WebElement getCookiesConsentButton() {
       return wait.get().until(ExpectedConditions.elementToBeClickable(By.id("gdpr-banner-accept")));
    } 

    public static WebElement getSearchBar() {
        return wait.get().until(ExpectedConditions.elementToBeClickable(By.id("gh-ac")));
    } 

    public static WebElement getSearchButton() {
       return wait.get().until(ExpectedConditions.elementToBeClickable(By.id("gh-btn")));
    } 






    















}
