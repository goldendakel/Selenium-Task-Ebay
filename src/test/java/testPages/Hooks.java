package testPages;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;


public class Hooks extends SeleniumHelper {

    @Before
    public synchronized void setUp() {
        Browser.setDriver("chrome");
    }

    @After
    public synchronized void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));
        }
        WebDriver driver = SeleniumHelper.getDriver();
        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException e) {
                e.printStackTrace();
            } finally {
                SeleniumHelper.driver.set(null);
                SeleniumHelper.wait.set(null);
            }
        }
    }
}