# Database Setup

## Introduction

Before executing the **Java JDBC Student Application**, the required MySQL database and table must be created. This document explains the complete database setup process based on the actual implementation used in this project.

The application connects to a database named **`test`** and stores Java object data inside the **`sample`** table.

---

# Database Requirements

The application requires the following database environment.

| Component | Value |
|-----------|-------|
| Database Server | MySQL |
| Database Name | `test` |
| Table Name | `sample` |
| Port | `3306` |
| JDBC Driver | MySQL Connector/J |

---

# Database Connection Configuration

The Java application uses the following configuration.

```java
String url = "jdbc:mysql://localhost:3306/test";
String user = "root";
String pass = "root";
```

Ensure that these values match your local MySQL configuration before running the program.

---

# Step 1 – Start MySQL Server

Before creating the database, verify that the MySQL server is running.

Example:

```text
Windows Services

↓

MySQL80

↓

Status : Running
```

If MySQL is not running, start the service before continuing.

---

# Step 2 – Login to MySQL

Open a terminal or command prompt and log in to MySQL.

```sql
mysql -u root -p
```

Enter your password when prompted.

---

# Step 3 – Create the Database

Execute the following SQL statement.

```sql
CREATE DATABASE test;
```

Expected Output

```text
Query OK, 1 row affected
```

---

# Step 4 – Verify the Database

Display all available databases.

```sql
SHOW DATABASES;
```

Example Output

```text
information_schema
mysql
performance_schema
sys
test
```

Confirm that the **`test`** database appears in the list.

---

# Step 5 – Select the Database

Activate the database.

```sql
USE test;
```

Expected Output

```text
Database changed
```

---

# Step 6 – Create the sample Table

The application inserts records into a table named **`sample`**.

Create the table using the following SQL statement.

```sql
CREATE TABLE sample
(
    roll INT PRIMARY KEY,
    name VARCHAR(100),
    cgpa FLOAT
);
```

---

# Table Structure

The table contains the following columns.

| Column | Data Type | Description |
|---------|-----------|-------------|
| roll | INT | Student Roll Number |
| name | VARCHAR(100) | Student Name |
| cgpa | FLOAT | Student CGPA |

---

# Step 7 – Verify the Table

Display the available tables.

```sql
SHOW TABLES;
```

Expected Output

```text
sample
```

---

# Step 8 – Verify the Table Structure

Execute:

```sql
DESCRIBE sample;
```

Example Output

| Field | Type | Null | Key |
|-------|------|------|-----|
| roll | int | NO | PRI |
| name | varchar(100) | YES | |
| cgpa | float | YES | |

This confirms that the table structure matches the Java application's requirements.

---

# Step 9 – Verify Empty Table

Before running the Java program, verify that the table exists.

```sql
SELECT * FROM sample;
```

Expected Output

```text
Empty set
```

This indicates that the table is ready to receive records.

---

# Data Inserted by the Application

The application creates the following Java objects.

```java
Demo d1 = new Demo(1, "Apple", 9.8f);

Demo d2 = new Demo(2, "Banana", 9.3f);
```

When the application executes successfully, these objects are inserted into the database.

Expected table contents:

| roll | name | cgpa |
|------|------|------|
| 1 | Apple | 9.8 |
| 2 | Banana | 9.3 |

---

# Verify Inserted Records

After executing the Java application, run:

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

# Complete Database Workflow

```text
Start MySQL Server
        │
        ▼
Login to MySQL
        │
        ▼
Create Database (test)
        │
        ▼
Select Database
        │
        ▼
Create sample Table
        │
        ▼
Verify Table Structure
        │
        ▼
Run Java Application
        │
        ▼
PreparedStatement Executes
        │
        ▼
Records Inserted
        │
        ▼
Verify Using SELECT
```

---

# Common Database Errors

| Error | Cause | Solution |
|-------|-------|----------|
| Unknown database `test` | Database not created | Create the database |
| Table `sample` doesn't exist | Table missing | Create the table |
| Duplicate entry for PRIMARY KEY | Roll number already exists | Use a different roll number or delete the existing record |
| Access denied | Incorrect username or password | Verify database credentials |
| Communications link failure | MySQL server stopped | Start the MySQL service |

---

# Best Practices

To maintain a reliable database environment:

- Create the database before running the application.
- Verify the table structure matches the Java code.
- Keep the JDBC URL synchronized with the database name.
- Avoid changing column names without updating the SQL query.
- Use meaningful primary key values.
- Back up important databases before making structural changes.
- Always verify inserted records using `SELECT`.

---

# Summary

The **Java JDBC Student Application** relies on a MySQL database named **`test`** and a table named **`sample`** to store student information. Proper database creation, table configuration, and verification are essential before executing the application. Once the setup is complete, the `PreparedStatement` inserts the `Demo` object data into the table, providing a practical demonstration of object persistence using JDBC.
