package Chap9_GeneralProgrammingPrinciple.item60;

public class BuyCandy {
    public static void main(String[] args) {
        int money = 100;
        int candyBought = 0;

        for (int price = 10; money >= price; price += 10) {
            money -= price;
            candyBought++;
        }

        System.out.println(candyBought + "개 구입");
        System.out.println("잔돈(달러):" + money);
    }
}
