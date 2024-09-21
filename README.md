# Event-Scheduler-System

The **Event Scheduler System** is a Spring Boot-based application that provides functionality for managing user accounts, calendars, and events. It allows users to register, log in, and manage their calendars. Events can be created, updated, deleted, and organizers can reschedule or update the location of events. The system ensures security through JWT-based authentication and role-based access control.

## Features

- **User Management**: 
  - User registration and login with secure password hashing.
  - JWT-based authentication for secure access.
  - Role-based authorization (e.g., User, Organizer).

- **Calendar Management**: 
  - Create, update, and delete calendars.
  - View calendar details including events.

- **Event Management**: 
  - Schedule, update, delete events.
  - Organizers can reschedule events and update their location.
  - View event details and meeting information.

- **Security**:
  - JWT Token generation and validation for secure API access.
  - Role-based security configuration using Spring Security.
  - CSRF protection disabled for REST API.

## Technologies Used

- **Spring Boot**: Backend framework.
- **Spring Security**: Security for user authentication and authorization.
- **JWT (JSON Web Token)**: For secure user sessions.
- **JPA/Hibernate**: For database interaction.
- **MySQL**: Database used for storing user and event data.

## Prerequisites

- **Java 17** or higher
- **Maven** for dependency management
- **MySQL** for the database

## Installation

1. Clone the repository:

