package abstracta.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstracta.ui.BasePage;

/**
 * Represents the search results page in the web application.
 * This page object allows interaction with the list of products displayed after a search.
 * Extends {@link BasePage} for common page functionalities.
 */
public class SearchResultsPage extends BasePage {

    /**
     * WebElement representing the container of search results.
     */
    @FindBy(css =  "#content .row:nth-of-type(3)")
    private WebElement searchResults;

    /**
     * Waits until the search results are loaded and visible on the page.
     *
     * @throws WebDriverException if the search results are not visible within the timeout.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        searchResults = wait.until(ExpectedConditions.visibilityOf(searchResults));
        LOG.info("Search results are loaded and visible");
    }

    /**
     * Selects a product from the search results based on its index.
     * Navigates to the Product Page of the selected product.
     *
     * @param productIndex The index of the product to select (0-based).
     * @return ProductPage instance representing the Product Page of the selected product.
     * @throws NoSuchElementException if no product is found at the given index.
     */
    public ProductPage selectProduct(int productIndex) {
        List<WebElement> products = searchResults.findElements(By.cssSelector("div.product-layout"));
        if (productIndex < 0 || productIndex >= products.size()) {
            throw new NoSuchElementException("No product found at index " + productIndex);
        }
        WebElement product = products.get(productIndex);
        // Assuming product name is within <a> tag inside "div.caption h4"
        WebElement productLink = product.findElement(By.cssSelector("div.caption h4 a"));
        productLink.click();
        return new ProductPage();
    }

    /**
     * Selects a product from the search results based on its name.
     * Navigates to the Product Page of the selected product.
     *
     * @param productName The name of the product to select.
     * @return ProductPage instance representing the Product Page of the selected product.
     * @throws NoSuchElementException if no product is found with the given name.
     */
    public ProductPage selectProductByName(String productName) {
        List<WebElement> products = searchResults.findElements(By.cssSelector("div.product-layout"));
        for (WebElement product : products) {
            WebElement productLink = product.findElement(By.cssSelector("div.caption h4 a"));
            if (productLink.getText().trim().equalsIgnoreCase(productName)) {
                productLink.click();
                return new ProductPage();
            }
        }
        throw new NoSuchElementException("No product found with name: " + productName);
    }
}