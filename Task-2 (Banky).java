import java.util.*;
public class BankSystem {
    private String[] AccNum;
    private String[] AccHolderNames;
    private double[] balances;
    private int AccCount;

    public BankSystem(int maxAccs) {
        AccNum = new String[maxAccs];
        AccHolderNames = new String[maxAccs];
        balances = new double[maxAccs];
        AccCount = 0;
    }

    public void createAcc(String AccNumber, String AccHolderName) {
        if (findAccIndex(AccNumber) == -1 && AccCount < AccNum.length) {
            AccNum[AccCount] = AccNumber;
            AccHolderNames[AccCount] = AccHolderName;
            balances[AccCount] = 0.0;
            System.out.println("Account created: Account Number = " + AccNumber + ", Account Holder Name = " + AccHolderName + ", Balance = 0.0");
            AccCount++;
        } else if (AccCount >= AccNum.length) {
            System.out.println("Account limit reached");
        } else {
            System.out.println("Account with this number already exists");
        }
    }

    public int findAccIndex(String AccNumber) {
        for (int i = 0; i < AccCount; i++) {
            if (AccNum[i].equals(AccNumber)) {
                return i;
            }
        }
        return -1;
    }

    public void deposit(String AccNumber, double amount) {
        int index = findAccIndex(AccNumber);
        if (index != -1 && amount > 0) {
            balances[index] += amount;
            System.out.println("Deposited: " + amount + " to Account Number: " + AccNumber);
        } else {
            System.out.println("Account not found or invalid deposit amount");
        }
    }

    public void withdraw(String AccNumber, double amount) {
        int index = findAccIndex(AccNumber);
        if (index != -1 && amount > 0 && amount <= balances[index]) {
            balances[index] -= amount;
            System.out.println("Withdrew: " + amount + " from Account Number: " + AccNumber);
        } else {
            System.out.println("Account not found or invalid withdraw amount or insufficient balance");
        }
    }

    public void transfer(String fromAccNumber, String toAccNumber, double amount) {
        int fromIndex = findAccIndex(fromAccNumber);
        int toIndex = findAccIndex(toAccNumber);
        if (fromIndex != -1 && toIndex != -1 && amount > 0 && amount <= balances[fromIndex]) {
            balances[fromIndex] -= amount;
            balances[toIndex] += amount;
            System.out.println("Transferred: " + amount + " from Account Number: " + fromAccNumber + " to Acc Number: " + toAccNumber);
        } else {
            System.out.println("Invalid transfer details or insufficient balance");
        }
    }

    public void displayAllAccounts() {
        for (int i = 0; i < AccCount; i++) {
            System.out.println("Account Number: " + AccNum[i] + ", Account Holder Name: " + AccHolderNames[i] + ", Balance: " + balances[i]);
        }
    }

    public static void main(String[] args) {
        BankSystem bankSystem = new BankSystem(10); // Assuming a maximum of 10 Accs
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println(" --- Welcome to the BANK ---\n");
        while (true) {
            System.out.println(" 1. Create Account");
            System.out.println(" 2. Deposit");
            System.out.println(" 3. Withdraw");
            System.out.println(" 4. Transfer");
            System.out.println(" 5. Display All Accounts");
            System.out.println(" 6. Exit");
            System.out.print(" Choose an option you would like to do : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Account number: ");
                    String AccNumber = sc.next();
                    System.out.print("Enter Account holder name: ");
                    String AccHolderName = sc.next();
                    bankSystem.createAcc(AccNumber, AccHolderName);
                    break;
                case 2:
                    System.out.print("Enter Account number: ");
                    AccNumber = sc.next();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    bankSystem.deposit(AccNumber, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter Account number: ");
                    AccNumber = sc.next();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    bankSystem.withdraw(AccNumber, withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter your Account number: ");
                    String fromAccNumber = sc.next();
                    System.out.print("Enter recipient's Account number: ");
                    String toAccNumber = sc.next();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = sc.nextDouble();
                    bankSystem.transfer(fromAccNumber, toAccNumber, transferAmount);
                    break;
                case 5:
                    bankSystem.displayAllAccounts();
                    break;
                case 6:
                    System.out.println("  EXITING...");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
