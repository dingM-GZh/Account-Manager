package acctMgr.model;

import java.math.BigDecimal;

import acctMgr.model.ModelEvent.EventKind;

public class Account extends AbstractModel {
	
	private String name;
	private int idNum;
	private BigDecimal balance;
	
	/***
	 * Default constructor when an Account object is created
	 */
	public Account() {
		name = "-";
		idNum = 0;
		balance = new BigDecimal("0.0");
	}
	
	/***
	 * Overloaded constructor to initialize member variables of a newly created Account object
	 * @param name the Account holder's name
	 * @param idNum identifying number of the Account object
	 * @param balance the amount of money stored in the Account
	 */
	public Account(String name, String idNum, String balance) {
		this.name = name;
		this.idNum = Integer.parseInt(idNum);
		this.balance = new BigDecimal(balance);
	}
	
	/***
	 * Adds a given amount to the balance of the account
	 * @param depositAmnt the amount to be added
	 */
	public void deposit(String depositAmnt) {
		BigDecimal amnt = new BigDecimal(depositAmnt);
	
		if(amnt.compareTo(BigDecimal.ZERO) >= 0) {
			balance = balance.add(amnt);
			ModelEvent event = new ModelEvent(EventKind.BalanceUpdate, balance, AgentStatus.NA);
			notifyChanged(event);
		}
		else {
			System.out.println("Invalid Amount \n");
		}
	}
	
	/***
	 * Deducts a given amount from the balance of the account
	 * @param withdrawAmnt the amount to be removed
	 * @throws OverdrawException error when withdrawal amount exceeds the current balance
	 */
	public void withdraw(String withdrawAmnt) throws OverdrawException {
		BigDecimal amnt = new BigDecimal(withdrawAmnt);
		
		if(balance.compareTo(amnt) >= 0) {
			balance = balance.subtract(amnt);
			ModelEvent event = new ModelEvent(EventKind.BalanceUpdate, balance, AgentStatus.NA);
			notifyChanged(event);
		}
		else {
			System.out.println("Insufficient Funds \n");
		}
	}
	
	/***
	 * Gets the ID number of the account
	 * @return the ID number of the account
	 */
	public int getIdNum() {
		return idNum;
	}
	
	/***
	 * Method to access the name on the account
	 * @return the name of the account
	 */
	public String getName() {
		return name;
	}
	
	/***
	 * Method to access the balance of the account
	 * @return the balance of the account
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	
	/***
	 * Assigns the ID number for the account
	 * @param idNum the number that is to be parsed as an Integer
	 */
	public void setIdNum(String idNum) {
		this.idNum = Integer.parseInt(idNum);
	}
	
	/***
	 * Assigns the name for the account holder
	 * @param name the account holder
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/***
	 * Assigns the balance to the account using a String
	 * @param balance the amount of money (String)
	 */
	public void setBalance(String balance) {
		this.balance = new BigDecimal(balance);
	}
	
	/***
	 * Assigns the balance to the account using a BigDecimal object
	 * @param newBalance the amount of money (BigDecimal)
	 */
	public void setBalance(BigDecimal newBalance) {
		this.balance = newBalance;
	}
}
