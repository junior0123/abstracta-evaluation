package abstracta.steps;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import abstracta.ui.PageTransporter;
import abstracta.ui.components.SearchBarComponent;
import abstracta.ui.components.TopBarComponent;
import abstracta.ui.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Contains step definitions for UI tests related to product search,
 * adding to cart, and shopping cart interactions.
 * This class uses Page Object Model to interact with UI elements
 * and performs assertions to validate the expected behavior.
 */
public class TestSteps {

    private final PageTransporter pageTransporter;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private ProductPage productPage;
    private ShoppingCartPage cartPage;
    private SearchBarComponent searchBar;

    /**
     * Constructor to initialize the PageTransporter, which manages page navigation.
     */
    public TestSteps() {
        this.pageTransporter = PageTransporter.getInstance();
    }

    /**
     * Step definition for navigating to the OpenCart Home Page.
     * Opens the browser and loads the application's home page.
     */
    @Given("I navigate to the OpenCart page")
    public void navigateToOpenCartPage() {
        homePage = pageTransporter.navigateToHomePage();
    }

    /**
     * Step definition for entering a product name in the search bar and submitting the search.
     * Uses the SearchBarComponent to perform the search action.
     *
     * @param productName the name of the product to search for.
     */
    @When("I enter {string} in the search bar and submit the search")
    public void enterProductInSearchBar(String productName) {
        searchBar = new SearchBarComponent();
        searchResultsPage = searchBar.performSearch(productName);
    }

    /**
     * Step definition for selecting the first product from the search results page.
     * Assumes search results are displayed and selects the product at index 0.
     */
    @When("I select the first product from the search results")
    public void selectFirstProduct() {
        productPage = searchResultsPage.selectProduct(0);
    }

    /**
     * Step definition for adding the current product page's product to the shopping cart.
     * Verifies that a success message is displayed after adding the product.
     */
    @When("I add the product to the shopping cart")
    public void addProductToCart() {
        productPage.addToCart();
        assertTrue(productPage.isSuccessMessageDisplayed(), "Success message should be displayed after adding to cart");
    }

    /**
     * Step definition for clicking the shopping cart button, typically located in the header.
     * Uses SearchBarComponent to interact with the cart button.
     */
    @When("I click the shopping cart button")
    public void clickShoppingCartButton() {
        searchBar.clickCartButton();
    }

    /**
     * Step definition for clicking on the "View Cart" button, often found in a dropdown cart menu.
     * Navigates to the full Shopping Cart Page.
     */
    @When("I click on the view cart button")
    public void clickOnViewCartPage() {
        cartPage = searchBar.clickOnViewCartPage();
    }

    /**
     * Step definition to verify if a specific product is present in the shopping cart.
     * Asserts that the product is indeed listed in the cart page.
     *
     * @param productName the name of the product expected to be in the cart.
     */
    @Then("I should see {string} in the shopping cart")
    public void verifyProductInCart(String productName) {
        boolean isProductPresent = cartPage.isProductInCart(productName);
        assertTrue(isProductPresent, "The product " + productName + " should be in the cart");
    }

    /**
     * Step definition to remove a product from the shopping cart.
     * Uses the ShoppingCartPage to perform the product removal.
     *
     * @param productName the name of the product to remove from the cart.
     */
    @When("I remove {string} from the shopping cart")
    public void removeProductFromCart(String productName) {
        cartPage.removeProduct(productName);
    }

    /**
     * Step definition to verify that a specific product is no longer in the shopping cart.
     * Also checks if the "empty cart" message is displayed as a result of removing the product.
     *
     * @param productName the name of the product expected to not be in the cart.
     */
    @Then("I should not see {string} in the shopping cart")
    public void verifyProductNotInCart(String productName) {
        // First, verify that the product is not in the cart
        boolean isProductPresent = cartPage.isProductInCart(productName);
        assertFalse(isProductPresent, "The product " + productName + " should not be in the cart");
        // Then, check if the empty cart message is displayed
        assertTrue(cartPage.isEmptyCartMessageDisplayed(), "The empty shopping cart message is not displayed.");
    }

    /**
     * Step definition to verify that the cart total is zero, indicating an empty cart.
     * Asserts that the cart total displayed on the page is indeed zero.
     */
    @Then("the cart total should be zero")
    public void verifyCartTotalZero() {
        assertTrue(cartPage.isCartTotalZero(), "Cart total is not zero.");
    }
}