package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductConfiguratorPage extends BasePage {

    // --- Left Preview Column ---
    private final By previewGraphic = By.xpath("//*[contains(@class, 'product-graphic') or contains(@class, 'monitor-graphic') or contains(@class, 'preview-graphic') or contains(text(), 'STUDIO PRO')]");
    private final By previewCaption = By.xpath("//*[contains(@class, 'preview-caption') or contains(@class, 'product-summary') or contains(text(), 'Finish') and contains(text(), 'Memory')]");
    private final By tradeInEstimateCta = By.xpath("//*[contains(text(), 'Hover for Atelier Trade In estimate') or contains(text(), 'Trade In estimate')]");
    private final By tradeInTooltip = By.xpath("//*[contains(@class, 'tooltip') or contains(@class, 'popover') or contains(@class, 'trade-in-modal') or contains(text(), 'Trade In') and contains(text(), 'estimate')]");

    // --- Right Configuration Column & Pricing ---
    private final By totalPrice = By.xpath("//*[contains(@class, 'total-price') or contains(@class, 'price-tag') or contains(text(), '$1,599') or contains(text(), '$1,999') or contains(text(), '$2,399') or @id='totalPrice']");

    // Finish Radios / Options
    private final By finishSilver = By.xpath("//input[@value='Silver' or @id='finish-silver'] | //*[contains(text(), 'Silver') and (contains(@class, 'finish-card') or contains(@class, 'radio') or self::label)]");
    private final By finishSpaceBlack = By.xpath("//input[@value='Space Black' or @id='finish-space-black'] | //*[contains(text(), 'Space Black') and (contains(@class, 'finish-card') or contains(@class, 'radio') or self::label)]");
    private final By finishStarlight = By.xpath("//input[@value='Starlight' or @id='finish-starlight'] | //*[contains(text(), 'Starlight') and (contains(@class, 'finish-card') or contains(@class, 'radio') or self::label)]");

    // Memory Radios / Options
    private final By memory16GB = By.xpath("//input[contains(@value, '16GB') or @id='mem-16'] | //*[contains(text(), '16GB Unified Memory') and (contains(@class, 'radio') or self::label or self::div)]");
    private final By memory32GB = By.xpath("//input[contains(@value, '32GB') or @id='mem-32'] | //*[contains(text(), '32GB Unified Memory') and (contains(@class, 'radio') or self::label or self::div)]");
    private final By memory64GB = By.xpath("//input[contains(@value, '64GB') or @id='mem-64'] | //*[contains(text(), '64GB Unified Memory') and (contains(@class, 'radio') or self::label or self::div)]");

    // Pro Apps Checkboxes
    private final By appLogicPro = By.xpath("//input[@id='app-logic' or contains(@value, 'Logic Pro')] | //label[contains(., 'Logic Pro')]//input | //label[contains(., 'Logic Pro')]");
    private final By appFinalCut = By.xpath("//input[@id='app-final-cut' or contains(@value, 'Final Cut')] | //label[contains(., 'Final Cut')]//input | //label[contains(., 'Final Cut')]");
    private final By appMotion = By.xpath("//input[@id='app-motion' or contains(@value, 'Motion')] | //label[contains(., 'Motion')]//input | //label[contains(., 'Motion')]");
    private final By appSelectAll = By.xpath("//input[@id='app-select-all' or contains(@value, 'all')] | //label[contains(., 'Select All Pro Studio Apps')]//input | //label[contains(., 'Select All Pro Studio Apps')]");
    private final By proAppsCounter = By.xpath("//*[contains(text(), 'selected') and contains(text(), '/ 3')]");

    // --- Lower Configurator Controls ---
    private final By orderUpdatesCounter = By.xpath("//*[contains(text(), '/ 2') and (contains(text(), 'Select') or contains(text(), 'Updates') or contains(@class, 'counter'))]");
    private final By smsAlertCheckbox = By.xpath("//label[contains(., 'SMS Text Alert')]//input | //input[@id='update-sms' or @value='SMS'] | //label[contains(., 'SMS Text Alert')]");
    private final By emailSummaryCheckbox = By.xpath("//label[contains(., 'Email Summary')]//input | //input[@id='update-email' or @value='Email'] | //label[contains(., 'Email Summary')]");
    private final By walletPassCheckbox = By.xpath("//label[contains(., 'Wallet Pass')]//input | //input[@id='update-wallet' or @value='Wallet'] | //label[contains(., 'Wallet Pass')]");
    private final By allOrderUpdateCheckboxes = By.xpath("//input[contains(@name, 'order-update') or contains(@id, 'update-') or ancestor::*[contains(., 'Order Updates')]//input[@type='checkbox']]");

    // Trade-In Serial Eligibility
    private final By tradeInSerialInput = By.xpath("//input[@id='tradeInSerial' or contains(@placeholder, 'serial') or contains(@name, 'serial')]");
    private final By verifyTradeInButton = By.xpath("//button[contains(text(), 'Verify Trade-In') or @id='verifyTradeInBtn']");
    private final By tradeInStatusMessage = By.xpath("//*[contains(@id, 'tradeInMsg') or contains(@class, 'trade-in-result') or contains(@class, 'trade-in-msg')]");

    // Software License Terms & Add to Bag
    private final By termsCheckbox = By.xpath("//input[@id='terms' or @id='agreeTerms' or contains(@name, 'terms')] | //label[contains(., 'Software License Terms')]//input | //label[contains(., 'Software License Terms')]");
    private final By addToBagButton = By.xpath("//button[contains(text(), 'Add Studio Pro to Bag') or @id='addToBagBtn']");

    // Free Engraving
    private final By engravingInput = By.xpath("//input[@id='engraving' or contains(@name, 'engraving') or contains(@placeholder, 'Engraving')]");
    private final By engravingCounter = By.xpath("//*[contains(text(), '/ 24') or contains(@id, 'engravingCounter') or contains(@class, 'char-count')]");

    // Store Pickup
    private final By regionSelect = By.xpath("//select[@id='pickupRegion' or contains(@name, 'region')]");
    private final By storeSelect = By.xpath("//select[@id='pickupStore' or contains(@name, 'store')]");
    private final By checkStockButton = By.xpath("//button[contains(text(), 'Check Store Stock') or @id='checkStockBtn']");
    private final By stockStatusMessage = By.xpath("//*[contains(@id, 'stockResult') or contains(@class, 'stock-status') or contains(text(), 'Stock') or contains(text(), 'available')]");

    public ProductConfiguratorPage(WebDriver driver) {
        super(driver);
    }

    // --- Preview Methods ---
    public boolean isPreviewGraphicDisplayed() {
        return isElementDisplayed(previewGraphic);
    }

    public String getPreviewCaptionText() {
        return getText(previewCaption);
    }

    public void hoverTradeInEstimate() {
        WebElement cta = findFirstAvailable(tradeInEstimateCta);
        hoverOver(cta);
    }

    public void clickTradeInEstimate() {
        click(tradeInEstimateCta);
    }

    public boolean isTradeInTooltipOrModalVisible() {
        return isElementDisplayed(tradeInTooltip);
    }

    // --- Configuration & Price Methods ---
    public String getTotalPriceText() {
        return getText(findFirstAvailable(totalPrice));
    }

    public void selectFinishSilver() {
        click(finishSilver);
    }

    public void selectFinishSpaceBlack() {
        click(finishSpaceBlack);
    }

    public void selectFinishStarlight() {
        click(finishStarlight);
    }

    public void selectMemory16GB() {
        click(memory16GB);
    }

    public void selectMemory32GB() {
        click(memory32GB);
    }

    public void selectMemory64GB() {
        click(memory64GB);
    }

    public void toggleLogicPro() {
        click(appLogicPro);
    }

    public void toggleFinalCut() {
        click(appFinalCut);
    }

    public void toggleMotion() {
        click(appMotion);
    }

    public void toggleSelectAllProApps() {
        click(appSelectAll);
    }

    public String getProAppsCounterText() {
        return getText(proAppsCounter);
    }

    public boolean isSelectAllAppsChecked() {
        WebElement el = find(appSelectAll);
        if ("input".equalsIgnoreCase(el.getTagName())) {
            return el.isSelected();
        }
        WebElement input = el.findElement(By.xpath(".//input"));
        return input.isSelected();
    }

    public boolean isLogicProChecked() {
        WebElement el = find(appLogicPro);
        if ("input".equalsIgnoreCase(el.getTagName())) {
            return el.isSelected();
        }
        return el.findElement(By.xpath(".//input")).isSelected();
    }

    public boolean isFinalCutChecked() {
        WebElement el = find(appFinalCut);
        if ("input".equalsIgnoreCase(el.getTagName())) {
            return el.isSelected();
        }
        return el.findElement(By.xpath(".//input")).isSelected();
    }

    public boolean isMotionChecked() {
        WebElement el = find(appMotion);
        if ("input".equalsIgnoreCase(el.getTagName())) {
            return el.isSelected();
        }
        return el.findElement(By.xpath(".//input")).isSelected();
    }

    // --- Lower Configurator Controls ---
    public String getOrderUpdatesCounterText() {
        return getText(orderUpdatesCounter);
    }

    public void toggleSmsAlert() {
        click(smsAlertCheckbox);
    }

    public void toggleEmailSummary() {
        click(emailSummaryCheckbox);
    }

    public void toggleWalletPass() {
        click(walletPassCheckbox);
    }

    public boolean isWalletPassEnabled() {
        return isElementEnabled(walletPassCheckbox);
    }

    public void enterTradeInSerial(String serial) {
        clearAndType(tradeInSerialInput, serial);
    }

    public void clickVerifyTradeIn() {
        click(verifyTradeInButton);
    }

    public String getTradeInStatusText() {
        return getText(tradeInStatusMessage);
    }

    public void toggleSoftwareTerms() {
        click(termsCheckbox);
    }

    public boolean isSoftwareTermsChecked() {
        WebElement el = find(termsCheckbox);
        if ("input".equalsIgnoreCase(el.getTagName())) {
            return el.isSelected();
        }
        return el.findElement(By.xpath(".//input")).isSelected();
    }

    public void clickAddToBag() {
        click(addToBagButton);
    }

    public boolean isAddToBagEnabled() {
        return isElementEnabled(addToBagButton);
    }

    public String getEngravingValue() {
        return getInputValue(engravingInput);
    }

    public void enterEngravingText(String text) {
        clearAndType(engravingInput, text);
    }

    public String getEngravingCounterText() {
        return getText(engravingCounter);
    }

    public boolean isStoreDropdownEnabled() {
        return isElementEnabled(storeSelect);
    }

    public void selectRegion(String region) {
        selectByVisibleText(regionSelect, region);
    }

    public void selectStore(String store) {
        selectByVisibleText(storeSelect, store);
    }

    public void clickCheckStoreStock() {
        click(checkStockButton);
    }

    public String getStockStatusText() {
        return getText(stockStatusMessage);
    }
}
