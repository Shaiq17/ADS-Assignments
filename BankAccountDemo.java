public class BankAccountDemo{
    public static void main(String[] args) {
        

        BankAccount acc1 = new BankAccount();
        acc1.display();
        acc1.deposit(500);
        acc1.withdraw(200);
        acc1.display();

        System.out.println();

 
        BankAccount acc2 = new BankAccount(10234);
        acc2.display();
        acc2.deposit(1000);
        acc2.display();

        System.out.println();

     
        BankAccount acc3 = new BankAccount(20456, 5000.0);
        acc3.display();
        acc3.withdraw(1200);
        acc3.display();

        System.out.println();

        BankAccount acc4 = new BankAccount(30567, "Alice Johnson", 7500.0);
        acc4.display();
        acc4.deposit(2500);
        acc4.withdraw(5000);
        acc4.display();
    }
}

class BankAccount {
    private int accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount() {
        this.accountNumber = 0;
        this.accountHolderName = "Unknown";
        this.balance = 0.0;
    }

    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = "Unknown";
        this.balance = 100.0;  // default 
    }

    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = "Unknown";
        this.balance = balance;
    }

    public BankAccount(int accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient ");
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }

    public void display() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: $" + balance);
    }
}