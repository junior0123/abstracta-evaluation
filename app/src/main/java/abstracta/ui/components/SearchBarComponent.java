package abstracta.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstracta.ui.BasePage;
import abstracta.ui.pages.SearchResultsPage;
import abstracta.ui.pages.ShoppingCartPage;

public class SearchBarComponent extends BasePage {

    /**
     * Search input field.
     */
    @FindBy(css = "#search input")
    private WebElement searchInputField;

    /**
     * Search button.
     */
    @FindBy(css = "#search button")
    private WebElement searchButton;

    /**
     * Default constructor for the SearchBarComponent class.
     * Initializes web elements using PageFactory and waits for the component to
     * load.
     */
    public SearchBarComponent() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
        LOG.info("Initialized SearchBar component with WebDriver and Wait instances");
    }

    /**
     * Waits until the web elements of the SearchBar component are clickable.
     *
     * @throws WebDriverException If an error occurs during the wait.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        LOG.info("Waiting until SearchBar component elements are loaded");
        searchInputField = wait.until(ExpectedConditions.elementToBeClickable(searchInputField));
        searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        LOG.info("SearchBar component elements are loaded");
    }

    /**
     * Performs a search by entering the search term and clicking the search button.
     *
     * @param searchTerm The search term to enter.
     * @return SearchResultsPage An instance of the search results page.
     */
    public SearchResultsPage performSearch(String searchTerm) {
        searchInputField.sendKeys(searchTerm);
        searchButton.click();
        LOG.info("Performed search with term: " + searchTerm);
        return new SearchResultsPage();
    }

    /**
     * Clicks the shopping cart button.
     */
    public void clickCartButton() {
        WebElement cartButton = wait
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("#cart"))));
        cartButton.click();
        LOG.info("Cart button clicked");
    }

    /**
     * Clicks on the "View Cart" button and navigates to the shopping cart page.
     *
     * @return ShoppingCartPage An instance of the shopping cart page.
     */
    public ShoppingCartPage clickOnViewCartPage() {
        String viewCartSelector = "//a[contains(., 'View Cart')]";
        WebElement viewCartButton = driver.findElement(By.xpath(viewCartSelector));
        viewCartButton.click();
        LOG.info("View Cart button clicked");
        return new ShoppingCartPage();
    }
}