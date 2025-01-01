package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;

public class CalculatorPage extends BasePage {

    // Constructor for CalculatorPage
    public CalculatorPage(AppiumDriver<WebElement> driver) {
        super(driver);  // Pass driver to the BasePage constructor
    }

    // Locator strategies for Calculator Buttons
    private By numberButton(String number) {
        return By.name(number);  // Using 'name' attribute for macOS Calculator buttons
    }

    private By operatorButton(String operator) {
        return By.name(operator);  // Operators (Add, Subtract, Multiply, etc.)
    }

    private By equalsButton = By.name("Equals");   // Locator for Equals button
    private By resultDisplay = By.xpath("//XCUIElementTypeScrollView[@label='Input']//XCUIElementTypeStaticText[@value]");  // Result Display
    private By undefinedresultDisplay = By.xpath("//XCUIElementTypeScrollView[@label='Undefined']//XCUIElementTypeStaticText[@value]");  // Result Display
    private By clearInput = By.name("All Clear");
    // Methods to interact with Calculator UI elements


    public void clickNumber(String number) {
        WebElement button = findElement(numberButton(number));
        button.click();
    }

    public void clickOperator(String operator) {
        WebElement button = findElement(operatorButton(operator));
        button.click();
    }

    public void clickEquals() {
        WebElement button = findElement(equalsButton);
        button.click();
    }

    // Method to get the result text
    public String getResultText() {
        WebElement result = findElement(resultDisplay);
        return cleanString(result.getAttribute("value"));
    }

    public String getUndefinedResultText() {
        WebElement result = findElement(undefinedresultDisplay);
        return cleanString(result.getAttribute("value"));
    }

    public void resetCalculator() {
        // Logic to clear the calculator or reset the state (like pressing the 'C' button)
        WebElement clearButton = driver.findElement(clearInput);
        clearButton.click();
    }

}
