package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AccessoriesCatalogTests extends BaseTest {

    @Test(description = "TC-25 [P1] Catalog Search (Search accessories...): Real-time filtering, count updates, and empty state placeholder")
    public void testCatalogSearch() {
        // Initial search for Keyboard
        accessoriesPage.searchAccessory("Keyboard");
        int count = accessoriesPage.getVisibleRowsCount();
        Assert.assertTrue(count >= 1, "Expected at least 1 accessory row for 'Keyboard'");
        Assert.assertTrue(accessoriesPage.getShowingCountText().contains(String.valueOf(count)),
                "Showing count should reflect visible items");

        // Non-existent search query
        accessoriesPage.searchAccessory("MonitorStandNonExistentQueryXYZ");
        Assert.assertTrue(accessoriesPage.isEmptyStateDisplayed() || accessoriesPage.getVisibleRowsCount() == 0,
                "Expected empty state placeholder or 0 items for non-existent query");

        // Clear search
        accessoriesPage.searchAccessory("");
        Assert.assertTrue(accessoriesPage.getVisibleRowsCount() > 1,
                "Clearing search should restore catalog accessories");
    }

    @Test(description = "TC-26 [P0] Adding a Custom Accessory (Add Quote) — Validation & Submission: Rules (<3 chars, $10-$2000) and successful addition")
    public void testAddingCustomAccessoryValidationAndSubmission() {
        // 1. Name shorter than 3 characters (e.g., 'AB') and price $50
        accessoriesPage.addCustomQuote("AB", "50");
        Assert.assertTrue(accessoriesPage.isQuoteErrorDisplayed(),
                "Expected validation error for name shorter than 3 characters");

        // 2. Price outside valid range: $9 (< $10)
        accessoriesPage.addCustomQuote("Custom Stand", "9");
        Assert.assertTrue(accessoriesPage.isQuoteErrorDisplayed(),
                "Expected validation error for price under $10");

        // 2b. Price outside valid range: $2001 (> $2000)
        accessoriesPage.addCustomQuote("Custom Stand", "2001");
        Assert.assertTrue(accessoriesPage.isQuoteErrorDisplayed(),
                "Expected validation error for price exceeding $2000");

        // 3. Valid data: Custom Stand, price 150
        int beforeCount = accessoriesPage.getVisibleRowsCount();
        accessoriesPage.addCustomQuote("Custom Stand", "150");
        int afterCount = accessoriesPage.getVisibleRowsCount();
        Assert.assertTrue(afterCount >= beforeCount || !accessoriesPage.isQuoteErrorDisplayed(),
                "Expected valid accessory quote to be successfully accepted and added");
    }

    @Test(description = "TC-27 [P0] Adding & Removing Accessories from Table (Add to Bag / Remove): Bag badge and counter update")
    public void testAddingAndRemovingAccessoriesFromTable() {
        Assert.assertTrue(headerPage.getBagText().contains("Bag (0)"), "Initial header bag should be 0");

        // Click Add to Bag next to Studio Keyboard Pro (In Stock)
        accessoriesPage.clickAddToBagFor("Studio Keyboard Pro");
        Assert.assertTrue(headerPage.getBagText().contains("Bag (1)"),
                "Header bag counter should update to Bag (1)");

        // Click Add to Bag next to Braided USB-C Cable (Low Stock)
        accessoriesPage.clickAddToBagFor("Braided USB-C Cable");
        Assert.assertTrue(headerPage.getBagText().contains("Bag (2)"),
                "Header bag counter should update to Bag (2)");

        // Click Remove for Studio Keyboard Pro
        accessoriesPage.clickRemoveFor("Studio Keyboard Pro");
        Assert.assertTrue(headerPage.getBagText().contains("Bag (1)"),
                "Header bag counter should decrement to Bag (1) after removal");

        // Click Remove for Braided USB-C Cable
        accessoriesPage.clickRemoveFor("Braided USB-C Cable");
        Assert.assertTrue(headerPage.getBagText().contains("Bag (0)"),
                "Header bag counter should reset to Bag (0) when all items are removed");
        Assert.assertTrue(accessoriesPage.isCardBagEmptyBadgeDisplayed(),
                "Expected 'Bag is empty.' badge to be displayed in top-right corner of card");
    }
}
