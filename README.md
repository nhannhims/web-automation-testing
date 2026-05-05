# Selenium Java Automation Framework

A professional, scalable, and maintainable automation testing framework built with Java, TestNG, and Selenium WebDriver. This project follows the **Page Object Model (POM)** design pattern and includes advanced features for enterprise-level automation.

## 🚀 Tech Stack

*   **Language:** Java 11
*   **Build Tool:** Maven
*   **Test Runner:** TestNG
*   **Automation Tool:** Selenium WebDriver (v4.27.0)
*   **Browser Management:** WebDriverManager
*   **Data Driven:** OpenCSV (CSV files)
*   **Reporting:** Allure Report
*   **Logging:** Log4j2
*   **CI/CD:** GitHub Actions

## 📁 Project Structure

```text
src
├── main/java/com/demo
│   ├── constants/       # Global constants (Timeouts, Navigation links)
│   ├── drivers/         # WebDriver Factory & ThreadLocal Manager
│   ├── listeners/       # TestNG Listeners (Retry, Transformer, TestListener)
│   ├── pages/           # Page Objects (POM) & BasePage
│   └── utils/           # Utility classes (LogUtils, Config reader, CSV reader)
├── test/java/com/demo
│   └── tests/           # Test script classes
└── test/resources
    ├── configs/         # Environment properties (staging, prod, config)
    ├── data/            # Test data files (login_data.csv)
    └── testng.xml       # Parallel execution suite configuration
```

## ✨ Key Features

1.  **Page Object Model (POM):** Clean separation of UI locators and test logic.
2.  **ThreadLocal WebDriver:** Supports safe **Parallel Execution** across multiple browsers.
3.  **Flaky Test Management:** Integrated `IRetryAnalyzer` to automatically retry failed tests.
4.  **Multi-Environment Support:** Switch between `staging`, `prod`, or `dev` using simple command-line arguments.
5.  **Headless Mode:** Pre-configured support for running tests without a GUI (ideal for CI/CD).
6.  **Advanced Interactions:** `BasePage` includes methods for JS click, scroll, hover, double click, and more, all wrapped with error handling.
7.  **Professional Logging:** Log4j2 integration with specialized `LogUtils` for structured console and file logging.
8.  **GitHub Actions Integration:** CI/CD pipeline configured to run tests automatically on every push.
9.  **Allure Report:** Beautiful HTML reports with failure screenshots and detailed steps.

## 🛠️ Getting Started

### Prerequisites
*   Java 11 or higher
*   Maven installed
*   Browser (Chrome/Firefox/Edge) installed

### Running Tests

#### 1. Default Run (using config.properties)
```bash
mvn clean test
```

#### 2. Run on Specific Environment
```bash
mvn clean test -Denv=staging
mvn clean test -Denv=prod
```

#### 3. Run in Parallel with Headless Mode
```bash
mvn clean test -Dheadless=true -Denv=staging
```

#### 4. Specify Browser
```bash
mvn clean test -Dbrowser=firefox
```

### Generating Allure Report
After running the tests, use these commands to view the report:
```bash
# To generate and open the report in your default browser
mvn allure:serve

# Or to just generate the static report files
mvn allure:report
```

## 📊 CI/CD with GitHub Actions
The project includes a `.github/workflows/maven.yml` file. Once you push your code to GitHub, the tests will automatically run on the GitHub runner in **headless mode**. You can view the results and download screenshots from the **Actions** tab in your repository.

---
*Created by Co-Well Education Automation Team*
