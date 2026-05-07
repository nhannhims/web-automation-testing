# Java Selenium & RestAssured Automation Framework

A professional-grade automation framework designed for end-to-end (E2E) testing of the [Automation Exercise](https://automationexercise.com) platform. This framework combines UI automation with API capabilities to ensure robust, fast, and maintainable test execution.

## 🚀 Key Features

- **Hybrid Testing (UI + API)**: Leveraging API calls for pre-test setup and post-test cleanup to eliminate data dependency and "zombie" accounts.
- **Page Object Model (POM)**: Organized architecture with a centralized `PageFactoryManager` for easy maintenance.
- **Data-Driven Testing (DDT)**: Full integration with CSV files for dynamic test data management.
- **Dynamic Data Generation**: Custom `RandomGenerator` utility for generating unique user profiles (Email, Address, Names) on the fly.
- **Professional Reporting**: Integrated with **Allure Reports**, providing detailed steps, screenshots on failure, and environment metadata.
- **Advanced Logging**: Detailed execution logs using **Log4j2** and custom `LogUtils`.
- **Parallel Execution**: Configured for parallel test execution via TestNG to reduce total run time.
- **Resilient Teardown**: Defensive cleanup mechanisms to ensure a clean state even after test failures.

## 🛠 Tech Stack

- **Core**: Java 11
- **UI Automation**: Selenium WebDriver 4.27.0
- **API Testing**: RestAssured 5.5.0
- **Test Runner**: TestNG 7.10.2
- **Reporting**: Allure Framework
- **Data Processing**: OpenCSV
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
    └── resources/      # CSV data, config files, and TestNG suites
```

## 📋 Prerequisites

- Java JDK 11 or higher
- Apache Maven
- Chrome Browser (Driver managed by WebDriverManager)

## 🏃 How to Run

1.  **Clone the repository**.
2.  **Run all tests**:
    ```bash
    mvn clean test
    ```
3.  **Generate and view report**:
    ```bash
    allure serve allure-results
    ```

## 📝 Test Scenarios

- **Registration**: 
    - Full E2E User Registration with dynamic data.
    - Negative test: Registration with an existing email (using API setup).
- **Authentication**: 
    - Valid/Invalid login flows using CSV data.
- **API**: 
    - End-to-end user account lifecycle (Create -> Delete) via REST API.

---
*Developed with best practices for scalability and reliability.*
