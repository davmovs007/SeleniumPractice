package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class TaxAndDocumentsPage extends BasePage {

    private final By fileUploadInput = By.xpath("//input[@type='file' or @id='taxDocUpload' or contains(@name, 'taxDoc')]");
    private final By verifyCertificateBtn = By.xpath("//button[contains(text(), 'Verify Certificate') or @id='verifyCertBtn']");
    private final By certificateStatus = By.xpath("//*[contains(@id, 'taxDocStatus') or contains(text(), 'No tax document attached') or contains(@class, 'cert-status')]");

    // Downloads
    private final By downloadReceiptBtn = By.xpath("//button[contains(text(), 'Download Receipt (.txt)') or contains(text(), 'Download Receipt') or @id='downloadReceiptBtn'] | //a[contains(text(), 'Download Receipt')]");
    private final By downloadCatalogBtn = By.xpath("//button[contains(text(), 'Download Catalog (.csv)') or contains(text(), 'Download Catalog') or @id='downloadCatalogBtn'] | //a[contains(text(), 'Download Catalog')]");
    private final By downloadSectionStatus = By.xpath("//*[contains(text(), 'Ready to download') or contains(@id, 'downloadStatus')]");

    public TaxAndDocumentsPage(WebDriver driver) {
        super(driver);
    }

    public void uploadCertificateFile(File file) {
        WebElement input = find(fileUploadInput);
        input.sendKeys(file.getAbsolutePath());
    }

    public void clickVerifyCertificate() {
        click(verifyCertificateBtn);
    }

    public String getCertificateStatusText() {
        return getText(findFirstAvailable(certificateStatus));
    }

    public void clickDownloadReceipt() {
        click(downloadReceiptBtn);
    }

    public void clickDownloadCatalog() {
        click(downloadCatalogBtn);
    }

    public String getDownloadSectionStatusText() {
        return getText(findFirstAvailable(downloadSectionStatus));
    }
}
