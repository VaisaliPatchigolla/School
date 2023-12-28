package crudoperations;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schools13","root","mysql123");

            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\nChoose operation:");
                System.out.println("1. Insert Student");
                System.out.println("2. Read Student Details");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Insert Student Marks");
                System.out.println("6. Read Student Marks");
                System.out.println("7. Update Student Marks");
                System.out.println("8. Delete Student Marks");
                System.out.println("9. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        insertStudent(connection, scanner);
                        break;
                    case 2:
                        readStudent(connection);
                        break;
                    case 3:
                        updateStudent(connection, scanner);
                        break;
                    case 4:
                        deleteStudent(connection, scanner);
                        break;
                    case 5:
                        insertStudentMarks(connection, scanner);
                        break;
                    case 6:
                        readStudentMarks(connection);
                        break;
                    case 7:
                        updateStudentMarks(connection, scanner);
                        break;
                    case 8:
                        deleteStudentMarks(connection, scanner);
                        break;
                    case 9:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertStudent(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter Student Details:");
        System.out.print("Reg No: ");
        int regno = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Class: ");
        String studentClass = scanner.nextLine();
        System.out.print("Section: ");
        String section = scanner.nextLine();
        System.out.print("DOB (YYYY-MM-DD): ");
        String dob = scanner.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Studentdetails VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, regno);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, studentClass);
        preparedStatement.setString(4, section);
        preparedStatement.setDate(5, Date.valueOf(dob));

        preparedStatement.executeUpdate();
        preparedStatement.close();

        System.out.println("Student inserted successfully.");
    }

    private static void readStudent(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Studentdetails");

        System.out.println("\nStudent Details:");
        while (resultSet.next()) {
            System.out.println("Reg No: " + resultSet.getInt("regno"));
            System.out.println("Name: " + resultSet.getString("name"));
            System.out.println("Class: " + resultSet.getString("class"));
            System.out.println("Section: " + resultSet.getString("section"));
            System.out.println("DOB: " + resultSet.getDate("dob"));
            System.out.println("-----------------------");
        }

        resultSet.close();
        statement.close();
    }

    private static void updateStudent(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter Student Details for Update:");
        System.out.print("Reg No: ");
        int regno = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("New Name: ");
        String newName = scanner.nextLine();
        System.out.print("New Class: ");
        String newClass = scanner.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Studentdetails SET name = ?, class = ? WHERE regno = ?");
        preparedStatement.setString(1, newName);
        preparedStatement.setString(2, newClass);
        preparedStatement.setInt(3, regno);

        int rowsUpdated = preparedStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("No student found with the given Reg No.");
        }

        preparedStatement.close();
    }

    private static void deleteStudent(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter Reg No of Student to Delete:");
        int regno = scanner.nextInt();

        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Studentdetails WHERE regno = ?");
        preparedStatement.setInt(1, regno);

        int rowsDeleted = preparedStatement.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("No student found with the given Reg No.");
        }

        preparedStatement.close();
    }

    private static void insertStudentMarks(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter Student Marks:");
        System.out.print("Reg No: ");
        int regno = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Subject Name: ");
        String subjectName = scanner.nextLine();
        System.out.print("Marks: ");
        int marks = scanner.nextInt();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Studentmarks VALUES (?, ?, ?, ?)");
        preparedStatement.setInt(1, regno);
        preparedStatement.setString(2, subjectName);
        preparedStatement.setInt(3, marks);
        preparedStatement.setInt(4, 0); // Set total to 0 initially

        preparedStatement.executeUpdate();
        preparedStatement.close();

        System.out.println("Student marks inserted successfully.");
    }

    private static void readStudentMarks(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Studentmarks");

        System.out.println("\nStudent Marks:");
        while (resultSet.next()) {
            System.out.println("Reg No: " + resultSet.getInt("regno"));
            System.out.println("Subject Name: " + resultSet.getString("subject_name"));
            System.out.println("Marks: " + resultSet.getInt("marks"));
            System.out.println("Total: " + resultSet.getInt("total"));
            System.out.println("-----------------------");
        }

        resultSet.close();
        statement.close();
    }

    private static void updateStudentMarks(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter Student Marks for Update:");
        System.out.print("Reg No: ");
        int regno = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Subject Name: ");
        String subjectName = scanner.nextLine();
        System.out.print("New Marks: ");
        int newMarks = scanner.nextInt();

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Studentmarks SET marks = ? WHERE regno = ? AND subject_name = ?");
        preparedStatement.setInt(1, newMarks);
        preparedStatement.setInt(2, regno);
        preparedStatement.setString(3, subjectName);

        int rowsUpdated = preparedStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Student marks updated successfully.");
        } else {
            System.out.println("No student marks found with the given Reg No and Subject Name.");
        }

        preparedStatement.close();
    }

    private static void deleteStudentMarks(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter Reg No and Subject Name of Student:");
        System.out.print("Reg No: ");
        int regno = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Subject Name: ");
        String subjectName = scanner.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Studentmarks1 WHERE regno = ? AND subject_name = ?");
        preparedStatement.setInt(1, regno);
        preparedStatement.setString(2, subjectName);

        int rowsDeleted = preparedStatement.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Student marks deleted successfully.");
        } else {
            System.out.println("No student marks found with the given Reg No and Subject Name.");
        }

        preparedStatement.close();
    }
}
