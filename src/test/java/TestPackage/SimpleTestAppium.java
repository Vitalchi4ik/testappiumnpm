package TestPackage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.net.URL;

public class SimpleTestAppium {
    @Test
    public void testSearch() throws Exception{
        URL serverUrl = new URL("http://127.0.0.1:4723/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2_API_25");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability("appWaitActivity", "*");

        AppiumDriver driver = new AndroidDriver(serverUrl, capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //open url (but firstly you need add appium chromedriver, than to activate developer mode + accept debbuging
        driver.get("https://ek.ua/");
        //first step
        driver.findElement(By.cssSelector("header > a.fast-search-show.fl-l")).click();
        //second step
        wait.until(visibilityOf(driver.findElement(By.name("search_"))));
        driver.findElement(By.name("search_")).sendKeys("Телевизоры");
        wait.until(visibilityOf(driver.findElement(By.cssSelector("#ek-search-tr-1 > td > a"))));
        driver.findElement(By.cssSelector("#ek-search-tr-1 > td > a")).click();
        //find actual result
        String actualResult = driver.findElement(By.className("page-title")).getText();
        String expectedResult = "Телевизоры";
        //add and compare actual and expected results
        Assert.assertTrue(actualResult.contains(expectedResult));
        driver.quit();
    }
}
