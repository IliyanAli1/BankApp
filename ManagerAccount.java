package coe528.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Iliyan Ali
 * 501186114
 * Section 09
 */

public class ManagerAccount extends UserAccount {
    
    /** 
     * OVERVIEW: This class represents a manager's account in a banking system. It is responsible for
     * authenticating login credentials, as well as creating and deleting customer account files. The
     * ManagerAccount class is immutable, meaning its state cannot be changed after it is created.
     * 
     * Abstraction Function:
     * AF(c) = A manager account, M, such that
     *         M.userName = c.userName and M.password = c.password
     * 
     * Representation Invariant:
     * RI(c) = true if c.userName.equals("admin") && c.password.equals("admin"), false otherwise.
    */

    //Constructor to set up variable
    public ManagerAccount() {    
        /** 
         * EFFECTS: Instantiates a ManagerAccount object with the userName and password as both "admin"
         */
        //userName and password should be admin and admin according to the doc
        userName = "admin";
        password = "admin";
    }
     
    public static boolean loginAuthenticate(String inputUserName, String inputPassword) { 
        
        /** 
         * REQUIRES: inputUserName and inputPassword are not null
         * EFFECTS: Returns true if inputUserName and inputPassword are the same as c.userName 
         * and c.password for a concrete ManagerAccount object c, otherwise false
         */
        
        //return true or false based on if it equals the correct username and password or not
        return (inputUserName.equals(userName) && inputPassword.equals(password));     
    }
    
    public boolean addCustomer(String newUserName, String newPassword) throws IOException {
        
        /** 
         * REQUIRES: newUserName and newPassword are not null strings
         * MODIFIES: The file "newUserName.txt"
         * EFFECTS: If a file with the name newUserName.txt does not already exist in src/CustomerFiles,
         * creates a new file named newUserName.txt in the src/CustomerFiles directory, writes the newPassword
         * on the first line and 100.0 on the second line, and returns true. If a file with the name
         * newUserName.txt already exists, does not modify it and returns false.
        */
        
        //sets a file object with the username as the file name. 
        File file = new File("src/main/java/CustomerFiles/" + newUserName + ".txt"); 
        if (file.exists()) { //if there exists a file with the username 
            //the file already exists with that userName, so cannot add
            System.out.println("Cannot add " + file + ", username already exists");
            //return false because couldnt add the user
            return false; 
        }
        //the file does not exists meaning we can create. 
        else { 
            //create new file
            file.createNewFile(); 
            //creates a file writer to write to the new file we just made 
            FileWriter writer = new FileWriter(file); 
            //writes the password on one line, and the default balance of 100 on the other.
            writer.write(newPassword + "\n" + "100.0"); 
            //close writer
            writer.close();
            System.out.println("File was successfully created in CustomerFiles directory " + file);
            //return true bcz file was made 
            return true; 
        }
    }

     
    public boolean deleteCustomer(String oldUserName) {
        
        /** 
         * REQUIRES: oldUserName is not a null string
         * MODIFIES: The file "oldUserName.txt"
         * EFFECTS: If a file does not exist with the name "oldUserName.txt" in the src/CustomerFiles directory, then do not delete anything and return false.
         * Otherwise, delete the file in the src/CustomerFiles/ directory that has the name "oldUserName.txt"
         */
        
        //sets a file object with the username as the file name
        File file = new File("src/main/java/CustomerFiles/" + oldUserName + ".txt"); 
        //if it deletes
        if (file.delete()) {
            System.out.println(file + "was successfuly deleted.");
            //return true
            return true;
        }
        //the file was not able to be deleted
        else { 
            System.out.println(file + "was not deleted.");
            //returning false bcz its not deleted
            return false; 
        }
    }
     
    private boolean repOk() {
        /*
         EFFECT: Returns true if rep invariant holds for this object, otherwise returns false
         */
        //if c.userName and c.passwords are both admin
        if(userName.equals("admin") && password.equals("admin")) { 
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return ("Username of account: " + userName + ", password of account: " + password);
    }

}
