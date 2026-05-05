# Task Manager 📝

A robust, full-stack Task Management application built with **Spring Boot**, **Thymeleaf**, and **PostgreSQL**. This application provides a comprehensive solution for managing tasks, user authentication, and tracking progress through a dashboard interface.

## 🚀 Features
- **User Authentication & Authorization**: Secure login and registration powered by **Spring Security**.
- **Task Management**: Create, read, update, and delete tasks easily.
- **Dashboard**: Get an overview of your tasks and progress.
- **User Profiles**: Manage users within the application.
- **Server-Side Rendering**: Fast and dynamic web pages served via **Thymeleaf**.
- **Database Driven**: Uses **Spring Data JPA** and **PostgreSQL** for persistent and reliable data storage.

## 🛠️ Technology Stack
- **Java 21**
- **Spring Boot 4**
  - Spring Web MVC
  - Spring Security
  - Spring Data JPA
- **Thymeleaf** (Template Engine)
- **PostgreSQL** (Database)
- **Lombok** (Boilerplate code reduction)
- **Maven** (Build Tool)

## 📁 Project Structure
The source code is organized into modular packages under `src/main/java/com/taskmanager/task_manager`:
- `auth/` - Authentication and registration logic
- `config/` - Application and security configuration classes
- `dashboard/` - Dashboard controllers and services
- `task/` - Task management models, controllers, and services
- `users/` - User models, controllers, and services
- `exception/` - Custom exception handling
- `common/` - Shared utilities and constants

## ⚙️ Prerequisites
Before running the application, ensure you have the following installed:
- **Java 21 JDK** or higher
- **PostgreSQL** (running on port `1972`)
- **Maven**

## 🔧 Database Setup
1. Open PostgreSQL and create a database named `task_manager`:
   ```sql
   CREATE DATABASE task_manager;
   ```
2. The application is configured to connect to `localhost:1972` with the username `postgres` and password `1234`. 
   *Note: If your database uses a different port, username, or password, update the `src/main/resources/application.properties` file accordingly:*
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:1972/task_manager
   spring.datasource.username=postgres
   spring.datasource.password=1234
   ```

## 🏃‍♂️ Getting Started
1. **Clone the repository**:
   ```bash
   git clone https://github.com/puvithk/todo-list-spring.git
   cd todo-list-spring
   ```

2. **Build the project** using Maven wrapper:
   ```bash
   ./mvnw clean install
   ```

3. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```
   *The application will automatically create and update the necessary database tables on startup (configured via `spring.jpa.hibernate.ddl-auto=update`).*

4. **Access the application**:
   Open your browser and navigate to `http://localhost:8080` (or the configured server port).

## 📄 License
This project is licensed under the MIT License.
