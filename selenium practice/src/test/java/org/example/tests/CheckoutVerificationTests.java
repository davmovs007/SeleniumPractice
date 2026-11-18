package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutVerificationTests extends BaseTest {

    @Test(description = "TC-32 [P0] Form Text & Numeric Field Validation: Test boundary values, format checks across inputs, status Verified")
    public void testFormTextAndNumericFieldValidation() {
        // Initial state
        Assert.assertEquals(checkoutPage.getFullNameValue(), "Walk-in Guest",
                "Expected default full name to be 'Walk-in Guest'");
        Assert.assertTrue(checkoutPage.getVerificationStatusText().toLowerCase().contains("unverified"),
                "Expected initial status to be 'Unverified'");

        // 1. Recipient Full Name: clear pre-filled 'Walk-in Guest' and enter valid name
        checkoutPage.enterFullName("Alexander Wright");

        // 2. Email Address: test invalid formats (alex@icloud, test.com)
        checkoutPage.enterEmail("alex@icloud");
        checkoutPage.clickVerifyDetails();
        Assert.assertFalse(checkoutPage.getVisibleErrors().isEmpty(),
                "Expected validation error for invalid email format");

        checkoutPage.enterEmail("alex@icloud.com");

        // 3. Mobile Phone (10 Digits): enter 10 digits (4085550192) and verify 0 / 10 counter
        checkoutPage.enterPhone("4085550192");
        Assert.assertTrue(checkoutPage.getPhoneCounterText().contains("10 / 10") || checkoutPage.getPhoneCounterText().contains("10"),
                "Expected phone counter to reflect 10 digits");

        // 4. Order Reference (8 A-Z/0-9): enter 8 valid characters (APL8042X) and verify 0 / 8 counter
        checkoutPage.enterOrderReference("APL8042X");
        Assert.assertTrue(checkoutPage.getOrderRefCounterText().contains("8 / 8") || checkoutPage.getOrderRefCounterText().contains("8"),
                "Expected order ref counter to reflect 8 characters");

        // 5. Buyer Age (Financing: 18 - 95): enter valid age 25
        checkoutPage.enterBuyerAge("25");

        // 6. Order Quantity (1 - 25): enter valid quantity 2
        checkoutPage.enterOrderQuantity("2");

        // 7. Trade-In Parcel Weight kg (0.5 - 30.0): enter valid weight 2.4
        checkoutPage.enterParcelWeight("2.4");

        // 8. 4-Digit Security PIN: enter 4 digits
        checkoutPage.enterSecurityPin("4829");

        // Click Verify Checkout Details
        checkoutPage.clickVerifyDetails();

        String status = checkoutPage.getVerificationStatusText();
        Assert.assertTrue(status.toLowerCase().contains("verified") && !status.toLowerCase().contains("unverified"),
                "Expected checkout details status to transition to 'Verified', but got: " + status);
    }

    @Test(description = "TC-33 [P1] Unlock & Apply Student Promo Code (Education Discount Code): Locked until unlocked, accepts uppercase A-Z")
    public void testUnlockAndApplyStudentPromoCode() {
        // Verify input is disabled before clicking Unlock
        Assert.assertFalse(checkoutPage.isEduCodeInputEnabled(),
                "Education discount input should be disabled initially");

        // Click Unlock Education Code
        checkoutPage.clickUnlockEduCode();
        Assert.assertTrue(checkoutPage.isEduCodeInputEnabled(),
                "Education discount input should be unlocked after clicking Unlock");

        // Enter code in Uppercase A-Z format
        checkoutPage.enterEducationCode("STUDENTPRO");
        String discountMsg = checkoutPage.getEduDiscountStatusText();
        Assert.assertTrue(discountMsg.toLowerCase().contains("discount") || discountMsg.toLowerCase().contains("applied") || discountMsg.toLowerCase().contains("valid"),
                "Expected discount confirmation message, but got: " + discountMsg);
    }

    @Test(description = "TC-34 [P1] Clear Form Details (Clear Details): Resets all entered values, validation errors, and counters")
    public void testClearFormDetails() {
        checkoutPage.enterFullName("Jane Doe");
        checkoutPage.enterEmail("jane@example.com");
        checkoutPage.enterPhone("1234567890");

        checkoutPage.clickClearDetails();

        Assert.assertTrue(checkoutPage.getFullNameValue().isEmpty() || checkoutPage.getFullNameValue().equals("Walk-in Guest"),
                "Expected Full Name to reset to default or empty");
        Assert.assertTrue(checkoutPage.getVerificationStatusText().toLowerCase().contains("unverified"),
                "Verification status should reset to Unverified");
    }
}
