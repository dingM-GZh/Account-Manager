package acctMgr.model;

import java.util.*;
import java.io.*;

public class AccountList extends AbstractModel {
	
	private String fileName;
	private List<Account> acctList; 
	private List<String> windowInfo;
	
	/***
	 *Default constructor when an AccountList object is created
	 */
	public AccountList() {
		fileName = "";
		acctList = new ArrayList<Account>();
		windowInfo = new ArrayList<String>();
	}
	
	/***
	 * Constructor of class AccountList
	 * @param fileName the name of the file that is being read from and written to
	 */
	public AccountList(String fileName) {
		this.fileName = fileName;
		acctList = new ArrayList<Account>();
		windowInfo = new ArrayList<String>();
	}
	
	/***
	 * Loads a list of accounts from a .txt file
	 * @throws IOException error if .txt is unaccessible (cannot be read from)
	 */
	public void loadAcct() throws IOException {
		List<String> fText = new ArrayList<String>();
		BufferedReader buffReader = new BufferedReader(new FileReader(fileName));
		try {
			String text;
			
			while((text = buffReader.readLine()) != null) {
				
				text = text.trim();
				fText.add(text);
			}
			
			for(int i = 0; i < fText.size(); i += 3) {
				Account account = new Account();
				account.setIdNum(fText.get(i));
				account.setName(fText.get(i+1));
				account.setBalance(fText.get(i+2));
				acctList.add(account);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		buffReader.close();
	}
	
	/***
	 * Adds a blank account to the List
	 */
	public void addAcct() {
		Account account = new Account();
		acctList.add(account);
	}
	
	/***
	 * Creates an account with the given parameters, then adds it to the AccountList object
	 * @param name the name of the new account
	 * @param idNum the ID number of the new account
	 * @param balance the current balance of the new account
	 */
	public void addAcct(String name, String idNum, String balance) {
		Account account = new Account();
		account.setIdNum(idNum);
		account.setName(name);
		account.setBalance(balance);
		
		acctList.add(account);
	}
	
	/***
	 * Adds an already initialized account into the AccountList object
	 * @param account the Account object being added to the list
	 */
	public void addAcct(Account account) {
		acctList.add(account);
	}

	/***
	 * Removes an account given the index of the List
	 * @param index the account's location in the list
	 */
	public void removeAcct(int index) {
		Account account = acctList.get(index);
		account = null;
		
		acctList.remove(index);
	}
	
	/***
	 * Removes an account by passing an object as a parameter
	 * @param account the AcctModel object
	 */
	public void removeAcct(Account account) {
		int idNum = account.getIdNum();
		
		int temp = 0;
		for(int i = 0; i < acctList.size(); i++) {
			temp = acctList.get(i).getIdNum();
			
			if(temp == idNum) {
				acctList.remove(i);
			}
		}
	}
	
	/***
	 * Saves the current list of Accounts and writes them to a .txt file
	 * @throws IOException error if .txt is unaccessible (cannot be written to)
	 */
	public void saveList() throws IOException {
		BufferedWriter buffWriter = new BufferedWriter(new FileWriter(fileName));
		try {
			for(int i = 0; i < acctList.size(); i++) {
				Account temp = acctList.get(i);
			
				System.out.println("Printing - " + i);
			
				buffWriter.write(Integer.toString(temp.getIdNum()));
				buffWriter.newLine();
				buffWriter.write(temp.getName());
				buffWriter.newLine();
				buffWriter.write(temp.getBalance().toString());
				buffWriter.newLine();
			} 
			System.out.println("Done writing to file \n");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		buffWriter.flush();
		buffWriter.close();
	}
	
	/***
	 * Method to access Account object from the list 
	 * @param index the specific location of the Account object
	 * @return the specified Account object
	 */
	public Account getAccount(int index) {
		return acctList.get(index);
	}
	
	/***
	 * Method to access the list of Account objects
	 * @return the list of Account objects
	 */
	public List<Account> getAcctList() {
		return acctList;
	}
	
	/***
	 * Method to access the Name and ID of all Account objects to be used as the window title
	 * @return the list of all names and IDs appended together
	 */
	public List<String> getNameID() {
		for(int i = 0; i < acctList.size(); i++) {
			windowInfo.add(acctList.get(i).getIdNum() + " "  + acctList.get(i).getName());
		}
		return windowInfo;
	}
	
	/***
	 * Method to assign the name of the file to be read from/written to 
	 * @param fileName name of the file
	 */
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
}