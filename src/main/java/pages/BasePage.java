package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;

public class BasePage {
    protected AppiumDriver<WebElement> driver;

    // Constructor to initialize AppiumDriver
    public BasePage(AppiumDriver<WebElement> driver) {
        this.driver = driver;
    }
    // Common method to find elements on any page
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public String cleanString(String input) {
        // Remove invisible characters (e.g., Left-to-right mark, Right-to-left override, etc.)
        return input.replaceAll("[\\u200B\\u200C\\u200E\\u200F\\u202A-\\u202E\\uFEFF]", "").trim();
    }



}
