package abstracta.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstracta.ui.BasePage;

/**
 * Represents the shopping cart page in the web application.
 * This page object provides methods to interact with the shopping cart,
 * such as verifying products, removing products, and checking for empty cart messages.
 * Extends {@link BasePage} for common page functionalities.
 */
public class ShoppingCartPage extends BasePage {

    /**
     * WebElement representing the main content area of the shopping cart page.
     */
    @FindBy(css = "#content")
    private WebElement content;

    /**
     * WebElement representing the message displayed when the cart is empty.
     */
    @FindBy(css = "#content p")
    private WebElement emptyCartMessage;

    /**
     * WebElement displaying the total number of items and total price in the cart.
     */
    @FindBy(id = "cart-total")
    private WebElement cartTotal;

    /**
     * Waits until the shopping cart page content is loaded and visible.
     *
     * @throws WebDriverException if the page content is not visible within the timeout.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        content = wait.until(ExpectedConditions.visibilityOf(content));
        LOG.info("Shopping cart page content is loaded and visible");
    }

    /**
     * Retrieves a specific row in the shopping cart table based on the product name.
     *
     * @param productName The name of the product to find the row for.
     * @return WebElement representing the table row containing the product.
     */
    public WebElement getRowByProductName(String productName) {
        String xpath = "//div[@class='table-responsive']//table[@class='table table-bordered']//tbody/tr[td[2]//a[normalize-space()='"
                + productName + "']]";
        return driver.findElement(By.xpath(xpath));
    }

    /**
     * Checks if a product with the given name is present in the shopping cart.
     *
     * @param productName The name of the product to check for.
     * @return {@code true} if the product is in the cart, {@code false} otherwise.
     */
    public boolean isProductInCart(String productName) {
        waitUntilPageObjectIsLoaded();
        List<WebElement> rows = driver.findElements(By.xpath(
                "//div[contains(@class, 'table-responsive')]//table[contains(@class, 'table-bordered')]//tr[td[contains(.,'"
                        + productName + "')]]"));
        return !rows.isEmpty();
    }

    /**
     * Removes a product from the shopping cart by clicking the remove button associated with the product.
     *
     * @param productName The name of the product to remove.
     */
    public void removeProduct(String productName) {
        String xpath = "//div[@class='table-responsive']//table[@class='table table-bordered']//tbody/tr[td[2]//a[normalize-space()='"
                + productName + "']]";
        WebElement row = driver.findElement(By.xpath(xpath));
        WebElement removeButton = row.findElement(By.cssSelector("button.btn-danger"));
        removeButton.click();
        // Wait until the product row disappears from the DOM
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    /**
     * Checks if the "empty cart" message is displayed on the page.
     *
     * @return {@code true} if the empty cart message is displayed, {@code false} otherwise.
     */
    public boolean isEmptyCartMessageDisplayed() {
        try {
            // Wait until the element contains the expected text
            Boolean messageIsPresent = wait.until(
                    ExpectedConditions.textToBePresentInElement(emptyCartMessage, "Your shopping cart is empty!"));
            LOG.info("Empty cart message is displayed: " + messageIsPresent);
            return messageIsPresent;
        } catch (Exception e) {
            LOG.error("Empty cart message not displayed");
            return false;
        }
    }

    /**
     * Checks if the cart total is zero, indicating an empty cart or no items with price.
     *
     * @return {@code true} if the cart total is "0 item(s) - $0.00", {@code false} otherwise.
     */
    public boolean isCartTotalZero() {
        try {
            // Wait until the element is visible
            cartTotal = wait.until(ExpectedConditions.visibilityOf(cartTotal));
            String totalText = cartTotal.getText().trim();
            LOG.info("Cart total text: " + totalText);
            return totalText.equals("0 item(s) - $0.00");
        } catch (Exception e) {
            LOG.error("Error checking cart total");
            return false;
        }
    }
}