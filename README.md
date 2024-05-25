
# Student Management Hibernate

## Description
The Student Management Hibernate project is designed to demonstrate the many-to-many mapping relationship using Spring Boot and Hibernate. This system efficiently manages the relationships between students and courses, ensuring seamless connections and robust data handling. The project focuses on providing a comprehensive understanding of Hibernate's capabilities in managing complex relationships.

## Features
- **Many-to-Many Mapping**: Demonstrates the many-to-many relationship between students and courses.
- **CRUD Operations**: Perform Create, Read, Update, and Delete operations on student and course data.
- **Data Management**: Efficiently manage student and course information, including enrollment details.
- **Exception Handling**: Robust error handling mechanisms to ensure smooth operation and user-friendly error messages.
- **Testing**: Comprehensive testing using JUnit and Mockito to ensure the reliability of the system.

## Technologies Used
- **Java**: Core programming language used to develop the application.
- **Spring Boot**: Framework for building the application.
- **Hibernate**: ORM framework for managing the database relationships.
- **MySQL**: Database for storing student and course information.
- **Maven**: Dependency management and build automation.
- **JUnit & Mockito**: Testing frameworks for unit and integration tests.

## Installation
1. **Clone the repository**:
   ```
   git clone https://github.com/biswas2200/Mapping-Entity-Spring-Boot.git
   ```
2. **Navigate to the project directory**:
   ```
   cd Mapping-Entity-Spring-Boot
   ```
3. **Configure the database**:
   - Update the `application.properties` file with your MySQL database credentials.
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

4. **Build the project using Maven**:
   ```
   mvn clean install
   ```
5. **Run the application**:
   ```
   mvn spring-boot:run
   ```

## Usage
- **API Endpoints**:
  - `GET /students`: Retrieve a list of all students.
  - `GET /students/{id}`: Retrieve details of a specific student by ID.
  - `POST /students`: Create a new student.
  - `PUT /students/{id}`: Update an existing student by ID.
  - `DELETE /students/{id}`: Delete a student by ID.
  - `GET /courses`: Retrieve a list of all courses.
  - `GET /courses/{id}`: Retrieve details of a specific course by ID.
  - `POST /courses`: Create a new course.
  - `PUT /courses/{id}`: Update an existing course by ID.
  - `DELETE /courses/{id}`: Delete a course by ID.

## Contribution
Feel free to fork this repository and contribute by submitting pull requests. Any improvements or suggestions are highly appreciated.
