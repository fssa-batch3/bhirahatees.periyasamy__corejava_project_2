# sec_a_bhirahatees.periyasamy__corejava_project_2

# Pupdesk


### [Milestone](https://github.com/fssa-batch3/sec_a_bhirahatees.periyasamy__corejava_project_2/milestone/)


## Table of Contents

1. [Introduction](#introduction)
    - Purpose
    - Features
    - User Persona
   - JavaDoc
2. [Prerequisites](#prerequisites)
    - Software Requirements
    - Database Setup
3. [Project Setup](#project-setup)
    - Java Project Creation
    - Library Dependencies
4. [Database](#database)
    - Entity-Relationship Diagram (ERD)
    - Database Tables
5. [Modules](#modules)
    - User Module
    - Ticket Module
    - Comment Module
6. [Validations](#validations)
    - User Validations
    - Ticket Validations
    - Comment Validations
7. [Testing](#testing)
    - Unit Testing
8. [Exception Handling](#exception-handling)
    - Common Error Messages
    - Exception Handling
9. [Resources](#resources)
    - External Libraries
    - References

## Introduction

### Purpose

Develop a Java-based help desk to address and resolve a wide range of issues that Students or employees might encounter.

### Features

- Add, Update, View, Delete,Search and List all Tickets.
- Register, Login, Update, Delete User.
- Adding comment below the Tickets.

### User Persona

- Ticket Raiser
- Issue Solver

### JavaDoc

- [JavaDoc Documentation]([https://fssa-batch3.github.com/fssa-batch3/sec_a_bhirahatees.periyasamy__corejava_project_2/))

## Prerequisites

### Software Requirements

- Java Development Kit (JDK)
- MySQL Database Server
- Integrated Development Environment

### Database Setup

- Table scripts: [Script](pupdesk-sql-queries.sql)

## Project Setup

### Java Project Creation

- Create a new Java project
- Set up a MySQL database

### Library Dependencies

- JDBC,
- MySQL Connector,
- JUnit,

## Database

### Entity-Relationship Diagram (ERD)


## ER Diagram

[![HDDPQgn.md.png](https://iili.io/HDDPQgn.md.png)](https://freeimage.host/i/HDDPQgn)

## Modules

### User Module :

- Add a user:
    - Allows to register new users.
- Update user details:
    - Enables to modify user information such as name, password, etc.
- Remove a user:
    - Allows to remove a user.
- List all users: (Librarian)
    - Show a list of all registered users.

### Ticket Module :

- Raise a Ticket:
    - Allow to raise a new Ticket.
- Close a Ticket: 
    - Allows to Close a Ticket.
- View Ticket details:
    - Display detailed information about a specific Ticket when selected.
- List all Ticket:
    - Show a list of all Tickets Open in the Help Desk.

### Comment Module :
- Create a Comment:
    - Allow users to put a comment under the Ticket.
- Delete a Comment:
    - Allow users to delete the comment
- Ticket Comment history:
    - Gives a history of Ticket comments.

## Validations

### User Validations :

- Name Validation
- Email Id Validation
- Password Validation
- Teamcode Validation

### Ticket Validations :

- validateEmail
- validateTicketId 
- validateSummary
- validatePriority
- validateStatus
- validateCreateDate


## Testing

### Unit Testing

- UserServiceUnitTest
    - Register, Login, Update, Delete and List all Users.
- TicketServiceUnitTest
    - Add, Update, View, Delete and List all Tickets

- UserValidationUnitTest
- BookValidationUnitTest


## Exception Handling

### Custom Exceptions :

- DAO Exception
- Validation Exception
- Service Exception

### Common Error Messages :

- Name cannot be null or empty
- Invalid Password
- Invalid email address
- Invalid phone number
- User already exists
- Ticket not found
- Invalid Ticket id
- Failed to update user information
- Failed to update book information
- Failed to close the Ticket
- Database connection error


## Resources

### External Libraries

- JDBC:
    - Java Database Connectivity library used for database interactions.
- MySQL Connector:
    - JDBC driver for connecting to the MySQL database.
- JUnit:
    - A testing framework for writing and running unit tests in Java

### References

- Java Platform, Standard Edition Documentation: https://docs.oracle.com/javase/8/docs/api/
- MySQL Documentation: https://dev.mysql.com/doc/
- JUnit 5 User Guide: https://junit.org/junit5/docs/current/user-guide/



