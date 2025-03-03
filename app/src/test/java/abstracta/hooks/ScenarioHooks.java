package abstracta.hooks;

import abstracta.framework.selenium.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import abstracta.utils.LoggerManager;

import java.util.logging.Level;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY;
import static org.openqa.selenium.edge.EdgeDriverService.EDGE_DRIVER_SILENT_OUTPUT_PROPERTY;

/**
 * Provides hooks to manage WebDriver lifecycle and scenario execution.
 * This class includes setup and teardown actions before and after scenarios,
 * as well as after each step, especially for UI tests.
 */
public class ScenarioHooks {
    private static final LoggerManager LOG = LoggerManager.getInstance();

    /**
     * Disables verbose logging from WebDriver and related components.
     * This is achieved by setting system properties to silence ChromeDriver and EdgeDriver outputs,
     * and by globally turning off Java logging.
     */
    public void disableOtherJavaLoggers() {
        System.setProperty(CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        System.setProperty(EDGE_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        java.util.logging.Logger.getLogger("").setLevel(Level.OFF);
    }

    /**
     * Executed before each scenario.
     * Initializes the WebDriver instance and disables Java loggers to clean up console output.
     *
     * @param scenario The current Cucumber scenario being executed.
     */
    @Before(order = 1)
    public void beforeScenario(Scenario scenario) {
        LOG.info("Scenario: --> " + scenario.getName());
        disableOtherJavaLoggers();
        DriverManager.getInstance().getWebDriver();
    }

    /**
     * Executed after each step in a scenario.
     * If a step fails and the scenario is tagged with "@UI", it captures a screenshot
     * and attaches it to the scenario report.
     *
     * @param scenario The current Cucumber scenario.
     */
    @AfterStep
    public void afterEachStep(Scenario scenario) {
        if (scenario.isFailed() && scenario.getSourceTagNames().contains("@UI")) {
            TakesScreenshot screenshotManager = (TakesScreenshot) DriverManager.getInstance().getWebDriver();
            byte[] screenshot = screenshotManager.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Step Failure Screenshot");
            LOG.info("Screenshot attached for failed step in scenario: " + scenario.getName());
        }
    }

    /**
     * Executed after all scenarios have completed.
     * Quits the WebDriver instance to close the browser and release resources.
     */
    @After
    public void afterScenario() {
        DriverManager.getInstance().quitWebDriver();
    }
}