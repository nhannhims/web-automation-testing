# Java Selenium & RestAssured Automation Framework

A professional-grade automation framework designed for end-to-end (E2E) testing of the [Automation Exercise](https://automationexercise.com) platform. This framework combines UI automation with API capabilities to ensure robust, fast, and maintainable test execution.

## 🚀 Key Features

- **Hybrid Testing (UI + API)**: Leveraging API calls for pre-test setup and post-test cleanup to eliminate data dependency and "zombie" accounts.
- **Page Object Model (POM)**: Organized architecture with a centralized `PageFactoryManager` for easy maintenance.
- **Data-Driven Testing (DDT)**: Full integration with CSV files for dynamic test data management (Registration, Login, Contact Us).
- **Dynamic Data Generation**: Custom `RandomGenerator` utility for generating unique user profiles (Email, Address, Names) on the fly.
- **Multi-Environment Support**: Configurable environment settings (Staging, Production) via `.properties` files.
- **Professional Reporting**: Integrated with **Allure Reports**, providing detailed steps, screenshots on failure, and environment metadata.
- **Advanced Logging**: Detailed execution logs using **Log4j2** and custom `LogUtils`.
- **Parallel Execution**: Configured for parallel test execution via TestNG to reduce total run time.
- **CI/CD Ready**: GitHub Actions workflow integration for automated testing on every push.

## 🛠 Tech Stack

- **Core**: Java 11
- **UI Automation**: Selenium WebDriver 4.27.0
- **API Testing**: RestAssured 5.5.0
- **Test Runner**: TestNG 7.10.2
- **Reporting**: Allure Framework 2.29.0
- **Utilities**: Lombok, OpenCSV, WebDriverManager
- **Build Tool**: Maven

## 📁 Project Structure

```text
src
├── main/java/com/demo
│   ├── api/            # API client implementations (RestAssured)
│   ├── constants/      # Framework and Test data constants
│   ├── models/         # POJO models (Lombok used for Builders)
│   ├── pages/          # Page Object classes (POM)
│   └── utils/          # Core utilities (Logging, Config, Random, CSV)
└── test
    ├── java/com/demo
    │   ├── tests/      # Functional Test Suites (UI & API)
    │   └── setup/      # Test base and lifecycle management
    └── resources/      # CSV data, environment configs, and TestNG suites
```

## 📋 Prerequisites

- Java JDK 11 or higher
- Apache Maven
- Chrome Browser (Driver managed by WebDriverManager)

## 🏃 How to Run

1.  **Clone the repository**.
2.  **Run all tests** (Default environment):
    ```bash
    mvn clean test
    ```
3.  **Run tests on specific environment**:
    ```bash
    mvn clean test -Denv=staging
    ```
4.  **Generate and view report**:
    ```bash
    allure serve allure-results
    ```

## 📝 Test Scenarios

- **Registration**: 
    - Full E2E User Registration with dynamic data.
    - Negative test: Registration with an existing email (using API setup).
- **Authentication**: 
    - Valid/Invalid login flows using CSV data.
- **Contact Us**:
    - Form submission with file upload verification.
- **Product Verification**:
    - Verify all products and product detail page (Category, Price, Availability, etc.).
- **API**: 
    - End-to-end user account lifecycle (Create -> Delete) via REST API.

## ⚙️ CI/CD

This project uses **GitHub Actions** for continuous integration. The workflow is defined in `.github/workflows/maven.yml` and triggers on:
- Pushes to the `main` branch.
- Pull requests to the `main` branch.

---
*Developed with best practices for scalability and reliability.*

