package Banking;

import Banking.BankingMain;
import com.sun.org.apache.bcel.internal.generic.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.SQLException;

import static javafx.application.Platform.exit;

public class BankingMain {
    static int value;
    static char option;
    java.util.Scanner sc = new Scanner(System.in);

    public void addAccount() throws SQLException {
        String Acno = null;
        String name = null;
        String address = null;
        String create_date = null;
        double amount = 0;
        Connection con;
        con = DatabaseConnection.getDbConnection();
        System.out.println("A/C add started");
        System.out.println("Enter your account number: ");
        Acno = sc.next();
        System.out.println("Enter your name: ");
        name = sc.next();
        System.out.println("Enter your address: ");
        address = sc.next();
        System.out.println("Enter today's date: ");
        create_date = sc.next();
        System.out.println("Enter the amount: ");
        amount = sc.nextInt();
        Statement st = con.createStatement();
        st.executeUpdate("insert into account values( '"+Acno+"','"+name+ "', '" + address+"'," +amount+ " ,'" + create_date +"')");
        System.out.println("Account successfully added");
    }
    public void deleteAccount() throws SQLException {
        String Acno = null;
        Connection con = null;
        con = DatabaseConnection.getDbConnection();
        System.out.println("Delete account");
        System.out.println("Enter your account number: ");
        Acno = sc.next();
        Statement st = con.createStatement();
        st.executeUpdate("delete from account where ACno ="+Acno);
        System.out.println("Account successfully deelted");
    }
    public void depositAccount() throws SQLException {

        double db_amount = 0;
        double total_amount;
        String Acno;
        Connection con;
        con = DatabaseConnection.getDbConnection();
        System.out.println("Deposit amount");
        System.out.println("Enter your account number: ");
        Acno = sc.next();
        System.out.println("Enter the deposit amount");
        double user_amount = sc.nextInt();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("Select Amount from account where ACno = "+Acno);
        while (rs.next()){
           db_amount = rs.getDouble("Amount");
        }
        total_amount = user_amount + db_amount;
        st.executeUpdate("Update account set Amount = "+total_amount+ "where ACno = "+Acno);
        System.out.println("Amount succesfully deposited.");

    }
    public void withdrawalAccount() throws SQLException {
        double db_amount = 0;
        double user_amount =0;
        String Acno = null;
        Connection con = null;
        con = DatabaseConnection.getDbConnection();
        System.out.println("Withdraw amount");
        System.out.println("Enter your account number: ");
        Acno = sc.next();
        System.out.println("Enter the withdrawal amount");
        user_amount = sc.nextInt();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("Select Amount from account where ACno = "+Acno);
        while (rs.next()){
            db_amount = rs.getDouble("Amount");
        }
        if (user_amount <= db_amount) {
            double new_amount = db_amount - user_amount;
            st.executeUpdate("update account set Amount= "+new_amount+ "where ACno = "+Acno);
            System.out.println("Amount succesfully withdrawal.");
        } else{
            System.out.println("No sufficient balance.");
        }
    }
    public void checkbalanceAccount() throws SQLException {
        String Acno = null;
        Connection con = null;
        con = DatabaseConnection.getDbConnection();
        System.out.println("Check balance of the account");
        sc = new java.util.Scanner(System.in);
        System.out.println("Enter your account number: ");
        Acno = sc.next();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("Select Amount from account where ACno = "+Acno);

        while (rs.next()){
            System.out.println("Your account number 101" + " "+ " balance is: "+rs.getDouble("amount"));
        }
    }
    public void exitAccount(){
        System.out.println("Exit account");
        exit();
    }
    public void viewDetail() throws SQLException {
        Connection con = null;
        con = DatabaseConnection.getDbConnection();
        System.out.println("View details of your selected account number");

    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("Select * from account");
        System.out.println("Id\tName\tAddress\tAmount\tUpdated date");

        while(rs.next()){
        System.out.println(rs.getString("ACno")+"\t"+rs.getString("Name")
                +"\t\t"+rs.getString("Address")+"\t\t"+rs.getDouble("Amount")+
                "\t"+rs.getString("UpdatedDate"));
    }
}
    public void operation() throws SQLException {
        BankingMain b = new BankingMain();
        java.util.Scanner sc = new Scanner(System.in);
        System.out.println("/////////////Banking Transaction////////////");
        System.out.println("1. Add account 2. Delete account 3. Deposit 4. Withdrawal 5. Check Balance 6. View Detail 7. Exit");
        System.out.println();
        System.out.println("Enter your choice:");

        value = sc.nextInt();

        switch (value){
            case 1:
                b.addAccount();
                break;
            case 2:
                b.deleteAccount();
                break;
            case 3:
                b.depositAccount();
                break;
            case 4:
                b.withdrawalAccount();
                break;
            case 5:
                b.checkbalanceAccount();
                break;
            case 6:
                b.viewDetail();
                break;
            case 7:
                b.exitAccount();
                break;


            default:
                System.out.println("Enter right choice");
                break;
        }
    }
    public static void main(String [] args) throws SQLException {

        BankingMain op = new BankingMain();
        op.operation();
        System.out.println("Do you want to do another transaction? (y/n)");
        java.util.Scanner rs = new java.util.Scanner(System.in);
        option = rs.next().charAt(0);
        if(option =='y'){
            op.operation();
        } else {
            System.out.println("Thank You!");
        }





    }
}
