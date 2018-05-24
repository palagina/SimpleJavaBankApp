package JavaBankApplication;

// ************************************************************************
// BankProgram.java	 Template created on 15.9.2016
// - The program class for the BankApplication exercise
// ************************************************************************
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BankProgram {
	private static Scanner input = new Scanner(System.in);
	private static ArrayList<Account> accountList = new ArrayList<Account>();
	static DecimalFormat twoDecimals = new DecimalFormat("0.00");
	
	// *** DO NOT change anything in the main method ***
	public static void main(String[] args) {
		int choice = -1;

		while (choice != 0) {

			switch (choice) {
			case 1:
				listAccounts();
				break;
			case 2:
				addAccount();
				break;
			case 3:
				depositMoney();
				break;
			case 4:
				withdrawMoney();
				break;
			case 5:
				deleteAccount();
				break;
			}

			displayMenu();
			choice = Integer.parseInt(input.nextLine());
		}

		System.out.println("\nThe program ends now. Bye!");
		input.close();
	} //main ends

	private static void displayMenu() {
		String line = "-----------------------------------------------------"
				+ "---------------------------------------------------------------";
		System.out.println(line);
		System.out.print(" 0 = Quit | 1 = List accounts | 2 = Add an account | " +
						 "3 = Deposit money | 4 = Withdraw money | 5 = Delete an account \n");
		System.out.println(line);
		System.out.print("Enter your choice: ");
	}

	// *** NB! Edit only the methods below. DO NOT add any other methods! ***

	private static void listAccounts() {
		System.out.print("\n*** Account list ***\n");
		for(Account accountObject : accountList) {
		System.out.println("Number: " + accountObject.getAccountNumber() + 
							"| Balance: " + twoDecimals.format(accountObject.getBalance()));
		}
	}

	private static void addAccount() {
		System.out.print("\n*** Add an account ***\n");
				System.out.print("Enter account number: ");
				String accountNumber = input.nextLine(); 
				
				if(findAccount(accountNumber) != null) {
					System.out.println("\n Account " +accountNumber + " already exists\n ");	
					}
				else {
					double balance = 0.00;
					accountList.add(new Account(accountNumber, balance));
					System.out.println("\nAccount created successfully!\n");	
					}
			}	

	// Finds an account in accountList by given account number.
	// Returns either a reference to the account object
	// OR null if the account is not found in accountList.
	private static Account findAccount(String accountNumber) {
		Account myAccount = null;
		for(Account accountObject : accountList) {
			if(accountObject.getAccountNumber().equals(accountNumber)){
				myAccount = accountObject;
			}
		}
		return myAccount;
	}

	private static void depositMoney() {
		System.out.print("\n*** Deposit money to an account ***\n");
		System.out.print("Enter account number: ");
		String accountNumber = input.nextLine(); 
		
		if(findAccount(accountNumber) == null) {
			System.out.println("\nAccount " + accountNumber + " doesn't exist\n");	
			}
		else {
			try{
				System.out.print("Enter the amount to be deposited: ");	
				double amount = Double.parseDouble(input.nextLine().replace(',', '.'));
					if(amount>=0){
						findAccount(accountNumber).setBalance(findAccount(accountNumber).getBalance() + amount);
						System.out.println("\nAmount successfully deposited\n ");	
					}
					else {
						System.out.println("\nCannot deposit a negative amount!\n ");
					}
			}
			catch (NumberFormatException nfe) {
				System.out.println("Enter a valid sum!");
			}
		}
	}

	private static void withdrawMoney() {
		System.out.print("\n*** Withdraw money from an account ***\n");
		System.out.print("Enter account number: ");
		String accountNumber = input.nextLine(); 
		
		if(findAccount(accountNumber) == null) {
			System.out.print("\nAccount " + accountNumber + " doesn't exist\n");	
			}
		else {
			try{
			System.out.print("Enter the amount to be withdrawn: ");	
			double amount = Double.parseDouble(input.nextLine().replace(',', '.'));
				if(amount<=findAccount(accountNumber).getBalance()) {
					if (amount>=0) {
					findAccount(accountNumber).setBalance(findAccount(accountNumber).getBalance() - amount);
					System.out.println("\nAmount successfully withdrawn\n ");	
					}
					else{
						System.out.println("\nCannot withdraw a negative amount!\n ");
					}
				}
				else {
					System.out.println("\nInsufficient finds\n");	
				}
			}
				catch (NumberFormatException nfe) {
					System.out.println("Enter a valid sum!");
				}
		}
	}

	private static void deleteAccount() {
		System.out.print("\n*** Delete an account ***\n");
		System.out.print("Enter account number: ");
		String accountNumber = input.nextLine(); 
			if(findAccount(accountNumber) == null) {
				System.out.print("\nNothing deleted. Account " + accountNumber + " doesn't exist\n");	
			}
			else {
				accountList.remove(findAccount(accountNumber));
				System.out.println("\n Account deleted successfully!\n ");	
			}
	}		
}

// End
