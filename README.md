# Selenium & RestAssured Hybrid Automation Framework

A professional, scalable, and maintainable **Hybrid Automation Framework** built with Java, TestNG, Selenium WebDriver, and RestAssured. This project follows the **Page Object Model (POM)** for UI and a **Model-driven** approach for API testing.

## 🚀 Tech Stack

*   **Language:** Java 11
*   **Build Tool:** Maven
*   **UI Automation:** Selenium WebDriver (v4.27.0)
*   **API Automation:** RestAssured (v5.5.0)
*   **Test Runner:** TestNG
*   **Data Management:** Lombok (Builders & POJOs)
*   **Reporting:** Allure Report
*   **Logging:** Log4j2
*   **CI/CD:** GitHub Actions

## 📁 Project Structure

```text
src
├── main/java/com/demo
│   ├── api/             # API Actions & Endpoints (UserAPI)
│   ├── constants/       # Global constants (API Paths, Timeouts)
│   ├── data/            # Data Interfaces (IUserData)
│   ├── drivers/         # WebDriver Factory & ThreadLocal
│   ├── listeners/       # TestNG Listeners (Retry, Allure)
│   ├── models/          # Data Models (UserModel POJO)
│   ├── pages/           # Page Objects (POM)
│   └── utils/           # Utilities (APIUtils, LogUtils, ConfigReader)
├── test/java/com/demo
│   └── tests/           # UI and API Test Cases
└── test/resources
    ├── configs/         # Environment properties (staging, prod)
    └── testng.xml       # Parallel execution suite configuration
```

## ✨ Key Features

1.  **Hybrid Automation:** Seamlessly combine UI and API testing in a single project.
2.  **Parallel Execution:** ThreadLocal WebDriver for safe concurrent testing.
3.  **Professional API Support:** Centralized `APIUtils` for GET, POST (JSON & Form), PUT, DELETE with automated logging.
4.  **Model-Driven Data:** Using **Lombok** `@Builder` and `UserModel` for professional data handling instead of raw Maps.
5.  **Multi-Environment Support:** Switch between `staging`, `prod` via `-Denv` command. Base URL and API Paths are separated for maximum flexibility.
6.  **Flaky Test Management:** Integrated `IRetryAnalyzer` to handle unstable tests.
7.  **Headless Mode:** Ready for CI/CD with optimized headless browser configurations.
8.  **GitHub Actions:** Full CI/CD pipeline integrated.

## 🛠️ Getting Started

### Running Tests

#### 1. Run all tests (UI & API)
```bash
mvn clean test
```

#### 2. Run on specific environment
```bash
mvn clean test -Denv=staging
```

#### 3. Run in Headless mode (CI/CD)
```bash
mvn clean test -Dheadless=true
```

### Generating Allure Report
```bash
mvn allure:serve
```

## 📊 API Testing Usage
You can call API actions directly in your tests to prepare data or verify backend:
```java
UserModel user = getValidUser();
UserAPI.createAccount(user); // Quick data creation via API
```

---
*Created by Co-Well Education Automation Team*
