import java.util.Random;

public class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    public void firstThread() throws InterruptedException {
        Random random = new Random();

        for (int a = 0; a < 10000; a++) {
            Account.transfer(account1, account2, random.nextInt(100));
        }
    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();

        for (int a = 0; a < 10000; a++) {
            Account.transfer(account2, account1, random.nextInt(100));
        }
    }

    public void finished() {
        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());

        System.out.println("Total balance: " + (account1.getBalance() + account2.getBalance()));
    }

// Output:
//    Account 1 balance: 3462
//    Account 2 balance: 17786
//    Total balance: 21248
}
