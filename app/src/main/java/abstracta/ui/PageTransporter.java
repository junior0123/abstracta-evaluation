package abstracta.ui;

import abstracta.framework.selenium.DriverManager;
import abstracta.framework.*;
import org.openqa.selenium.WebDriver;
import abstracta.ui.pages.*;
import abstracta.utils.LoggerManager;

/**
 * Singleton class to manage navigation between pages.
 */
public class PageTransporter {
    private static final LoggerManager LOG = LoggerManager.getInstance();
    private static final CredentialsManager CREDENTIALS_MANAGER = CredentialsManager.getInstance();
    private String homePageURL;

    private WebDriver driver;
    private static PageTransporter instance;

    /**
     * Protected constructor to initialize the PageTransporter instance.
     */
    protected PageTransporter() {
        initialize();
    }

    /**
     * Returns the singleton instance of PageTransporter.
     *
     * @return the singleton instance of PageTransporter
     */
    public static PageTransporter getInstance() {
        if (instance == null) {
            instance = new PageTransporter();
        }
        return instance;
    }

    /**
     * Initializes the PageTransporter with the WebDriver and base URL.
     */
    private void initialize() {
        LOG.info("Initializing Page Transporter");
        this.driver = DriverManager.getInstance().getWebDriver();
        this.homePageURL = CREDENTIALS_MANAGER.getBaseURL();
    }

    /**
     * Navigates to the specified URL.
     *
     * @param url the URL to navigate to
     */
    private void goToURL(String url) {
        LOG.info("Navigating to URL: " + url);
        driver.navigate().to(url);
    }

    /**
     * Navigates to the Home page.
     *
     * @return a new instance of LoginPage
     */
    public HomePage navigateToHomePage() {
        LOG.info("Navigating to Home Page");
        goToURL(homePageURL);
        return new HomePage();
    }
}
