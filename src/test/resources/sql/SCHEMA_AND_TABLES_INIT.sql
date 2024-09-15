CREATE SCHEMA IF NOT EXISTS school_db;


DROP TABLE IF EXISTS students_courses;

DROP TABLE IF EXISTS groups;

CREATE TABLE school_db.groups
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(5)
);


DROP TABLE IF EXISTS students;

CREATE TABLE school_db.students
(
    id         SERIAL PRIMARY KEY,
    group_id   INT,
    first_name VARCHAR(20),
    last_name  VARCHAR(20)
);


DROP TABLE IF EXISTS courses;

CREATE TABLE school_db.courses
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(40),
    description VARCHAR(255)
);

CREATE TABLE school_db.students_courses
(
    id SERIAL PRIMARY KEY,
    student_id int,
    course_id  int
);