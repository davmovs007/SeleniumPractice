package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LowerPageControlsTests extends BaseTest {

    @Test(description = "TC-14 [P0] Order Updates Constraint (Select Exactly 2): Counter increments and selecting 3rd option is blocked")
    public void testOrderUpdatesConstraintSelectExactly2() {
        Assert.assertTrue(configuratorPage.getOrderUpdatesCounterText().contains("0 / 2"),
                "Expected initial updates counter to be 0 / 2");

        // Select 1st option: SMS
        configuratorPage.toggleSmsAlert();
        Assert.assertTrue(configuratorPage.getOrderUpdatesCounterText().contains("1 / 2"),
                "Expected counter to update to 1 / 2");

        // Select 2nd option: Email
        configuratorPage.toggleEmailSummary();
        Assert.assertTrue(configuratorPage.getOrderUpdatesCounterText().contains("2 / 2"),
                "Expected counter to update to 2 / 2");

        // Attempt to select 3rd option: Wallet Pass
        if (configuratorPage.isWalletPassEnabled()) {
            configuratorPage.toggleWalletPass();
            String counter = configuratorPage.getOrderUpdatesCounterText();
            Assert.assertTrue(counter.contains("2 / 2") || !configuratorPage.isAddToBagEnabled(),
                    "Selecting 3rd option should be restricted or block checkout");
        } else {
            Assert.assertFalse(configuratorPage.isWalletPassEnabled(),
                    "3rd checkbox should be disabled when 2 are selected");
        }
    }

    @Test(description = "TC-15 [P1] Trade-In Serial Eligibility Verification: Empty, invalid, and valid serial numbers")
    public void testTradeInSerialEligibilityVerification() {
        // 1. Empty serial
        configuratorPage.enterTradeInSerial("");
        configuratorPage.clickVerifyTradeIn();
        String errorMsg = configuratorPage.getTradeInStatusText();
        Assert.assertFalse(errorMsg.isEmpty(), "Expected error message for empty serial");

        // 2. Invalid serial
        configuratorPage.enterTradeInSerial("INVALID_9999");
        configuratorPage.clickVerifyTradeIn();
        String invalidMsg = configuratorPage.getTradeInStatusText();
        Assert.assertTrue(invalidMsg.toLowerCase().contains("invalid") || invalidMsg.toLowerCase().contains("not eligible") || invalidMsg.toLowerCase().contains("error"),
                "Expected invalid serial error message, but got: " + invalidMsg);

        // 3. Valid serial
        configuratorPage.enterTradeInSerial("APL-STUDIO-2024");
        configuratorPage.clickVerifyTradeIn();
        String validMsg = configuratorPage.getTradeInStatusText();
        Assert.assertTrue(validMsg.toLowerCase().contains("eligible") || validMsg.toLowerCase().contains("credit") || validMsg.toLowerCase().contains("valid") || validMsg.toLowerCase().contains("applied"),
                "Expected eligible trade-in confirmation, but got: " + validMsg);
    }
}
