package acctMgr.view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import acctMgr.controller.AccountController;
import acctMgr.controller.Controller;
import acctMgr.model.Model;
import acctMgr.model.ModelEvent;
import acctMgr.model.OverdrawException;
import acctMgr.model.Account;

public class AccountView extends JFrameView {
	
	public static String currency;
	private Account account;
	
	public static JFrame frame;
	public static JPanel panel;
	public static JLabel balance;
	
	static JButton deposit;
	static JButton withdraw;
	static JButton dismiss;
	
	public static JTextField txtField;
	
	public static final String DEPOSIT = "Deposit";
	public static final String WITHDRAW = "Withdraw";
	public static final String DISMISS = "Dismiss";

	/***
	 * The constructor of the UI that displays the Account object
	 * @param model the account object that is to be displayed
	 * @param controller the AccountController object 
	 * @param currency the currency the balance is going to be displayed as (USD, Euros, Yen)
	 */
	public AccountView(Model model, Controller controller, String currency) {
		super(model, controller);
		// TODO Auto-generated constructor stub
		
		account = (Account)model;
		Handler handler = new Handler();		
		String window = ((Account)getModel()).getIdNum() + " " + ((Account)getModel()).getName() + " - ";
		
		if(currency.equals(AccountListView.USD)) {
			frame = new JFrame(window + AccountListView.USD);
		}
		else if(currency.equals(AccountListView.EUROS)) {
			frame = new JFrame(window + AccountListView.EUROS);
		}
		else {
			frame = new JFrame(window + AccountListView.YEN);
		}
		
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setSize(new Dimension(300, 200));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(250, 150));
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		balance = new JLabel("Current Balance: " + account.getBalance());
		txtField = new JTextField(12);
		
		deposit = new JButton(DEPOSIT);
		withdraw = new JButton(WITHDRAW);
		dismiss = new JButton(DISMISS);
		
		txtField.addActionListener(handler);
		deposit.addActionListener(handler);
		withdraw.addActionListener(handler);
		dismiss.addActionListener(handler);
		
		panel.add(balance, BorderLayout.NORTH);
		panel.add(txtField, BorderLayout.CENTER);
		panel.add(deposit, BorderLayout.EAST);
		panel.add(withdraw, BorderLayout.WEST);
		panel.add(dismiss, BorderLayout.SOUTH);
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	/***
	 * Updates the Account object over any changes that happen
	 * In this case, updates the balance on the UI
	 * @param me the specific event that happens
	 */
	@Override
	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		String msg = "Current Balance: " + me.getBalance();
		balance.setText(msg);
		System.out.println("AccountView - modelChanged method called");
		System.out.println("New Balance is: " + me.getBalance());
	}
	
	class Handler implements ActionListener {
		
		/***
		 * Determines the specific event that occurs and sends it to the AccountController
		 */
		public void actionPerformed(ActionEvent ae) {
			try {
				((AccountController)getController()).operation(ae.getActionCommand());
			} catch (OverdrawException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("");
				JOptionPane.showMessageDialog(frame, "Disk Space is Low");
			}
		}
	}
}
