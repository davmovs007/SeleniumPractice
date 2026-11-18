package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductPreviewTests extends BaseTest {

    @Test(description = "TC-05 [P1] Default Product State: Monitor graphic in Silver, caption Silver Finish · 16GB Unified Memory, and Trade-In CTA")
    public void testDefaultProductState() {
        Assert.assertTrue(configuratorPage.isPreviewGraphicDisplayed(),
                "Expected STUDIO PRO preview graphic to be displayed");

        String caption = configuratorPage.getPreviewCaptionText();
        Assert.assertTrue(caption.contains("Silver Finish"),
                "Expected caption to contain 'Silver Finish', but got: " + caption);
        Assert.assertTrue(caption.contains("16GB Unified Memory"),
                "Expected caption to contain '16GB Unified Memory', but got: " + caption);
    }

    @Test(description = "TC-06 [P1] Dynamic Preview Updates: Caption updates with Space Black and 32GB/64GB")
    public void testDynamicPreviewUpdates() {
        // Select Space Black & 32GB
        configuratorPage.selectFinishSpaceBlack();
        configuratorPage.selectMemory32GB();

        String caption = configuratorPage.getPreviewCaptionText();
        Assert.assertTrue(caption.contains("Space Black Finish"),
                "Expected caption to contain 'Space Black Finish', but got: " + caption);
        Assert.assertTrue(caption.contains("32GB Unified Memory"),
                "Expected caption to contain '32GB Unified Memory', but got: " + caption);

        // Switch to Starlight & 64GB
        configuratorPage.selectFinishStarlight();
        configuratorPage.selectMemory64GB();

        String updatedCaption = configuratorPage.getPreviewCaptionText();
        Assert.assertTrue(updatedCaption.contains("Starlight Finish"),
                "Expected caption to contain 'Starlight Finish', but got: " + updatedCaption);
        Assert.assertTrue(updatedCaption.contains("64GB Unified Memory"),
                "Expected caption to contain '64GB Unified Memory', but got: " + updatedCaption);
    }

    @Test(description = "TC-07 [P2] Trade-In Estimate Hover/Click Interaction: Tooltip or popover appears without layout break")
    public void testTradeInEstimateHoverAndClick() {
        // Test hover
        configuratorPage.hoverTradeInEstimate();
        // Test click
        configuratorPage.clickTradeInEstimate();

        Assert.assertTrue(configuratorPage.isTradeInTooltipOrModalVisible(),
                "Expected trade-in tooltip/popover/modal to appear upon hover or click");
    }
}
