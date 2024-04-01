package testPages;

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

        chromeOptions.addArguments("--incognito");
        // chromeOptions.addArguments("headless", "--disable-gpu", "disable-infobars", "--disable-extensions", "--no-sandbox", "--window-size=1920,1080", --disable-dev-shm-usage");
        chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36");
        chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        WebDriver driverInstance = ThreadGuard.protect(new ChromeDriver(chromeOptions));
        ThreadGuard.protect(driverInstance);
        SeleniumHelper.driver.set(driverInstance);
        wait.set(new WebDriverWait(driverInstance, Duration.ofSeconds(15)));
        driverInstance.manage().window().maximize();

    }
}