package acctMgr.test;

import org.junit.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import acctMgr.model.Account;
import acctMgr.model.OverdrawException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountTest {
	private static Account account = null;
	
	/***
	 * Creates an Account object to be tested
	 * @throws Exception error that is to be passed up in the hierarchy (not handled in test case)
	 */
	@BeforeClass
	public static void createAcctObj() throws Exception{
		account = new Account();
		account.setBalance("0.0");
		System.out.println("AcctModel object created! \n");
	}
	
	/***
	 * Deletes created Account object
	 * @throws Exception error that is to be passed up in the hierarchy (not handled in test case)
	 */
	@AfterClass
	public static void deleteAcctObj() throws Exception {
		account = null;
		System.out.println("AcctModel object cleared.");
	}
	
	/***
	 * Tests the deposit method of the Account object
	 * @throws Exception error that is to be passed up in the hierarchy (not handled in test case)
	 */
	@Test
	public void depositTest() throws Exception {
		System.out.println("Running testDeposit()");
		System.out.println("Original Balance is: " + account.getBalance());
		
		account.setBalance("0.0");
		account.deposit("-1.0");
		assertEquals("-1.0 deposited - amounts cannot be negative", new BigDecimal("0.0"), account.getBalance());
		
		account.deposit("0.9");
		assertEquals("0.9 deposited - amounts must be greater than 1.0", new BigDecimal("0.0"), account.getBalance());;
		
		account.setBalance("0.0");
		account.deposit("100.0");
		assertEquals("Balance should be a 100.0", new BigDecimal("100.0"), account.getBalance());
		
		System.out.println("Ending Balance is: " + account.getBalance());
	}
	
	/***
	 * Tests the withdraw method of the Account object
	 * Also makes sure that the OverdrawException operates properly
	 * @throws OverdrawException error that occurs when the withdrawal amount is greater than the balance
	 */
	@Test
	public void withdrawTest() throws OverdrawException {
		System.out.println("Running testWithdraw");
		System.out.println("Original Balance is: " + account.getBalance());
		
		account.withdraw("150");
		assertEquals("account is not 0.0", new BigDecimal("0.0"), account.getBalance());
		
		account.withdraw("50");
		assertEquals("Balance is not 50", new BigDecimal("50"), account.getBalance());
	}
}