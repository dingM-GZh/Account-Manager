package acctMgr.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.swing.JOptionPane;

import acctMgr.model.Account;
import acctMgr.model.OverdrawException;
import acctMgr.view.AccountListView;
import acctMgr.view.AccountView;
import acctMgr.view.JFrameView;

public class AccountController extends AbstractController {
	
	private static Account account;
	private static String currency;
	
	private static BigDecimal balance;
	private static BigDecimal euro;
	private static BigDecimal yen; 
	private static MathContext mc;
	
	/***
	 * Main constructor of the AccountController class
	 * @param currency displaying the balance either as USD, Euros, or Yen
	 * @param account the account object that is going to be displayed and modified
	 */
	public AccountController(String currency, Account account) {
		this.account = account;
		this.currency = currency;
		
		euro = new BigDecimal("0.79");
		yen = new BigDecimal("94.1");
		mc = new MathContext(0, RoundingMode.CEILING);

		setModel(account);
		domesticToForeign();
		setView(new AccountView(account, this, currency));
	}
	
	/***
	 * Determines what method of the Account object to execute
	 * @param selection the command given from a button press
	 * @throws OverdrawException when the user withdraws more than the balance
	 */
	public void operation(String selection) throws OverdrawException {
		if(selection.equals(AccountView.DEPOSIT)) {
			String depositAmnt = ((AccountView)getView()).txtField.getText();
			System.out.println("Button Pressed - " + AccountView.DEPOSIT);
			
			if(depositAmnt.equals("")) {
				System.out.println("ERROR - Deposit Amount Missing \n");
				account.deposit("0");
			}
			else {
				BigDecimal deposit = new BigDecimal(depositAmnt);
				BigDecimal one = new BigDecimal("1");
				if(deposit.compareTo(one) <= 0) {
					System.out.println("ERROR - Not Enough To Deposit \n");
					AccountView.txtField.setText("");
				}
				else {
					account.deposit(depositAmnt);
					AccountView.txtField.setText("");
				}
			}
		}
		else if(selection.equals(AccountView.WITHDRAW)) {
			String withdrawAmnt = ((AccountView)getView()).txtField.getText();
			System.out.println("Button Pressed - " + AccountView.WITHDRAW);
			try {
				if(withdrawAmnt.equals("")) {
					System.out.println("ERROR - Withdraw Amount Missing \n");
				}
				else {
					if(new BigDecimal(withdrawAmnt).compareTo(((Account)getModel()).getBalance()) == 1) {
						throw new OverdrawException(new BigDecimal(withdrawAmnt).subtract(((Account)getModel()).getBalance()));
					}
					else {
						account.withdraw(withdrawAmnt);
						AccountView.txtField.setText("");
					}
				}
			}
			catch(OverdrawException e) {
				e.printStackTrace();
				AccountView.txtField.setText("");
				System.out.println("yeet");
				
				JOptionPane.showMessageDialog(((AccountView)getView()).frame, "ERROR - OverdrawException \nInsufficient Funds");
			}
		}
		else if (selection.equals(AccountView.DISMISS)) {
			System.out.println("Button Pressed - " + AccountView.DISMISS);
			foreignToDomestic();
			
			AccountView.frame.dispose();
		}
	}
	
	/***
	 * Converts USD to Euros/Yen
	 */
	public static void domesticToForeign() {
		if(currency.equals(AccountListView.EUROS)) {
			System.out.println("Converting USD to Euros");
			
			balance = (account.getBalance()).multiply(euro, mc);
			balance = balance.setScale(2, RoundingMode.CEILING);
			account.setBalance(balance);
			System.out.println("1 USD = 0.79 Euros \n");
			
		}
		else if(currency.equals(AccountListView.YEN)) {
			System.out.println("Converting USD to Yen");
			
			balance = (account.getBalance()).multiply(yen, mc);
			account.setBalance(balance);
			System.out.println("1 USD = 94.1 Yen \n");
		}
		else {
			System.out.println("USD - No Conversion To \n");
		}
	}
	
	/***
	 * Converts Euros/Yen to USD
	 */
	public static void foreignToDomestic() {
		if(currency.equals(AccountListView.EUROS)) {
			System.out.println("Converting Euros back to USD \n");
			
			balance = (account.getBalance()).divide(euro, 2, RoundingMode.CEILING);
			account.setBalance(balance);
		}
		else if(currency.equals(AccountListView.YEN)) {
			System.out.println("Converting Yen back to USD \n");
			
			balance = (account.getBalance()).divide(yen, 2, RoundingMode.CEILING);
			account.setBalance(balance);
		}
		else {
			System.out.println("USD - No Conversion From \n");
		}
	}
}