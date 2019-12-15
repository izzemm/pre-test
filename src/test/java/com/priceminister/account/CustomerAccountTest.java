package com.priceminister.account;


import static org.junit.Assert.*;

import com.priceminister.account.exceptions.IllegalBalanceException;
import org.junit.*;

import com.priceminister.account.implementation.*;
import org.junit.rules.ExpectedException;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {
    
    Account customerAccount;
    AccountRule rule;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
        Assert.assertEquals(new Double(0), customerAccount.getBalance());
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {
        customerAccount.add(10d);
        Assert.assertEquals(new Double(10d), customerAccount.getBalance());
    }
    
    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     */
    @Test
    public void testWithdrawAndReportBalanceIllegalBalance() throws Exception {
        Double withdrawnAmount = 20.0;
        expectedEx.expect(IllegalBalanceException.class);
        expectedEx.expectMessage("Illegal account balance: " + withdrawnAmount);
        rule = new CustomerAccountRule();
        customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
    }

    // Also implement missing unit tests for the above functionalities.

    @Test
    public void testWithdrawAndReportBalance() throws Exception {
        customerAccount.add(100d);
        rule = new CustomerAccountRule();
        Double withdrawnAmount = 10.0;
        Double expectedBalance = 90.0;
        Double resultingAccountBalance = customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
        assertEquals(withdrawnAmount, resultingAccountBalance);
        assertEquals(expectedBalance, customerAccount.getBalance());
    }

    @Test
    public void testWithdrawAndReportBalanceIllegalBalance_andCheckThatBalanceNotModified() {
        customerAccount.add(10d);
        rule = new CustomerAccountRule();
        Double withdrawnAmount = 20.0;
        Double expectedBalance = 10.0;
        try {
            customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalBalanceException);
            assertEquals("Illegal account balance: " + withdrawnAmount, e.getMessage());
        } finally {
            assertEquals(expectedBalance, customerAccount.getBalance());
        }
    }

    @Test
    public void testWithDrawZeroAmount() throws Exception {
        expectedEx.expect(Exception.class);
        expectedEx.expectMessage("The with drawn amount (" + 0.0 + ") should be upper than zero");
        customerAccount.withdrawAndReportBalance(0.0, rule);
    }

    @Test
    public void testWithDrawWith_negativeDrawnAmount() throws Exception {
        expectedEx.expect(Exception.class);
        expectedEx.expectMessage("The with drawn amount (-10.0) should be upper than zero");
        customerAccount.withdrawAndReportBalance(-10.0, rule);
    }

    @Test
    public void testWithNotInitializedRule() throws Exception {
        expectedEx.expect(Exception.class);
        expectedEx.expectMessage("Rule not initialized");
        Double resultingAccountBalance = customerAccount.withdrawAndReportBalance(10.0, rule);
    }
}
