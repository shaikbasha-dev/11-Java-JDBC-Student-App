# Troubleshooting Guide

## Introduction

While working with JDBC applications, developers may encounter various runtime errors related to the database, JDBC driver, SQL statements, or application configuration. This document provides solutions to the most common problems that may occur while executing the **Java JDBC Student Application**.

The troubleshooting steps in this guide are based on the actual implementation of this project, which connects to the MySQL **`test`** database and inserts records into the **`sample`** table using `PreparedStatement`.

---

# Troubleshooting Workflow

```text
Application Error
        │
        ▼
Read Exception Message
        │
        ▼
Identify Root Cause
        │
        ▼
Apply Recommended Solution
        │
        ▼
Run Application Again
        │
        ▼
Verify Database Output
```

---

# Problem 1 – Access Denied

### Example Error

```text
Access denied for user 'root'
```

### Possible Cause

- Incorrect MySQL username
- Incorrect password
- User account does not have permission

### Solution

Verify the connection details.

```java
String user = "root";
String pass = "root";
```

Ensure they match your local MySQL configuration.

---

# Problem 2 – Unknown Database

### Example Error

```text
Unknown database 'test'
```

### Possible Cause

The database has not been created.

### Solution

Create the database.

```sql
CREATE DATABASE test;
```

Then select it.

```sql
USE test;
```

---

# Problem 3 – Table Doesn't Exist

### Example Error

```text
Table 'test.sample' doesn't exist
```

### Possible Cause

The required table is missing.

### Solution

Create the table.

```sql
CREATE TABLE sample
(
    roll INT PRIMARY KEY,
    name VARCHAR(100),
    cgpa FLOAT
);
```

---

# Problem 4 – Duplicate Primary Key

### Example Error

```text
Duplicate entry '1' for key 'PRIMARY'
```

### Possible Cause

A record with the same `roll` value already exists.

### Solution

View existing records.

```sql
SELECT * FROM sample;
```

Either:

- Use different roll numbers.
- Remove existing records if appropriate.

Example:

```sql
DELETE FROM sample
WHERE roll = 1;
```

---

# Problem 5 – No Suitable Driver

### Example Error

```text
No suitable driver found
```

### Possible Cause

The MySQL Connector/J library is not available on the application's classpath.

### Solution

- Download MySQL Connector/J.
- Add the JAR file to the project.
- Rebuild the project.

---

# Problem 6 – Communications Link Failure

### Example Error

```text
Communications link failure
```

### Possible Cause

The MySQL server is not running.

### Solution

Start the MySQL service.

Verify the server is running before executing the program.

---

# Problem 7 – SQL Syntax Error

### Example Error

```text
SQLSyntaxErrorException
```

### Possible Cause

- Invalid SQL statement
- Incorrect table name
- Incorrect column names

### Solution

Verify that the SQL matches the database schema.

The SQL used in this project is:

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(?, ?, ?);
```

---

# Problem 8 – Incorrect JDBC URL

### Example Error

```text
Unknown database
```

or

```text
Connection refused
```

### Possible Cause

Incorrect JDBC URL.

### Solution

Verify the connection string.

```java
jdbc:mysql://localhost:3306/test
```

Ensure:

- Host is correct.
- Port is correct.
- Database name is correct.

---

# Problem 9 – SQLException Printed

### Console Output

```text
Database Transaction Failure:
```

followed by:

```text
java.sql.SQLException
```

### Meaning

The application entered the `catch` block because a database operation failed.

### Solution

Read the complete stack trace carefully to identify the root cause before making changes.

---

# Problem 10 – No Data in Database

### Possible Cause

The application did not complete successfully.

### Verification

Execute:

```sql
SELECT * FROM sample;
```

If the table is empty:

- Verify the application completed without exceptions.
- Confirm `executeUpdate()` was called.
- Check the database connection.
- Verify the SQL statement.

---

# Diagnostic Checklist

Before debugging, verify the following.

| Item | Verify |
|------|--------|
| MySQL Server | Running |
| Database | `test` exists |
| Table | `sample` exists |
| JDBC URL | Correct |
| Username | Correct |
| Password | Correct |
| JDBC Driver | Added |
| SQL Statement | Valid |
| Java Program | Compiles Successfully |

---

# Error Resolution Workflow

```text
Run Application
        │
        ▼
Exception Occurs?
        │
   Yes  │  No
        │
        ▼
Read Stack Trace
        │
        ▼
Identify Cause
        │
        ▼
Apply Fix
        │
        ▼
Run Again
        │
        ▼
Successful Execution
```

---

# Useful Verification Commands

## Verify Database

```sql
SHOW DATABASES;
```

---

## Verify Tables

```sql
SHOW TABLES;
```

---

## Verify Table Structure

```sql
DESCRIBE sample;
```

---

## Verify Inserted Records

```sql
SELECT * FROM sample;
```

---

# Debugging Tips

When debugging JDBC applications:

- Read the complete exception message.
- Check the stack trace carefully.
- Verify database connectivity.
- Confirm the database schema matches the SQL statement.
- Ensure the JDBC driver is available.
- Test SQL queries directly in MySQL before running the Java application.
- Verify the database contains the expected records after execution.

---

# Prevention Tips

Many runtime problems can be avoided by following these practices.

- Start the MySQL server before running the application.
- Create the required database and table in advance.
- Keep JDBC configuration synchronized with the database.
- Use `PreparedStatement` for parameterized SQL.
- Close database resources after execution.
- Verify inserted records using SQL queries.

---

# Summary

Most issues encountered while running the **Java JDBC Student Application** are related to database configuration, JDBC driver setup, authentication, or schema mismatches. By carefully reading exception messages, verifying the MySQL environment, and following the troubleshooting steps in this guide, developers can quickly identify and resolve problems while gaining a deeper understanding of JDBC application debugging.
