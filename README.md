# iLibreria - Sistema de Gestión de Biblioteca
[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/Sansgar/java-sistema-biblioteca)

iLibreria is a desktop application developed in Java for managing the operations of a public library. It provides a complete and easy-to-use system for handling book loans, returns, and maintaining a detailed inventory of books and registered users. The application features a modern and intuitive graphical user interface built with Java Swing and the FlatLaf theme engine.

## Features

*   **Dashboard**: A central hub that provides quick access to all modules of the system and displays the current date.
*   **User Management**:
    *   Register new library users with details like name, address, and phone number.
    *   View a list of all registered users.
    *   Search for users by name.
    *   Edit existing user information.
    *   Delete users from the system.
*   **Book Management**:
    *   Add new books to the library's inventory with comprehensive details (Title, Author, ISBN, Stock, etc.).
    *   View, search, edit, and delete existing book records.
    *   The system tracks total stock and currently available copies.
*   **Lending Module**:
    *   Process book loans by associating a user ID with a book ID.
    *   The system validates user and book existence.
    *   Automatically decrements the count of available books upon lending.
    *   Prevents lending unavailable books or lending the same book twice to the same user.
*   **Returns Module**:
    *   Process the return of a borrowed book.
    *   Automatically increments the count of available books upon return.
*   **Reports**:
    *   View a comprehensive log of all lending activities, including user ID, book ID, checkout date, and return date.

## Technologies Used

*   **Language**: Java
*   **GUI**: Java Swing
*   **UI Theme**: [FlatLaf](https://www.formdev.com/flatlaf/) Dark Theme
*   **Database**: MySQL
*   **Build & Dependency Management**: Apache Maven
*   **Components**: LGoodDatePicker for an improved date selection UI.

## Getting Started

Follow these instructions to get a local copy of the project up and running for development and testing purposes.

### Prerequisites

*   **Java Development Kit (JDK)**: Version 23 or later.
*   **Apache Maven**: Make sure Maven is installed and configured in your system's PATH.
*   **MySQL Server**: An active instance of MySQL server.

### Database Setup

The application requires a MySQL database to store its data.

1.  Connect to your MySQL server.
2.  Create a new database named `ilib`:
    ```sql
    CREATE DATABASE ilib;
    ```
3.  Use the newly created database:
    ```sql
    USE ilib;
    ```
4.  Run the following SQL scripts to create the necessary tables:

    **`users` table:**
    ```sql
    CREATE TABLE users (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        last_name VARCHAR(255) NOT NULL,
        domicilio VARCHAR(255),
        tel VARCHAR(50) UNIQUE,
        sanctions INT DEFAULT 0,
        sanc_money INT DEFAULT 0
    );
    ```

    **`books` table:**
    ```sql
    CREATE TABLE books (
        id INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        date VARCHAR(50),
        author VARCHAR(255),
        category VARCHAR(100),
        edit VARCHAR(100),
        lang VARCHAR(50),
        pages INT,
        description TEXT,
        stock INT,
        available INT,
        ISBN VARCHAR(100) UNIQUE
    );
    ```

    **`lendings` table:**
    ```sql
    CREATE TABLE lendings (
        id INT AUTO_INCREMENT PRIMARY KEY,
        user_id INT NOT NULL,
        book_id INT NOT NULL,
        date_out VARCHAR(50),
        date_return VARCHAR(50),
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
        FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
    );
    ```
> **Note:** The default database connection is configured for `localhost:3306` with username `root` and an empty password. If your configuration is different, please update the connection details in `src/main/java/com/mycompany/db/Database.java`.

### Installation & Launch

1.  Clone the repository to your local machine:
    ```sh
    git clone https://github.com/Sansgar/java-sistema-biblioteca.git
    ```
2.  Navigate to the project directory:
    ```sh
    cd java-sistema-biblioteca
    ```
3.  Build the project and download dependencies using Maven:
    ```sh
    mvn clean install
    ```
4.  Run the application:
    ```sh
    mvn exec:java -Dexec.mainClass="com.mycompany.ilibreria.Dashboard"
    ```

## Project Structure

The project follows a structured architecture to separate concerns, primarily using the Data Access Object (DAO) pattern.

*   `com.mycompany.ilibreria`: Contains the main application entry point (`Dashboard.java`) and the DAO implementations.
*   `com.mycompany.views`: All Swing `JPanel` classes that represent the different screens of the application (e.g., `Usuarios`, `Libros`, `Prestamos`).
*   `com.mycompany.models`: Plain Old Java Objects (POJOs) (`UsuariosM`, `LibrosM`) that model the database entities.
*   `com.mycompany.interfaces`: Java interfaces for the DAO pattern (`DAOUsuarios`, `DAOLibros`), defining the contract for database operations.
*   `com.mycompany.db`: Contains the `Database.java` class responsible for managing the MySQL connection.
*   `com.mycompany.utils`: Utility classes with helper methods used across the application.
*   `pom.xml`: The Maven Project Object Model file, defining project dependencies and build configurations.
