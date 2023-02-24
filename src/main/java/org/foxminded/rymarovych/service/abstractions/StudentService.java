package org.foxminded.rymarovych.service.abstractions;

public interface StudentService {

    String printStudentsRelatedToCourse(String courseName);

    void studentAddition(int groupId, String firstName, String lastName);

    void removeStudentById(int id);

}
