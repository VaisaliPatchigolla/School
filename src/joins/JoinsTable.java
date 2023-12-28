package joins;


import java.sql.*;
public class JoinsTable {
     public static void main(String[] args) {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schools13","root","mysql123");
                performLeftJoin(connection);

	            connection.close();
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void performLeftJoin(Connection connection) throws SQLException {
	        Statement statement = connection.createStatement();
	        String query = "SELECT t1.*, t2.subject_name, t2.marks, t2.total " +
	                       "FROM Studentdetails t1 " +
	                       "LEFT JOIN Studentmarks t2 ON t1.regno = t2.regno";

	        ResultSet resultSet = statement.executeQuery(query);

	        System.out.println("Combined Student and Marks Information (LEFT JOIN):");
	        while (resultSet.next()) {
	            System.out.println("Reg No: " + resultSet.getInt("regno"));
	            System.out.println("Name: " + resultSet.getString("name"));
	            System.out.println("Class: " + resultSet.getString("class"));
	            System.out.println("Section: " + resultSet.getString("section"));
	            System.out.println("DOB: " + resultSet.getDate("dob"));
	            System.out.println("Subject Name: " + resultSet.getString("subject_name"));
	            System.out.println("Marks: " + resultSet.getInt("marks"));
	            System.out.println("Total: " + resultSet.getInt("total"));
	            System.out.println("-----------------------");
	        }

	        resultSet.close();
	        statement.close();
	    }
	}