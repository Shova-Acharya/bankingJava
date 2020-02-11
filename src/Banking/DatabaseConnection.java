package Banking;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //to get database connection
    public static Connection getDbConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "");
            System.out.println("Database connection successful");
        } catch (Exception e) {
            e.printStackTrace();
        } //catch (SQLException e){
          //e.printStackTrace();
       // }
        return con;
    }
}
