package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FinalAuthorizationPage extends BasePage {

    // Fraud Protection & Final Order
    private final By prepareFinalCheckoutBtn = By.xpath("//button[contains(text(), 'Prepare Final Checkout') or @id='prepareFinalCheckoutBtn']");
    private final By placeFinalOrderBtn = By.xpath("//button[contains(text(), 'Place Final Order') or @id='placeFinalOrderBtn']");
    private final By fraudStatus = By.xpath("//*[contains(@id, 'fraudCheckStatus') or contains(text(), 'Fraud Protection') or contains(text(), 'Locked')]");

    // Electronic Invoice
    private final By generateInvoiceBtn = By.xpath("//button[contains(text(), 'Generate Invoice PDF') or @id='generateInvoiceBtn']");
    private final By invoiceStatus = By.xpath("//*[contains(@id, 'invoiceStatus') or contains(text(), 'Invoice Status:')]");

    // Retail Support Alerts
    private final By storePickupAlertBtn = By.xpath("//button[contains(text(), 'Store Pickup Alert') or @id='storePickupAlertBtn']");
    private final By cancelExpressHoldBtn = By.xpath("//button[contains(text(), 'Cancel Express Hold') or @id='cancelExpressHoldBtn']");
    private final By addGiftNoteBtn = By.xpath("//button[contains(text(), 'Add Gift Note') or @id='addGiftNoteBtn']");
    private final By supportDialogPlaceholder = By.xpath("//*[contains(@id, 'supportDialogArea') or contains(text(), 'No support dialog triggered yet.') or contains(@class, 'support-dialog')]");

    public FinalAuthorizationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPlaceFinalOrderEnabled() {
        return isElementEnabled(placeFinalOrderBtn);
    }

    public String getPlaceFinalOrderButtonText() {
        return getText(findFirstAvailable(placeFinalOrderBtn));
    }

    public void clickPrepareFinalCheckout() {
        click(prepareFinalCheckoutBtn);
    }

    public void waitForOrderUnlocked() {
        wait.until(ExpectedConditions.elementToBeClickable(placeFinalOrderBtn));
    }

    public void clickPlaceFinalOrder() {
        click(placeFinalOrderBtn);
    }

    public String getInvoiceStatusText() {
        return getText(findFirstAvailable(invoiceStatus));
    }

    public void clickGenerateInvoice() {
        click(generateInvoiceBtn);
    }

    public void waitForInvoiceGenerated() {
        wait.until(driver -> {
            String txt = getInvoiceStatusText();
            return txt.contains("Generated") || txt.contains("Ready");
        });
    }

    public void clickStorePickupAlert() {
        click(storePickupAlertBtn);
    }

    public void clickCancelExpressHold() {
        click(cancelExpressHoldBtn);
    }

    public void clickAddGiftNote() {
        click(addGiftNoteBtn);
    }

    public String getSupportDialogText() {
        return getText(findFirstAvailable(supportDialogPlaceholder));
    }
}
