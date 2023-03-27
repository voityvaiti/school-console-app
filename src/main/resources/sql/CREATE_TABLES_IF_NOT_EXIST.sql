CREATE TABLE IF NOT EXISTS groups
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(5)
);

CREATE TABLE IF NOT EXISTS students
(
    id         SERIAL PRIMARY KEY,
    group_id   INT,
    first_name VARCHAR(20),
    last_name  VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS courses
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(40),
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS students_courses
(
    id         SERIAL PRIMARY KEY,
    student_id int,
    course_id  int
);