# Output

## Introduction

This document explains the expected output of the **Java JDBC Student Application**. It covers the console output, database output, and the internal processing results generated after successful execution of the application.

The project inserts two Java objects into the MySQL **`sample`** table using a parameterized SQL query executed through **PreparedStatement**.

---

# Input Objects

The application creates the following objects in memory.

## Object 1

```java
Demo d1 = new Demo(1, "Apple", 9.8f);
```

### Object Values

| Field | Value |
|-------|-------|
| roll | 1 |
| name | Apple |
| cgpa | 9.8 |

---

## Object 2

```java
Demo d2 = new Demo(2, "Banana", 9.3f);
```

### Object Values

| Field | Value |
|-------|-------|
| roll | 2 |
| name | Banana |
| cgpa | 9.3 |

These objects exist only in JVM memory until they are inserted into the database.

---

# Console Output

After both objects are successfully inserted, the application executes:

```java
System.out.println(
    "Data pipeline processed. Records written successfully."
);
```

### Expected Console Output

```text
Data pipeline processed. Records written successfully.
```

This message confirms that:

- Both `Demo` objects were processed.
- SQL statements executed successfully.
- Records were inserted into the database.
- No `SQLException` occurred.

---

# Database Output

Execute the following SQL query after running the application.

```sql
SELECT * FROM sample;
```

### Expected Output

```text
+------+--------+------+
| roll | name   | cgpa |
+------+--------+------+
| 1    | Apple  | 9.8  |
| 2    | Banana | 9.3  |
+------+--------+------+
```

This confirms that the Java objects have been successfully persisted into the MySQL database.

---

# Data Transformation

The application transforms Java objects into database records.

## Java Object

```text
roll = 1
name = Apple
cgpa = 9.8
```

↓

## SQL Statement

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(1, 'Apple', 9.8);
```

↓

## Database Record

| roll | name | cgpa |
|------|------|------|
| 1 | Apple | 9.8 |

The same process is repeated for the second object.

---

# SQL Generated During Execution

For the first object:

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(1, 'Apple', 9.8);
```

For the second object:

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(2, 'Banana', 9.3);
```

Although the application uses placeholders (`?`), these are the effective SQL statements executed after parameter binding.

---

# Internal Execution Result

```text
Demo Object Created
        │
        ▼
Connection Established
        │
        ▼
PreparedStatement Created
        │
        ▼
Parameters Bound
        │
        ▼
SQL Executed
        │
        ▼
Row Inserted
        │
        ▼
Resources Closed
```

This sequence occurs once for each `Demo` object.

---

# Database State Before Execution

```sql
SELECT * FROM sample;
```

Possible Output

```text
Empty set
```

If the table is empty, the application inserts both records.

---

# Database State After Execution

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

# Exception Output

If a database error occurs, the `catch` block executes.

```java
System.err.println(
    "Database Transaction Failure:"
);

e.printStackTrace();
```

### Example Output

```text
Database Transaction Failure:

java.sql.SQLException:
...
```

The exact exception details depend on the underlying problem.

---

# Common Error Outputs

## Invalid Credentials

```text
Access denied for user 'root'
```

### Cause

Incorrect username or password.

---

## Unknown Database

```text
Unknown database 'test'
```

### Cause

The `test` database has not been created.

---

## Missing Table

```text
Table 'test.sample' doesn't exist
```

### Cause

The `sample` table has not been created.

---

## Duplicate Primary Key

```text
Duplicate entry '1' for key 'PRIMARY'
```

### Cause

A record with the same `roll` value already exists.

---

## Missing JDBC Driver

```text
No suitable driver found
```

### Cause

The MySQL Connector/J library is missing from the project.

---

# Expected Result Summary

| Operation | Result |
|-----------|--------|
| Demo Object Creation | Successful |
| Database Connection | Established |
| PreparedStatement Creation | Successful |
| Parameter Binding | Completed |
| SQL Execution | Successful |
| Record Insertion | 2 Rows Inserted |
| Resource Cleanup | Completed |
| Console Message | Displayed |

---

# Output Verification Checklist

Verify the following after execution.

- Application compiles successfully.
- Console displays the success message.
- No `SQLException` is thrown.
- Database contains two records.
- The `sample` table reflects the object data.
- JDBC resources are closed properly.

---

# Learning Outcomes

By observing the program output, learners can understand:

- How Java objects are converted into database records.
- How `PreparedStatement` executes parameterized SQL.
- How `executeUpdate()` modifies database data.
- How database records can be verified using SQL.
- How exception messages assist in debugging JDBC applications.

---

# Summary

The output of the **Java JDBC Student Application** demonstrates the successful persistence of Java object data into the MySQL **`sample`** table. Upon execution, two `Demo` objects are converted into database records through a parameterized `PreparedStatement`, resulting in a success message on the console and two new rows in the database. Together, the console output and SQL verification provide clear confirmation that the application executed correctly and that the JDBC workflow completed successfully.
