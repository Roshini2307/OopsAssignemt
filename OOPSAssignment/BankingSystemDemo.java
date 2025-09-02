
/**
 * A simple Banking System Demo to illustrate
 * Object-Oriented Programming concepts in Java.
 *
 * <p>Concepts covered:</p>
 * <ul>
 *   <li>Classes and Objects</li>
 *   <li>Encapsulation (private data with getters &amp; setters)</li>
 *   <li>Inheritance</li>
 *   <li>Polymorphism (method overriding &amp; dynamic binding)</li>
 *   <li>Abstract classes</li>
 *   <li>Exception Handling</li>
 *   <li>Arrays &amp; Strings</li>
 *   <li>JavaDoc Documentation</li>
 * </ul>
 *
 * <p>This class contains the main method which drives the demo.</p>
 * 
 * @author Rose
 * @version 1.1
 */
public class BankingSystemDemo {

    /**
     * Default constructor for BankingSystemDemo.
     * This is not strictly required, but added for JavaDoc clarity.
     */
    public BankingSystemDemo() {
        // No initialization required
    }

    /**
     * Main method to test the Banking System.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Using polymorphism
        BankAccount[] accounts = new BankAccount[2];
        accounts[0] = new SavingsAccount("Alice", 5000, 5.0);
        accounts[1] = new CurrentAccount("Bob", 3000, 1000);

        for (BankAccount acc : accounts) {
            acc.displayAccountType();
            System.out.println("Account Holder: " + acc.getAccountHolder());
            System.out.println("Initial Balance: " + acc.getBalance());

            acc.deposit(2000);
            System.out.println("After Deposit: " + acc.getBalance());

            try {
                acc.withdraw(4000);
                System.out.println("After Withdrawal: " + acc.getBalance());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("----------------------");
        }

        // String example
        String bankName = "National Bank of India";
        System.out.println("Bank Name: " + bankName.toUpperCase());

        // finalize() example (not recommended in real use)
        BankingSystemDemo demo = new BankingSystemDemo();
        demo = null; // eligible for GC
        System.gc(); // request garbage collection
    }

    /**
     * finalize() method - called before garbage collection.
     * (Note: Deprecated in modern Java, just for demo here.)
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize method called before object is garbage collected.");
        super.finalize();
    }
}

/**
 * Abstract class representing a generic bank account.
 */
abstract class BankAccount {
    private String accountHolder;
    private double balance;

    /**
     * Constructor for BankAccount.
     *
     * @param accountHolder Name of the account holder
     * @param balance Initial balance
     */
    public BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    /**
     * Deposits money into the account.
     *
     * @param amount amount to deposit
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Withdraws money from the account.
     *
     * @param amount amount to withdraw
     * @throws Exception if balance is insufficient
     */
    public void withdraw(double amount) throws Exception {
        if (amount > balance) {
            throw new Exception("Insufficient balance!");
        }
        balance -= amount;
    }

    /**
     * Gets the account balance.
     *
     * @return current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the account holder's name.
     *
     * @return account holder
     */
    public String getAccountHolder() {
        return accountHolder;
    }

    /**
     * Abstract method to display account type.
     */
    public abstract void displayAccountType();
}

/**
 * Represents a Savings Account.
 * Supports interest rate.
 */
class SavingsAccount extends BankAccount {
    private double interestRate;

    /**
     * Constructor for SavingsAccount.
     *
     * @param accountHolder Account holder name
     * @param balance Initial balance
     * @param interestRate Interest rate of savings account
     */
    public SavingsAccount(String accountHolder, double balance, double interestRate) {
        super(accountHolder, balance);
        this.interestRate = interestRate;
    }

    /**
     * Displays account type.
     */
    @Override
    public void displayAccountType() {
        System.out.println("Savings Account with interest rate: " + interestRate + "%");
    }
}

/**
 * Represents a Current Account.
 * Supports overdraft facility.
 */
class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    /**
     * Constructor for CurrentAccount.
     *
     * @param accountHolder Account holder name
     * @param balance Initial balance
     * @param overdraftLimit Overdraft limit
     */
    public CurrentAccount(String accountHolder, double balance, double overdraftLimit) {
        super(accountHolder, balance);
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Withdraw method overridden to allow overdraft.
     *
     * @param amount amount to withdraw
     * @throws Exception if overdraft limit is exceeded
     */
    @Override
    public void withdraw(double amount) throws Exception {
        if (amount > getBalance() + overdraftLimit) {
            throw new Exception("Overdraft limit exceeded!");
        }
        if (amount <= getBalance()) {
            super.withdraw(amount);
        } else {
            double remaining = amount - getBalance();
            super.withdraw(getBalance()); // zero out balance
            System.out.println("Overdraft used: " + remaining);
        }
    }

    /**
     * Displays account type.
     */
    @Override
    public void displayAccountType() {
        System.out.println("Current Account with overdraft limit: " + overdraftLimit);
    }
}
