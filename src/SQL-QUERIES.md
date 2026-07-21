# SQL Queries

## Introduction

The **Java JDBC Student Application** communicates with a MySQL database using a **parameterized SQL INSERT statement** executed through the `PreparedStatement` interface.

Unlike applications that concatenate SQL strings manually, this project separates SQL logic from application data by using placeholders (`?`). This approach improves security, readability, and maintainability while protecting the application from SQL Injection attacks.

This document explains every SQL statement required to configure and execute the project.

---

# Database Creation

Before running the application, create the required database.

```sql
CREATE DATABASE test;
```

### Purpose

Creates a database named **`test`**, which is referenced in the JDBC connection URL.

---

# Selecting the Database

```sql
USE test;
```

### Purpose

Makes the **`test`** database the active working database.

---

# Creating the sample Table

The application stores object data inside the **`sample`** table.

```sql
CREATE TABLE sample
(
    roll INT PRIMARY KEY,
    name VARCHAR(100),
    cgpa FLOAT
);
```

### Purpose

Creates the table required by the application.

---

# Table Structure

| Column | Data Type | Description |
|---------|-----------|-------------|
| `roll` | INT | Student Roll Number (Primary Key) |
| `name` | VARCHAR(100) | Student Name |
| `cgpa` | FLOAT | Student CGPA |

---

# SQL Statement Used by the Application

The Java application executes the following parameterized SQL query.

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(?, ?, ?);
```

### Purpose

Inserts a single student record into the **`sample`** table.

The question mark (`?`) placeholders are replaced with object values at runtime using the `PreparedStatement` methods.

---

# Placeholder Mapping

The application binds object values to the SQL placeholders.

```java
ps.setInt(1, d.roll);

ps.setString(2, d.name);

ps.setFloat(3, d.cgpa);
```

The mapping is shown below.

| Placeholder | Java Object Field | Example Value |
|-------------|-------------------|---------------|
| `?1` | `d.roll` | `1` |
| `?2` | `d.name` | `Apple` |
| `?3` | `d.cgpa` | `9.8` |

---

# SQL Generated for the First Object

The first Java object is:

```java
Demo d1 = new Demo(1, "Apple", 9.8f);
```

After parameter binding, the SQL statement executed by MySQL becomes:

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(1, 'Apple', 9.8);
```

---

# SQL Generated for the Second Object

The second Java object is:

```java
Demo d2 = new Demo(2, "Banana", 9.3f);
```

The executed SQL becomes:

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(2, 'Banana', 9.3);
```

---

# Execution Method

The SQL statement is executed using:

```java
ps.executeUpdate();
```

### Purpose

Executes SQL statements that modify database data.

Suitable for:

- INSERT
- UPDATE
- DELETE
- CREATE
- DROP
- ALTER

In this project, it inserts one row into the database for each `Demo` object.

---

# Data Stored After Execution

After the application runs successfully, the **`sample`** table contains:

| roll | name | cgpa |
|------|------|------|
| 1 | Apple | 9.8 |
| 2 | Banana | 9.3 |

---

# Verification Query

Verify the inserted records.

```sql
SELECT * FROM sample;
```

Example Output

```text
+------+--------+------+
| roll | name   | cgpa |
+------+--------+------+
| 1    | Apple  | 9.8  |
| 2    | Banana | 9.3  |
+------+--------+------+
```

---

# Table Structure Verification

To inspect the table structure:

```sql
DESCRIBE sample;
```

Expected Output

```text
+-------+--------------+------+-----+
| Field | Type         | Null | Key |
+-------+--------------+------+-----+
| roll  | int          | NO   | PRI |
| name  | varchar(100) | YES  |     |
| cgpa  | float        | YES  |     |
+-------+--------------+------+-----+
```

---

# Show Available Tables

```sql
SHOW TABLES;
```

Expected Output

```text
sample
```

---

# Show Available Databases

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

---

# SQL Execution Workflow

```text
Create SQL Template
        │
        ▼
INSERT INTO sample
VALUES (?, ?, ?)
        │
        ▼
Create PreparedStatement
        │
        ▼
Bind Parameters
        │
        ▼
Compile SQL
        │
        ▼
Send to MySQL Server
        │
        ▼
Insert Record
        │
        ▼
Return Success
```

---

# Why Use Parameterized SQL?

The project intentionally uses parameterized SQL instead of string concatenation.

### Advantages

- Prevents SQL Injection attacks.
- Separates SQL logic from application data.
- Automatically converts Java data types to SQL types.
- Improves readability.
- Supports reusable execution plans.
- Enhances application security.

---

# Common SQL Errors

| Error | Cause | Solution |
|-------|-------|----------|
| Unknown database | Database not created | Execute `CREATE DATABASE test;` |
| Table doesn't exist | Missing `sample` table | Create the table |
| Duplicate entry | Existing primary key value | Use a unique roll number |
| SQLSyntaxErrorException | Invalid SQL syntax | Verify SQL statement |
| Data truncation | Incompatible data type | Verify column types |

---

# SQL Best Practices

When writing SQL for JDBC applications:

- Use `PreparedStatement` instead of `Statement`.
- Keep SQL statements readable.
- Use meaningful table and column names.
- Define appropriate primary keys.
- Validate application data before insertion.
- Verify database changes using `SELECT`.
- Close database resources after execution.

---

# Summary

The **Java JDBC Student Application** uses a single parameterized `INSERT` statement to persist Java object data into the MySQL **`sample`** table. Through `PreparedStatement`, object fields are safely mapped to SQL placeholders, providing secure, efficient, and maintainable database interaction. The SQL statements documented here represent the complete database operations required for the current implementation.
