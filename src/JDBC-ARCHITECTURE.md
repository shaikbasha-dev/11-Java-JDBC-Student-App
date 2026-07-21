# JDBC Architecture

## Introduction

Java Database Connectivity (JDBC) is the standard Java API used to establish communication between Java applications and relational databases. It provides a common interface that allows Java programs to execute SQL statements, retrieve results, and manipulate database records regardless of the underlying database vendor.

In this project, JDBC enables the **Java JDBC Student Application** to persist Java object data into a MySQL database using a **PreparedStatement**.

---

# Project Architecture Overview

The application follows a layered communication model.

```text
+------------------------------------------------------+
|              Java JDBC Student Application           |
+------------------------------------------------------+
                      │
                      ▼
+------------------------------------------------------+
|                  Demo Object (DTO)                  |
|  roll | name | cgpa                                |
+------------------------------------------------------+
                      │
                      ▼
+------------------------------------------------------+
|                insertObject() Method                |
+------------------------------------------------------+
                      │
                      ▼
+------------------------------------------------------+
|                 JDBC API (java.sql)                 |
+------------------------------------------------------+
                      │
                      ▼
+------------------------------------------------------+
|                 DriverManager Class                 |
+------------------------------------------------------+
                      │
                      ▼
+------------------------------------------------------+
|               MySQL JDBC Driver (Connector/J)       |
+------------------------------------------------------+
                      │
                      ▼
+------------------------------------------------------+
|                  MySQL Database                     |
|                   Database : test                   |
|                   Table    : sample                 |
+------------------------------------------------------+
```

---

# JDBC Components Used

The project uses several important JDBC components.

| Component | Purpose |
|-----------|---------|
| DriverManager | Creates database connections |
| Connection | Represents an active connection to MySQL |
| PreparedStatement | Executes parameterized SQL statements |
| SQLException | Handles database-related exceptions |

Each component performs a specific role in the communication process.

---

# Component Details

## 1. Demo Object

The application begins by creating a Java object.

Example:

```java
Demo d1 = new Demo(1, "Apple", 9.8f);
```

The object temporarily stores student information in JVM memory.

Fields include:

- roll
- name
- cgpa

At this stage, no database operation has been performed.

---

## 2. insertObject() Method

The object is passed to the persistence layer.

```java
insertObject(d1);
```

This method acts as the bridge between:

- Object-Oriented Programming
- Relational Database Management System (RDBMS)

Its responsibilities include:

- Creating a connection
- Preparing SQL
- Binding parameters
- Executing the SQL statement
- Closing resources

---

## 3. DriverManager

The `DriverManager` class is responsible for locating an appropriate JDBC driver and establishing a connection.

Example:

```java
Connection con =
DriverManager.getConnection(
url,
user,
pass
);
```

Responsibilities:

- Load the JDBC driver
- Authenticate the user
- Connect to MySQL
- Return a `Connection` object

---

## 4. Connection

The `Connection` object represents an active communication session between Java and MySQL.

Example:

```java
Connection con
```

The connection is required before any SQL statement can be executed.

Responsibilities:

- Open database session
- Create statements
- Manage transactions
- Close communication channel

---

## 5. PreparedStatement

The application creates a parameterized SQL statement.

```java
PreparedStatement ps =
con.prepareStatement(sql);
```

Unlike a regular `Statement`, a `PreparedStatement` separates SQL logic from user data.

Advantages:

- Prevents SQL Injection
- Improves performance
- Supports parameter binding
- Increases readability
- Encourages reusable SQL execution

---

## 6. Parameter Binding

The object's fields are mapped to SQL placeholders.

```java
ps.setInt(1, d.roll);

ps.setString(2, d.name);

ps.setFloat(3, d.cgpa);
```

Parameter Mapping:

| Placeholder | Object Field | Example Value |
|-------------|--------------|---------------|
| ?1 | roll | 1 |
| ?2 | name | Apple |
| ?3 | cgpa | 9.8 |

The SQL template:

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(?, ?, ?);
```

is transformed into:

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(1, 'Apple', 9.8);
```

for the first object.

---

## 7. SQL Execution

The prepared statement is executed using:

```java
ps.executeUpdate();
```

This method sends the compiled SQL statement to the MySQL server.

The database engine validates the SQL, inserts the record into the `sample` table, and returns the execution status.

---

## 8. Resource Cleanup

After execution, the application explicitly closes the JDBC resources.

```java
ps.close();

con.close();
```

This ensures:

- Database connections are released.
- Memory usage is minimized.
- Server resources are available for future requests.
- Connection leaks are prevented.

---

# End-to-End Communication Flow

The complete communication sequence is shown below.

```text
Demo Object
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
Bind Parameters
      │
      ▼
executeUpdate()
      │
      ▼
MySQL JDBC Driver
      │
      ▼
MySQL Server
      │
      ▼
Database : test
      │
      ▼
Table : sample
      │
      ▼
Record Inserted
      │
      ▼
Close PreparedStatement
      │
      ▼
Close Connection
```

---

# Internal JDBC Workflow

```text
Java Application
        │
        ▼
Create Demo Object
        │
        ▼
Call insertObject()
        │
        ▼
DriverManager.getConnection()
        │
        ▼
Connection Created
        │
        ▼
prepareStatement(sql)
        │
        ▼
Bind Parameters
        │
        ▼
executeUpdate()
        │
        ▼
MySQL Executes SQL
        │
        ▼
Insert Completed
        │
        ▼
Close JDBC Resources
```

---

# Why PreparedStatement?

This project intentionally uses `PreparedStatement` instead of `Statement`.

| PreparedStatement | Benefit |
|-------------------|---------|
| Parameterized Queries | Prevents SQL Injection |
| Precompiled SQL | Better Performance |
| Automatic Type Conversion | Fewer Conversion Errors |
| Clear Syntax | Improved Readability |
| Reusable Query | Easier Maintenance |

These advantages make `PreparedStatement` the preferred choice for production applications.

---

# Architecture Benefits

The architecture used in this project provides several advantages.

- Clear separation between application logic and database operations.
- Reusable database insertion method.
- Secure parameterized SQL execution.
- Modular object-oriented design.
- Easy maintenance and scalability.
- Strong foundation for enterprise Java development.

---

# Relation to Enterprise Applications

The workflow demonstrated in this project is similar to the persistence layer used in enterprise frameworks such as:

- Spring JDBC
- Spring Data JPA
- Hibernate ORM

Although those frameworks automate many tasks, they ultimately rely on the same JDBC architecture illustrated in this project.

---

# Summary

The Java JDBC Student Application demonstrates the complete JDBC architecture required to persist Java objects into a relational database. Beginning with the creation of a `Demo` object, the application establishes a database connection through `DriverManager`, prepares a parameterized SQL statement, binds object values using `PreparedStatement`, executes the SQL statement with `executeUpdate()`, and safely closes all JDBC resources. Understanding this architecture provides a strong foundation for mastering professional Java database development and modern enterprise frameworks.
