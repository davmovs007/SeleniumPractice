package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccessoriesPage extends BasePage {

    private final By searchInput = By.xpath("//input[contains(@placeholder, 'Search accessories') or @id='accessorySearch']");
    private final By showingCount = By.xpath("//*[contains(text(), 'Showing ') and contains(text(), 'accessories')]");
    private final By accessoryTableRows = By.xpath("//table[contains(@class, 'accessories') or @id='accessoriesTable']//tbody//tr | //*[contains(@class, 'accessory-item')]");
    private final By emptyStatePlaceholder = By.xpath("//*[contains(text(), 'No accessories found') or contains(@class, 'empty-search') or contains(text(), 'No results')]");

    // Custom Quote Form
    private final By customNameInput = By.xpath("//input[@id='customAccessoryName' or contains(@placeholder, 'Accessory name') or @name='customName']");
    private final By customPriceInput = By.xpath("//input[@id='customAccessoryPrice' or contains(@placeholder, 'Price') or @name='customPrice']");
    private final By addQuoteButton = By.xpath("//button[contains(text(), 'Add Quote') or @id='addQuoteBtn']");
    private final By quoteErrorMessage = By.xpath("//*[contains(@id, 'quoteError') or contains(@class, 'quote-error') or contains(@class, 'text-danger')]");

    // Card Bag State
    private final By cardBagStatus = By.xpath("//*[contains(text(), 'Bag is empty') or contains(@class, 'card-bag-status') or contains(@id, 'cardBagStatus')]");

    public AccessoriesPage(WebDriver driver) {
        super(driver);
    }

    public void searchAccessory(String query) {
        clearAndType(searchInput, query);
    }

    public String getShowingCountText() {
        return getText(showingCount);
    }

    public int getVisibleRowsCount() {
        return findAll(accessoryTableRows).size();
    }

    public boolean isEmptyStateDisplayed() {
        return isElementDisplayed(emptyStatePlaceholder);
    }

    public void addCustomQuote(String name, String price) {
        clearAndType(customNameInput, name);
        clearAndType(customPriceInput, price);
        click(addQuoteButton);
    }

    public String getQuoteErrorText() {
        return getText(quoteErrorMessage);
    }

    public boolean isQuoteErrorDisplayed() {
        return isElementDisplayed(quoteErrorMessage);
    }

    public void clickAddToBagFor(String accessoryName) {
        By addBtn = By.xpath("//tr[contains(., '" + accessoryName + "')]//button[contains(text(), 'Add to Bag')] | //*[contains(., '" + accessoryName + "')]//button[contains(text(), 'Add to Bag')]");
        click(addBtn);
    }

    public void clickRemoveFor(String accessoryName) {
        By removeBtn = By.xpath("//tr[contains(., '" + accessoryName + "')]//button[contains(text(), 'Remove')] | //*[contains(., '" + accessoryName + "')]//button[contains(text(), 'Remove')]");
        click(removeBtn);
    }

    public String getCardBagStatusText() {
        return getText(cardBagStatus);
    }

    public boolean isCardBagEmptyBadgeDisplayed() {
        return isElementDisplayed(cardBagStatus);
    }
}
