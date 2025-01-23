package coe528.project;

import java.io.IOException;

/**
 * @author Iliyan Ali
 * 501186114
 * Section 09
 */

public abstract class CustomerLevel {
    
    protected abstract boolean onlinePurchase(double money, CustomerAccount customer) throws IOException;
    
        
    public static void updateLevel(CustomerAccount customer) { //method for updating level

        //if balance is less than 10K
        if (customer.getBalance() < 10000)
        {
            //set the level to silver if conditions are met
            customer.setLevel(new Silver()); 
            System.out.println("Level changed to silver level");
        }
        //if customer balance is between 10K and 20K
        else if (customer.getBalance() >= 10000 && customer.getBalance() < 20000) 
        {
                customer.setLevel(new Gold());
                 System.out.println("Level changed to gold level");
        } 
        //if its greater than 20K
        else 
        {
            customer.setLevel(new Platinum());
             System.out.println("Level changed to platinum level");
        }
    }
    
    @Override
    public abstract String toString();
}
