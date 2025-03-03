package abstracta.ui.components;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstracta.ui.BasePage;
import abstracta.ui.pages.ShoppingCartPage;

public class TopBarComponent extends BasePage {

    /**
     * Shopping cart button in the top bar.
     */
    @FindBy(css = "[title = 'Shopping Cart']")
    private WebElement shoppingCartButton;

    /**
     * Waits until the shopping cart button is clickable, ensuring the component is
     * loaded.
     *
     * @throws WebDriverException if the element is not clickable within the
     *                            timeout.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        shoppingCartButton = wait.until(ExpectedConditions.elementToBeClickable(shoppingCartButton));
        LOG.info("Shopping cart button is loaded and clickable");
    }

    /**
     * Clicks the shopping cart button in the top bar and navigates to the Shopping
     * Cart page.
     *
     * @return ShoppingCartPage instance representing the Shopping Cart page.
     */
    public ShoppingCartPage clickShoppingCartButton() {
        shoppingCartButton.click();
        LOG.info("Shopping cart button clicked");
        return new ShoppingCartPage();
    }

}