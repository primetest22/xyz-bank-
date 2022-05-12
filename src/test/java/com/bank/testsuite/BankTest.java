package com.bank.testsuite;

import com.bank.pages.*;
import com.bank.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BankTest extends TestBase {
    HomePage homePage;
    BankManagerLoginPage bankManagerLoginPage;
    AddCustomerPage addCustomerPage;
    OpenAccountPage openAccountPage;
    AccountPage accountPage;
    CustomerLoginPage customerLoginPage;
    CustomersPage customersPage;


    @BeforeMethod(alwaysRun = true)
    public void init() {
        homePage = new HomePage();
        bankManagerLoginPage = new BankManagerLoginPage();
        addCustomerPage = new AddCustomerPage();
        openAccountPage = new OpenAccountPage();
        accountPage = new AccountPage();
        customerLoginPage = new CustomerLoginPage();
        customersPage = new CustomersPage();
    }

    @Test(groups = {"sanity","regression"})
    public void bankManagerShouldAddCustomerSuccessfully() throws InterruptedException {
        homePage.clickBankManagerLogin();
        bankManagerLoginPage.addCustomerButton();
        addCustomerPage.firstName("Mahendra");
        addCustomerPage.lastName("Dhoni");
        addCustomerPage.postCode("380050");
        addCustomerPage.addCustomer();
        addCustomerPage.alertTextVerify("Customer added successfully with customer id :6");
        addCustomerPage.acceptAlert();
    }

    @Test(groups = {"sanity","smoke","regression"})
    public void bankManagerShouldOpenAccountSuccessfully() throws InterruptedException {
        bankManagerShouldAddCustomerSuccessfully();
        bankManagerLoginPage.openAccount();
        openAccountPage.selectFromCustomerDropdown("Mahendra Dhoni");
        openAccountPage.selectFromCurrencyDropdown("Pound");
        openAccountPage.clickProcess();
        openAccountPage.alertTextVerify("Account created successfully with account Number :1016");
        openAccountPage.acceptAlert();
    }

    @Test(groups = {"smoke","regression"})
    public void customerShouldLoginAndLogoutSuccessfully() throws InterruptedException {
        bankManagerShouldOpenAccountSuccessfully();
        openAccountPage.backToHomePage();
        homePage.clickCustomerLogin();
        customerLoginPage.selectFromYourNameDropdown("Mahendra Dhoni");
        customerLoginPage.loginButton();
        accountPage.verifyLogoutTabDisplayed();
        accountPage.clickLogoutTab();
        customersPage.verifyDisplayNameMessage("Your Name :");
    }

    @Test(groups = {"regression"})
    public void customerShouldDepositMoneySuccessfully() throws InterruptedException {
        bankManagerShouldOpenAccountSuccessfully();
        openAccountPage.backToHomePage();
        homePage.clickCustomerLogin();
        customerLoginPage.selectFromYourNameDropdown("Mahendra Dhoni");
        customerLoginPage.loginButton();
        accountPage.clickDepositTab();
        accountPage.enterAmount("100");
        accountPage.clickDepositButton();
        accountPage.verifyDepositMessage("Deposit Successful");
    }

    @Test(groups = {"regression"})
    public void customerShouldWithdrawMoneySuccessfully() throws InterruptedException {
        customerShouldDepositMoneySuccessfully();
        openAccountPage.backToHomePage();
        homePage.clickCustomerLogin();
        customerLoginPage.selectFromYourNameDropdown("Mahendra Dhoni");
        customerLoginPage.loginButton();
        accountPage.clickWithdrawalTab();
        accountPage.enterWithdrawlAmount("50");
        accountPage.clickWithdrawlButton();
        accountPage.verifyWithdrawMessage("Transaction successful");
    }
}
