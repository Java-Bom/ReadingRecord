package Chap3_CommonMethodOfObject.item10.lock;

public class AsynchronousAccount implements Account {

    private long amount;


    public void deposit(long money) {
        amount += money;
        System.out.println(money + "원 입금되었습니다. \n 현재잔액: " + this.amount);
    }

    public void withdraw(long withdrawalAmount) throws InterruptedException {
            if (this.amount >= withdrawalAmount) {
                Thread.sleep(100L);
                this.amount -= withdrawalAmount;
                System.out.println(Thread.currentThread().getName() + "  " + withdrawalAmount + "원 출금되었습니다. \n 현재잔액: " + this.amount);
            }
    }

    public long getAmount() {
        return amount;
    }
}
