# Frequently Asked Questions (FAQ)

## Introduction

This document answers the most common questions that beginners ask while learning the **Java JDBC Student Application**. The questions are based on the actual implementation of this project and explain how Java objects are stored in a MySQL database using JDBC.

---

# General Questions

## 1. What is the purpose of this project?

This project demonstrates how to store Java object data in a MySQL database using JDBC and `PreparedStatement`.

---

## 2. Which programming language is used?

Java.

---

## 3. Which database is used?

MySQL.

---

## 4. Which API is used?

Java Database Connectivity (JDBC).

---

## 5. What is the database name?

```text
test
```

---

## 6. What is the table name?

```text
sample
```

---

## 7. Which SQL operation is performed?

Only the **INSERT** operation.

---

## Java Questions

## 8. What is the Demo class?

The `Demo` class is a simple Data Transfer Object (DTO) that stores student information.

Fields:

- roll
- name
- cgpa

---

## 9. Why are objects created?

Objects temporarily hold student information before it is inserted into the database.

Example:

```java
Demo d1 = new Demo(1, "Apple", 9.8f);
```

---

## 10. Why is insertObject() used?

It contains all the database insertion logic.

Responsibilities include:

- Opening the connection
- Preparing SQL
- Binding parameters
- Executing SQL
- Closing resources

---

# JDBC Questions

## 11. What is DriverManager?

`DriverManager` establishes a connection between the Java application and the MySQL database.

---

## 12. What is a Connection?

A `Connection` object represents an active communication session with the database.

---

## 13. What is PreparedStatement?

`PreparedStatement` is a JDBC interface that executes parameterized SQL statements securely.

---

## 14. Why not use Statement?

`PreparedStatement` is preferred because it:

- Prevents SQL Injection
- Supports parameters
- Improves readability
- Automatically handles data types

---

## 15. What is parameter binding?

Parameter binding assigns Java values to SQL placeholders (`?`).

Example:

```java
ps.setInt(1, d.roll);
ps.setString(2, d.name);
ps.setFloat(3, d.cgpa);
```

---

## 16. What does executeUpdate() return?

It returns the number of rows affected by the SQL statement.

For each successful insert in this project, the returned value is typically:

```text
1
```

---

# SQL Questions

## 17. What SQL query is used?

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(?, ?, ?);
```

---

## 18. Why are question marks used?

They act as placeholders for values that are supplied later through `PreparedStatement`.

---

## 19. How can inserted data be verified?

Execute:

```sql
SELECT * FROM sample;
```

---

## 20. What happens if the same roll number is inserted again?

Since `roll` is the primary key, MySQL throws a duplicate key error.

---

# Execution Questions

## 21. What is the console output?

```text
Data pipeline processed. Records written successfully.
```

---

## 22. What if an exception occurs?

The application prints:

```text
Database Transaction Failure:
```

followed by the exception stack trace.

---

## 23. Why are ps.close() and con.close() called?

To release database resources and prevent resource leaks.

---

## 24. Can this project retrieve data?

No.

The current implementation only demonstrates inserting records into the database.

---

## 25. Can this project update or delete records?

No.

Only object insertion is implemented in the current version.

---

# Learning Questions

## 26. What concepts are learned?

- JDBC
- MySQL Connectivity
- DriverManager
- Connection
- PreparedStatement
- Parameter Binding
- SQL INSERT
- Object Persistence
- SQLException Handling
- Resource Cleanup

---

## 27. Is this project suitable for beginners?

Yes.

It is designed as a beginner-friendly project that introduces JDBC fundamentals through a simple object insertion example.

---

## 28. How does this project relate to enterprise development?

Enterprise frameworks such as Spring JDBC, Spring Data JPA, and Hibernate internally rely on JDBC concepts similar to those demonstrated here.

---

## 29. What should be learned after this project?

Recommended next topics include:

- ResultSet
- Batch Processing
- Transactions
- CallableStatement
- Connection Pooling
- Spring JDBC
- Hibernate
- Spring Data JPA

---

## 30. What is the main takeaway from this project?

The project demonstrates the complete lifecycle of persisting Java objects into a relational database using JDBC, from object creation to successful database insertion and resource cleanup.

---

# Quick Reference

| Question | Answer |
|----------|--------|
| Language | Java |
| API | JDBC |
| Database | MySQL |
| Database Name | test |
| Table | sample |
| SQL Operation | INSERT |
| Statement Type | PreparedStatement |
| Execution Method | executeUpdate() |
| Object Class | Demo |
| Main Class | StudentApp |
| Records Inserted | 2 |
| Exception | SQLException |

---

# Summary

This FAQ serves as a quick reference for learners studying the **Java JDBC Student Application**. It answers common questions about Java, JDBC, MySQL, parameterized SQL, object persistence, exception handling, and application execution. Reviewing these questions helps reinforce the key concepts implemented in this project and provides an effective revision resource before interviews or practical exercises.
