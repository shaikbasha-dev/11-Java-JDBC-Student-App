# Program Overview

## Introduction

The **Java JDBC Student Application** is a simple yet practical Java backend project that demonstrates how object-oriented Java applications interact with a MySQL relational database using **Java Database Connectivity (JDBC)**.

Unlike applications that directly execute SQL statements using the `Statement` interface, this project adopts the **PreparedStatement** approach to safely insert Java object data into a database table. Prepared statements improve security, readability, and performance while preventing SQL Injection attacks.

The project is intentionally designed to be beginner-friendly while introducing professional JDBC programming practices that are commonly used in enterprise applications.

---

# Project Objective

The primary objective of this project is to demonstrate how a Java object can be transformed into a relational database record using JDBC.

The application performs the following operations:

- Creates Java objects representing student data.
- Establishes a connection with a MySQL database.
- Uses a parameterized SQL query.
- Maps object attributes to SQL placeholders.
- Inserts records into a relational table.
- Closes all database resources after execution.

---

# Project Scenario

Consider an educational application where student information is first created inside a Java program before being permanently stored in a database.

This project simulates that workflow.

Two student objects are created in memory.

```text
Demo d1 = new Demo(1, "Apple", 9.8f);
Demo d2 = new Demo(2, "Banana", 9.3f);
```

Each object contains:

- Roll Number
- Student Name
- CGPA

Instead of manually writing SQL for every student, the application passes each object to a reusable insertion method that stores the object's data inside the MySQL database.

---

# Database Used

The application connects to the following database.

```text
Database Name : test
```

Inside the database, the following table is used.

```text
Table Name : sample
```

---

# Table Structure

The application expects a relational table similar to the following.

| Column | Data Type | Description |
|---------|-----------|-------------|
| roll | INT | Student Roll Number |
| name | VARCHAR | Student Name |
| cgpa | FLOAT | Student CGPA |

---

# Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Application Development |
| JDBC | Database Connectivity |
| MySQL | Relational Database |
| DriverManager | Connection Management |
| Connection | Database Session |
| PreparedStatement | Parameterized SQL Execution |
| SQL | Data Storage |
| JVM | Program Execution |

---

# Core Components

The application consists of three major components.

---

## 1. Demo Class

The `Demo` class represents the data model.

It stores temporary student information before the data is transferred into the database.

Fields include:

- roll
- name
- cgpa

The constructor initializes each object with the required values.

Example:

```java
Demo d1 = new Demo(1, "Apple", 9.8f);
```

---

## 2. StudentApp Class

The `StudentApp` class controls the complete execution of the application.

Its responsibilities include:

- Creating Demo objects
- Calling the insertion method
- Handling SQL exceptions
- Displaying execution status

This serves as the application's entry point.

---

## 3. insertObject() Method

This method performs the complete database insertion process.

Responsibilities include:

- Creating a database connection
- Preparing the SQL statement
- Binding object values
- Executing the SQL query
- Closing JDBC resources

This method acts as the bridge between Java objects and relational database records.

---

# SQL Statement Used

The project uses a parameterized SQL statement.

```sql
INSERT INTO sample (roll, name, cgpa)
VALUES (?, ?, ?);
```

The question mark (`?`) placeholders are replaced with object values during execution using the `PreparedStatement` interface.

---

# Parameter Mapping

Each object field is mapped to its corresponding SQL parameter.

| Object Field | SQL Placeholder |
|--------------|-----------------|
| roll | Parameter 1 |
| name | Parameter 2 |
| cgpa | Parameter 3 |

The mapping is performed using:

```java
ps.setInt(1, d.roll);
ps.setString(2, d.name);
ps.setFloat(3, d.cgpa);
```

---

# Execution Flow

The overall workflow of the application is illustrated below.

```text
Program Starts
        │
        ▼
Create Demo Object
        │
        ▼
Call insertObject()
        │
        ▼
Connect to MySQL Database
        │
        ▼
Prepare SQL Statement
        │
        ▼
Bind Object Values
        │
        ▼
Execute INSERT Query
        │
        ▼
Store Record in Database
        │
        ▼
Close Resources
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

# JDBC Features Demonstrated

This project demonstrates several important JDBC concepts.

- DriverManager
- Connection
- PreparedStatement
- Parameter Binding
- executeUpdate()
- SQLException
- Database Connectivity
- Resource Cleanup

---

# Object-Oriented Concepts Demonstrated

The project also reinforces several Core Java concepts.

- Classes
- Objects
- Constructors
- Methods
- Method Invocation
- Parameters
- Object Passing
- Encapsulation
- Exception Handling

---

# Security Considerations

Instead of concatenating SQL strings, the application uses `PreparedStatement`.

Benefits include:

- Prevents SQL Injection
- Performs automatic data type conversion
- Improves query readability
- Supports reusable compiled SQL statements
- Enhances application security

---

# Expected Outcome

After successful execution:

- A connection is established with the MySQL database.
- Two `Demo` objects are processed.
- Two new records are inserted into the `sample` table.
- Database resources are closed.
- The application displays:

```text
Data pipeline processed. Records written successfully.
```

---

# Learning Outcomes

After studying this project, learners will be able to:

- Understand JDBC architecture.
- Connect Java applications to MySQL.
- Create parameterized SQL queries.
- Use the `PreparedStatement` interface.
- Map Java objects to database records.
- Execute SQL statements safely.
- Handle SQL exceptions.
- Manage JDBC resources correctly.
- Build the foundation for enterprise Java database applications.

---

# Summary

The **Java JDBC Student Application** provides a practical introduction to Java database programming by combining object-oriented programming with relational database operations. Through the use of `PreparedStatement`, object parameter binding, and JDBC resource management, the project demonstrates how Java applications securely persist object data into a MySQL database. It serves as an excellent foundation for learning advanced technologies such as Hibernate, Spring JDBC, Spring Data JPA, and Spring Boot.
