package utils;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SeleniumHelper {

    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    
    public static WebDriver getDriver() {
        return driver.get();
    }

    public static synchronized Actions getActions() {
        return new Actions(getDriver());
    }

    public static void xJs(String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript(script, args);
    }

    public static final Logger logger = LoggerFactory.getLogger(SeleniumHelper.class);
    
}
