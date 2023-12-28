package tables;

import java.sql.*;

public class Table2School13{
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

    
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schools13","root","mysql123");
    Statement statement = connection.createStatement();
    statement.executeUpdate("CREATE TABLE IF NOT EXISTS Studentmarks (" +
            "regno INT," +
            "subject_name VARCHAR(255)," +
            "marks INT" +
            ")");
    System.out.println("Table created successfully.");

}
}
