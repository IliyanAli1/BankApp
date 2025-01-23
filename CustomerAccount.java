package coe528.project;

//following imports to use below.
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Iliyan Ali
 * 501186114
 * Section 09
 */


//customer account will be child of userAccount class as it will inherit it. 
public class CustomerAccount extends UserAccount {
    
    //file for customer account
    private File accountFile;
    //respresenting the balance of the account
    private double balance;
    //level of the account (gold, silver, platinum)
    private CustomerLevel level;
    
    
    //no exception thrown as long as file exists
    public CustomerAccount(File customerFile) throws FileNotFoundException{
        this.accountFile= customerFile;
        //makes a scanner called reader to set up instance varaibles for the object with info gathered from inside the file
        Scanner reader= new Scanner(this.accountFile);
        
        
        //sets username from name of the file 
        this.userName=this.accountFile.getName().replaceFirst("\\.txt$", "");
        //password from first line 
        this.password= reader.nextLine(); 
        //balance from second line
        this.balance = reader.nextDouble();
        //sets the level of the object using the CustomerLevel static method
        //CustomerLevel.updateLevel(this); 
        //close the reader
        reader.close();     
    }
    
    //method will update the file of the customer object. it will update as the values of variable change
    private void updateFile() throws IOException { 
        //setting up the write to update the file after changes has been made to password and balance
          FileWriter writer = new FileWriter(this.accountFile); 
          //writing the new password on one line, and the balance on other. (same format as set in contructor)
          writer.write(password + "\n" + balance);
          //close the writer
          writer.close();
    }
    
    //checks if username and password combo exists in the directory somewhere. will return either true or false. 
    public static boolean loginAuthenticate(String inputUserName, String inputPassword) throws FileNotFoundException{
        //will set a file object with the inputted username  as the file name. 
        File file = new File("src/main/java/CustomerFiles/" + inputUserName + ".txt");
        //if a file exists with the username (.exists() is built in function)
        if (file.exists()) { 
            //sets up a scanner called reader to read the  file
            Scanner reader = new Scanner(file); 
            //if the passowrd also exists (both username and password match) return true
            if(reader.nextLine().equals(inputPassword)) { 
                System.out.println("Customer authenticated (verified), username and password, " + inputUserName + " and " + inputPassword + " is correct and file is " + file);
                return true;
            }
            //username exists(correct) but password was doesn't (incorrect)
            else { 
                System.out.println("Username/file exists as " + inputUserName + ", but the password is incorrect");
                return false; 
            }
        }
        //the username is not matching so authentication is false 
        else { 
            System.out.println("File for the username " + inputUserName + " does not even exist");
            return false;
        }
    }
    
    //Depositting money
    public boolean depositMoney(double money) throws IOException { 
        //if money is negative number
        if(money < 0) {
            System.out.println("Depositing negative value money is not possible. Deposit failed");
            return false; 
        }
        //if money is zero or positive number
        else{
            //adds to balance
            balance += money; 
            //update level (if it even updates)
            level.updateLevel(this); 
            //update the file with new changes made
            this.updateFile();
            System.out.println("Deposited successful, account balance is: " + balance);
            return true;
        }    
    }
    
    //Withdrawing money
    public boolean withdrawMoney(double money) throws IOException { 
        //if money requested to withdraw is less than 0 or greater than balance in the account. 
        if (money < 0 || money > balance) { 
            System.out.println("Withdrawal failed. Money requested to withdraw must be non negative and less than total account balance");
            return false; 
        }
        else {
            //subtract withdrawn money from total balance
            balance -= money; 
            //update the level (if it even happens)
            level.updateLevel(this); 
            //update the file with new changes made
            this.updateFile(); 
            System.out.println("Withdrawal successful, account balance is: " + balance);
            return true;
        }
    }

    public boolean onlinePurchase(double money) throws IOException { //Uses state pattern to implement this functionality
        return (level.onlinePurchase(money, this)); //uses the state method handler for this method (state pattern). 
    }

    //Getter and setter
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double b) throws IOException { //changes the balance to whatever is in the parameter
        this.balance = b;
        //update the level (if it even happens)
        level.updateLevel(this); 
        //update the file with new changes made
        this.updateFile(); 
    }
    
    public void setLevel(CustomerLevel level) {
        this.level = level;
    }
    
    public CustomerLevel getLevel() {
        return level;
    }
    
    @Override
    public String toString() {
        return(accountFile + ", " + userName + ", " + password + ", " + balance + ", " + level);
    }
    
}
