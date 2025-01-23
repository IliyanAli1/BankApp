package coe528.project;

/**
 * @author Iliyan Ali
 * 501186114
 * Section 09
 */

//abstract class to represent all accounts
public abstract class UserAccount {
    
    static protected String userName;
    static protected String password;
    
    //Getter functions for username and password
    public String getUserName() {
        return userName;
    }
    
    public String getPassword() {
        return password;
    }
    
}
