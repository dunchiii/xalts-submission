# Xalts QA Assignment – Dhruvansh Tanwar

This repository contains the solution for the QA Automation assignment provided by Xalts.

---

## Assignment Breakdown

### 1. Automated Testing 
- Automated test cases for:
  - **Sign Up**
  - **Sign In**
  - **Sign Out**
- Built with **Selenium + TestNG + PageObjectModel + PageFactory + ExtentReports**
- Includes:
  - Grouped execution (`Smoke`, `Regression`)
  - Screenshot capture for both passed and failed tests
  - ExtentReports integration

### 2. Manual Test Plan 
- Covers functional, validation, negative, and integration scenarios
- Includes 22+ test cases
- Delivered in Excel format

---

## Key Files

- `SignUpTest.java`, `SignInTest.java`, `SignOutTest.java` – core automated tests
- `TestPlan.xlsx` – full test plan (manual + automated)
- `Extent-Report-Output/extent-report.html` – HTML test execution report
- `screenshots/` – screenshots of test steps
- `testng-smoke.xml` & `testng-regression.xml` – grouped test suites

---

## Run Instructions

```bash
mvn test
