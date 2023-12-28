package tables;

import java.sql.*;
public class Table1School13 {
	
	    public static void main(String[] args) throws SQLException, ClassNotFoundException {
	    
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schools13","root","mysql123");
                Statement statement = connection.createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS Studentdetails (" +
	                    "regno INT PRIMARY KEY," +
	                    "name VARCHAR(255)," +
	                    "class VARCHAR(50)," +
	                    "section VARCHAR(10)," +
	                    "dob DATE" +
	                    ")");
                System.out.println("Table created successfully.");
	        
	    }
	}