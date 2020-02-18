package item16;

import java.util.ArrayList;
import java.util.List;

class Client {

    private List<Product> products;
    private int balance;

    public Client(int balance) {
        this.balance = balance;
        products = new ArrayList<>();
    }

    public Shopper getShopper(){
        return new Shopper();
    }

    public void buyProduct(Product product){
        if(balance < product.cost){
            throw new IllegalArgumentException();
        }
        balance -= product.cost;
        products.add(product);
    }

    class Shopper{
        public String name = "shopper";

        public void hello(){

        }

    }
}




