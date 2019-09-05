package acctMgr.test;

import acctMgr.model.AccountList;
import acctMgr.model.Account;

import org.junit.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountListTest {

	private static AccountList accountList;
	
	/***
	 * Creates a .txt file of account objects that is going to be read
	 * Also creates an AccountList object to be tested
	 * @throws Exception error that is to be passed up in the hierarchy (not handled in test case)
	 */
	@BeforeClass
	public static void createAcctListObj() throws Exception {
		String fName = "testFile.txt";
		accountList = new AccountList();
		accountList.setFileName(fName);
		
		List<Account> accounts = new ArrayList<Account>();
		
		Account TerryCrews = new Account("TerryCrews", "3", "10.00");
		Account DenzelWA = new Account("DenzelWashington", "1", "100.00");
		Account SamJax = new Account("SamuelL.Jackson", "2", "50.00");
		System.out.println("Sample Accounts Created");
		
		accounts.add(DenzelWA);
		accounts.add(SamJax);
		accounts.add(TerryCrews);
		System.out.println("Sample Accounts Added to Sample ArrayList<Accounts>");
		
		File file = new File(fName);
		BufferedWriter br = new BufferedWriter(new FileWriter(file));
		
		for(int i = 0; i < accounts.size(); i++) {
			br.write(Integer.toString(accounts.get(i).getIdNum()) + '\n');
			br.write(accounts.get(i).getName() + '\n');
			br.write(accounts.get(i).getBalance().toString() + '\n');
		}
		br.flush();
		br.close(); 
	}
	
	/***
	 * Deletes the AccountList object 
	 * @throws Exception error that is to be passed up in the hierarchy (not handled in test case)
	 */
	@AfterClass
	public static void deleteAcctListObj() throws Exception {
		accountList = null;
	}
	
	/***
	 * Tests the loadAcct method of the AccountList object
	 * @throws Exception error that is to be passed up in the hierarchy (not handled in test case)
	 */
	@Test
	public void loadAcctTest() throws Exception {;
		accountList.setFileName("testInput.txt");
		accountList.loadAcct();
		assertFalse("ArrayList<Account> should not be empty", (accountList.getAcctList()).isEmpty());
		
		List<Account> accounts = accountList.getAcctList();
		for(int i = 0; i < accounts.size(); i++) {
			System.out.println(accounts.get(i).getIdNum());
			System.out.println(accounts.get(i).getName());
			System.out.println(accounts.get(i).getBalance().toString() + '\n');
		}
	}
	
	/***
	 * Tests the saveList method of the AccountList object
	 * @throws Exception error that is to be passed up in the hierarchy (not handled in test case)
	 */
	@Test
	public void saveList() throws Exception {
		String outFileName = "testOutput.txt";
		accountList.setFileName(outFileName);
		
		accountList.saveList();
		
		File temp = new File(outFileName);
		assertTrue("Ouput File Not Found", temp.exists());
	}
	
	/***
	 * Tests all of the acctAcct methods of the AccountList object
	 * @throws Exception error that is to be passed up in the hierarchy (not handled in test case)
	 */
	@Test
	public void addAcctTest() throws Exception{
		assertTrue("ArrayList of Account objects should be EMPTY", (accountList.getAcctList()).isEmpty());
		
		accountList.addAcct();
		accountList.addAcct("Null_1", "-1", "0.0");
		accountList.addAcct(new Account("Null_2", "-2", "0.0"));
		
		assertFalse("ArrayList of Account objects should HAVE OBJECTS", (accountList.getAcctList()).isEmpty());
	}
}