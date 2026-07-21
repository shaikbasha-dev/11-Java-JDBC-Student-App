# Execution Guide

## Introduction

This document provides a step-by-step guide for compiling, configuring, and executing the **Java JDBC Student Application**. It follows the actual implementation of the project, which inserts Java object data into the MySQL **`test`** database using a **PreparedStatement**.

By following this guide, you will successfully execute the application and verify that the records have been stored in the **`sample`** table.

---

# Prerequisites

Ensure the following software is installed before running the project.

| Software | Purpose |
|----------|---------|
| Java Development Kit (JDK 17 or later) | Compile and run Java applications |
| MySQL Server | Database server |
| MySQL Connector/J | JDBC Driver |
| Java IDE (IntelliJ IDEA, Eclipse, or VS Code) | Development environment |

---

# Step 1 – Start MySQL Server

Before executing the Java application, ensure that the MySQL server is running.

Example:

```text
Windows Services

↓

MySQL80

↓

Status : Running
```

---

# Step 2 – Create the Database

Login to MySQL.

```sql
mysql -u root -p
```

Create the required database.

```sql
CREATE DATABASE test;
```

Select the database.

```sql
USE test;
```

---

# Step 3 – Create the Table

Execute the following SQL statement.

```sql
CREATE TABLE sample
(
    roll INT PRIMARY KEY,
    name VARCHAR(100),
    cgpa FLOAT
);
```

Verify the table.

```sql
SHOW TABLES;
```

Expected Output

```text
sample
```

---

# Step 4 – Verify Java Source Code

Open **StudentApp.java** and verify the database configuration.

```java
String url = "jdbc:mysql://localhost:3306/test";
String user = "root";
String pass = "root";
```

Update these values if your MySQL configuration is different.

---

# Step 5 – Add MySQL Connector/J

Ensure that the MySQL JDBC Driver has been added to your project.

Without the JDBC Driver, the application cannot establish a connection with the MySQL database.

---

# Step 6 – Compile the Application

Compile the Java source file.

```bash
javac StudentApp.java
```

If compilation succeeds, a corresponding `.class` file is generated.

Expected Output

```text
StudentApp.class
```

---

# Step 7 – Execute the Application

Run the compiled application.

```bash
java StudentApp
```

---

# What Happens During Execution?

The following sequence occurs internally.

1. JVM starts the application.
2. The `main()` method is executed.
3. Two `Demo` objects are created.
4. `insertObject(d1)` is invoked.
5. A connection to the `test` database is established.
6. A `PreparedStatement` is created.
7. The first object's values are bound to the SQL placeholders.
8. The first record is inserted into the `sample` table.
9. JDBC resources are closed.
10. The same process is repeated for the second object.
11. A success message is displayed.
12. The application terminates.

---

# Execution Workflow

```text
Start Program
      │
      ▼
Load StudentApp
      │
      ▼
Create Demo Objects
      │
      ▼
Connect to Database
      │
      ▼
Create PreparedStatement
      │
      ▼
Bind Object Values
      │
      ▼
Execute INSERT Query
      │
      ▼
Close PreparedStatement
      │
      ▼
Close Connection
      │
      ▼
Repeat for Next Object
      │
      ▼
Display Success Message
      │
      ▼
Program Ends
```

---

# Expected Console Output

If both records are inserted successfully, the application displays:

```text
Data pipeline processed. Records written successfully.
```

---

# Verify Database Records

After execution, verify that the records have been inserted.

```sql
SELECT * FROM sample;
```

Expected Output

```text
+------+--------+------+
| roll | name   | cgpa |
+------+--------+------+
| 1    | Apple  | 9.8  |
| 2    | Banana | 9.3  |
+------+--------+------+
```

---

# Execution Sequence

```text
StudentApp.java
        │
        ▼
JVM
        │
        ▼
main()
        │
        ▼
Create Demo Objects
        │
        ▼
insertObject()
        │
        ▼
DriverManager
        │
        ▼
Connection
        │
        ▼
PreparedStatement
        │
        ▼
executeUpdate()
        │
        ▼
MySQL Database
        │
        ▼
sample Table
        │
        ▼
Records Stored
```

---

# Common Execution Problems

| Problem | Possible Cause | Solution |
|----------|----------------|----------|
| Access denied | Invalid username or password | Verify MySQL credentials |
| Unknown database | Database not created | Create the `test` database |
| Table doesn't exist | Missing `sample` table | Create the required table |
| No suitable driver | MySQL Connector/J not added | Add the JDBC driver |
| Communications link failure | MySQL server not running | Start the MySQL service |
| Duplicate entry | Existing `roll` value | Use a unique roll number or delete the existing record |

---

# Execution Checklist

Before running the application, confirm the following.

- MySQL Server is running.
- Database `test` exists.
- Table `sample` exists.
- JDBC Driver has been added.
- Database URL is correct.
- Username and password are correct.
- Java program compiles successfully.
- No syntax errors exist in the SQL query.

---

# Best Practices During Execution

- Verify the database connection before executing SQL statements.
- Use `PreparedStatement` for parameterized queries.
- Close JDBC resources immediately after use.
- Keep database credentials secure.
- Verify inserted records using `SELECT`.
- Handle `SQLException` properly for easier debugging.

---

# Summary

The execution process of the **Java JDBC Student Application** is straightforward and demonstrates the complete lifecycle of a JDBC-based database operation. The application creates Java objects, establishes a connection to the MySQL **`test`** database, inserts records into the **`sample`** table using a `PreparedStatement`, closes all allocated resources, and confirms successful execution through a console message. This workflow provides a solid practical foundation for understanding JDBC and enterprise Java database programming.
