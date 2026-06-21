# Student Data Object Mapping & PreparedStatement Sub-System

##  Project Overview

This project demonstrates how to map structural Object-Oriented Programming (OOP) class components into a relational database using Java Database Connectivity (JDBC). Rather than writing raw hardcoded queries, this system utilizes a compiled **`PreparedStatement`** architecture to process data parameters cleanly and securely.

---

##  Technologies Used
* **Java SE** (Object-Oriented Programming Language)
* **JDBC API** (Database Connection Framework)
* **MySQL** (Relational Database Management System)
* **SQL** (Structured Query Language)

---

##  Method Registry & Technical Specs

Here is the operational breakdown of the architectural units within this package:

| Component / Method | Context Scope | Input Parameter | Core Operational Purpose |
| :--- | :--- | :--- | :--- |
| **`Demo` Class** | Default (Package-Private) | Class Structure | Models the entity framework blueprint holding raw state attributes (`roll`, `name`, `cgpa`). |
| **`main` Method** | `public static` | `String[] args` | Main controller. Instantiates the transient memory data elements and manages the transaction runtime wrapper. |
| **`insertObject`** | `public static` | `Demo d` | Relational broker. Opens the JDBC socket pool, compiles the SQL blueprint, links values to positional markers, and writes to disk. |

---

##  Architectural Processes Explained

### 1. The Power of `PreparedStatement` Over Standard Statements

Unlike legacy basic statements, a `PreparedStatement` pre-compiles the query outline (`INSERT INTO sample VALUES (?,?,?)`) on the SQL server before injecting variables. This yields two core system benefits:

* **Security Shield**: It guarantees that user input is treated strictly as a text or number primitive, preventing malicious inputs from hijacking the execution logic (SQL Injection).
* **Performance Speed**: The database does not need to parse and analyze the query structure multiple times when processing loop sets.

### 2. Positional Index Placeholder Layout

The variable data bindings are structured sequentially from left to right based on the placeholder tracking indices:

* `ps.setInt(1, d.roll)` maps directly into column position 1 (`roll`).
* `ps.setString(2, d.name)` maps directly into column position 2 (`name`).
* `ps.setFloat(3, d.cgpa)` maps directly into column position 3 (`cgpa`).

---

##  Program Pseudocode Flow

```text

START
    // Step 1: Model Entity Records inside Heap Memory
    ALLOCATE Demo Instance d1 WITH INITIAL_VALUES (1, "Apple", 9.8)
    ALLOCATE Demo Instance d2 WITH INITIAL_VALUES (2, "Banana", 9.3)

    TRY
        // Pass model objects into persistence processor blocks
        CALL Sub_Routine insertObject(d1)
        CALL Sub_Routine insertObject(d2)
        PRINT "Data pipeline processed. Records written successfully."
    CATCH SQLException Exception_Handler
        PRINT Exception_Handler Error_Stack_Trace
    END TRY
END

SUB_ROUTINE insertObject(Demo instance_reference)
    SET connection_target = "jdbc:mysql://localhost:3306/test"
    SET connection_user   = "root"
    SET connection_pass   = "root"
    SET statement_query   = "INSERT INTO sample (roll, name, cgpa) VALUES (?, ?, ?)"

    OPEN active_connection TO connection_target USING connection_user, connection_pass
    COMPILE statement_query THROUGH active_connection ASSIGN TO precompiled_statement

    BIND instance_reference.roll INTO precompiled_statement POSITION 1
    BIND instance_reference.name INTO precompiled_statement POSITION 2
    BIND instance_reference.cgpa INTO precompiled_statement POSITION 3

    EXECUTE_UPDATE USING precompiled_statement
    
    DEALLOCATE precompiled_statement
    DEALLOCATE active_connection
END SUB_ROUTINE
```

## Expected Outputs

When the MySQL server framework is online and your database structure is active, the app logs this processing message:

Data pipeline processed. Records written successfully.


If an interface mapping error occurs, it dumps technical execution stack reports like this:

java.sql.SQLSyntaxErrorException: Table 'test.sample' doesn't exist


## Design Summary

This component bridges the gap between raw data storage layouts and high-level object-oriented designs. By processing standard domain objects through specialized methods and replacing raw text queries with safe query compilers, this module implements reliable enterprise architectures suitable for production portfolio code catalogs.

