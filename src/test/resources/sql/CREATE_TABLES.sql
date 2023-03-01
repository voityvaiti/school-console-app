DROP TABLE IF EXISTS students_courses;


DROP TABLE IF EXISTS groups;

CREATE TABLE groups
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(5)
);


DROP TABLE IF EXISTS students;

CREATE TABLE students
(
    id         SERIAL PRIMARY KEY,
    group_id   INT,
    first_name VARCHAR(20),
    last_name  VARCHAR(20)
);


DROP TABLE IF EXISTS courses;

CREATE TABLE courses
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(40),
    description VARCHAR(255)
);

CREATE TABLE students_courses
(
    student_id int,
    course_id  int
);