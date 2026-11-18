package org.example.tests;

import org.example.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest {
    protected WebDriver driver;
    protected File downloadDir;

    protected HeaderAndNavPage headerPage;
    protected ProductConfiguratorPage configuratorPage;
    protected AccessoriesPage accessoriesPage;
    protected ExpressCheckoutPage expressPage;
    protected CheckoutFormPage checkoutPage;
    protected FinalAuthorizationPage authPage;
    protected TaxAndDocumentsPage taxPage;

    @BeforeMethod
    public void setUp() {
        downloadDir = new File(System.getProperty("user.dir"), "target/downloads");
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }

        ChromeOptions options = new ChromeOptions();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        // Download directory settings
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadDir.getAbsolutePath());
        prefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        String targetUrl = System.getProperty("app.url", "http://192.168.10.35:5050/#checkout");
        driver.get(targetUrl);

        // Initialize page objects
        headerPage = new HeaderAndNavPage(driver);
        configuratorPage = new ProductConfiguratorPage(driver);
        accessoriesPage = new AccessoriesPage(driver);
        expressPage = new ExpressCheckoutPage(driver);
        checkoutPage = new CheckoutFormPage(driver);
        authPage = new FinalAuthorizationPage(driver);
        taxPage = new TaxAndDocumentsPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            }
        }
    }
}
