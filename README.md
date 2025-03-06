# **Jupiter Toys Test Automation Framework** 

A **Test Automation Framework** for **Jupiter Toys**, built using **Java, Selenium, TestNG, and Extent Reports**.

---

## ** Features**
 **Selenium WebDriver** – Automates UI testing  
 **TestNG** – Manages test execution  
 **Extent Reports** – Generates detailed HTML test reports  
 **Maven** – Handles dependencies and build automation  
 **Page Object Model (POM)** – Enhances code maintainability  

---

## ** Prerequisites**
Ensure you have the following installed:
- **Java 17** → [Download Here](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Maven** → [Download Here](https://maven.apache.org/download.cgi)
- **Git** → [Download Here](https://git-scm.com/downloads)
- **Google Chrome** (Latest version)
- **ChromeDriver** (Ensure it matches your Chrome version)

---

## **Setup & Installation**

### **1️⃣ Clone the repository**
```sh
  git clone https://github.com/Kaushik-Sorathiya/Jupitertoys-Test-Framework.git
  cd Jupitertoys-Test-Framework
```

### **2️⃣ Install dependencies**
```sh
  mvn clean install
```

### **3️⃣ Run tests**
```sh
  mvn test
```

---

## ** View Test Report**
After execution, the **Extent Report** is generated at:
 `target/extent-reports/index.html`

Open the **index.html** file in a browser to view the results.

---

## ** Test Cases Implemented**

### **Test Case 1: Contact Page Error Validation**
1. From the home page, go to the contact page.
2. Click the submit button.
3. Verify error messages.
4. Populate mandatory fields.
5. Validate that errors are gone.

### **Test Case 2: Contact Page Form Submission**
1. From the home page, go to the contact page.
2. Populate mandatory fields.
3. Click the submit button.
4. Validate the successful submission message.
 **Note:** This test runs **5 times** with a DataProvider to ensure a 100% pass rate.

### **Test Case 3: Cart and Checkout Validation**
1. Buy **2 Stuffed Frog**, **5 Fluffy Bunny**, and **3 Valentine Bear**.
2. Go to the cart page.
3. Verify the subtotal for each product is correct.
4. Verify the price for each product.
5. Verify that **total = sum(subtotals)**.

---

## ** Technologies Used**
- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Extent Reports**
- **Maven**
- **GitHub Actions** (CI/CD Integration)

---

## ** Customize Test Execution**
Modify the **TestNG XML** to include/exclude test cases:  
 `src/test/resources/testng.xml`

---
