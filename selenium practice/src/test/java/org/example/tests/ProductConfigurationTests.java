package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductConfigurationTests extends BaseTest {

    @Test(description = "TC-08 [P0] Finish Radio Selection: Single finish selection, base price $1,599 remains unchanged")
    public void testFinishRadioSelection() {
        configuratorPage.selectFinishSilver();
        String price = configuratorPage.getTotalPriceText();
        Assert.assertTrue(price.contains("1,599"), "Base price should be $1,599 after selecting Silver");

        configuratorPage.selectFinishSpaceBlack();
        price = configuratorPage.getTotalPriceText();
        Assert.assertTrue(price.contains("1,599"), "Base price should remain $1,599 after selecting Space Black");

        configuratorPage.selectFinishStarlight();
        price = configuratorPage.getTotalPriceText();
        Assert.assertTrue(price.contains("1,599"), "Base price should remain $1,599 after selecting Starlight");
    }

    @Test(description = "TC-09 [P0] Memory Upgrade Pricing (32GB): Price updates from $1,599 to $1,999")
    public void testMemoryUpgrade32GB() {
        configuratorPage.selectMemory32GB();
        String price = configuratorPage.getTotalPriceText();
        Assert.assertTrue(price.contains("1,999"),
                "Expected total price to update to $1,999, but got: " + price);
    }

    @Test(description = "TC-10 [P0] Memory Upgrade Pricing (64GB): Price updates from $1,599 to $2,399")
    public void testMemoryUpgrade64GB() {
        configuratorPage.selectMemory64GB();
        String price = configuratorPage.getTotalPriceText();
        Assert.assertTrue(price.contains("2,399"),
                "Expected total price to update to $2,399, but got: " + price);
    }

    @Test(description = "TC-11 [P0] Pro Apps Individual Selection: Sequential counter increment and auto-checking of Select All")
    public void testProAppsIndividualSelection() {
        // Initially 0 / 3 selected
        Assert.assertTrue(configuratorPage.getProAppsCounterText().contains("0 / 3"),
                "Expected initial counter to be 0 / 3");

        // 1. Select Logic Pro
        configuratorPage.toggleLogicPro();
        Assert.assertTrue(configuratorPage.getProAppsCounterText().contains("1 / 3"),
                "Expected counter to update to 1 / 3 selected");

        // 2. Select Final Cut
        configuratorPage.toggleFinalCut();
        Assert.assertTrue(configuratorPage.getProAppsCounterText().contains("2 / 3"),
                "Expected counter to update to 2 / 3 selected");

        // 3. Select Motion
        configuratorPage.toggleMotion();
        Assert.assertTrue(configuratorPage.getProAppsCounterText().contains("3 / 3"),
                "Expected counter to update to 3 / 3 selected");

        // Check if Select All is automatically checked
        Assert.assertTrue(configuratorPage.isSelectAllAppsChecked(),
                "Expected 'Select All Pro Studio Apps' to auto-check when all 3 apps are checked");
    }

    @Test(description = "TC-12 [P1] Pro Apps 'Select All' Toggle: Checking selects all 3 (3/3), unchecking deselects all 3 (0/3)")
    public void testProAppsSelectAllToggle() {
        // Toggle on Select All
        configuratorPage.toggleSelectAllProApps();
        Assert.assertTrue(configuratorPage.getProAppsCounterText().contains("3 / 3"),
                "Expected counter to be 3 / 3 after Select All");
        Assert.assertTrue(configuratorPage.isLogicProChecked(), "Logic Pro should be checked");
        Assert.assertTrue(configuratorPage.isFinalCutChecked(), "Final Cut should be checked");
        Assert.assertTrue(configuratorPage.isMotionChecked(), "Motion should be checked");

        // Toggle off Select All
        configuratorPage.toggleSelectAllProApps();
        Assert.assertTrue(configuratorPage.getProAppsCounterText().contains("0 / 3"),
                "Expected counter to reset to 0 / 3 after unchecking Select All");
        Assert.assertFalse(configuratorPage.isLogicProChecked(), "Logic Pro should be unchecked");
        Assert.assertFalse(configuratorPage.isFinalCutChecked(), "Final Cut should be unchecked");
        Assert.assertFalse(configuratorPage.isMotionChecked(), "Motion should be unchecked");
    }

    @Test(description = "TC-13 [P1] Pro Apps Partial Deselection: Unchecking one app deselects Select All and updates counter to 2/3")
    public void testProAppsPartialDeselection() {
        // Select all 3 apps
        configuratorPage.toggleSelectAllProApps();
        Assert.assertTrue(configuratorPage.isSelectAllAppsChecked(), "Select All should be checked");

        // Uncheck Motion
        configuratorPage.toggleMotion();

        // Select All should now be unchecked
        Assert.assertFalse(configuratorPage.isSelectAllAppsChecked(),
                "Expected 'Select All' to be unchecked after unchecking Motion");
        Assert.assertTrue(configuratorPage.isLogicProChecked(), "Logic Pro should remain checked");
        Assert.assertTrue(configuratorPage.isFinalCutChecked(), "Final Cut should remain checked");
        Assert.assertFalse(configuratorPage.isMotionChecked(), "Motion should be unchecked");
        Assert.assertTrue(configuratorPage.getProAppsCounterText().contains("2 / 3"),
                "Expected counter to be 2 / 3 selected");
    }
}
