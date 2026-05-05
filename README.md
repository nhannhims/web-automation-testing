# Selenium Java Automation Framework

A professional, scalable, and maintainable automation testing framework built with Java, TestNG, and Selenium WebDriver. This project follows the **Page Object Model (POM)** design pattern and includes advanced features like **Data Driven Testing (DDT)** and **Allure Reporting**.

## 🚀 Tech Stack

*   **Language:** Java 11
*   **Build Tool:** Maven
*   **Test Runner:** TestNG
*   **Automation Tool:** Selenium WebDriver (v4.27.0)
*   **Browser Management:** WebDriverManager
*   **Data Driven:** OpenCSV (CSV files)
*   **Reporting:** Allure Report
*   **Logging:** Log4j2

## 📁 Project Structure

```text
src
├── main/java/com/demo
│   ├── constants/       # Global constants (Timeouts, Navigation links)
│   ├── drivers/         # WebDriver Factory & ThreadLocal Manager
│   ├── listeners/       # TestNG Listeners (Allure integration, Screenshots)
│   ├── pages/           # Page Objects (POM)
│   └── utils/           # Utility classes (CSV reader, Config reader, Navigation)
├── test/java/com/demo
│   └── tests/           # Test script classes
└── test/resources
    ├── configs/         # Environment properties (config.properties)
    ├── data/            # Test data files (login_data.csv)
    └── testng.xml       # Test execution suite configuration
```

## ✨ Key Features

1.  **Page Object Model (POM):** Clean separation of UI locators and test logic.
2.  **ThreadLocal WebDriver:** Supports safe parallel execution across multiple browsers.
3.  **Driver Factory:** Isolated logic for Chrome, Firefox, and Edge browsers.
4.  **Dynamic Locators:** Smart `BasePage` methods that auto-detect locator types (XPath, CSS, ID) and support dynamic value formatting.
5.  **Data-Driven Testing:** Execute test cases with multiple datasets stored in external CSV files.
6.  **Config Management:** Environment-specific settings (URLs, timeouts) managed via `.properties` files.
7.  **Allure Report:** Beautiful, interactive HTML reports with automated failure screenshots and step-by-step documentation.

## 🛠️ Getting Started

### Prerequisites
*   Java 11 or higher
*   Maven installed
*   Browser (Chrome/Firefox/Edge) installed

### Configuration
Update the application URL and other settings in:
`src/test/resources/configs/config.properties`

### Running Tests
Execute all tests defined in `testng.xml`:
```bash
mvn clean test
```

### Generating Allure Report
After running the tests, use these commands to view the report:
```bash
# To generate and open the report in your default browser
mvn allure:serve

# Or to just generate the static report files
mvn allure:report
```

## 📊 Test Data
Test data is managed in `src/test/resources/data/login_data.csv`. You can add new test cases or update existing credentials directly in this file without changing the code.

---
*Created by Antigravity Automation Team*
