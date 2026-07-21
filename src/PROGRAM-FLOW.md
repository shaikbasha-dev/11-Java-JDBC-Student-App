# Program Flow

## Introduction

Every Java application follows a sequence of execution steps from program startup until termination. Understanding this sequence is essential for mastering both Core Java and JDBC.

This document explains how the **Java JDBC Student Application** executes internally, beginning with the `main()` method, progressing through object creation, database communication, parameterized SQL execution, and finally releasing all allocated resources.

---

# High-Level Execution Flow

The overall execution of the application can be represented as follows.

```text
Program Starts
        │
        ▼
JVM Loads StudentApp Class
        │
        ▼
main() Method Invoked
        │
        ▼
Create Demo Objects
        │
        ▼
Call insertObject(d1)
        │
        ▼
Store First Record
        │
        ▼
Call insertObject(d2)
        │
        ▼
Store Second Record
        │
        ▼
Display Success Message
        │
        ▼
Program Ends
```

---

# Step 1 – Program Starts

Execution begins when the JVM loads the `StudentApp` class and invokes the `main()` method.

```java
public static void main(String[] args)
```

The `main()` method serves as the application's entry point.

---

# Step 2 – Create Demo Objects

Two objects of the `Demo` class are created.

```java
Demo d1 = new Demo(1, "Apple", 9.8f);
Demo d2 = new Demo(2, "Banana", 9.3f);
```

Each object temporarily stores student information in memory.

### Object 1

| Field | Value |
|--------|-------|
| roll | 1 |
| name | Apple |
| cgpa | 9.8 |

### Object 2

| Field | Value |
|--------|-------|
| roll | 2 |
| name | Banana |
| cgpa | 9.3 |

At this stage, the data exists only in the JVM memory.

---

# Step 3 – Enter Try Block

The application enters the protected execution block.

```java
try
{
    insertObject(d1);
    insertObject(d2);
}
```

The purpose of the `try` block is to safely execute database operations that may throw `SQLException`.

---

# Step 4 – Call insertObject()

The first object is passed to the insertion method.

```java
insertObject(d1);
```

The method parameter receives the object.

```java
public static void insertObject(Demo d)
```

Now the object reference `d` points to `d1`.

---

# Step 5 – Create Database Configuration

Inside the method, database configuration values are initialized.

```java
String url = "jdbc:mysql://localhost:3306/test";
String user = "root";
String pass = "root";
```

These values identify:

- Database Server
- Port Number
- Database Name
- Username
- Password

---

# Step 6 – Prepare SQL Statement

The SQL query is created using placeholders.

```java
String sql =
"INSERT INTO sample (roll, name, cgpa)
VALUES (?, ?, ?)";
```

The placeholders (`?`) reserve positions for the object values that will be supplied later.

---

# Step 7 – Establish Database Connection

The application establishes a connection with MySQL.

```java
Connection con =
DriverManager.getConnection(
url,
user,
pass
);
```

Flow:

```text
Java Application
        │
        ▼
DriverManager
        │
        ▼
MySQL JDBC Driver
        │
        ▼
MySQL Database
```

A successful connection returns a `Connection` object.

---

# Step 8 – Create PreparedStatement

The SQL statement is compiled.

```java
PreparedStatement ps =
con.prepareStatement(sql);
```

Unlike the `Statement` interface, `PreparedStatement` precompiles the SQL query and allows safe parameter binding.

Benefits include:

- SQL Injection prevention
- Better performance
- Strong type checking
- Improved readability

---

# Step 9 – Bind Object Values

The object's fields are assigned to SQL placeholders.

```java
ps.setInt(1, d.roll);
ps.setString(2, d.name);
ps.setFloat(3, d.cgpa);
```

Mapping:

| Placeholder | Object Value |
|-------------|--------------|
| ?1 | d.roll |
| ?2 | d.name |
| ?3 | d.cgpa |

For the first object, the query becomes logically equivalent to:

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(1, 'Apple', 9.8);
```

When processing the second object:

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(2, 'Banana', 9.3);
```

---

# Step 10 – Execute SQL Statement

The prepared statement is sent to the MySQL server.

```java
ps.executeUpdate();
```

The database performs the INSERT operation and stores the record in the `sample` table.

---

# Step 11 – Close JDBC Resources

After successful execution, resources are released.

```java
ps.close();
con.close();
```

Closing resources prevents:

- Connection leaks
- Memory leaks
- Database lock issues
- Resource exhaustion

---

# Step 12 – Repeat for Second Object

The same process is repeated for:

```java
insertObject(d2);
```

Since the method is reusable, no changes are required in the insertion logic.

Only the object's values change.

---

# Step 13 – Display Success Message

After both insert operations complete successfully, the application displays:

```java
System.out.println(
"Data pipeline processed. Records written successfully."
);
```

Expected console output:

```text
Data pipeline processed. Records written successfully.
```

---

# Step 14 – Exception Handling

If any database-related error occurs during execution, control transfers to the `catch` block.

```java
catch(SQLException e)
```

The application displays:

```text
Database Transaction Failure:
```

Followed by:

```java
e.printStackTrace();
```

This prints detailed exception information, making debugging easier.

---

# Complete Program Workflow

```text
Program Starts
        │
        ▼
Load StudentApp Class
        │
        ▼
Execute main()
        │
        ▼
Create Demo Object d1
        │
        ▼
Create Demo Object d2
        │
        ▼
Call insertObject(d1)
        │
        ▼
Create Database Connection
        │
        ▼
Prepare SQL Statement
        │
        ▼
Bind d1 Values
        │
        ▼
Execute INSERT
        │
        ▼
Close Resources
        │
        ▼
Call insertObject(d2)
        │
        ▼
Create Database Connection
        │
        ▼
Prepare SQL Statement
        │
        ▼
Bind d2 Values
        │
        ▼
Execute INSERT
        │
        ▼
Close Resources
        │
        ▼
Display Success Message
        │
        ▼
Program Ends
```

---

# Summary

The program follows a simple and structured execution flow. It begins by creating Java objects, passes those objects to a reusable insertion method, establishes a JDBC connection, prepares a parameterized SQL statement, binds object values, inserts records into the MySQL `sample` table, closes all database resources, and finally reports successful execution. This workflow demonstrates the complete lifecycle of object persistence using **JDBC** and **PreparedStatement**, making it an excellent example of secure and maintainable Java database programming.
