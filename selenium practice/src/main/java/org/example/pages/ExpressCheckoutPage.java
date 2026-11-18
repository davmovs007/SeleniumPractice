package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExpressCheckoutPage extends BasePage {

    // Drag and Drop Bundle
    private final By airPodsCard = By.xpath("//*[contains(text(), 'AirPods Max Case') and (contains(@class, 'draggable') or contains(@class, 'card') or @draggable='true')]");
    private final By expressDropzone = By.xpath("//*[contains(text(), 'Drop Here for Express') or contains(@class, 'dropzone') or @id='expressDropzone']");
    private final By expressStatus = By.xpath("//*[contains(text(), 'Ready for express interaction') or contains(@id, 'expressStatus') or contains(@class, 'express-status')]");

    // Double-Click to Pay
    private final By doubleClickPayBtn = By.xpath("//button[contains(text(), 'Double-Click to Pay') or @id='doubleClickPayBtn']");
    private final By paymentStatus = By.xpath("//*[contains(@id, 'paymentStatus') or contains(@class, 'payment-auth') or contains(text(), 'Authorized') or contains(text(), 'Payment')]");

    // Saved Build Context Menu
    private final By savedBuildBtn = By.xpath("//button[contains(text(), 'Saved Build: Studio M4') or contains(text(), 'Right-Click') or @id='savedBuildBtn']");
    private final By customContextMenu = By.xpath("//*[contains(@class, 'context-menu') or contains(@class, 'custom-menu') or @id='savedBuildContextMenu']");

    // Hardware Serial Lookup
    private final By serialLookupInput = By.xpath("//input[contains(@placeholder, 'Enter serial') or contains(@placeholder, 'Shift for uppercase') or @id='serialLookup']");
    private final By serialLookupStatus = By.xpath("//*[contains(@id, 'serialLookupResult') or contains(@class, 'lookup-result')]");

    public ExpressCheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void dragAirPodsToExpressDropzone() {
        WebElement source = findFirstAvailable(airPodsCard);
        WebElement target = findFirstAvailable(expressDropzone);
        dragAndDrop(source, target);
    }

    public void dragAirPodsOutsideDropzone() {
        WebElement source = findFirstAvailable(airPodsCard);
        scrollToElement(source);
        actions.clickAndHold(source).moveByOffset(150, 0).release().perform();
    }

    public String getExpressStatusText() {
        return getText(findFirstAvailable(expressStatus));
    }

    public void singleClickPayButton() {
        click(doubleClickPayBtn);
    }

    public void doubleClickPayButton() {
        WebElement btn = findFirstAvailable(doubleClickPayBtn);
        doubleClick(btn);
    }

    public String getPaymentStatusText() {
        return getText(findFirstAvailable(paymentStatus));
    }

    public void rightClickSavedBuild() {
        WebElement btn = findFirstAvailable(savedBuildBtn);
        contextClick(btn);
    }

    public boolean isCustomContextMenuDisplayed() {
        return isElementDisplayed(customContextMenu);
    }

    public void enterSerialWithShift(String lowercaseText) {
        WebElement input = findFirstAvailable(serialLookupInput);
        scrollToElement(input);
        input.clear();
        actions.moveToElement(input).click().keyDown(Keys.SHIFT).sendKeys(lowercaseText).keyUp(Keys.SHIFT).perform();
    }

    public String getSerialLookupInputValue() {
        return getInputValue(findFirstAvailable(serialLookupInput));
    }
}
