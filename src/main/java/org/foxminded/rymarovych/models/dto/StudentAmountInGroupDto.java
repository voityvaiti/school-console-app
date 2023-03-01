package org.foxminded.rymarovych.models.dto;

import java.util.Objects;

public class StudentAmountInGroupDto {

    private int groupId;
    private int amountOfStudents;

    public StudentAmountInGroupDto(int groupId, Long amountOfStudents) {
        this.groupId = groupId;
        this.amountOfStudents = amountOfStudents.intValue();
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getAmountOfStudents() {
        return amountOfStudents;
    }

    public void setAmountOfStudents(int amountOfStudents) {
        this.amountOfStudents = amountOfStudents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAmountInGroupDto that = (StudentAmountInGroupDto) o;
        return groupId == that.groupId && amountOfStudents == that.amountOfStudents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, amountOfStudents);
    }
}
