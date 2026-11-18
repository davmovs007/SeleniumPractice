package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement findVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    protected void click(By locator) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        scrollToElement(el);
        try {
            el.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", el);
        }
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        scrollToElement(element);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    protected void jsClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    protected void clearAndType(By locator, String text) {
        WebElement el = findVisible(locator);
        clearAndType(el, text);
    }

    protected void clearAndType(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        element.clear();
        // Fallback for custom react/vue controlled inputs: select all and backspace
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.BACK_SPACE);
        if (text != null && !text.isEmpty()) {
            element.sendKeys(text);
        }
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", element);
        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", element);
    }

    protected String getText(By locator) {
        return findVisible(locator).getText().trim();
    }

    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
    }

    protected String getInputValue(By locator) {
        return find(locator).getAttribute("value");
    }

    protected String getInputValue(WebElement element) {
        return element.getAttribute("value");
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            WebElement el = driver.findElement(locator);
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isCheckboxChecked(By locator) {
        WebElement el = find(locator);
        return el.isSelected();
    }

    protected void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center', inline: 'nearest'});", element);
    }

    protected void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    protected void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    protected void selectByVisibleText(By selectLocator, String text) {
        WebElement selectEl = findVisible(selectLocator);
        Select select = new Select(selectEl);
        select.selectByVisibleText(text);
    }

    protected void selectByVisibleText(WebElement selectEl, String text) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(selectEl)));
        select.selectByVisibleText(text);
    }

    protected String getSelectedOptionText(By selectLocator) {
        Select select = new Select(findVisible(selectLocator));
        return select.getFirstSelectedOption().getText().trim();
    }

    protected void hoverOver(WebElement element) {
        scrollToElement(element);
        actions.moveToElement(element).perform();
    }

    protected void doubleClick(WebElement element) {
        scrollToElement(element);
        actions.doubleClick(element).perform();
    }

    protected void contextClick(WebElement element) {
        scrollToElement(element);
        actions.contextClick(element).perform();
    }

    protected void dragAndDrop(WebElement source, WebElement target) {
        scrollToElement(source);
        actions.dragAndDrop(source, target).perform();
    }

    /**
     * Resolves an element using primary and fallback locators
     */
    protected WebElement findFirstAvailable(By... locators) {
        for (By locator : locators) {
            try {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    return elements.get(0);
                }
            } catch (Exception ignored) {
            }
        }
        for (By locator : locators) {
            try {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    return elements.get(0);
                }
            } catch (Exception ignored) {
            }
        }
        return wait.until(ExpectedConditions.presenceOfElementLocated(locators[0]));
    }
}
