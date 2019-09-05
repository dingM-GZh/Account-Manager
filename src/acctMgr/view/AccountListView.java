package acctMgr.view;

import java.util.List;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

import acctMgr.controller.AccountListController;
import acctMgr.controller.Controller;
import acctMgr.model.Model;
import acctMgr.model.ModelEvent;
import acctMgr.model.Account;
import acctMgr.model.AccountList;

public class AccountListView extends JFrameView{
	
	public static void main(String args[]) throws Exception { 
		String fileName = args[0];
		
		AccountListController acctListController = new AccountListController(fileName);
	}
	
	private AccountList acctListObj;
	
	private static List<Account> accounts;
	private List<String> nameID;
	
	public static JFrame frame;
	public static JLabel menu;
	public static JComboBox acctList;
	
	public static JPanel panel;
	
	public static JButton editUSD;
	public static JButton editEuros;
	public static JButton editYen;
	public static JButton save;
	public static JButton exit;
	
	public static final String USD = "Edit in USD";
	public static final String EUROS = "Edit in Euros";
	public static final String YEN = "Edit in Yen";
	public static final String SAVE = "Save";
	public static final String EXIT = "Exit";
	public static final String ACCT_CHNG = "Change";
	
	/***
	 * The constructor to display the AccountList object
	 * @param model the AccountList object being displayed
	 * @param controller the AccountListController handling the data
	 * @throws Exception error that could occur 
	 */
	public AccountListView(Model model, Controller controller) throws Exception {
		super(model, controller);
		// TODO Auto-generated constructor stub
		
		Handler handler = new Handler();
		
		frame = new JFrame("Account List View");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setSize(new Dimension(350, 200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		((AccountList)getModel()).loadAcct();
		nameID = ((AccountList)getModel()).getNameID();
		
		menu = new JLabel("Select an Account: ");
		acctList = new JComboBox(nameID.toArray());
		acctList.setActionCommand(ACCT_CHNG);
		acctList.setSelectedIndex(0);
		
		accounts = ((AccountList)getModel()).getAcctList();
		printAccountList();

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(500,300));
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		editUSD = new JButton(USD);
		editEuros = new JButton(EUROS);
		editYen = new JButton(YEN);
		save = new JButton(SAVE);
		exit = new JButton(EXIT);
		
		acctList.addActionListener(handler);
		editUSD.addActionListener(handler);
		editEuros.addActionListener(handler);
		editYen.addActionListener(handler);
		save.addActionListener(handler);
		exit.addActionListener(handler);
		
		panel.add(menu, BorderLayout.NORTH);
		panel.add(acctList, BorderLayout.NORTH);
		panel.add(editUSD, BorderLayout.EAST);
		panel.add(editEuros, BorderLayout.CENTER);
		panel.add(editYen, BorderLayout.WEST);
		panel.add(save, BorderLayout.SOUTH);
		panel.add(exit, BorderLayout.SOUTH);
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	/***
	 * Updates the AccountList object over any changes that happen
	 * In this case, updates the balance on the UI
	 * @param me the specific event that happens
	 */
	@Override
	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		// String msg = "Current Balance: " + me.getBalance();
		// txtField.setText(msg);
	}
	
	/***
	 * Prints the all Accounts loaded from the .txt file
	 */
	public static void printAccountList() {
		
		System.out.println("The Accounts Loaded Are: ");
		System.out.println("-------------------------");
		for(int i = 0; i < accounts.size(); i++) {
			System.out.println("ID Number: " + accounts.get(i).getIdNum());
			System.out.println("Name     : " + accounts.get(i).getName());
			System.out.println("Balance  : " + accounts.get(i).getBalance());
		}
		System.out.println("------------------------- \n");
	}

	class Handler implements ActionListener {
		
		/***
		 * Determines the specific event that occurs and sends it to the AccountListController
		 * @param ae the ActionEvent that is passed to the AccountListController
		 */
		public void actionPerformed(ActionEvent ae) {
			try {
				((AccountListController)getController()).operation(ae.getActionCommand());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
