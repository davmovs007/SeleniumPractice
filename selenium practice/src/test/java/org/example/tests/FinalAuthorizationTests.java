package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FinalAuthorizationTests extends BaseTest {

    @Test(description = "TC-35 [P0] Unlock Final Order Button (Fraud Protection Check): Place Final Order button disabled (Locked) until security check completes")
    public void testUnlockFinalOrderButtonFraudProtection() {
        // Check state before verification: Place Final Order should be disabled or Locked
        String btnText = authPage.getPlaceFinalOrderButtonText();
        Assert.assertTrue(btnText.contains("Locked") || !authPage.isPlaceFinalOrderEnabled(),
                "Place Final Order button should initially remain disabled ('Locked')");

        // Click Prepare Final Checkout
        authPage.clickPrepareFinalCheckout();

        // Wait for security check / cooldown timer
        authPage.waitForOrderUnlocked();

        Assert.assertTrue(authPage.isPlaceFinalOrderEnabled(),
                "Place Final Order button should unlock and allow placing the order after security check");
    }

    @Test(description = "TC-36 [P1] Electronic Invoice Generation (Electronic Invoice): Transitions from Idle to Generated / Ready")
    public void testElectronicInvoiceGeneration() {
        String initialStatus = authPage.getInvoiceStatusText();
        Assert.assertTrue(initialStatus.toLowerCase().contains("idle"),
                "Expected initial invoice status to be 'Invoice Status: Idle', but got: " + initialStatus);

        // Click Generate Invoice PDF
        authPage.clickGenerateInvoice();

        // Wait for generation completion
        authPage.waitForInvoiceGenerated();
        String updatedStatus = authPage.getInvoiceStatusText();
        Assert.assertTrue(updatedStatus.toLowerCase().contains("generated") || updatedStatus.toLowerCase().contains("ready"),
                "Expected invoice status to transition to 'Generated' or 'Ready', but got: " + updatedStatus);
    }

    @Test(description = "TC-37 [P2] Retail Support Alert Buttons (Retail Support Alerts): Store Pickup Alert, Cancel Express Hold, Add Gift Note replace placeholder")
    public void testRetailSupportAlertButtons() {
        // Initial placeholder
        Assert.assertTrue(authPage.getSupportDialogText().contains("No support dialog triggered yet"),
                "Expected initial placeholder 'No support dialog triggered yet.'");

        // 1. Store Pickup Alert
        authPage.clickStorePickupAlert();
        Assert.assertFalse(authPage.getSupportDialogText().contains("No support dialog triggered yet"),
                "Placeholder should be replaced after Store Pickup Alert");

        // 2. Cancel Express Hold
        authPage.clickCancelExpressHold();
        Assert.assertTrue(authPage.getSupportDialogText().toLowerCase().contains("hold") || authPage.getSupportDialogText().toLowerCase().contains("cancel"),
                "Support dialog should reflect Cancel Express Hold");

        // 3. Add Gift Note
        authPage.clickAddGiftNote();
        Assert.assertTrue(authPage.getSupportDialogText().toLowerCase().contains("gift") || authPage.getSupportDialogText().toLowerCase().contains("note"),
                "Support dialog should reflect Add Gift Note");
    }
}
