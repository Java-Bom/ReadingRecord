package item10.lock;

public interface Account {

    void deposit(long money);
    void withdraw(long withdrawalAmount) throws InterruptedException;
    long getAmount();

}
