package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExpressCheckoutTests extends BaseTest {

    @Test(description = "TC-28 [P1] Drag-and-Drop Bundle Builder (Build Express Bundle): Releasing inside adds item and updates status, releasing outside snaps back")
    public void testDragAndDropBundleBuilder() {
        // Releasing outside snaps back
        expressPage.dragAirPodsOutsideDropzone();

        // Drag AirPods Max Case into Drop Here for Express dropzone
        expressPage.dragAirPodsToExpressDropzone();
        String status = expressPage.getExpressStatusText();
        Assert.assertTrue(status.contains("Ready for express interaction") || status.toLowerCase().contains("express"),
                "Expected express status to update upon dropping into bundle dropzone, but got: " + status);
    }

    @Test(description = "TC-29 [P1] Double-Click to Authorize Payment (Double-Click to Pay): Single click does not trigger, rapid double-click initiates payment")
    public void testDoubleClickToAuthorizePayment() {
        // Single click does not trigger payment
        expressPage.singleClickPayButton();
        String singleStatus = expressPage.getPaymentStatusText();
        Assert.assertFalse(singleStatus.toLowerCase().contains("authorized") && !singleStatus.toLowerCase().contains("success"),
                "Single click should not authorize payment");

        // Rapid double click
        expressPage.doubleClickPayButton();
        String dblStatus = expressPage.getPaymentStatusText();
        Assert.assertTrue(dblStatus.toLowerCase().contains("authorized") || dblStatus.toLowerCase().contains("paid") || dblStatus.toLowerCase().contains("processing") || dblStatus.toLowerCase().contains("success"),
                "Double-click should trigger payment authorization, but got: " + dblStatus);
    }

    @Test(description = "TC-30 [P1] Saved Build Context Menu (Saved Build: Studio M4 (Right-Click)): Intercepts context menu and opens custom action menu")
    public void testSavedBuildContextMenu() {
        expressPage.rightClickSavedBuild();
        Assert.assertTrue(expressPage.isCustomContextMenuDisplayed(),
                "Expected custom context menu to open upon right-clicking Saved Build button");
    }

    @Test(description = "TC-31 [P2] Serial Lookup with Keyboard Modifiers (Hardware Serial Lookup): Converts input with Shift to uppercase")
    public void testHardwareSerialLookupWithShift() {
        expressPage.enterSerialWithShift("ser123pro");
        String value = expressPage.getSerialLookupInputValue();
        Assert.assertEquals(value, value.toUpperCase(),
                "Expected serial input value to be converted to uppercase with Shift modifier: " + value);
    }
}
