public class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();
    
    public void firstThread() throws InterruptedException {
        
    }

    public void secondThread() throws InterruptedException {
        
    }

    public void finished() {
        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());
        
        System.out.println("Total balance: " + (account1.getBalance() + account2.getBalance()));
    }
    
// Output:
//    Account 1 balance: 10000
//    Account 2 balance: 10000
//    Total balance: 20000
}
