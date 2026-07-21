# Method Reference

## Introduction

This document provides a detailed reference for every method implemented in the **Java JDBC Student Application**. Each method has a specific responsibility in the application's execution, from creating objects to storing them in the MySQL database.

Understanding these methods helps learners understand how Java methods cooperate to implement object persistence using JDBC.

---

# Method Overview

The application contains two executable methods.

| Method | Purpose |
|---------|---------|
| `main()` | Application entry point |
| `insertObject(Demo d)` | Inserts a Demo object into the MySQL database |

In addition, the `Demo` class contains one constructor used to initialize object data.

---

# Demo Constructor

## Signature

```java
Demo(int roll, String name, float cgpa)
```

---

## Purpose

The constructor initializes a `Demo` object with the supplied student information.

Instead of assigning values one by one after object creation, the constructor initializes all object fields immediately.

---

## Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| `roll` | `int` | Student roll number |
| `name` | `String` | Student name |
| `cgpa` | `float` | Student CGPA |

---

## Example

```java
Demo d1 = new Demo(1, "Apple", 9.8f);
```

After execution:

| Field | Value |
|--------|-------|
| roll | 1 |
| name | Apple |
| cgpa | 9.8 |

---

## Responsibility

The constructor only initializes object state.

It does **not**:

- Connect to the database
- Execute SQL
- Insert records
- Handle exceptions

Its sole responsibility is object creation.

---

# main() Method

## Signature

```java
public static void main(String[] args)
```

---

## Purpose

The `main()` method is the application's entry point.

The Java Virtual Machine invokes this method when the program starts.

---

## Responsibilities

The method performs the following tasks:

- Create two `Demo` objects.
- Invoke the database insertion method.
- Handle SQL exceptions.
- Display the success message.

---

## Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| `args` | `String[]` | Command-line arguments supplied during execution |

The current implementation does not use command-line arguments.

---

## Internal Workflow

```text
main()

│

├── Create Demo Object 1

├── Create Demo Object 2

├── Call insertObject(d1)

├── Call insertObject(d2)

└── Print Success Message
```

---

## Objects Created

### First Object

```java
Demo d1 = new Demo(1, "Apple", 9.8f);
```

### Second Object

```java
Demo d2 = new Demo(2, "Banana", 9.3f);
```

These objects are passed individually to the persistence method.

---

## Exception Handling

The method encloses all database operations within a `try-catch` block.

Possible exceptions include:

- Database unavailable
- Invalid credentials
- Missing table
- SQL syntax errors
- Network failures

---

# insertObject() Method

## Signature

```java
public static void insertObject(Demo d)
throws SQLException
```

---

## Purpose

The `insertObject()` method performs the complete database insertion process for a single `Demo` object.

It serves as the bridge between the object-oriented Java application and the relational MySQL database.

---

## Parameter

| Parameter | Type | Description |
|-----------|------|-------------|
| `d` | `Demo` | Object containing the student information to be inserted |

---

## Return Type

```java
void
```

The method performs the insertion operation without returning a value.

---

## Exception

```java
throws SQLException
```

Instead of handling database exceptions internally, the method propagates them to the calling method (`main()`), where centralized exception handling is performed.

---

## Responsibilities

The method performs the following operations sequentially:

1. Read database configuration.
2. Create the SQL statement.
3. Establish a database connection.
4. Create a `PreparedStatement`.
5. Bind object values.
6. Execute the SQL statement.
7. Close JDBC resources.

---

## Database Configuration

```java
String url = "jdbc:mysql://localhost:3306/test";
String user = "root";
String pass = "root";
```

These values specify:

- Database protocol
- Server location
- Port number
- Database name
- Username
- Password

---

## SQL Statement

```java
INSERT INTO sample
(roll, name, cgpa)
VALUES
(?, ?, ?)
```

The query uses parameter placeholders to safely accept values at runtime.

---

## Connection Creation

```java
Connection con =
DriverManager.getConnection(
url,
user,
pass
);
```

The `DriverManager` establishes communication with the MySQL server and returns an active `Connection` object.

---

## PreparedStatement Creation

```java
PreparedStatement ps =
con.prepareStatement(sql);
```

This compiles the SQL statement before execution.

Benefits include:

- SQL Injection prevention
- Automatic type conversion
- Reusable execution plan
- Improved readability

---

## Parameter Binding

```java
ps.setInt(1, d.roll);

ps.setString(2, d.name);

ps.setFloat(3, d.cgpa);
```

### Mapping

| SQL Placeholder | Object Field |
|-----------------|--------------|
| `?1` | `d.roll` |
| `?2` | `d.name` |
| `?3` | `d.cgpa` |

---

## SQL Execution

```java
ps.executeUpdate();
```

This executes the parameterized `INSERT` statement.

If successful, one new row is inserted into the `sample` table.

---

## Resource Cleanup

```java
ps.close();

con.close();
```

Closing resources is essential for:

- Preventing connection leaks
- Releasing server resources
- Maintaining application performance
- Avoiding database connection exhaustion

---

# Method Interaction

The methods interact as shown below.

```text
main()

│

├── Create Demo Object

│

├── Call insertObject()

│

▼

insertObject()

│

├── Connect Database

├── Prepare SQL

├── Bind Parameters

├── Execute INSERT

└── Close Resources

│

▼

Return to main()
```

---

# Method Call Sequence

```text
JVM

│

▼

main()

│

├── Demo()

├── Demo()

│

├── insertObject(d1)

│

└── insertObject(d2)

        │

        ▼

Database Updated
```

---

# Best Practices Demonstrated

The methods demonstrate several recommended Java and JDBC practices.

- Small, focused methods
- Clear separation of responsibilities
- Reusable persistence method
- Parameterized SQL queries
- Centralized exception handling
- Explicit resource cleanup
- Object-oriented design

---

# Summary

The **Java JDBC Student Application** contains a compact yet well-structured set of methods that clearly separate object creation from database persistence. The `Demo` constructor initializes object data, the `main()` method controls the application flow, and the `insertObject()` method encapsulates all JDBC operations required to safely insert a Java object into the MySQL `sample` table. This modular design improves readability, reusability, and maintainability while demonstrating professional Java programming practices.
