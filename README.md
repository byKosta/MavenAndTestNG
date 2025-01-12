# Getting Started with Java + Maven + TestNG + Selenium

This guide will help you set up a project using Java, Maven, TestNG, and Selenium for automated testing. Follow the steps below to create your own testing framework and start writing tests.

---

## Prerequisites

Before you start, ensure you have the following tools installed:

1. **Java Development Kit (JDK):**
   - Download and install the latest JDK from [Oracle](https://www.oracle.com/java/technologies/javase-jdk-downloads.html) or [OpenJDK](https://openjdk.org/install/).
   - Verify installation:
     ```bash
     java -version
     ```

2. **Maven:**
   - Download Maven from [Apache Maven](https://maven.apache.org/download.cgi).
   - Verify installation:
     ```bash
     mvn -version
     ```

3. **IntelliJ IDEA (or any preferred IDE):**
   - Download IntelliJ IDEA from [JetBrains](https://www.jetbrains.com/idea/download/).

4. **Web Browser and WebDriver:**
   - Install the browser you plan to test (e.g., Chrome, Firefox).
   - Download the corresponding WebDriver (e.g., [ChromeDriver](https://chromedriver.chromium.org/downloads), [GeckoDriver](https://github.com/mozilla/geckodriver/releases)).

---

## Step 1: Create a Maven Project

1. Open IntelliJ IDEA and create a new project:
   - Go to `File > New > Project`.
   - Select `Maven` as the project type.
   - Choose a Group ID (e.g., `com.example.tests`) and an Artifact ID (e.g., `selenium-tests`).

2. Add the following dependencies to your `pom.xml` file:
   ```xml
   <dependencies>
       <!-- TestNG -->
       <dependency>
           <groupId>org.testng</groupId>
           <artifactId>testng</artifactId>
           <version>7.9.0</version>
           <scope>test</scope>
       </dependency>

       <!-- Selenium -->
       <dependency>
           <groupId>org.seleniumhq.selenium</groupId>
           <artifactId>selenium-java</artifactId>
           <version>4.10.0</version>
       </dependency>

       <!-- WebDriver Manager -->
       <dependency>
           <groupId>io.github.bonigarcia</groupId>
           <artifactId>webdrivermanager</artifactId>
           <version>5.5.3</version>
       </dependency>
   </dependencies>
   ```

3. Reload your Maven project to download the dependencies.

---

## Step 2: Set Up TestNG

1. Create a new directory structure for your tests:
   - `src/main/java` (for main application code, if any).
   - `src/test/java` (for test code).

2. Configure TestNG by creating a `testng.xml` file in the root of your project:
   ```xml
   <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
   <suite name="Test Suite">
       <test name="Sample Test">
           <classes>
               <class name="com.example.tests.SampleTest"/>
           </classes>
       </test>
   </suite>
   ```

3. Write your first TestNG test:
   ```java
   package com.example.tests;

   import org.openqa.selenium.WebDriver;
   import org.openqa.selenium.chrome.ChromeDriver;
   import org.testng.annotations.AfterMethod;
   import org.testng.annotations.BeforeMethod;
   import org.testng.annotations.Test;

   public class SampleTest {

       private WebDriver driver;

       @BeforeMethod
       public void setUp() {
           // Set up WebDriver using WebDriver Manager
           io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
           driver = new ChromeDriver();
           driver.manage().window().maximize();
       }

       @Test
       public void testGoogleHomePage() {
           driver.get("https://www.google.com");
           assert driver.getTitle().contains("Google");
       }

       @AfterMethod
       public void tearDown() {
           if (driver != null) {
               driver.quit();
           }
       }
   }
   ```

---

## Step 3: Run Tests

1. Run tests via the TestNG XML file:
   - Right-click on `testng.xml` and select `Run`.

2. Run tests via Maven:
   ```bash
   mvn test
   ```

---

## Step 4: Learn More

- **Selenium Documentation:** [https://www.selenium.dev/documentation/](https://www.selenium.dev/documentation/)
- **TestNG Documentation:** [https://testng.org/doc/](https://testng.org/doc/)
- **Maven Documentation:** [https://maven.apache.org/](https://maven.apache.org/)
- **WebDriver Manager Documentation:** [https://bonigarcia.dev/webdrivermanager/](https://bonigarcia.dev/webdrivermanager/)

---

## Tips and Best Practices

1. Use Page Object Model (POM) for better test structure.
2. Avoid hardcoding values; use a `config.properties` file for configuration.
3. Use assertions effectively to validate test results.
4. Integrate with CI/CD tools like Jenkins or GitHub Actions for automated test execution.
5. Add logs and screenshots for better debugging.

---

## Troubleshooting

1. **WebDriver version mismatch:**
   - Ensure WebDriver matches the browser version.

2. **Dependencies not downloading:**
   - Run `mvn clean install` to refresh dependencies.

3. **TestNG tests not running:**
   - Ensure `testng.xml` is correctly configured.

---
