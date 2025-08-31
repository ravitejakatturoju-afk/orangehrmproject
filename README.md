# OrangeHRM Selenium Test Automation

Automated test suite for OrangeHRM demo application using Selenium WebDriver and TestNG.

## Project Structure
```
src/
├── main/java/com/orangehrm/
│   ├── pages/          # Page Object Model classes
│   └── utils/          # Utility classes
├── main/resources/     # Configuration files
└── test/java/com/orangehrm/
    └── tests/          # Test classes
```

## Prerequisites
- Java 11+
- Maven 3.6+
- Chrome browser

## Running Tests

### Headed Mode (Default)
```bash
mvn test
```

### Headless Mode
```bash
mvn test -Dheadless=true
```

### Specific Test Suite
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## Test Coverage
- **LoginTest**: Valid/invalid login scenarios
- **DashboardTest**: Dashboard functionality verification

## Features
- Page Object Model design pattern
- Reusable utilities and test data
- Headless/headed mode support
- Proper test organization with TestNG
- Explicit waits for stability

## Test Data
- Valid credentials: Admin/admin123
- Invalid scenarios handled automatically