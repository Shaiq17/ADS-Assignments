class BankAccount {
	int accountNumber;
	double balance = 0.0;
	String name;
	
	BankAccount() {
		this.accountNumber = 0;
		this.balance = 0.0;
		this.name = "null";
	}
	
	BankAccount (int acc) {
		this.accountNumber = acc;
	}
	
	BankAccount (int acc, double balance) {
		this.accountNumber = acc;
		this.balance = balance;
	}
	
	BankAccount (int acc, String name, double balance) {
		this.accountNumber = acc;
		this.name = name;
		this.balance = balance;
	}
	
	void deposit(double amount) {
		this.balance += amount;
		System.out.println(""+amount+" credited to: "+this.accountNumber);
	}
	
	void withdraw(double WithdrawAmount) {
		if(WithdrawAmount <= this.balance) {
			this.balance -= WithdrawAmount;
			System.out.println(""+WithdrawAmount+" debited from account: "+this.accountNumber);
		}
		else {
			System.out.println("insufficient balance!");
		
	}
	}
	
	void display() {
		System.out.println("\n====Account details====");
		System.out.println("Account Number: " + this.accountNumber);
		System.out.println("Account holder's name: " + this.name);
		System.out.println("Balance: " +this.balance);
	}
	
}

public class Bank {
		public static void main(String[] args) {
			BankAccount acc1 = new BankAccount();
			BankAccount acc2 = new BankAccount(23545);
			BankAccount acc3 = new BankAccount(13354, 100000.0);
			BankAccount acc4 = new BankAccount(12345, "Shaiq", 25000.0);
			
			acc1.display();
			acc2.display();
			acc3.display();
			acc4.display();
			
			acc4.deposit(5000);
			acc4.withdraw(2500);
			acc4.display();
		}
	}