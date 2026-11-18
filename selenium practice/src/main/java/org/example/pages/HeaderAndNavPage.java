package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HeaderAndNavPage extends BasePage {

    private final By logoLocator = By.xpath("//a[contains(@class, 'navbar-brand') or contains(text(), 'Atelier')]");
    private final By studioProLink = By.xpath("//nav//a[contains(text(), 'Studio Pro') or @href='#studio-pro' or @href='#configurator']");
    private final By accessoriesLink = By.xpath("//nav//a[contains(text(), 'Accessories') or @href='#accessories']");
    private final By expressPayLink = By.xpath("//nav//a[contains(text(), 'Express Pay') or contains(text(), 'Express') or @href='#express']");
    private final By checkoutLink = By.xpath("//nav//a[contains(text(), 'Checkout') or @href='#checkout']");
    private final By exploreDropdown = By.xpath("//nav//*[contains(text(), 'Explore') and (contains(@class, 'dropdown') or @role='button' or self::a or self::button)]");
    private final By exploreDropdownMenu = By.xpath("//*[contains(@class, 'dropdown-menu') or contains(@class, 'menu')]");
    private final By exploreDropdownItems = By.xpath("//*[contains(@class, 'dropdown-menu')]//a | //*[contains(@class, 'dropdown-menu')]//button");
    private final By bagCounter = By.xpath("//*[contains(text(), 'Bag (') or contains(@class, 'bag-count') or @id='bagCounter']");
    private final By resetButton = By.xpath("//button[contains(text(), 'Reset') or @id='resetBtn' or @id='reset-config']");
    private final By promoBanner = By.xpath("//*[contains(@class, 'banner') or contains(@class, 'promo') or contains(text(), 'Atelier Trade In')]");

    public HeaderAndNavPage(WebDriver driver) {
        super(driver);
    }

    public void clickLogo() {
        click(logoLocator);
    }

    public void clickStudioProLink() {
        click(studioProLink);
    }

    public void clickAccessoriesLink() {
        click(accessoriesLink);
    }

    public void clickExpressPayLink() {
        click(expressPayLink);
    }

    public void clickCheckoutLink() {
        click(checkoutLink);
    }

    public void clickExploreDropdown() {
        click(exploreDropdown);
    }

    public boolean isExploreDropdownMenuVisible() {
        return isElementDisplayed(exploreDropdownMenu) || !findAll(exploreDropdownItems).isEmpty();
    }

    public List<WebElement> getExploreDropdownItems() {
        return findAll(exploreDropdownItems);
    }

    public String getBagText() {
        return getText(bagCounter);
    }

    public void clickResetButton() {
        click(resetButton);
    }

    public String getPromoBannerText() {
        return getText(promoBanner);
    }

    public boolean isPromoBannerDisplayed() {
        return isElementDisplayed(promoBanner);
    }
}
