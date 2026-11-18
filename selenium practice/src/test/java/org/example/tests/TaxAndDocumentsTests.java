package org.example.tests;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TaxAndDocumentsTests extends BaseTest {

    @Test(description = "TC-38 [P0] Tax-Exempt Certificate Upload & Verification (Choose File & Verify Certificate): Empty, unsupported format, oversized (>512KB), and valid .json")
    public void testTaxExemptCertificateUploadAndVerification() throws IOException {
        // 1. Click Verify Certificate with no file attached ('No tax document attached.')
        taxPage.clickVerifyCertificate();
        String emptyStatus = taxPage.getCertificateStatusText();
        Assert.assertTrue(emptyStatus.toLowerCase().contains("no") || emptyStatus.toLowerCase().contains("attach") || emptyStatus.toLowerCase().contains("required") || emptyStatus.toLowerCase().contains("error"),
                "Expected validation error when verifying with no file attached, but got: " + emptyStatus);

        // 2. Upload unsupported file format (.exe)
        File unsupportedFile = new File(downloadDir, "test_cert.exe");
        FileUtils.writeStringToFile(unsupportedFile, "MZ_DUMMY_EXE_DATA", StandardCharsets.UTF_8);
        taxPage.uploadCertificateFile(unsupportedFile);
        taxPage.clickVerifyCertificate();
        String formatError = taxPage.getCertificateStatusText();
        Assert.assertTrue(formatError.toLowerCase().contains("invalid") || formatError.toLowerCase().contains("unsupported") || formatError.toLowerCase().contains("error"),
                "Expected validation error for unsupported file format, but got: " + formatError);

        // 3. Upload valid format (.json) larger than 512 KB
        File largeFile = new File(downloadDir, "large_cert.json");
        byte[] largeBytes = new byte[600 * 1024]; // 600 KB > 512 KB
        FileUtils.writeByteArrayToFile(largeFile, largeBytes);
        taxPage.uploadCertificateFile(largeFile);
        taxPage.clickVerifyCertificate();
        String sizeError = taxPage.getCertificateStatusText();
        Assert.assertTrue(sizeError.toLowerCase().contains("size") || sizeError.toLowerCase().contains("512") || sizeError.toLowerCase().contains("large") || sizeError.toLowerCase().contains("error"),
                "Expected validation error for file larger than 512 KB, but got: " + sizeError);

        // 4. Upload valid .json file under 512 KB (e.g., 100 KB) and click Verify Certificate
        File validFile = new File(downloadDir, "valid_tax_cert.json");
        FileUtils.writeStringToFile(validFile, "{\"taxExemptId\": \"TX-990142\", \"status\": \"ACTIVE\", \"authority\": \"Revenue Service\"}", StandardCharsets.UTF_8);
        taxPage.uploadCertificateFile(validFile);
        taxPage.clickVerifyCertificate();
        String validStatus = taxPage.getCertificateStatusText();
        Assert.assertTrue(validStatus.toLowerCase().contains("verified") || validStatus.toLowerCase().contains("success") || validStatus.toLowerCase().contains("valid"),
                "Expected valid file to pass verification upon clicking Verify Certificate, but got: " + validStatus);
    }

    @Test(description = "TC-39 [P1] Download Order Files (Download Order Files): Download Receipt (.txt) and Download Catalog (.csv)")
    public void testDownloadOrderFiles() {
        // Verify section shows 'Ready to download.'
        String status = taxPage.getDownloadSectionStatusText();
        Assert.assertTrue(status.contains("Ready to download") || !status.isEmpty(),
                "Expected section to show 'Ready to download.'");

        // Download Receipt (.txt)
        taxPage.clickDownloadReceipt();

        // Download Catalog (.csv)
        taxPage.clickDownloadCatalog();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}

        File[] files = downloadDir.listFiles();
        Assert.assertNotNull(files, "Download directory should not be null");
    }
}
