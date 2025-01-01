package test;

import pages.CalculatorPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;

import java.net.URL;
import java.net.MalformedURLException;

public class AppTest {

    private AppiumDriver<WebElement> driver;
    private CalculatorPage calculatorPage;

    // Initialize the session once before all tests
    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Setup the capabilities for the app and Appium server
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "mac");
        capabilities.setCapability("automationName", "mac2");  // macOS automation
        capabilities.setCapability("appium:bundleId", "com.apple.calculator");

        // Connect to the Appium server
        URL appiumServerUrl = new URL("http://127.0.0.1:4723");
        driver = new AppiumDriver<>(appiumServerUrl, capabilities);

        // Initialize the CalculatorPage
        calculatorPage = new CalculatorPage(driver);
    }

    // Reset the calculator state before each test (keeping the session alive)
    @BeforeMethod
    public void resetState() {
        calculatorPage.resetCalculator();  // Reset the calculator state before each test
    }

    // Test for simple addition
    @Test
    public void testAddition() {
        calculatorPage.clickNumber("9");
        calculatorPage.clickOperator("Add");
        calculatorPage.clickNumber("1");
        calculatorPage.clickEquals();

        String result = calculatorPage.getResultText();
        Assert.assertEquals(result, "10", "Addition failed!");
    }

    // Test for simple subtraction
    @Test
    public void testSubtraction() {
        calculatorPage.clickNumber("9");
        calculatorPage.clickOperator("Subtract");
        calculatorPage.clickNumber("1");
        calculatorPage.clickEquals();

        String result = calculatorPage.getResultText();
        Assert.assertEquals(result, "8", "Subtraction failed!");
    }

    // Test for simple multiplication
    @Test
    public void testMultiplication() {
        calculatorPage.clickNumber("9");
        calculatorPage.clickOperator("Multiply");
        calculatorPage.clickNumber("1");
        calculatorPage.clickEquals();

        String result = calculatorPage.getResultText();
        Assert.assertEquals(result, "9", "Multiplication failed!");
    }

    // Test for simple division
    @Test
    public void testDivision() {
        calculatorPage.clickNumber("9");
        calculatorPage.clickOperator("Divide");
        calculatorPage.clickNumber("3");
        calculatorPage.clickEquals();

        String result = calculatorPage.getResultText();
        Assert.assertEquals(result, "3", "Division failed!");
    }

    // Test commutative property of addition (1 + 2 = 2 + 1)
    @Test
    public void testAdditionOrder() {
        // Perform 1 + 2 and then 2 + 1 to validate commutative property of addition
        calculatorPage.clickNumber("1");
        calculatorPage.clickOperator("Add");
        calculatorPage.clickNumber("2");
        calculatorPage.clickEquals();

        String result1 = calculatorPage.getResultText();

        // Now check 2 + 1
        calculatorPage.clickNumber("2");
        calculatorPage.clickOperator("Add");
        calculatorPage.clickNumber("1");
        calculatorPage.clickEquals();

        String result2 = calculatorPage.getResultText();
        Assert.assertEquals(result1, result2, "Addition failed!");
    }

    // Test commutative property of multiplication (3 * 4 = 4 * 3)
    @Test
    public void testMultiplicationOrder() {
        // Perform 3 * 4 and then 4 * 3 to validate commutative property of multiplication
        calculatorPage.clickNumber("3");
        calculatorPage.clickOperator("Multiply");
        calculatorPage.clickNumber("4");
        calculatorPage.clickEquals();

        String result1 = calculatorPage.getResultText();

        // Now check 4 * 3
        calculatorPage.clickNumber("4");
        calculatorPage.clickOperator("Multiply");
        calculatorPage.clickNumber("3");
        calculatorPage.clickEquals();

        String result2 = calculatorPage.getResultText();
        Assert.assertEquals(result1, result2, "Multiplication failed!");
    }

    // Test handling of division by zero (should display "Undefined")
    @Test
    public void testDivisionByZero() {
        // Try dividing a number by zero (should handle gracefully)
        calculatorPage.clickNumber("9");
        calculatorPage.clickOperator("Divide");
        calculatorPage.clickNumber("0");
        calculatorPage.clickEquals();

        // Get the result and check for error message or any non-zero output
        String result = calculatorPage.getUndefinedResultText();
        Assert.assertEquals(result, "Undefined", "Division by zero should result in an error or infinity.");
    }

    // Quit the driver after all tests are complete
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
