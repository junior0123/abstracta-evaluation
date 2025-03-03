package abstracta.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import abstracta.ui.BasePage;


public class HomePage extends BasePage {

    /**
     * Slide show element of the home page.
     */
    @FindBy(css = ".swiper-viewport #slideshow0")
    private WebElement slideShowElement;

    /**
     * Content element of the home page.
     */
    @FindBy(css =  "#content .row")
    private WebElement content;

    /**
     * Constructor to initialize the HomePage elements and wait until the page is fully loaded.
     */
    public HomePage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
        LOG.info("Initialized HomePage with WebDriver and Wait instances");
    }

    /**
     * Waits until the home page elements are fully loaded.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {
        LOG.info("Waiting until HomePage elements are loaded");
        slideShowElement = wait.until(ExpectedConditions.visibilityOf(slideShowElement));
        content = wait.until(ExpectedConditions.visibilityOf(content));
        LOG.info("HomePage elements are loaded");
    }
}
