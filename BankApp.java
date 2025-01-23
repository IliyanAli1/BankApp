package coe528.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * @author Iliyan Ali
 * 501186114
 * Section 09
 */

public class BankApp extends Application {
    
    //storing home screen
    private Scene initialScene; 
    //the manager object is used by the app
    private ManagerAccount manager = new ManagerAccount(); 

    @Override
    public void start(Stage primaryStage) {

        //creating buttons for manager or customer option
        Button bManager = new Button("Manager"); 
        Button bCustomer = new Button("Customer");

        //action events for the buttons 
        //if manager button is clicked
        bManager.setOnAction(e -> 
        {
            //set scene to manager login screen if the manager button clicked
            primaryStage.setScene(createManagerLog(primaryStage));
        });

        //if customer button is clicked
        bCustomer.setOnAction(e ->
        {
            //set scene to customer login screen if the customer button clicked
            primaryStage.setScene(createCustomerLog(primaryStage));
        });

        //add to hbox which sets the buttons up horizontally 
        HBox buttons = new HBox(25); 
        //put them in the center
        buttons.setAlignment(Pos.CENTER); 
        //adding manager button and customer button
        buttons.getChildren().addAll(bManager, bCustomer); 

        // Create a label for the title
        Label titleLabel = new Label("BANKING APPLICATION");
        titleLabel.setFont(new Font("Arial", 30)); // Set the font size to 30
        titleLabel.setTextFill(Color.WHITE); // Set the text color to white

        // Create a VBox to hold the title and buttons
        VBox layout = new VBox(20); // 20 is the spacing between elements
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titleLabel, buttons);

        StackPane root = new StackPane();
        //add the VBox to the pane
        root.getChildren().add(layout); 
        initialScene = new Scene(root, 500, 500);
        // Set the background to a green gradient
        initialScene.getRoot().setStyle("-fx-background-color: linear-gradient(to bottom, #00C853, #388E3C);");

        //Setting height width, title and setting scene to initial scene
        primaryStage.setMinWidth(500);    
        primaryStage.setMinHeight(500);
        primaryStage.setTitle("Banking Application");
        primaryStage.setScene(initialScene);
        primaryStage.show();
    }

    //For the manager log on screen. (after manager button has been clicked scene is set to this)
    private Scene createManagerLog(Stage primaryStage) {
        //create labels and text fields 
        Label lblUserName = new Label("Enter your username: ");
        lblUserName.setTextFill(Color.WHITE); 
        TextField txtUserName = new TextField();
        Label lblPassword = new Label("Enter your password: ");
        lblPassword.setTextFill(Color.WHITE); 
        TextField txtPassword = new TextField();
        
        //create a login button 
        Button bLogin = new Button("Log In");
        //create a back button to exit to main screen
        Button bBack = new Button("Back"); 
        
        // Label for the Manager Page title
        Label lblManagerPage = new Label("Manager Page");
        // Set the font size and style
        lblManagerPage.setFont(new Font("Arial", 30));
        lblManagerPage.setTextFill(Color.WHITE);
        lblManagerPage.setAlignment(Pos.CENTER);
 
        //if login button is pressed
        bLogin.setOnAction(e -> 
        {   
            //if password is authenticated, then go to manager scene
            if (manager.loginAuthenticate(txtUserName.getText(), txtPassword.getText())==true) 
            {
                primaryStage.setScene(createManagerScreen(primaryStage));
            }

        });
        
        //go back to main screen if back button is pressed
        bBack.setOnAction(e -> 
        {
            manager = new ManagerAccount(); // Reset the manager object
            primaryStage.setScene(initialScene);
                }); 
        
        //adding lable for username and and textfield for username to hbox
        HBox UserName = new HBox(15); 
        UserName.getChildren().addAll(lblUserName, txtUserName);
        
        //adding lable for password and and password for username to hbox
        HBox Password = new HBox(15);
        Password.getChildren().addAll(lblPassword, txtPassword);
        
        //Centering the Hbox
        UserName.setAlignment(Pos.CENTER);
        Password.setAlignment(Pos.CENTER);
        
        //adding everything to Vbox and centering
        VBox inputs = new VBox(20);
        inputs.getChildren().addAll(lblManagerPage,UserName, Password, bLogin, bBack);
        inputs.setAlignment(Pos.CENTER);

        //adding the Vbox to stackpane
        StackPane root = new StackPane();
        root.getChildren().addAll(inputs);
        //setting background look
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #7B1FA2, #4A148C);");
        
        //return a new scene
        return new Scene(root, 500, 500);
    }
    
    private Scene createCustomerLog(Stage primaryStage) {
        //create labels and text fields for username
        Label lblUserName = new Label("Enter username: ");
        lblUserName.setTextFill(Color.WHITE);
        TextField txtUserName = new TextField();
        
        //create labels and text fields for password
        Label lblPassword = new Label("Enter password: ");
        lblPassword.setTextFill(Color.WHITE);
        TextField txtPassword = new TextField();
        
        // Label for the Customer Page title
        Label lblCustomerPage = new Label("Customer Page");
        lblCustomerPage.setFont(new Font("Arial", 30)); // Set the font size and style
        lblCustomerPage.setTextFill(Color.WHITE); // Set the text color to white
        lblCustomerPage.setAlignment(Pos.CENTER); // Center the label

        //create a login button and back
        Button bLogin = new Button("Log In"); 
        Button bBack = new Button("Back");
        
        
        bLogin.setOnAction(e -> 
        {
            try
            {   //if authenticated then
                if (CustomerAccount.loginAuthenticate(txtUserName.getText(), txtPassword.getText()))
                {   
                    //if authenticated then, create set scene to customerScreen
                    primaryStage.setScene(createCustomerScreen(primaryStage, new CustomerAccount(new File("src/main/java/CustomerFiles/" + txtUserName.getText() + ".txt"))));
                    System.out.println("Authenticated.");
                }
                else {
                    System.out.println("Not Authenticated");
                }
            } catch (FileNotFoundException ex)
            {
                Logger.getLogger(BankApp.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
   
        //go back to main screen if back button is pressed
        bBack.setOnAction(e -> 
        {
            manager = new ManagerAccount(); // Reset the manager object
            primaryStage.setScene(initialScene);
                }); 

        //adding lable for username and and textfield for username to hbox
        HBox UserName = new HBox(15); 
        UserName.getChildren().addAll(lblUserName, txtUserName);
        
        //adding lable for password and and textfield for password to hbox
        HBox Password = new HBox(15);
        Password.getChildren().addAll(lblPassword, txtPassword);
        
        //centering the Hbox
        UserName.setAlignment(Pos.CENTER);
        Password.setAlignment(Pos.CENTER);
        
        //adding buttons and Hboxes to Vbox
        VBox inputs = new VBox(20);
        inputs.getChildren().addAll(lblCustomerPage,UserName, Password, bLogin, bBack);
        inputs.setAlignment(Pos.CENTER);

        //adding Vbox to stackpane
        StackPane root = new StackPane();
        root.getChildren().addAll(inputs);
        //setting background colour. 
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #2196F3, #0D47A1);");
        return new Scene(root, 500, 500);
    }
    
    private Scene createManagerScreen(Stage primaryStage) {
        //labels and textfields
        Label lblCustomerUserName = new Label("Enter Customer's Username: ");
        lblCustomerUserName.setTextFill(Color.WHITE); 
        TextField txtCustomerUserName = new TextField();
        Label lblCustomerPassword = new Label("Enter password: ");
        lblCustomerPassword.setTextFill(Color.WHITE); 
        TextField txtCustomerPassword = new TextField();
        Label status = new Label(""); //empty by default but will be used to display any status updates
        status.setTextFill(Color.WHITE);
        
        
        // Label for the Manager Page title
        Label lblManagerPage = new Label("Manager Page");
        lblManagerPage.setFont(new Font("Arial", 30)); 
        lblManagerPage.setTextFill(Color.WHITE); 
        lblManagerPage.setAlignment(Pos.CENTER); 

        //Create and delete account button and back button
        Button bCreate = new Button("Create Account"); 
        Button bDelete = new Button("Delete Account");
        Button bBack = new Button("Log Out");
        
        //if button to create account is pressed
        bCreate.setOnAction(e -> {
            try
            {
                //if making account was sucessful
                if(manager.addCustomer(txtCustomerUserName.getText(), txtCustomerPassword.getText())) { 
                        status.setText(txtCustomerUserName.getText() + " was added");
                }
                //if it failed (account already exists or whatever)
                else {
                    status.setText(txtCustomerUserName.getText() + " could not be added");
                }
            } 
            catch (IOException ex)
            {
                Logger.getLogger(BankApp.class.getName()).log(Level.SEVERE, null, ex);
            }     
        });    
        
        //If delete button is pressed
        bDelete.setOnAction(e -> {
            //if deletion is successful
            if(manager.deleteCustomer(txtCustomerUserName.getText())) {
                 status.setText(txtCustomerUserName.getText() + " was deleted");
            }
            //if not deleted
            else {
                status.setText(txtCustomerUserName.getText() + " could not be deleted");
            }
            
        });
        
        //go back to main scene (initial scnene)
        //go back to main screen if back button is pressed
        bBack.setOnAction(e -> 
        {
            manager = new ManagerAccount(); // Reset the manager object
            primaryStage.setScene(initialScene);
                }); 
        
        //Hbox for username containing lable and textfield
        HBox UserName = new HBox(15);
        UserName.getChildren().addAll(lblCustomerUserName, txtCustomerUserName);
        UserName.setAlignment(Pos.CENTER);
        
        //Hbox for password containing lable and textfield
        HBox Password = new HBox(15);
        Password.getChildren().addAll(lblCustomerPassword, txtCustomerPassword);
        Password.setAlignment(Pos.CENTER);
        
        //Hbox for status 
        HBox statusBox = new HBox(15);
        statusBox.getChildren().addAll(status);
        statusBox.setAlignment(Pos.CENTER);
        
        //Hbox for buttons
        HBox button = new HBox(10);
        button.getChildren().addAll(bCreate, bDelete, bBack);
        button.setAlignment(Pos.CENTER);
        
        //Hbox for manager page label
        HBox managerPage= new HBox(15);
        managerPage.getChildren().addAll(lblManagerPage);
        managerPage.setAlignment(Pos.CENTER);
        
        //adding everythig to VBox
        VBox inputs = new VBox(5);
        inputs.getChildren().addAll(managerPage,UserName, Password, statusBox, button);
        inputs.setAlignment(Pos.CENTER);
        
        //adding VBox to stake pane
        StackPane root = new StackPane();
        root.getChildren().addAll(inputs);
        //setting background
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #7B1FA2, #4A148C);");
        
        //returning new scene
        return new Scene(root, 500, 500);

    }
    
    private Scene createCustomerScreen(Stage primaryStage, CustomerAccount customer) {
        //textfields and labels
        Label lblCustomerUserName = new Label("Customer username: " + customer.getUserName()); 
        lblCustomerUserName.setTextFill(Color.WHITE); 
        Label lblLevel = new Label("Account Level: " + customer.getLevel());
        lblLevel.setTextFill(Color.WHITE); 
        Label lblBalance = new Label("Account Balance: " + Double.toString(customer.getBalance())); 
        lblBalance.setTextFill(Color.WHITE); 
        TextField moneyField = new TextField(); 
        Label status = new Label(""); 
        status.setTextFill(Color.WHITE); 
        
        
        // Label for the Customer Page title
        Label lblCustomerPage = new Label("Customer Page");
        lblCustomerPage.setFont(new Font("Arial", 30)); // Set the font size and style
        lblCustomerPage.setTextFill(Color.WHITE); // Set the text color to white
        lblCustomerPage.setAlignment(Pos.CENTER); // Center the label
         
        Button bDeposit = new Button("Deposit"); //button declarations
        Button bWithdraw = new Button("Withdraw");
        Button bOnlinePurchase = new Button("Online Purchase");
        Button bRefresh = new Button("Reload page");
        Button bBack = new Button("Log Out");
        
        //Add all to VBOX
        VBox info = new VBox(5); 
        info.getChildren().addAll(lblCustomerPage,lblCustomerUserName, lblLevel, lblBalance);
        info.setAlignment(Pos.CENTER);
        
        //Deposit button pressed
        bDeposit.setOnAction(e -> { 
            try
            {   
                //if deposit is successfull
                if (customer.depositMoney(Double.valueOf(moneyField.getText()))) {
                    status.setText("Successfully deposited into the bank account");
                   
                }
                //if deposit fails
                else {
                    status.setText("Unable to deposit this amount.");
                }
            } catch (IOException ex)
            {
                Logger.getLogger(BankApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (Exception ep) {
                status.setText("Please enter a valid amount");
            }
        });
        
        //if withdraw button is pressed
        bWithdraw.setOnAction(e -> {
            try
            {
                //if withdraw is sucessful
                if(customer.withdrawMoney(Double.valueOf(moneyField.getText()))) {
                    status.setText("Successfully withdrew from the bank account");
                }  
                //if it is unsucessful
                else {
                    status.setText("Unable to withdraw this amount");
                }
            } 
            catch (IOException ex){
                Logger.getLogger(BankApp.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (Exception ep){
                status.setText("Please enter a valid amount");
            }
        });
        
        //if online purchase button is pressed
        bOnlinePurchase.setOnAction(e -> {
            try
            {
                //if was able to make the purchase
                if (customer.onlinePurchase(Double.valueOf(moneyField.getText()))== true) { 
                    status.setText("Successfully made the purchase");
                   
                }
                else {
                    status.setText("Unable to purchase");
                }
            }
            catch (Exception ep) {
                status.setText("Please enter a valid amount");
            }
        });
        
        //if refresh button is pressed. Purpose: refresh the labels that display customer info after deposit etc
        bRefresh.setOnAction(e -> {
            //set texts again
            lblCustomerUserName.setText("Customer Username: " + customer.getUserName());
            lblLevel.setText("Account Level: " + customer.getLevel());
            lblBalance.setText("Account Balance: " + Double.toString(customer.getBalance()));
            status.setText("");
        }); 
        

        //go back to main screen if back button is pressed
        bBack.setOnAction(e -> 
        {
            manager = new ManagerAccount(); // Reset the manager object
            primaryStage.setScene(initialScene);
                }); 
       
        //put all into VBOX
        VBox moneyInput = new VBox(2);
        moneyInput.getChildren().addAll(moneyField, status);
        moneyInput.setAlignment(Pos.CENTER);
        HBox topButtons = new HBox(2);
        topButtons.getChildren().addAll(bDeposit, bWithdraw, bOnlinePurchase);
        topButtons.setAlignment(Pos.CENTER);
        HBox bottomButtons = new HBox(2);
        bottomButtons.getChildren().addAll(bRefresh, bBack);
        bottomButtons.setAlignment(Pos.CENTER);
        VBox allElements = new VBox(1);
        allElements.getChildren().addAll(info, moneyInput, topButtons, bottomButtons);
        allElements.setAlignment(Pos.CENTER);
        
        //put both VBOX into stackPane
        StackPane root = new StackPane();
        root.getChildren().addAll(allElements);
        //add background
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #2196F3, #0D47A1);");
        
        return new Scene(root, 500, 500);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
