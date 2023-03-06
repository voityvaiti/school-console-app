CREATE TABLE groups
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(5)
);

CREATE TABLE students
(
    id         SERIAL PRIMARY KEY,
    group_id   INT,
    first_name VARCHAR(20),
    last_name  VARCHAR(20)
);

CREATE TABLE courses
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(40),
    description VARCHAR(255)
);

CREATE TABLE students_courses
(
    id SERIAL PRIMARY KEY,
    student_id int,
    course_id  int
);