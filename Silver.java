package coe528.project;

import java.io.IOException;

/**
 * @author Iliyan Ali
 * 501186114
 * Section 09
 */
public class Silver extends CustomerLevel {
    
    @Override
    public boolean onlinePurchase(double money, CustomerAccount customer) throws IOException {
        //if the purchase is 50 dollars or more AND the balance of the user is enough to cover cost + fees
        if (money >= 50 && customer.getBalance() >= (20 + money)) {
            
            //balance updated
            customer.setBalance(customer.getBalance() - (money + 20)); 
            System.out.println("The purchase was made for the silver customer, the balance is: " + customer.getBalance());
            //update the level of customer (depending on balance left after purchase) 
            updateLevel(customer); 
            //true since purchase was made
            return true; 
        } 
        else {
            System.out.println("Purchase was not made");
            return false; 
        }

    }
        
    @Override
        public String toString() {
        return "Silver";
    }
    
}
