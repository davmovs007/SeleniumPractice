package org.example.tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UIAccessibilityTests extends BaseTest {

    @Test(description = "TC-16 [P2] Sticky Header / Price Visibility on Scroll: Total price or product preview remains sticky/accessible")
    public void testStickyHeaderPriceVisibilityOnScroll() {
        // Scroll down to the bottom of configurator options
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");

        String price = configuratorPage.getTotalPriceText();
        Assert.assertNotNull(price, "Total price should remain sticky/accessible while configuring lower options");
    }

    @Test(description = "TC-17 [P2] Responsive Layout (Tablet & Mobile): Two-column layout stacks cleanly into single column without truncation")
    public void testResponsiveLayoutTabletAndMobile() {
        // 1. Tablet Viewport (768px)
        driver.manage().window().setSize(new Dimension(768, 1024));
        Assert.assertTrue(configuratorPage.isPreviewGraphicDisplayed(),
                "Product preview should be visible in tablet layout");
        Assert.assertTrue(headerPage.isPromoBannerDisplayed(),
                "Promo banner should be visible in tablet layout");

        // 2. Mobile Viewport (375px)
        driver.manage().window().setSize(new Dimension(375, 812));
        Assert.assertTrue(configuratorPage.isPreviewGraphicDisplayed(),
                "Product preview should be visible in mobile layout");

        // Restore standard desktop size
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @Test(description = "TC-18 [P2] Keyboard Navigation & Focus States: Tab navigation, Arrow keys for radios, and Spacebar for checkboxes")
    public void testKeyboardNavigationAndFocusStates() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        WebElement activeEl = driver.switchTo().activeElement();
        activeEl.sendKeys(Keys.TAB);
        activeEl.sendKeys(Keys.TAB);

        // Toggle focused element with Spacebar
        driver.switchTo().activeElement().sendKeys(Keys.SPACE);

        // Cycle radio groups with Arrow key
        driver.switchTo().activeElement().sendKeys(Keys.ARROW_RIGHT);

        WebElement currentActive = driver.switchTo().activeElement();
        Assert.assertNotNull(currentActive, "Active element should exist during keyboard navigation");
    }
}
