
import java.sql.*;
import java.util.Scanner;

public class UpdateMarks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Get user input
        System.out.print("Enter Roll Number: ");
        int rollNo = sc.nextInt();

        System.out.print("Enter New Marks: ");
        int newMarks = sc.nextInt();

        // Step 2: Database connection details
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String user = "root";              // your MySQL username
        String password = "Rose##23."; // your MySQL password

        Connection con = null;
        PreparedStatement pst = null;

        try {
            // Step 3: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 4: Establish connection
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database successfully!");

            // Step 5: Prepare SQL query
            String query = "UPDATE students SET marks = ? WHERE roll_no = ?";

            // Step 6: Create PreparedStatement
            pst = con.prepareStatement(query);
            pst.setInt(1, newMarks);
            pst.setInt(2, rollNo);

            // Step 7: Execute update
            int rowsUpdated = pst.executeUpdate();

            // Step 8: Display result
            if (rowsUpdated > 0) {
                System.out.println("Marks updated successfully!");
            } else {
                System.out.println("No student found with that roll number.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Add the connector .jar to your project.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        } finally {
            // Step 9: Close resources
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
                sc.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

