# school-console-app

Application that works about school such as 
students, courses and groups, and able to store and read it from database.

Console menu provides several functions:

- Find all the groups with less or equal student count
- Find all the students related to the course with the given name
- Add a new student
- Delete student 
- Add a student to the course 
- Remove the student from one of their courses

On startup, application checks if tables are exist is database and 
if tables are not empty. Otherwise, application creates and/or fills 
the tables with sample data

### Technology stack

- Java 19
- Spring Boot
- PostgreSQL
- Spring Data JPA
- JUnit
- Mockito
- Testcontainers
