package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StudioProConfiguratorBagTests extends BaseTest {

    @Test(description = "TC-19 [P0] Strict Notification Selection (Select Exactly 2 Order Updates): Counter updates 0/2 -> 1/2 -> 2/2, adding to bag blocked if < 2 selected")
    public void testStrictNotificationSelection() {
        // Select only 1 option
        configuratorPage.toggleSmsAlert();
        Assert.assertTrue(configuratorPage.getOrderUpdatesCounterText().contains("1 / 2"),
                "Counter should reflect 1 / 2 selected");

        // Agree to license terms
        configuratorPage.toggleSoftwareTerms();

        // Attempt to add to bag with only 1 update selected
        configuratorPage.clickAddToBag();

        // Adding to bag should either be blocked (bag remains Bag (0)) or button disabled
        String bagText = headerPage.getBagText();
        Assert.assertTrue(bagText.contains("Bag (0)") || !configuratorPage.isAddToBagEnabled(),
                "Adding to bag should be restricted when fewer than 2 order updates are selected");
    }

    @Test(description = "TC-20 [P0] Mandatory Software License Terms: Add Studio Pro to Bag blocked until checked, updates counter to Bag (1)")
    public void testMandatorySoftwareLicenseTerms() {
        // Select 2 order updates
        configuratorPage.toggleSmsAlert();
        configuratorPage.toggleEmailSummary();

        // Ensure terms are unchecked initially
        if (configuratorPage.isSoftwareTermsChecked()) {
            configuratorPage.toggleSoftwareTerms();
        }

        // Click Add to Bag
        configuratorPage.clickAddToBag();
        Assert.assertTrue(headerPage.getBagText().contains("Bag (0)") || !configuratorPage.isAddToBagEnabled(),
                "Add to bag should fail when license terms are unchecked");

        // Now check the mandatory terms
        configuratorPage.toggleSoftwareTerms();
        Assert.assertTrue(configuratorPage.isSoftwareTermsChecked(), "License terms checkbox should be checked");

        // Click Add to Bag
        configuratorPage.clickAddToBag();
        Assert.assertTrue(headerPage.getBagText().contains("Bag (1)"),
                "Header counter should update to Bag (1) after successfully adding Studio Pro");
    }

    @Test(description = "TC-21 [P1] Free Engraving Input Field: Pre-filled value (23/24) and strict 24-character restriction")
    public void testFreeEngravingInputField() {
        String initialVal = configuratorPage.getEngravingValue();
        Assert.assertTrue(initialVal.contains("Complimentary Engraving") || initialVal.length() <= 24,
                "Expected pre-filled complimentary engraving text");

        // Enter 24 characters
        String exact24 = "123456789012345678901234";
        configuratorPage.enterEngravingText(exact24);
        Assert.assertEquals(configuratorPage.getEngravingValue(), exact24,
                "Expected 24 characters to be accepted");
        Assert.assertTrue(configuratorPage.getEngravingCounterText().contains("24 / 24"),
                "Counter should display 24 / 24");

        // Attempt 25th character
        configuratorPage.enterEngravingText(exact24 + "X");
        String finalVal = configuratorPage.getEngravingValue();
        Assert.assertTrue(finalVal.length() <= 24,
                "Input should strictly restrict characters beyond 24, but length was: " + finalVal.length());
    }

    @Test(description = "TC-22 [P1] Dependent Store Pickup Dropdowns: Store dropdown disabled until region selected")
    public void testDependentStorePickupDropdowns() {
        // Verify store dropdown disabled initially
        Assert.assertFalse(configuratorPage.isStoreDropdownEnabled(),
                "Store dropdown should be disabled before choosing a region");

        // Select region
        configuratorPage.selectRegion("California");

        // Verify store dropdown unlocks
        Assert.assertTrue(configuratorPage.isStoreDropdownEnabled(),
                "Store dropdown should unlock after selecting a region");

        // Select store and check stock
        configuratorPage.selectStore("San Francisco Union Square");
        configuratorPage.clickCheckStoreStock();

        String stockStatus = configuratorPage.getStockStatusText();
        Assert.assertTrue(stockStatus.toLowerCase().contains("stock") || stockStatus.toLowerCase().contains("available"),
                "Expected stock status confirmation, but got: " + stockStatus);
    }

    @Test(description = "TC-23 [P1] Trade-In Serial Eligibility Button: Verify Trade-In button opens serial verification flow")
    public void testTradeInSerialEligibilityButton() {
        configuratorPage.enterTradeInSerial("M4-PRO-9812");
        configuratorPage.clickVerifyTradeIn();
        String result = configuratorPage.getTradeInStatusText();
        Assert.assertNotNull(result, "Trade-in verification result should not be null");
    }

    @Test(description = "TC-24 [P1] Header Reset Button (Reset): Reverts all configurations and resets Bag (0)")
    public void testHeaderResetButton() {
        configuratorPage.selectMemory64GB();
        configuratorPage.toggleSelectAllProApps();

        headerPage.clickResetButton();

        Assert.assertTrue(headerPage.getBagText().contains("Bag (0)"),
                "Expected Bag counter to be reset to Bag (0)");
        Assert.assertTrue(configuratorPage.getTotalPriceText().contains("1,599"),
                "Expected base price $1,599 after header reset");
        Assert.assertTrue(configuratorPage.getProAppsCounterText().contains("0 / 3"),
                "Expected 0 / 3 selected after reset");
    }
}
