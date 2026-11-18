package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutFormPage extends BasePage {

    private final By fullNameInput = By.xpath("//input[@id='fullName' or @name='fullName' or contains(@placeholder, 'Full Name') or contains(@placeholder, 'Recipient')]");
    private final By emailInput = By.xpath("//input[@id='email' or @type='email' or @name='email' or contains(@placeholder, 'Email')]");
    private final By phoneInput = By.xpath("//input[@id='phone' or @type='tel' or contains(@placeholder, 'Phone') or contains(@name, 'phone')]");
    private final By phoneCounter = By.xpath("//*[contains(@id, 'phoneCounter') or contains(text(), '/ 10')]");
    private final By orderRefInput = By.xpath("//input[@id='orderRef' or contains(@placeholder, 'Order Reference') or contains(@name, 'orderRef')]");
    private final By orderRefCounter = By.xpath("//*[contains(@id, 'orderRefCounter') or contains(text(), '/ 8')]");
    private final By buyerAgeInput = By.xpath("//input[@id='buyerAge' or contains(@name, 'age') or contains(@placeholder, 'Age')]");
    private final By orderQuantityInput = By.xpath("//input[@id='orderQty' or contains(@name, 'quantity') or contains(@placeholder, 'Quantity')]");
    private final By parcelWeightInput = By.xpath("//input[@id='parcelWeight' or contains(@name, 'weight') or contains(@placeholder, 'Weight')]");
    private final By securityPinInput = By.xpath("//input[@id='securityPin' or contains(@name, 'pin') or contains(@placeholder, 'PIN')]");

    private final By verifyDetailsBtn = By.xpath("//button[contains(text(), 'Verify Checkout Details') or @id='verifyCheckoutBtn']");
    private final By clearDetailsBtn = By.xpath("//button[contains(text(), 'Clear Details') or @id='clearDetailsBtn']");
    private final By verificationStatus = By.xpath("//*[contains(@id, 'checkoutVerificationStatus') or contains(text(), 'Unverified') or contains(text(), 'Verified')]");
    private final By inlineErrorList = By.xpath("//*[contains(@class, 'error') or contains(@class, 'invalid-feedback') or contains(@class, 'text-danger')]");

    // Education Promo Code
    private final By unlockEduCodeBtn = By.xpath("//button[contains(text(), 'Unlock Education Code') or @id='unlockEduBtn']");
    private final By eduCodeInput = By.xpath("//input[@id='educationCode' or contains(@placeholder, 'Education Code') or contains(@placeholder, 'Unlock Education Code to apply')]");
    private final By eduDiscountStatus = By.xpath("//*[contains(@id, 'eduDiscountStatus') or contains(@class, 'discount-msg')]");

    public CheckoutFormPage(WebDriver driver) {
        super(driver);
    }

    public String getFullNameValue() {
        return getInputValue(findFirstAvailable(fullNameInput));
    }

    public void enterFullName(String name) {
        clearAndType(fullNameInput, name);
    }

    public void enterEmail(String email) {
        clearAndType(emailInput, email);
    }

    public void enterPhone(String phone) {
        clearAndType(phoneInput, phone);
    }

    public String getPhoneCounterText() {
        return getText(findFirstAvailable(phoneCounter));
    }

    public void enterOrderReference(String ref) {
        clearAndType(orderRefInput, ref);
    }

    public String getOrderRefCounterText() {
        return getText(findFirstAvailable(orderRefCounter));
    }

    public void enterBuyerAge(String age) {
        clearAndType(buyerAgeInput, age);
    }

    public void enterOrderQuantity(String qty) {
        clearAndType(orderQuantityInput, qty);
    }

    public void enterParcelWeight(String weight) {
        clearAndType(parcelWeightInput, weight);
    }

    public void enterSecurityPin(String pin) {
        clearAndType(securityPinInput, pin);
    }

    public void clickVerifyDetails() {
        click(verifyDetailsBtn);
    }

    public void clickClearDetails() {
        click(clearDetailsBtn);
    }

    public String getVerificationStatusText() {
        return getText(findFirstAvailable(verificationStatus));
    }

    public List<WebElement> getVisibleErrors() {
        return findAll(inlineErrorList);
    }

    public boolean isEduCodeInputEnabled() {
        return isElementEnabled(eduCodeInput);
    }

    public void clickUnlockEduCode() {
        click(unlockEduCodeBtn);
    }

    public void enterEducationCode(String code) {
        clearAndType(eduCodeInput, code);
    }

    public String getEduDiscountStatusText() {
        return getText(findFirstAvailable(eduDiscountStatus));
    }
}
