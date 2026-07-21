# Pseudocode

## Introduction

Pseudocode is a simplified representation of a program's logic written in plain English. It focuses on the sequence of operations rather than the programming language syntax.

This document explains the logical flow of the **Java JDBC Student Application**, which stores Java object data into a MySQL database using **PreparedStatement**.

---

# Algorithm

```text
START

Create Demo object d1
    Roll = 1
    Name = "Apple"
    CGPA = 9.8

Create Demo object d2
    Roll = 2
    Name = "Banana"
    CGPA = 9.3

TRY

    Pass d1 to insertObject()

    Pass d2 to insertObject()

    Display success message

CATCH SQLException

    Display error message

END TRY

STOP
```

---

# insertObject() Algorithm

```text
Receive Demo object

Store database URL

Store database username

Store database password

Create parameterized SQL INSERT query

Connect to MySQL database

Create PreparedStatement

Bind roll number

Bind student name

Bind CGPA

Execute INSERT query

Close PreparedStatement

Close Connection

Return to caller
```

---

# Complete Program Logic

```text
START
        │
        ▼
Create Demo Object 1
        │
        ▼
Create Demo Object 2
        │
        ▼
Call insertObject(d1)
        │
        ▼
Connect to Database
        │
        ▼
Prepare SQL Query
        │
        ▼
Bind Object Values
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
Connect to Database
        │
        ▼
Prepare SQL Query
        │
        ▼
Bind Object Values
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
STOP
```

---

# Object Creation Logic

The application first creates two Java objects.

```text
Object 1

Roll : 1
Name : Apple
CGPA : 9.8
```

```text
Object 2

Roll : 2
Name : Banana
CGPA : 9.3
```

These objects temporarily store data inside JVM memory before being transferred to the database.

---

# Database Connection Logic

```text
Read Database URL

Read Username

Read Password

↓

DriverManager

↓

Create Connection

↓

Connection Established
```

If the connection fails, an `SQLException` is generated.

---

# SQL Preparation Logic

The application prepares the following SQL statement.

```sql
INSERT INTO sample
(roll, name, cgpa)
VALUES
(?, ?, ?);
```

The placeholders are intentionally left empty until object values are assigned.

---

# Parameter Binding Logic

For every object received by the method:

```text
Parameter 1 ← roll

Parameter 2 ← name

Parameter 3 ← cgpa
```

Example for the first object:

```text
Parameter 1 = 1

Parameter 2 = Apple

Parameter 3 = 9.8
```

Example for the second object:

```text
Parameter 1 = 2

Parameter 2 = Banana

Parameter 3 = 9.3
```

---

# SQL Execution Logic

```text
PreparedStatement

↓

Receive Parameters

↓

Compile Query

↓

Send Query to MySQL

↓

Insert Record

↓

Return Success
```

The application uses:

```java
executeUpdate()
```

because the SQL statement modifies database data.

---

# Resource Cleanup Logic

After every successful execution:

```text
Close PreparedStatement

↓

Close Connection

↓

Return Control
```

This prevents unnecessary database resource consumption.

---

# Exception Handling Logic

If any problem occurs during execution:

```text
SQLException Generated

↓

Control Transfers to catch Block

↓

Display

Database Transaction Failure

↓

Print Complete Stack Trace

↓

Terminate Program
```

---

# High-Level Workflow

```text
Java Objects
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
MySQL Database
       │
       ▼
sample Table
       │
       ▼
Record Inserted
```

---

# Decision Flow

```text
Program Starts
       │
       ▼
Create Objects
       │
       ▼
Database Connection Successful?
       │
   Yes │ No
       │
       ▼
Prepare Statement
       │
       ▼
Bind Parameters
       │
       ▼
Execute INSERT
       │
       ▼
Close Resources
       │
       ▼
Process Next Object?
       │
   Yes │ No
       │
       ▼
Display Success Message
       │
       ▼
Program Ends
```

---

# Learning Outcomes

After studying this pseudocode, learners will understand how to:

- Design the logical flow of a JDBC application.
- Create reusable database methods.
- Transfer object data into relational tables.
- Use parameterized SQL statements.
- Perform safe database insertion.
- Handle SQL exceptions.
- Properly release JDBC resources.
- Visualize the execution sequence before writing Java code.

---

# Summary

The pseudocode presented in this document simplifies the internal logic of the Java JDBC Student Application by separating the algorithm from Java syntax. It illustrates how objects are created, passed to a reusable persistence method, connected to the MySQL database, inserted using a `PreparedStatement`, and safely cleaned up after execution. Understanding this logical sequence helps learners build a strong foundation before exploring the actual source code.
