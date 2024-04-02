package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;

public class Browser extends SeleniumHelper {

    public Browser() {
    }

    public static synchronized void setDriver(String browser) {

        ChromeOptions chromeOptions = new ChromeOptions();

            //Open Chrome in Incognito mode
        chromeOptions.addArguments("--incognito");
            //Hide the "chrome is controlled by automated test software"
        chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
            //Unable to Accept the Cookies in headless. The option is turned off
        //chromeOptions.addArguments("headless", "--disable-gpu", "disable-infobars", "--disable-extensions", "--no-sandbox", "--window-size=1920,1080", "--disable-dev-shm-usage");
        
        WebDriver driverInstance = ThreadGuard.protect(new ChromeDriver(chromeOptions));
        ThreadGuard.protect(driverInstance);
        SeleniumHelper.driver.set(driverInstance);
        wait.set(new WebDriverWait(driverInstance, Duration.ofSeconds(7)));
        driverInstance.manage().window().maximize();

    }
}