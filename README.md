# Abstracta Technical Evaluation Automation


This project is an automated UI test suite developed for the Abstracta technical evaluation.  Built using **Selenium with Java**, along with **Cucumber** and **JUnit**, it demonstrates a complete end-to-end test on the [http://opencart.abstracta.us/](http://opencart.abstracta.us/) website, executed via a **GitHub Actions pipeline**.  The project is structured using the **Page Object Model (POM)** design pattern.

The primary goal of this project is to **successfully fulfill all specified requirements for the technical evaluation**.  The automated test implements the required steps of searching for and managing an "iPhone" product in the shopping cart.  Furthermore, this project showcases best automation practices and a robust, fully integrated solution deployed via CI/CD.

## Software Requirements

Before running the tests, ensure you have the following software installed on your system:

*   **Java Development Kit (JDK) 17 or higher**:  The project is configured to use Java 17. You can download it from [Oracle Java Downloads](https://www.oracle.com/java/technologies/javase-downloads.html) or [Adoptium](https://adoptium.net/).
*   **Gradle**:  The project uses Gradle as its build automation tool.  While the project includes a Gradle wrapper (`gradlew` and `gradlew.bat`), you can also install Gradle separately from [Gradle Releases](https://gradle.org/releases/).
*   **Web Browsers**: You need to have the browsers you intend to test on installed. This project is configured to run tests on:
    *   **Google Chrome**
    *   **Mozilla Firefox**
    *   **Microsoft Edge**
    You will also need the corresponding WebDriver executables for your browsers. This project is set up to manage WebDriver executables automatically via Selenium Manager, but ensure your browsers are correctly installed.

## Setup and Execution Instructions

Follow these steps to set up and run the automated tests:

1.  **Clone the Repository**:
    Clone the project repository to your local machine using Git:
    ```bash
    git clone git@github.com:junior0123/abstracta-evaluation.git
    ```
    ```bash
    cd abstracta-evaluation
    ```
2.  **Build the Project using Gradle Wrapper:**
    
      ```bash
      ./gradlew build
      ```

This command will download dependencies (if it's the first time) and build the project

2.  **Navigate to the Project Directory**:
    Ensure you are in the root directory of the cloned project.

3.  **Configuration**:
    The project uses property files for configuration:

    *   **`environments.properties` (src/main/resources/environments.properties)**: This file is used to define environment-specific settings such as base URLs and user credentials.

        Example:
        ```properties
        local.baseURL=http://opencart.abstracta.us/
        ```
        You can modify these values to match your test environment.

    *   **`webdriver.properties` (src/main/resources/webdriver.properties)**: This file configures WebDriver settings like timeouts, implicit wait, polling time, and headless mode.

        Example:
        ```properties
        webdriver.implicit.wait.time=5000
        webdriver.timeout=10000
        webdriver.polling.time=1000
        webdriver.headless.mode=false
        ```
        Adjust these settings as needed for your test execution. `webdriver.headless.mode` controls headless browser execution (`true` for headless, `false` for visible browser).

4.  **Execute Tests**:
    You can execute the tests using Gradle tasks.  Use the Gradle wrapper (`gradlew` for Linux/macOS, `gradlew.bat` for Windows) provided with the project.

    *   **Run All UI Tests**: To execute all tests tagged with `@UI`, use the following command:
        ```bash
        gradle clean executeFeatures -PcucumberOptions="@UI"
        ```
        or
        
        ```bash
        ./gradlew clean executeFeatures -PcucumberOptions="@UI"
        ```
    *   **Run Tests with Specific Tags**: You can target specific scenarios or features using Cucumber tags. For example, to run only `@Cart` scenarios:
        ```bash
        gradle clean executeFeatures -PcucumberOptions="@Cart"
        ```
       
    *   **Select Browser**: You can specify the browser to use for test execution using the `-Pbrowser` property. Supported browsers are `chrome`, `edge`, and `firefox`. For example, to run tests in Edge:
        ```bash
        gradle clean executeFeatures -PcucumberOptions="@UI" -Pbrowser="edge"
        ```

    *   **Run in Headless Mode**: To execute tests in headless mode, set the `-PheadlessMode` property to `true`:
        ```bash
        gradle clean executeFeatures -PcucumberOptions="@UI" -Pbrowser="chrome" -PheadlessMode="true"
        ```
        To run in non-headless mode (visible browser), set it to `false` or omit the property (default is `false`).

    *   **Example Command with Browser and Headless Mode**: To run UI tests in Edge browser in headless mode:
        ```bash
        gradle clean executeFeatures -PcucumberOptions="@UI" -Pbrowser="edge" -PheadlessMode="True"
        ```


## Test Reports

After executing the tests, you can find the following reports:

*   **Cucumber HTML Reports**: Detailed HTML reports are generated after each test execution. You can find them at: `app\reports\cucumber-html-reports\overview-features.html`. Open this file in your browser to view the test execution results, feature summaries, and scenario details.


## Logs

For debugging and detailed information about test execution, log files are generated in the `logs` directory at the root of the project. Review these logs to troubleshoot issues or understand the test flow.

## Project Structure

The project is structured as follows:
## Project Structure


*   **`src/main/java`**: Contains the main application code, including:
    *   `framework`: Selenium WebDriver setup and configuration.
    *   `ui/pages`: Page Object classes representing web pages.
    *   `utils`: Utility classes like LoggerManager.
*   **`src/test/java`**: Contains test-related code:
    *   `hooks`: Cucumber hooks for setup and teardown.
    *   `steps`: Cucumber step definitions.
*   **`src/test/resources/features`**: Cucumber feature files written in Gherkin.
*   **`src/main/resources`**: Configuration files (`environments.properties`, `webdriver.properties`, `log4j2.properties`).
*   **`reports`**: Directory for generated Cucumber HTML reports.
*   **`logs`**: Directory for application log files.
*   **`Github`** Directory for the pipeline execution with yml file.

## Pipeline Execution and Report

The execution of the Continuous Integration (CI) pipeline, using GitHub Actions, is publicly visible at:

[https://github.com/junior0123/abstracta-evaluation/actions](https://github.com/junior0123/abstracta-evaluation/actions)

To download the test execution report, please follow these steps:

1. **Navigate to the GitHub Actions link above.**
2. **Locate the latest successful workflow run.**  (Look for a run with a green checkmark and the status "Success").
3. **Click on the successful workflow run.**
4. **Scroll down to the "Artifacts" section.** This section is usually located at the bottom of the workflow run summary.
5. **Download the report artifact.** The report file (e.g., a .zip or .html file) will be available for download in the "Artifacts" section.

This report provides detailed information about the test execution results.

## About the Author 👨‍💻

This project was created by **Alvaro Sivila**, a dedicated QA Automation Engineer with expertise in various automation tools and frameworks. If you're interested in my work, feel free to check out my portfolio or connect with me on LinkedIn:

- **Portfolio:** [Portfolio](https://junior0123.github.io/QAPortfolio/)
- **LinkedIn:** [Alvaro Sivila](https://www.linkedin.com/in/alvaro-sivila-ram%C3%ADrez-0a8537113/)