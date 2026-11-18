package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationAndHeaderTests extends BaseTest {

    @Test(description = "TC-01 [P1] Top Navigation Links: Click each link and verify explore dropdown")
    public void testTopNavigationLinks() {
        headerPage.clickLogo();
        headerPage.clickStudioProLink();
        headerPage.clickAccessoriesLink();
        headerPage.clickExpressPayLink();
        headerPage.clickCheckoutLink();

        headerPage.clickExploreDropdown();
        Assert.assertTrue(headerPage.isExploreDropdownMenuVisible(),
                "Expected Explore dropdown menu to be visible upon clicking Explore");
    }

    @Test(description = "TC-02 [P1] Bag Counter Initial State: Displays Bag (0) on initial page load")
    public void testBagCounterInitialState() {
        String bagText = headerPage.getBagText();
        Assert.assertTrue(bagText.contains("Bag (0)"),
                "Expected Bag counter to display 'Bag (0)', but got: " + bagText);
    }

    @Test(description = "TC-03 [P0] Reset Button Functionality: Reverts configurations to default and price to $1,599")
    public void testResetButtonFunctionality() {
        // Change finish to Space Black
        configuratorPage.selectFinishSpaceBlack();
        // Upgrade memory to 32GB
        configuratorPage.selectMemory32GB();
        // Check Logic Pro
        configuratorPage.toggleLogicPro();

        // Click Reset
        headerPage.clickResetButton();

        // Verify total price resets to $1,599
        String price = configuratorPage.getTotalPriceText();
        Assert.assertTrue(price.contains("1,599"),
                "Expected total price to reset to $1,599, but got: " + price);

        // Verify apps counter resets to 0 / 3 selected
        String appsCounter = configuratorPage.getProAppsCounterText();
        Assert.assertTrue(appsCounter.contains("0 / 3"),
                "Expected Pro Apps counter to reset to '0 / 3 selected', but got: " + appsCounter);

        // Verify preview caption resets to Silver & 16GB
        String caption = configuratorPage.getPreviewCaptionText();
        Assert.assertTrue(caption.contains("Silver") && caption.contains("16GB"),
                "Expected preview caption to revert to Silver and 16GB, but got: " + caption);
    }

    @Test(description = "TC-04 [P3] Promo Banner Environment Leak Check: Inspect sub-header banner for raw local IP")
    public void testPromoBannerEnvironmentLeakCheck() {
        Assert.assertTrue(headerPage.isPromoBannerDisplayed(), "Expected promo banner to be displayed");
        String bannerText = headerPage.getPromoBannerText();

        Assert.assertTrue(bannerText.contains("Atelier Trade In"),
                "Expected banner to mention Atelier Trade In, but got: " + bannerText);

        boolean containsRawIp = bannerText.contains("http://192.168.10.35:5050") || bannerText.contains("192.168.10.35");
        if (containsRawIp) {
            System.out.println("[AUDIT NOTE] TC-04 Detected raw environment IP in promo banner: " + bannerText);
        }
    }
}
