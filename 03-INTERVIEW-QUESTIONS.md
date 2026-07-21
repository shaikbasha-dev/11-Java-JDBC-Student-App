# JDBC Interview Questions

## Introduction

This document contains interview questions based on the **Java JDBC Student Application**. Every question is directly related to the concepts implemented in this project, including JDBC architecture, `DriverManager`, `Connection`, `PreparedStatement`, parameter binding, object persistence, exception handling, and MySQL integration.

These questions are useful for:

- Java Developer Interviews
- JDBC Interviews
- Full Stack Developer Interviews
- Campus Placements
- Technical Assessments
- Revision before Interviews

---

# Basic Interview Questions

## 1. What is JDBC?

**Answer**

JDBC (Java Database Connectivity) is a standard Java API that enables Java applications to communicate with relational databases. It allows Java programs to execute SQL statements, retrieve results, and manipulate database records.

---

## 2. What is the purpose of JDBC?

**Answer**

JDBC provides a standard interface for:

- Connecting to databases
- Executing SQL statements
- Reading query results
- Updating database records
- Managing database resources

---

## 3. Which database is used in this project?

**Answer**

MySQL.

Database Name:

```text
test
```

---

## 4. Which table is used in this project?

**Answer**

```text
sample
```

---

## 5. Which JDBC class creates the database connection?

**Answer**

```java
DriverManager
```

Connection is created using:

```java
DriverManager.getConnection(url, user, pass);
```

---

## 6. Which interface represents a database connection?

**Answer**

```java
Connection
```

---

## 7. Which JDBC interface executes the SQL statement in this project?

**Answer**

```java
PreparedStatement
```

---

## 8. Why is PreparedStatement used?

**Answer**

Because it:

- Prevents SQL Injection
- Supports parameterized queries
- Improves performance
- Automatically converts Java data types
- Produces cleaner code

---

## 9. Which SQL statement is used?

**Answer**

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(?, ?, ?);
```

---

## 10. What method executes the INSERT query?

**Answer**

```java
executeUpdate()
```

---

# Intermediate Interview Questions

## 11. What is parameter binding?

**Answer**

Parameter binding is the process of assigning Java object values to SQL placeholders (`?`) using methods such as:

```java
setInt()

setString()

setFloat()
```

---

## 12. What values are inserted into the database?

**Answer**

| Roll | Name | CGPA |
|------|------|------|
| 1 | Apple | 9.8 |
| 2 | Banana | 9.3 |

---

## 13. What is the purpose of the Demo class?

**Answer**

The `Demo` class is a DTO (Data Transfer Object) that stores student data before it is inserted into the database.

---

## 14. What is object persistence?

**Answer**

Object persistence is the process of storing Java object data permanently in a database.

---

## 15. What is the purpose of insertObject()?

**Answer**

It performs the complete database insertion process.

Responsibilities include:

- Creating the connection
- Preparing SQL
- Binding parameters
- Executing SQL
- Closing resources

---

## 16. Why are JDBC resources closed?

**Answer**

To:

- Prevent memory leaks
- Release database connections
- Improve performance
- Avoid resource exhaustion

---

## 17. Which exception is handled?

**Answer**

```java
SQLException
```

---

## 18. What happens if the database connection fails?

**Answer**

The application enters the `catch` block, prints:

```text
Database Transaction Failure:
```

and displays the exception stack trace.

---

## 19. Why is executeUpdate() used instead of executeQuery()?

**Answer**

Because `executeUpdate()` is designed for SQL statements that modify data, such as `INSERT`, `UPDATE`, and `DELETE`, whereas `executeQuery()` is used only for `SELECT` statements.

---

## 20. What happens after executeUpdate() completes?

**Answer**

The record is inserted into the database, after which the `PreparedStatement` and `Connection` are closed.

---

# Advanced Interview Questions

## 21. Explain the JDBC workflow used in this project.

**Answer**

1. Create `Demo` objects.
2. Call `insertObject()`.
3. Obtain a `Connection`.
4. Create a `PreparedStatement`.
5. Bind object values.
6. Execute the SQL statement.
7. Close JDBC resources.

---

## 22. Why is PreparedStatement preferred over Statement?

**Answer**

Because it:

- Prevents SQL Injection
- Uses parameterized SQL
- Can improve execution efficiency
- Produces cleaner and safer code

---

## 23. How are Java object fields mapped to SQL?

**Answer**

| Object Field | PreparedStatement Method |
|--------------|--------------------------|
| roll | setInt() |
| name | setString() |
| cgpa | setFloat() |

---

## 24. What is the JDBC URL used in this project?

**Answer**

```text
jdbc:mysql://localhost:3306/test
```

---

## 25. What is the purpose of DriverManager?

**Answer**

`DriverManager` establishes communication between the Java application and the database by creating a `Connection` object.

---

## 26. What is SQL Injection?

**Answer**

SQL Injection is a security vulnerability where malicious SQL code is inserted into application input to manipulate database queries.

Using `PreparedStatement` significantly reduces this risk by separating SQL commands from parameter values.

---

## 27. Which JDBC objects are used in this project?

**Answer**

- DriverManager
- Connection
- PreparedStatement
- SQLException

---

## 28. What would happen if the sample table does not exist?

**Answer**

An `SQLException` is thrown indicating that the table does not exist.

---

## 29. What is the output after successful execution?

**Answer**

Console Output:

```text
Data pipeline processed. Records written successfully.
```

Database Output:

| roll | name | cgpa |
|------|------|------|
| 1 | Apple | 9.8 |
| 2 | Banana | 9.3 |

---

## 30. Which enterprise technologies build upon JDBC?

**Answer**

Examples include:

- Spring JDBC
- Spring Data JPA
- Hibernate ORM

These frameworks internally rely on JDBC for database communication.

---

# Rapid Fire Questions

| Question | Answer |
|----------|--------|
| API Used | JDBC |
| Database | MySQL |
| Database Name | test |
| Table Name | sample |
| Driver Class Manager | DriverManager |
| Connection Interface | Connection |
| Statement Type | PreparedStatement |
| SQL Operation | INSERT |
| SQL Execution Method | executeUpdate() |
| Exception Type | SQLException |
| Number of Objects Inserted | 2 |
| Number of Columns | 3 |
| Primary Key | roll |
| Object Class | Demo |
| Main Class | StudentApp |

---

# Interview Tips

While explaining this project during an interview:

- Explain the complete JDBC workflow step by step.
- Describe why `PreparedStatement` is preferred.
- Explain parameter binding with examples.
- Discuss how Java objects are converted into database records.
- Mention resource cleanup using `close()`.
- Demonstrate the database output using `SELECT * FROM sample;`.

---

# Summary

The questions in this document cover the complete implementation of the **Java JDBC Student Application**, from basic JDBC concepts to advanced topics such as parameterized SQL, object persistence, exception handling, and database communication. Mastering these questions provides a strong foundation for Java and JDBC interviews while reinforcing the practical concepts demonstrated in this project.
