import java.sql.*; // Import JDBC classes to connect and work with MySQL

/**
 * Headline: Demo - Entity Data Model
 * Description: A structural class wrapper acting as a Data Transfer Object (DTO)
 * to hold temporary student attributes in memory.
 */
class Demo { 
    int roll; // Stores roll number
    String name; // Stores student name
    float cgpa; // Stores student CGPA

    // Constructor to initialize object values
    Demo(int roll, String name, float cgpa) { 
        this.roll = roll; // Assign roll value to object field
        this.name = name; // Assign name value to object field
        this.cgpa = cgpa; // Assign cgpa value to object field
    }
}

/**
 * Headline: StudentApp - Parameterized Object Persistence Engine
 * Description: Orchestrates entity object mapping into active MySQL relational tables
 * utilizing pre-compiled parameterized queries to prevent SQL Injection risks.
 */
public class StudentApp { 
    
    /**
     * Headline: Application Entry Point
     * Description: Initializes transient object states and routes them through the persistence layer inside a protected try-catch matrix.
     */
    public static void main(String[] args) { 
        Demo d1 = new Demo(1, "Apple", 9.8f); // Create first student object
        Demo d2 = new Demo(2, "Banana", 9.3f); // Create second student object

        try { 
            insertObject(d1); // Insert first object into database
            insertObject(d2); // Insert second object into database
            System.out.println("Data pipeline processed. Records written successfully.");
        } catch (SQLException e) { 
            System.err.println("Database Transaction Failure:");
            e.printStackTrace(); // Print error details if connection breaks
        }
    }

    /**
     * Headline: Prepared Parameterized Insertion Routine
     * Description: Bridges the gap between the Java OOP layer and Relational DB tables using PreparedStatement bindings.
     * @param d The entity object containing raw data primitives to persist.
     * @throws SQLException Relays network transport or table schema mismatches to the higher execution block.
     */
    public static void insertObject(Demo d) throws SQLException { 
        String url = "jdbc:mysql://localhost:3306/test"; // Database URL
        String user = "root"; // Username
        String pass = "root"; // Password
        String sql = "INSERT INTO sample (roll, name, cgpa) VALUES (?, ?, ?)"; // SQL query with placeholders

        // Manually open socket communication channels
        Connection con = DriverManager.getConnection(url, user, pass); 
        PreparedStatement ps = con.prepareStatement(sql); 

        // Safely map values into designated indexed query placeholders
        ps.setInt(1, d.roll);    // Bind object field 'roll' to first placeholder index (?)
        ps.setString(2, d.name);  // Bind object field 'name' to second placeholder index (?)
        ps.setFloat(3, d.cgpa);   // Bind object field 'cgpa' to third placeholder index (?)
        
        ps.executeUpdate(); // Ship the compiled parameterized query payload to the server engine

        // Explicit cleanup execution to prevent system port exhaustion
        ps.close(); 
        con.close(); 
    }
}
