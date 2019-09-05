package acctMgr.controller;

import java.io.IOException;
import java.math.BigDecimal;

import acctMgr.model.AccountList;
import acctMgr.model.OverdrawException;
import acctMgr.model.Account;
import acctMgr.view.AccountListView;
import acctMgr.view.AccountView;
import acctMgr.view.JFrameView;

public class AccountListController extends AbstractController {
	
	private static Account account;

	/***
	 * Main constructor of the AccountListController class
	 * @param fileName the .txt file 
	 * @throws Exception error to be thrown if trouble accessing file or creating GUI
	 */
	public AccountListController(String fileName) throws Exception {
		setModel(new AccountList(fileName));
		setView(new AccountListView((AccountList)getModel(), this));
	}
	
	/***
	 * Determines what method of the AccountList object to execute
	 * @param selection the command given from a button press
	 * @throws IOException error if the .txt file cannot be read from/written to
	 */
	public void operation(String selection) throws IOException {
		if(selection.equals(AccountListView.ACCT_CHNG)) {
			String name = (String) AccountListView.acctList.getSelectedItem();
			System.out.println(name);
			
		}
		else if(selection.equals(AccountListView.USD)) {
			System.out.println("Button Pressed - " + AccountListView.USD);
			
			int index = AccountListView.acctList.getSelectedIndex();
			account = (((AccountList) getModel()).getAccount(index));
			AccountController USD = new AccountController(AccountListView.USD, account);
		}
		else if(selection.equals(AccountListView.EUROS)) {
			System.out.println("Button Pressed - " + AccountListView.EUROS);
			
			int index = AccountListView.acctList.getSelectedIndex();
			account = (((AccountList) getModel()).getAccount(index));
			AccountController EURO = new AccountController(AccountListView.EUROS, account);
		}
		else if(selection.equals(AccountListView.YEN)) {
			System.out.println("Button Pressed - " + AccountListView.YEN);
			
			int index = AccountListView.acctList.getSelectedIndex();
			account = (((AccountList) getModel()).getAccount(index));
			AccountController YEN = new AccountController(AccountListView.YEN, account);
		}
		else if(selection.equals(AccountListView.SAVE)) {
			System.out.println("Button Pressed - " + AccountListView.SAVE);
			((AccountList)getModel()).saveList();
			
		}
		else if(selection.equals(AccountListView.EXIT)) {
			System.out.println("Button Pressed - " + AccountListView.EXIT);
			((AccountList)getModel()).saveList();
			System.exit(0);
		}
	}
}
