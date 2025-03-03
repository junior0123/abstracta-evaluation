package abstracta.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstracta.ui.BasePage;

/**
 * Represents a product page in the web application.
 * This page object provides methods to interact with elements on a product page,
 * such as adding the product to the cart and verifying success messages.
 * Extends {@link BasePage} for common page functionalities.
 */
public class ProductPage extends BasePage {

    /**
     * Represents the main content area of the product page.
     */
    @FindBy(css = "#content")
    private WebElement content;

    /**
     * Button to add the product to the shopping cart.
     */
    @FindBy(css = ".form-group #button-cart")
    private WebElement addToCartButton;

    /**
     * Success message alert displayed after adding the product to the cart.
     */
    @FindBy(css = "div.alert.alert-success.alert-dismissible")
    private WebElement successMessageAlert;

    /**
     * Waits until the product page elements are loaded and visible.
     * Specifically, it waits for the content area and the "Add to Cart" button to be interactable.
     *
     * @throws WebDriverException if elements are not loaded within the timeout.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        content = wait.until(ExpectedConditions.visibilityOf(content));
        LOG.info("Product page is loaded and visible");
        addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        LOG.info("Add to cart button is loaded and clickable");
    }

    /**
     * Clicks the "Add to Cart" button to add the product to the shopping cart.
     */
    public void addToCart() {
        addToCartButton.click();
        LOG.info("Added product to cart");
    }

    /**
     * Checks if the success message is displayed after adding a product to the cart.
     * It explicitly waits for the success message to be visible.
     *
     * @return {@code true} if the success message is displayed, {@code false} otherwise.
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            successMessageAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.alert.alert-success.alert-dismissible")));
            return successMessageAlert.isDisplayed();
        } catch (Exception e) {
            LOG.error("Success message not displayed");
            e.printStackTrace();
            return false;
        }
    }
}